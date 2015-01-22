package com.inkubator.hrm.batch;

import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.web.model.LoanModel;
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

import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanUploadReader implements ItemReader<LoanModel> {

	private String createdBy;	
	private final String pathUpload;
        private Long loanSchemeId;
	private final String extension;
	private FlatFileItemReader<LoanModel> csvFileReader;
	private PoiItemReader<LoanModel> excelFileReader;
        
       
	
	public LoanUploadReader(String filePath){           
           
                this.extension = StringUtils.substringAfterLast(filePath, ".");
                this.pathUpload = filePath;           
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
		lineTokenizer.setNames(new String[]{"Nik","NominalPrincipal","Termin"});
		
		//mapped to an object
		BeanWrapperFieldSetMapper<LoanModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(LoanModel.class);
		
		DefaultLineMapper<LoanModel> lineMapper =  new DefaultLineMapper<>();
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
			BeanPropertyRowMapper<LoanModel> rowMapper = new BeanPropertyRowMapper<>();
			rowMapper.setTargetType(LoanModel.class);		
			rowMapper.afterPropertiesSet();	
                       
			//initial poiItemReader
			excelFileReader = new PoiItemReader<>();
			excelFileReader.setResource(resource);
			excelFileReader.setLinesToSkip(1);
			excelFileReader.setRowMapper(rowMapper);                                        
			excelFileReader.afterPropertiesSet();
			excelFileReader.open(new ExecutionContext());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println("selesai excel");
	}
	
	@Override
	public LoanModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		LoanModel model = null;
		if(StringUtils.equals(extension, "csv")){
			model = csvFileReader.read();	
		}else {
			model = excelFileReader.read();
		}
			
		//kenapa di cek null, karena tanda batch process telah berakhir ialah read.process == null
		if(model!= null){ 
			model.setCreatedBy(createdBy);
			model.setPathUpload(pathUpload);
                        model.setLoanSchemaId(loanSchemeId);                                            
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

    public Long getLoanSchemeId() {
        return loanSchemeId;
    }

    public void setLoanSchemeId(Long loanSchemeId) {
        this.loanSchemeId = loanSchemeId;
    }

   
        
    
	
}
