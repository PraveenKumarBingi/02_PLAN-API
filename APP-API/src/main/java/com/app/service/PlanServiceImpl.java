package com.app.service;

import com.app.entity.PlanCategory;
import com.app.entity.PlanEntity;
import com.app.repository.PlanCategoryRepository;
import com.app.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public  class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanCategoryRepository planCategoryRepository;

    @Override
    public PlanCategory savePlanCategory(PlanCategory planCategory) {
        return planCategoryRepository.save(planCategory);
    }

    @Override
    public List<PlanCategory> getAllPlanCategories() {
        return planCategoryRepository.findAll();
    }

    @Override
    public boolean savePlan(PlanEntity planEntity) {
        PlanEntity savedPlan = planRepository.save(planEntity);
        return savedPlan != null;
    }

    @Override
    public List<PlanEntity> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public boolean updatePlan(PlanEntity planEntity) {
        if (planRepository.existsById(planEntity.getPlanId())) {
            planRepository.save(planEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePlanById(Integer planId) {
        if (planRepository.existsById(planId)) {
            planRepository.deleteById(planId);
            return true;
        }
        return false;
    }

    @Override
    public boolean planStatusChange(Integer planId, String activeSw) {
        Optional<PlanEntity> optionalPlan = planRepository.findById(planId);
        if (optionalPlan.isPresent()) {
            PlanEntity planEntity = optionalPlan.get();
            planEntity.setActiveSw(activeSw);
            planRepository.save(planEntity);
            return true;
        }
        return false;
    }

    @Override
    public PlanEntity getPlanById(Integer planId) {
        return planRepository.findById(planId).orElse(null);
    }

    @Override
    public PlanCategory getPlanCategoryById(Integer categoryId) {
        // Retrieve PlanCategory from the repository
        return planCategoryRepository.findById(categoryId).orElse(null);
    }
}
