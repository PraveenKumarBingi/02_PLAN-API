package com.api.entity;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EligibilityDetails {
	
	@Id
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
	
	private LocalDate createDate;
	private LocalDate updateDate;
	
	private String createdBy;	
	private String updatedBy;
}
