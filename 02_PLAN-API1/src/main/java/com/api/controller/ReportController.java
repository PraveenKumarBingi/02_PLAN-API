package com.api.controller;

import java.io.IOException;
import java.util.List;

import com.api.model.EligibilityModel;
import com.api.request.SearchRequest;
import com.api.responce.SearchResponse;
import com.api.service.ReportService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    
    private ReportService reportService;
    

    public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}

	@PostMapping("/create")
    public ResponseEntity<EligibilityModel> createUserData(@RequestBody EligibilityModel eligibilityModel) {
        EligibilityModel createdModel = reportService.createUseerData(eligibilityModel);
        return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
    }

    @GetMapping("/unique-plan-names")
    public ResponseEntity<List<String>> getUniquePlanNames() {
        List<String> uniquePlanNames = reportService.findUniquePlanName();
        return new ResponseEntity<>(uniquePlanNames, HttpStatus.OK);
    }

    @GetMapping("/unique-plan-statuses")
    public ResponseEntity<List<String>> getUniquePlanStatuses() {
        List<String> uniquePlanStatuses = reportService.findUniquePlanStatus();
        return new ResponseEntity<>(uniquePlanStatuses, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
        List<SearchResponse> searchResponses = reportService.search(request);
        return new ResponseEntity<>(searchResponses, HttpStatus.OK);
    }

    @GetMapping("/generate-excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=report.xls");
        reportService.generateExcel(response);
    }

    @GetMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        reportService.generatePdf(response);
    }
}
