package com.ione.service;

import com.ione.dto.ConsignmentRequestDTO;
import com.ione.dto.ConsignmentResponseDTO;
import com.ione.entity.Consignment;
import com.ione.entity.Customer;
import com.ione.entity.Driver;
import com.ione.entity.Order;
import com.ione.repository.ConsignmentRepository;
import com.ione.repository.CustomerRepository;
import com.ione.repository.DriverRepository;
import com.ione.repository.OrderRepository;
import com.ione.service.ConsignmentService;
import com.ione.util.PdfGenerator;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsignmentServiceImpl implements ConsignmentService {

    private final ConsignmentRepository consignmentRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public Consignment createConsignment(ConsignmentRequestDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getDeliveryStatus() != com.ione.entity.enums.DeliveryStatus.SUCCEED) {
            throw new IllegalStateException("Consignment can only be created for SUCCEED orders.");
        }

        ConsignmentResponseDTO responseDTO = new ConsignmentResponseDTO();

        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Driver driver = driverRepository.findById(order.getVehicle().getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        @NotNull
        @DecimalMin("0.0")
        BigDecimal totalCost = dto.getTotalCost();
        BigDecimal platformFee = totalCost.multiply(BigDecimal.valueOf(0.1));
        BigDecimal driverPayment = totalCost.subtract(platformFee);

        customer.setBalance(customer.getBalance().subtract(totalCost));
        driver.setBalance(driver.getBalance().add(driverPayment));

        customerRepository.save(customer);
        driverRepository.save(driver);

        Consignment consignment = new Consignment();
        consignment.setOrder(order);
        consignment.setTotalCost(totalCost);
        consignment.setPlatformFee(platformFee);
        consignment.setDriverPayment(driverPayment);
        consignment.setIssuedAt(LocalDateTime.now());

        try {
            String fileUrl = PdfGenerator.generateConsignmentPdf(consignment, order);
            consignment.setFileUrl(fileUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate PDF for consignment", e);
        }

        return consignmentRepository.save(consignment);
    }


    @Override
    public Consignment getById(Long id) {
        return consignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consignment not found"));
    }

    @Override
    public List<Consignment> getAll() {
        return consignmentRepository.findAll();
    }
    public boolean isOwner(String email, Integer id) {
        return customerRepository.findById(id)
                .map(customer -> customer.getEmail().equalsIgnoreCase(email))
                .orElse(false);
    }

}
