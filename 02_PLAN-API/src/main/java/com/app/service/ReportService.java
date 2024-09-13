package com.app.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.request.SearchRequest;
import com.app.responce.SearchResponce;
import com.lowagie.text.DocumentException;

public interface ReportService {
	

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	public List<SearchResponce> search(SearchRequest request);
	
	public void generateExcel(HttpServletResponse response) throws IOException;
	
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException;
}
