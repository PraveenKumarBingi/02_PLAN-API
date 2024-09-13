package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.binding.PlanBinding;
import com.app.constants.AppConstants;
import com.app.entity.PlanCategory;
import com.app.entity.PlanEntity;
import com.app.props.AppProperties;
import com.app.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private AppProperties appProperties;  // Inject AppProperties

    // Create a new Plan Category
    @PostMapping("/SaveCategory")
    public ResponseEntity<PlanCategory> createPlanCategory(@RequestBody PlanCategory planCategory) {
    	PlanCategory savePlanCategory = planService.savePlanCategory(planCategory);
    	return new ResponseEntity<>(savePlanCategory, HttpStatus.CREATED);
    }

    // Retrieve all Plan Categories
    @GetMapping("/GetAllCategories")
    public ResponseEntity<List<PlanCategory>> getAllPlanCategories() {
        List<PlanCategory> planCategories = planService.getAllPlanCategories();
        return ResponseEntity.ok(planCategories);
    }

    // Retrieve Plan Category by ID
    @GetMapping("/GetCategory/{id}")
    public ResponseEntity<PlanCategory> getPlanCategoryById(@PathVariable("id") Integer categoryId) {
        PlanCategory category = planService.getPlanCategoryById(categoryId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Save a new plan
    @PostMapping("/SavePlans")
    public ResponseEntity<String> savePlan(@RequestBody PlanBinding planBinding) {
        // Create and populate PlanEntity from PlanBinding
        PlanEntity planEntity = new PlanEntity();
        planEntity.setPlanId(planBinding.getPlanId());
        planEntity.setPlanName(planBinding.getPlanName());
        planEntity.setPlanStartDate(planBinding.getPlanStartDate());
        planEntity.setPlanEndDate(planBinding.getPlanEndDate());
        planEntity.setActiveSw(planBinding.getActiveSw());
        planEntity.setPlanCategoryId(planBinding.getPlanCategoryId());
        planEntity.setCreatedBy(planBinding.getCreatedBy());
        planEntity.setUpdatedBy(planBinding.getUpdatedBy());
        planEntity.setCreateDate(planBinding.getCreateDate()); 
        planEntity.setUpdateDate(planBinding.getUpdateDate());

        // Save the plan using the service
        boolean result = planService.savePlan(planEntity);

        // Determine the appropriate message based on the result
        String msg;
        if (result) {
            msg = appProperties.getMessages().get(AppConstants.PLAN_SAVE_SUCC);
            return ResponseEntity.ok(msg);
        } else {
            msg = appProperties.getMessages().get(AppConstants.PLAN_SAVE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }


    // Get all plans
    @GetMapping("/GetAllPlans")
    public ResponseEntity<List<PlanBinding>> getAllPlans() {
        List<PlanEntity> plans = planService.getAllPlans();
        List<PlanBinding> planBindings = new ArrayList<>();

        for (PlanEntity plan : plans) {
            PlanBinding binding = new PlanBinding();
            binding.setPlanId(plan.getPlanId());
            binding.setPlanName(plan.getPlanName());
            binding.setPlanStartDate(plan.getPlanStartDate());
            binding.setPlanEndDate(plan.getPlanEndDate());
            binding.setActiveSw(plan.getActiveSw());
            binding.setCreatedBy(plan.getCreatedBy());
            binding.setUpdatedBy(plan.getUpdatedBy());
            binding.setCreateDate(plan.getCreateDate());
            binding.setUpdateDate(plan.getUpdateDate());
            planBindings.add(binding);
        }

        return ResponseEntity.ok(planBindings);
    }

    // Update a plan
    @PutMapping("/UpdatePlanCategory")
    public ResponseEntity<String> updatePlan(@RequestBody PlanBinding planBinding) {
        // Create and populate PlanEntity from PlanBinding
        PlanEntity planEntity = new PlanEntity();
        planEntity.setPlanId(planBinding.getPlanId());
        planEntity.setPlanName(planBinding.getPlanName());
        planEntity.setPlanStartDate(planBinding.getPlanStartDate());
        planEntity.setPlanEndDate(planBinding.getPlanEndDate());
        planEntity.setActiveSw(planBinding.getActiveSw());
        planEntity.setPlanCategoryId(planBinding.getPlanCategoryId());
        planEntity.setCreatedBy(planBinding.getCreatedBy());
        planEntity.setUpdatedBy(planBinding.getUpdatedBy());
        planEntity.setCreateDate(planBinding.getCreateDate());
        planEntity.setUpdateDate(planBinding.getUpdateDate());

        // Update the plan using the service
        boolean isUpdated = planService.updatePlan(planEntity);

        // Determine the appropriate message based on the result
        String msg;
        if (isUpdated) {
            msg = appProperties.getMessages().get(AppConstants.PLAN_SAVE_SUCC);
            return ResponseEntity.ok(msg);
        } else {
            msg = appProperties.getMessages().get(AppConstants.PLAN_UPDATE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    // Delete a plan by ID
    @DeleteMapping("/DeletePlan/{planId}")
    public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {
        // Attempt to delete the plan by ID
        boolean result = planService.deletePlanById(planId);

        // Determine the appropriate message based on the result
        String msg;
        if (result) {
            msg = appProperties.getMessages().get(AppConstants.PLAN_DELETE_SUCC);
        } else {
            msg = appProperties.getMessages().get(AppConstants.PLAN_DELETE_FAIL);
        }

        // Return response with appropriate status and message
        return ResponseEntity.ok(msg);
    }

    // Change the status of a plan
    @GetMapping("/ChangeStatus/{planId}")
    public ResponseEntity<String> changePlanStatus(@PathVariable Integer planId, @RequestParam String activeSw) {
        // Change the plan status using the service
        boolean isStatusChanged = planService.planStatusChange(planId, activeSw);

        // Determine the appropriate message based on the result
        String msg;
        if (isStatusChanged) {
            msg = appProperties.getMessages().get(AppConstants.PLAN_STATUS_CHANGE);
            return ResponseEntity.ok(msg);
        } else {
            msg = appProperties.getMessages().get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }
}
