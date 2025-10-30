package com.moselix.portal.service;


import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Placement;
import com.moselix.portal.modal.Student;
import com.moselix.portal.repository.PlacementRepository;
import com.moselix.portal.repository.StudentRepository;
import com.moselix.portal.request.PlacementRequestDTO;
import com.moselix.portal.response.PlacementResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlacementService {

    private final PlacementRepository placementRepository;
    private final StudentRepository studentRepository;

    // ✅ Add new placement record
    public PlacementResponseDTO addPlacement(PlacementRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

        Placement placement = Placement.builder()
                .student(student)
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .packageOffered(dto.getPackageOffered() != null ? dto.getPackageOffered() : BigDecimal.ZERO)
                .placementDate(dto.getPlacementDate())
                .status(Placement.Status.valueOf(dto.getStatus() != null ? dto.getStatus() : "Pending"))
                .build();

        Placement saved = placementRepository.save(placement);

        return PlacementResponseDTO.builder()
                .placementId(saved.getPlacementId())
                .studentName(saved.getStudent().getFirstName() + " " + saved.getStudent().getLastName())
                .companyName(saved.getCompanyName())
                .position(saved.getPosition())
                .packageOffered(saved.getPackageOffered())
                .placementDate(saved.getPlacementDate())
                .status(saved.getStatus().name())
                .build();
    }
}
