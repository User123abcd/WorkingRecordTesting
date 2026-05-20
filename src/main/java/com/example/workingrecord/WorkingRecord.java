package com.example.workingrecord;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "working_record") // Explicitly name the table
public class WorkingRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "record_date") // 'date' is a reserved SQL keyword, so we rename the column
    private String date;
    
    private String category;
    
    @Column(name = "am_time")
    private String am;
    
    @Column(name = "pm_time")
    private String pm;
    
    private Double p100;
    private Double p150;
    private Double p200;
    
    @Column(name = "record_month") // 'month' can sometimes conflict depending on the database
    private String month;
    
    private String username;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAm() { return am; }
    public void setAm(String am) { this.am = am; }

    public String getPm() { return pm; }
    public void setPm(String pm) { this.pm = pm; }

    public Double getP100() { return p100; }
    public void setP100(Double p100) { this.p100 = p100; }

    public Double getP150() { return p150; }
    public void setP150(Double p150) { this.p150 = p150; }

    public Double getP200() { return p200; }
    public void setP200(Double p200) { this.p200 = p200; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}