package com.app.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.entity.EligibilityDetails;
import com.app.repository.EligibilityDetailsRepo;
import com.app.request.SearchRequest;
import com.app.responce.SearchResponce;
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

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EligibilityDetailsRepo eligiblitityRepo;

    @Override
	public List<String> getUniquePlanNames() {
		return eligiblitityRepo.findPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return eligiblitityRepo.findPlanStatuses();
	}


    @Override
    public List<SearchResponce> search(SearchRequest request) {
        List<SearchResponce> response = new ArrayList<>();
        
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
        List<EligibilityDetails> entities = eligiblitityRepo.findAll(example);
        
        for (EligibilityDetails entity : entities) {
            SearchResponce sr = new SearchResponce();
            BeanUtils.copyProperties(entity, sr);
            response.add(sr);
        }
        return response;
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<EligibilityDetails> entities = eligiblitityRepo.findAll();
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Eligibility Report");
        HSSFRow headerRow = sheet.createRow(0);
        
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Gender");
        headerRow.createCell(3).setCellValue("Mobile No");
        headerRow.createCell(4).setCellValue("SSN");
        
        int rowIndex = 1;
        for (EligibilityDetails entity : entities) {
            HSSFRow dataRow = sheet.createRow(rowIndex++);
            dataRow.createCell(0).setCellValue(entity.getName());
            dataRow.createCell(1).setCellValue(entity.getEmail());
            dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
            dataRow.createCell(3).setCellValue(entity.getMobile());
            dataRow.createCell(4).setCellValue(entity.getSsn());
        }
        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        List<EligibilityDetails> entities = eligiblitityRepo.findAll();
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        
        document.open();
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        
        Paragraph paragraph = new Paragraph("SEARCH REPORT", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Mobile No", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);
        
        for (EligibilityDetails entity : entities) {
            table.addCell(entity.getName());
            table.addCell(entity.getEmail());
            table.addCell(String.valueOf(entity.getMobile()));
            table.addCell(String.valueOf(entity.getGender()));
            table.addCell(String.valueOf(entity.getSsn()));
        }
        
        document.add(table);
        document.close();
    }


}
