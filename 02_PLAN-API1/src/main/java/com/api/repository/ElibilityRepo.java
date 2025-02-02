package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.EligibilityDetails;

@Repository
public interface ElibilityRepo extends JpaRepository<EligibilityDetails, Integer> {
    
    @Query("SELECT DISTINCT e.planName FROM EligibilityDetails e")
    List<String> findUniquePlanName();

    @Query("SELECT DISTINCT e.planStatus FROM EligibilityDetails e")
    List<String> findUniquePlanStatus();
}
