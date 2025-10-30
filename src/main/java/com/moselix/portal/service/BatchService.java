package com.moselix.portal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moselix.portal.modal.Batch;
import com.moselix.portal.modal.Course;
import com.moselix.portal.modal.Trainer;
import com.moselix.portal.repository.BatchRepository;
import com.moselix.portal.repository.CourseRepository;
import com.moselix.portal.repository.TrainerRepository;
import com.moselix.portal.request.BatchRequestDTO;
import com.moselix.portal.response.BatchResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    // 1️⃣ Create a new batch
    public Batch createBatch(String batchName, Long courseId, Long trainerId, LocalDate startDate, LocalDate endDate) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        Batch batch = Batch.builder()
                .batchName(batchName)
                .course(course)
                .trainer(trainer)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return batchRepository.save(batch);
    }

    // 2️⃣ Get batch by ID
    public Batch getBatchById(Long batchId) {
        return batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));
    }

    // 3️⃣ Get all batches
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    // 4️⃣ Get batches by course
    public List<Batch> getBatchesByCourse(Long courseId) {
        return batchRepository.findByCourse_CourseId(courseId);
    }

    // 5️⃣ Update batch details
    public Batch updateBatch(Long batchId, String batchName, LocalDate startDate, LocalDate endDate) {
        Batch batch = getBatchById(batchId);

        if (batchName != null) batch.setBatchName(batchName);
        if (startDate != null) batch.setStartDate(startDate);
        if (endDate != null) batch.setEndDate(endDate);

        return batchRepository.save(batch);
    }

    // 6️⃣ Delete batch
    public void deleteBatch(Long batchId) {
        batchRepository.deleteById(batchId);
    }

    // 7️⃣ Check if batch is currently active
    public boolean isBatchActive(Batch batch) {
        LocalDate today = LocalDate.now();
        return (batch.getStartDate() != null && batch.getEndDate() != null) &&
               (today.isEqual(batch.getStartDate()) || today.isAfter(batch.getStartDate())) &&
               (today.isEqual(batch.getEndDate()) || today.isBefore(batch.getEndDate()));
    }
    
    
    
    
    
    
    
    
    
    
    // Add a new batch
    public BatchResponseDTO addBatch(BatchRequestDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + dto.getCourseId()));

        Trainer trainer = trainerRepository.findById(dto.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + dto.getTrainerId()));

        Batch batch = Batch.builder()
                .batchName(dto.getBatchName())
                .course(course)
                .trainer(trainer)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        Batch savedBatch = batchRepository.save(batch);

        return BatchResponseDTO.builder()
                .batchId(savedBatch.getBatchId())
                .batchName(savedBatch.getBatchName())
                .courseName(savedBatch.getCourse().getCourseName())
                .trainerName(savedBatch.getTrainer().getName())
                .startDate(savedBatch.getStartDate())
                .endDate(savedBatch.getEndDate())
                .build();
    }

}

