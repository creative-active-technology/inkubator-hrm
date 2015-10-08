package com.inkubator.hrm.batch;

import java.util.Date;

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

import com.inkubator.hrm.web.model.ApplicantUploadBatchModel;

/**
 *
 * @author rizkykojek
 */
public class RecruitApplicantUploadReader implements ItemReader<ApplicantUploadBatchModel> {

	private Long vacancyAdvertisementId;
	private String createdBy;
	private Date createdOn;
	private String uploadPath;
	private final String extension;
	private FlatFileItemReader<ApplicantUploadBatchModel> csvFileReader;
	private PoiItemReader<ApplicantUploadBatchModel> excelFileReader;
	
	public RecruitApplicantUploadReader(String path){
		this.uploadPath = path;
		this.extension = StringUtils.substringAfterLast(path, ".");
		if(StringUtils.equals(this.extension, "csv")){
			this.initializationCsvReader(path);
		} else {
			this.initializationExcelReader(path);
		}
	}
	
	private void initializationCsvReader(String filePath){
		//read a CSV file
		Resource resource = new FileSystemResource(filePath);
		
		//split by separated coma
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		lineTokenizer.setNames(new String[]{"FirstName","LastName","Gender","CityOfBirth","DateOfBirth","EmailAddress","PhoneNumber","EducationLevel","InstitutionEducation","Score","Scale","EducationStartYear","EducationEndYear","CertificateNumber"});
		
		//mapped to an object
		BeanWrapperFieldSetMapper<ApplicantUploadBatchModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(ApplicantUploadBatchModel.class);
		
		DefaultLineMapper<ApplicantUploadBatchModel> lineMapper =  new DefaultLineMapper<>();
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
			BeanPropertyRowMapper<ApplicantUploadBatchModel> rowMapper = new BeanPropertyRowMapper<>();
			rowMapper.setTargetType(ApplicantUploadBatchModel.class);		
			rowMapper.afterPropertiesSet();		
		
			//initial poiItemReader
			excelFileReader = new PoiItemReader<>();
			excelFileReader.setResource(resource);
			excelFileReader.setLinesToSkip(3);
			excelFileReader.setRowMapper(rowMapper);
			excelFileReader.afterPropertiesSet();
			excelFileReader.open(new ExecutionContext());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
	}
	
	@Override
	public ApplicantUploadBatchModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		ApplicantUploadBatchModel model = null;
		if(StringUtils.equals(extension, "csv")){
			model = csvFileReader.read();	
		}else {
			model = excelFileReader.read();
		}
			
		//kenapa di cek null, karena tanda batch process telah berakhir ialah read.process == null
		if(model!= null){
			model.setVacancyAdvertisementId(vacancyAdvertisementId);
			model.setUploadPath(uploadPath);
			model.setCreatedBy(createdBy);
			model.setCreatedOn(createdOn);
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public Long getVacancyAdvertisementId() {
		return vacancyAdvertisementId;
	}

	public void setVacancyAdvertisementId(Long vacancyAdvertisementId) {
		this.vacancyAdvertisementId = vacancyAdvertisementId;
	}	
	
}
