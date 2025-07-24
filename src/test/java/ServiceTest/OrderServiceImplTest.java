package ServiceTest;

import com.ione.entity.*;
import com.ione.entity.enums.DeliveryStatus;
import com.ione.entity.enums.VehicleType;
import com.ione.repository.CustomerRepository;
import com.ione.repository.OrderRepository;
import com.ione.repository.VehicleRepository;
import com.ione.security.AuthUtil;
import com.ione.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock private OrderRepository orderRepository;
    @Mock private CustomerRepository customerRepository;
    @Mock private VehicleRepository vehicleRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    void updateDeliveryStatus_shouldUpdateForOwnerCustomer_withValidTransition() {
        Customer customer = new Customer();
        customer.setEmail("customer@example.com");

        Order order = buildOrder();
        order.setCustomer(customer);
        order.setDeliveryStatus(DeliveryStatus.PENDING);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        try (MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(AuthUtil::getCurrentUsername).thenReturn("customer@example.com");
            mocked.when(AuthUtil::getCurrentUserRole).thenReturn("CUSTOMER");

            Order updated = orderServiceImpl.updateDeliveryStatus(1L, DeliveryStatus.GOING_TO_LOAD);
            assertNotNull(updated);
            assertEquals(DeliveryStatus.GOING_TO_LOAD, updated.getDeliveryStatus());
        }
    }

    @Test
    void updateDeliveryStatus_shouldUpdateForOwnerDriver_withValidTransition() {
        Driver driver = new Driver();
        driver.setEmail("driver@example.com");

        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(driver);

        Order order = buildOrder();
        order.setVehicle(vehicle);
        order.setDeliveryStatus(DeliveryStatus.IN_TRANSIT);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        try (MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(AuthUtil::getCurrentUsername).thenReturn("driver@example.com");
            mocked.when(AuthUtil::getCurrentUserRole).thenReturn("DRIVER");

            Order updated = orderServiceImpl.updateDeliveryStatus(1L, DeliveryStatus.DELIVERED);
            assertNotNull(updated);
            assertEquals(DeliveryStatus.DELIVERED, updated.getDeliveryStatus());
        }
    }

    @Test
    void updateDeliveryStatus_shouldThrowAccessDenied_forWrongCustomer() {
        Customer customer = new Customer();
        customer.setEmail("real@example.com");

        Order order = buildOrder();
        order.setCustomer(customer);
        order.setDeliveryStatus(DeliveryStatus.PENDING);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        try (MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(AuthUtil::getCurrentUsername).thenReturn("fake@example.com");
            mocked.when(AuthUtil::getCurrentUserRole).thenReturn("CUSTOMER");

            assertThrows(AccessDeniedException.class, () ->
                    orderServiceImpl.updateDeliveryStatus(1L, DeliveryStatus.GOING_TO_LOAD));
        }
    }

    @Test
    void updateDeliveryStatus_shouldThrowAccessDenied_forWrongDriver() {
        Driver driver = new Driver();
        driver.setEmail("owner@example.com");

        Vehicle vehicle = new Vehicle();
        vehicle.setOwner(driver);

        Order order = buildOrder();
        order.setVehicle(vehicle);
        order.setDeliveryStatus(DeliveryStatus.IN_TRANSIT);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        try (MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(AuthUtil::getCurrentUsername).thenReturn("intruder@example.com");
            mocked.when(AuthUtil::getCurrentUserRole).thenReturn("DRIVER");

            assertThrows(AccessDeniedException.class, () ->
                    orderServiceImpl.updateDeliveryStatus(1L, DeliveryStatus.DELIVERED));
        }
    }

    // ✅ Helper to construct a valid order
    private Order buildOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCargoDescription("Electronics");
        order.setWeightTons(5.5f);
        order.setVolumeCubicMeters(12.3f);
        order.setPickupLocation("Warehouse A");
        order.setDeliveryLocation("Shop B");
        order.setRecipientPhone("87001112233");
        order.setVehicleType(VehicleType.AWNING);
        order.setCreatedAt(LocalDateTime.now());
        order.setDeliveryStatus(DeliveryStatus.PENDING); // default status
        return order;
    }
}
