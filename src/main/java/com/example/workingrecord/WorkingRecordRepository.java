package com.example.workingrecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingRecordRepository extends JpaRepository<WorkingRecord, Long> {
    
    // Spring Data JPA automatically writes the SQL query for this method based on its name!
    List<WorkingRecord> findByMonthIgnoreCaseAndUsername(String month, String username);
}