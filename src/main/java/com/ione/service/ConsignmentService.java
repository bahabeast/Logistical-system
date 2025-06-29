package com.ione.service;

import com.ione.dto.ConsignmentRequestDTO;
import com.ione.entity.Consignment;

import java.util.List;

public interface ConsignmentService {
    Consignment createConsignment(ConsignmentRequestDTO dto);
    Consignment getById(Long id);
    List<Consignment> getAll();
}
