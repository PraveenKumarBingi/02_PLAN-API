package com.api.service;

import java.io.IOException;
import java.util.List;

import com.api.model.EligibilityModel;
import com.api.request.SearchRequest;
import com.api.responce.SearchResponse;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
    
    public EligibilityModel createUseerData(EligibilityModel eligibilityModel);
    
    public List<String> findUniquePlanName();
    
    public List<String> findUniquePlanStatus();
    
    public List<SearchResponse> search(SearchRequest request);
    
    public void generateExcel(HttpServletResponse response) throws IOException;
    
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException;
}
