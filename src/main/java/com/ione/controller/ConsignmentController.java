package com.ione.controller;

import com.ione.dto.ConsignmentRequestDTO;
import com.ione.dto.ConsignmentResponseDTO;
import com.ione.entity.Consignment;
import com.ione.mapper.ConsignmentMapper;
import com.ione.service.ConsignmentService;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/consignments")
@RequiredArgsConstructor
public class ConsignmentController {

    private final ConsignmentService consignmentService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<ConsignmentResponseDTO> create(@RequestBody @Valid ConsignmentRequestDTO dto) {
        Consignment consignment = consignmentService.createConsignment(dto);
        ConsignmentResponseDTO responseDTO = ConsignmentMapper.toDTO(consignment);
        return ResponseEntity.ok(responseDTO);
    }
    @PreAuthorize("hasRole('DRIVER','CUSTOMER)")
    @GetMapping("/{id}")
    public ResponseEntity<Consignment> get(@PathVariable Long id) {
        return ResponseEntity.ok(consignmentService.getById(id));
    }
    @PreAuthorize("hasRole('DRIVER','CUSTOMER)")
    @GetMapping
    public ResponseEntity<List<Consignment>> getAll() {
        return ResponseEntity.ok(consignmentService.getAll());
    }
    @PreAuthorize("hasRole('DRIVER','CUSTOMER)")
    @GetMapping("/pdf/{id}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long id) throws IOException {
        Consignment consignment = consignmentService.getById(id);
        Path path = Paths.get(consignment.getFileUrl());
        UrlResource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"")
                .body((Resource) resource);
    }

}
