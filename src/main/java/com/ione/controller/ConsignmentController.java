package com.ione.controller;

import com.ione.entity.Consignment;
import com.ione.service.ConsignmentService;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<Consignment> create(@Valid @RequestParam Long orderId) {
        return ResponseEntity.ok(consignmentService.createConsignment(orderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consignment> get(@PathVariable Long id) {
        return ResponseEntity.ok(consignmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Consignment>> getAll() {
        return ResponseEntity.ok(consignmentService.getAll());
    }
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
