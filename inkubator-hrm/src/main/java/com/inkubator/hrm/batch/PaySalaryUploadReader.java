package com.inkubator.hrm.batch;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;

/**
 *
 * @author rizkykojek
 */
public class PaySalaryUploadReader implements ItemReader<PaySalaryUploadFileModel> {

	private String createdBy;
	private String paySalaryComponentId;
	private String extension;
	private FlatFileItemReader<PaySalaryUploadFileModel> csvFileReader;
	
	public PaySalaryUploadReader(String filePath){
		this.extension = StringUtils.substringAfterLast(filePath, ".");
		if(StringUtils.equals(this.extension, "csv")){
			this.initializationCsvReader(filePath);
		} else {
			//excel generated jek
		}
		
	}
	
	private void initializationCsvReader(String filePath){
		//read a CSV file
		Resource resource = new FileSystemResource(filePath);
		
		//split by separated coma
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		lineTokenizer.setNames(new String[]{"Nik","Nominal"});
		
		//mapped to an object
		BeanWrapperFieldSetMapper<PaySalaryUploadFileModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<PaySalaryUploadFileModel>();
		beanWrapperFieldSetMapper.setTargetType(PaySalaryUploadFileModel.class);
		
		DefaultLineMapper<PaySalaryUploadFileModel> lineMapper =  new DefaultLineMapper<PaySalaryUploadFileModel>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);		
		
		//initial flatFileItemReader
		csvFileReader = new FlatFileItemReader<PaySalaryUploadFileModel>();		
		csvFileReader.setLineMapper(lineMapper);
		csvFileReader.setResource(resource);
		csvFileReader.setLinesToSkip(1);
		csvFileReader.open(new ExecutionContext());
	}
	
	@Override
	public PaySalaryUploadFileModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		PaySalaryUploadFileModel model = null;
		if(StringUtils.equals(extension, "csv")){
			model = csvFileReader.read();	
		}else {
			//excel generated jek
		}
			
		//kenapa di cek null, karena tanda batch process telah berakhir ialah read.process == null
		if(model!= null){ 
			model.setCreatedBy(createdBy);
			model.setPaySalaryComponentId(Long.parseLong(paySalaryComponentId));
		}
		
		return model;
		
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getPaySalaryComponentId() {
		return paySalaryComponentId;
	}

	public void setPaySalaryComponentId(String paySalaryComponentId) {
		this.paySalaryComponentId = paySalaryComponentId;
	}
}
