package com.inkubator.hrm.batch;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.excel.mapping.BeanPropertyRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.inkubator.hrm.web.model.FingerSwapCapturedUploadFileModel;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedUploadReader implements ItemReader<FingerSwapCapturedUploadFileModel> {

	private String createdBy;
	private Long machineId;
	private final String extension;
	private FlatFileItemReader<FingerSwapCapturedUploadFileModel> csvFileReader;
	private PoiItemReader<FingerSwapCapturedUploadFileModel> excelFileReader;
	
	public FingerSwapCapturedUploadReader(String filePath){
           
		this.extension = StringUtils.substringAfterLast(filePath, ".");
		if(StringUtils.equals(this.extension, "csv")){
			this.initializationCsvReader(filePath);
		} else {
			this.initializationExcelReader(filePath);
		}
		
	}
	
	private void initializationCsvReader(String filePath){
		//read a CSV file
		Resource resource = new FileSystemResource(filePath);
		
		//split by separated coma
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		lineTokenizer.setNames(new String[]{"Nik","MachineCode","FingerDate"});
		
		//mapped to an object
		BeanWrapperFieldSetMapper<FingerSwapCapturedUploadFileModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(FingerSwapCapturedUploadFileModel.class);
		
		DefaultLineMapper<FingerSwapCapturedUploadFileModel> lineMapper =  new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);		
		
		//initial flatFileItemReader
		csvFileReader = new FlatFileItemReader<>();		
		csvFileReader.setLineMapper(lineMapper);
		csvFileReader.setResource(resource);
		csvFileReader.setLinesToSkip(1);
		csvFileReader.open(new ExecutionContext());
	}
	
	private void initializationExcelReader(String filePath){
            
		//read a Excel file
		Resource resource = new FileSystemResource(filePath);
		
		try {
			//mapped to an object
			BeanPropertyRowMapper<FingerSwapCapturedUploadFileModel> rowMapper = new BeanPropertyRowMapper<>();
			rowMapper.setTargetType(FingerSwapCapturedUploadFileModel.class);		
			rowMapper.afterPropertiesSet();		
		
			//initial poiItemReader
			excelFileReader = new PoiItemReader<>();
			excelFileReader.setResource(resource);
			excelFileReader.setLinesToSkip(1);
			excelFileReader.setRowMapper(rowMapper);
			excelFileReader.afterPropertiesSet();
			excelFileReader.open(new ExecutionContext());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
	}
	
	@Override
	public FingerSwapCapturedUploadFileModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		FingerSwapCapturedUploadFileModel model = null;
		if(StringUtils.equals(extension, "csv")){
			model = csvFileReader.read();	
		}else {
			model = excelFileReader.read();
		}
			
		//kenapa di cek null, karena tanda batch process telah berakhir ialah read.process == null
		if(model!= null){ 
			model.setCreatedBy(createdBy);
			model.setMachineId(machineId);
		}
		
		return model;		
	}
	
	public void destroy() throws Exception {
		//should close reader
		if(StringUtils.equals(extension, "csv")){
			csvFileReader.close();
		}else {
			excelFileReader.close();
		}
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}	
	
}
