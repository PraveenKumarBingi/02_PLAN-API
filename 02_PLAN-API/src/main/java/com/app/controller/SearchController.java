package com.app.controller;

import com.app.request.SearchRequest;
import com.app.responce.SearchResponce;
import com.app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ReportService reportService;

    // API to search based on criteria
    @PostMapping("/search")
    public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
        try {
            List<SearchResponse> results = reportService.search(request);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // API to get unique plan names
    @GetMapping("/planNames")
    public ResponseEntity<List<String>> getUniquePlanNames() {
        try {
            List<String> planNames = reportService.getUniquePlanNames();
            return new ResponseEntity<>(planNames, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // API to get unique plan statuses
    @GetMapping("/planStatuses")
    public ResponseEntity<List<String>> getUniquePlanStatuses() {
        try {
            List<String> planStatuses = reportService.getUniquePlanStatuses();
            return new ResponseEntity<>(planStatuses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
