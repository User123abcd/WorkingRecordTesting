package com.example.workingrecord;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class WorkingRecordController {

    private final WorkingRecordRepository repository;

    public WorkingRecordController(WorkingRecordRepository repository) {
        this.repository = repository;
    }

    // Save a new record
    @PostMapping
    public ResponseEntity<WorkingRecord> saveRecord(@RequestBody WorkingRecord record) {
        WorkingRecord savedRecord = repository.save(record);
        return ResponseEntity.ok(savedRecord);
    }

    // Get records by month and username
    @GetMapping
    public ResponseEntity<List<WorkingRecord>> getRecords(
            @RequestParam String month,
            @RequestParam String username) {
        
        List<WorkingRecord> records = repository.findByMonthIgnoreCaseAndUsername(month, username);
        return ResponseEntity.ok(records);
    }
    
    // Get a specific record by ID (needed for the update form)
    @GetMapping("/{id}")
    public ResponseEntity<WorkingRecord> getRecordById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing record
    @PutMapping("/{id}")
    public ResponseEntity<WorkingRecord> updateRecord(@PathVariable Long id, @RequestBody WorkingRecord updatedRecord) {
        return repository.findById(id)
                .map(existingRecord -> {
                    // Update the fields
                    existingRecord.setDate(updatedRecord.getDate());
                    existingRecord.setCategory(updatedRecord.getCategory());
                    existingRecord.setAm(updatedRecord.getAm());
                    existingRecord.setPm(updatedRecord.getPm());
                    existingRecord.setP100(updatedRecord.getP100());
                    existingRecord.setP150(updatedRecord.getP150());
                    existingRecord.setP200(updatedRecord.getP200());
                    // Keep the original month and username
                    existingRecord.setMonth(updatedRecord.getMonth());
                    existingRecord.setUsername(updatedRecord.getUsername());
                    
                    WorkingRecord savedRecord = repository.save(existingRecord);
                    return ResponseEntity.ok(savedRecord);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}