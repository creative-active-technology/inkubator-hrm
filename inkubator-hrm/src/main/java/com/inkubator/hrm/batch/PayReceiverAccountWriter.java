package com.inkubator.hrm.batch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import com.inkubator.hrm.web.model.PayReceiverAccountModel;

/**
 *
 * @author rizkykojek
 */
public class PayReceiverAccountWriter implements ItemWriter<PayReceiverAccountModel> {
	
	private static final String[] HEADERS = { "No", "Nik", "Nama Karyawan", "Nama Akun", "Nomor Rekening", "Bank", "Jumlah(%)", "Total(Rp)" };

	private String fileName;
	private Workbook workbook;
	private CellStyle dataCellStyle;
	private int currRow = 0;	
	private int currNo = 0;
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet("Daftar Rekening Penerima");
        sheet.setDefaultColumnWidth(20); 
        
        addHeaders(sheet);
        initDataStyle();
 
    }
	
	@AfterStep
    public void afterStep(StepExecution stepExecution) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
    }
	
	@Override
	public void write(List<? extends PayReceiverAccountModel> items) throws Exception {
		Sheet sheet = workbook.getSheetAt(0);
		 
        for (PayReceiverAccountModel data : items) {
        	currRow++;
        	currNo++;
            Row row = sheet.createRow(currRow);
            createStringCell(row, String.valueOf(currNo), 0);
            createStringCell(row, data.getNik(), 1);
            createStringCell(row, data.getName(), 2);
            createStringCell(row, data.getAccountName(), 3);
            createStringCell(row, data.getAccountNumber(), 4);
            createStringCell(row, data.getBankName(), 5);
            createNumericCell(row, data.getPercent(), 6);
            createNumericCell(row, data.getNominal(), 7);
        }
	}
	
	private void addHeaders(Sheet sheet) {
		 
        Workbook wb = sheet.getWorkbook();
 
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
 
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);
 
        Row row = sheet.createRow(0);
        int col = 0;
 
        for (String header : HEADERS) {
            Cell cell = row.createCell(col);
            cell.setCellValue(header);
            cell.setCellStyle(style);
            col++;
        }
        currRow++;
    }
	
	private void initDataStyle() {
        dataCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
 
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        dataCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        dataCellStyle.setFont(font);
    }
	
	private void createStringCell(Row row, String val, int col) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(val);
    }
 
    private void createNumericCell(Row row, int val, int col) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(val);
    }
    
    private void createNumericCell(Row row, double val, int col) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(val);
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}    
    
}
