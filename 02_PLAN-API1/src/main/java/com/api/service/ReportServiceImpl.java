package com.api.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.api.entity.EligibilityDetails;
import com.api.model.EligibilityModel;
import com.api.repository.ElibilityRepo;
import com.api.request.SearchRequest;
import com.api.responce.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

    private final ElibilityRepo eligibilityRepo;

    public ReportServiceImpl(ElibilityRepo eligibilityRepo) {
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityModel createUseerData(EligibilityModel eligibilityModel) {
        EligibilityDetails EB = new EligibilityDetails();
        BeanUtils.copyProperties(eligibilityModel, EB);

        EligibilityDetails save = eligibilityRepo.save(EB);

        EligibilityModel EM = new EligibilityModel();
        BeanUtils.copyProperties(save, EM);

        return EM;
    }

    @Override
    public List<String> findUniquePlanName() {
        return eligibilityRepo.findUniquePlanName();
    }

    @Override
    public List<String> findUniquePlanStatus() {
        return eligibilityRepo.findUniquePlanStatus();
    }

    @Override
    public List<SearchResponse> search(SearchRequest request) {
        List<SearchResponse> response = new ArrayList<>();

        EligibilityDetails queryBuilder = new EligibilityDetails();

        String planName = request.getPlanName();
        if (planName != null && !planName.isEmpty()) {
            queryBuilder.setPlanName(planName);
        }

        String planStatus = request.getPlanStatus();
        if (planStatus != null && !planStatus.isEmpty()) {
            queryBuilder.setPlanStatus(planStatus);
        }

        LocalDate planStartDate = request.getPlanStartDate();
        if (planStartDate != null) {
            queryBuilder.setPlanStartDate(planStartDate);
        }

        LocalDate planEndDate = request.getPlanEndDate();
        if (planEndDate != null) {
            queryBuilder.setPlanEndDate(planEndDate);
        }

        Example<EligibilityDetails> example = Example.of(queryBuilder);

        List<EligibilityDetails> entities = eligibilityRepo.findAll(example);
        for (EligibilityDetails entity : entities) {
            SearchResponse sr = new SearchResponse();
            BeanUtils.copyProperties(entity, sr);
            response.add(sr);
        }
        return response;
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<EligibilityDetails> entities = eligibilityRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Gender");
        headerRow.createCell(3).setCellValue("MobileNo");
        headerRow.createCell(4).setCellValue("SSN");

        int i = 1;
        for (EligibilityDetails entity : entities) {
            HSSFRow dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(entity.getName());
            dataRow.createCell(1).setCellValue(entity.getEmail());
            dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
            dataRow.createCell(3).setCellValue(entity.getMobile());
            dataRow.createCell(4).setCellValue(entity.getSsn());
            i++;
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

  
    @Override
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        List<EligibilityDetails> entities = eligibilityRepo.findAll();
        
        // Create a new Document instance with A4 page size
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        // Open the document for writing
        document.open();
        
        // Create a font for the title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(18);
        titleFont.setColor(Color.BLUE);
        
        // Add a title to the document
        Paragraph title = new Paragraph("SEARCH REPORT", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        
        // Create a table with 5 columns
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);
        
        // Create a cell style for the header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        
        // Create font for the header cells
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA);
        headerFont.setColor(Color.WHITE);
        
        // Add table headers
        cell.setPhrase(new Phrase("Name", headerFont));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Email", headerFont));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Mobile No", headerFont));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Gender", headerFont));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("SSN", headerFont));
        table.addCell(cell);
        
        // Add data rows
        for (EligibilityDetails entity : entities) {
            table.addCell(entity.getName());
            table.addCell(entity.getEmail());
            table.addCell(String.valueOf(entity.getMobile()));
            table.addCell(String.valueOf(entity.getGender()));
            table.addCell(String.valueOf(entity.getSsn()));
        }
        
        // Add the table to the document
        document.add(table);
        
        // Close the document
        document.close();
    }


   
    
}
