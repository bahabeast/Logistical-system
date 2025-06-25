package com.ione.service;

import com.ione.entity.Consignment;

import java.util.List;

public interface ConsignmentService {
    Consignment createConsignment(Long orderId);
    Consignment getById(Long id);
    List<Consignment> getAll();
}
