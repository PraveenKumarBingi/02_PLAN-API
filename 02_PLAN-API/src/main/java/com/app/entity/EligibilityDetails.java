package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "ELIGIBILITY_DETAILS")
@EntityListeners(AuditingEntityListener.class)  // Add this to support auditing
public class EligibilityDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer eligId;
    private String name;
    private Long mobile;
    private String email;
    private Character gender;
    private Long ssn;
    private String planName;
    private String planStatus;
    
    @CreationTimestamp
    private LocalDate planStartDate;
    
    @CreationTimestamp
    private LocalDate planEndDate;
    
    @CreationTimestamp
    private LocalDate createDate;
    
    @CreationTimestamp
    private LocalDate updateDate;
    
    private String createdBy;  // Updated to be manually set
    private String updatedBy;  // Updated to be manually set
}
