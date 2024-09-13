package com.app.service;

import java.util.List;

import com.app.entity.PlanCategory;
import com.app.entity.PlanEntity;

public interface PlanService {
	
	
	PlanCategory savePlanCategory(PlanCategory planCategory);

	public  List<PlanCategory> getAllPlanCategories();

	PlanCategory getPlanCategoryById(Integer categoryId);
	
    public boolean savePlan(PlanEntity planEntity);

    public List<PlanEntity> getAllPlans();

    public PlanEntity getPlanById(Integer planId);

    public boolean updatePlan(PlanEntity planEntity);

    public boolean deletePlanById(Integer planId);

    public boolean planStatusChange(Integer planId, String activeSw);


	
}
