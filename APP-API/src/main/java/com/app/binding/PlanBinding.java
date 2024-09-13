package com.app.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanBinding {
	

	private Integer planId;	
	
	private String planName;
	
	private LocalDate planStartDate; 
	
	private LocalDate planEndDate; 
	
	private String activeSw;
	
	private Integer planCategoryId;
	
	private String createdBy;
	
	private String updatedBy;

	private LocalDate createDate;

	private LocalDate updateDate;
	
	

}
