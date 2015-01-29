/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.batch;

import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.model.PayTempOvertimeFileModel;
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

/**
 *
 * @author deni
 */
public class PayTempOvertimeUploadReader implements ItemReader<PayTempOvertimeFileModel> {

    private String createdBy;
    private String paySalaryComponentId;
    private final String pathUpload;
    private final String extension;
    private FlatFileItemReader<PayTempOvertimeFileModel> csvFileReader;
    private PoiItemReader<PayTempOvertimeFileModel> excelFileReader;

    public PayTempOvertimeUploadReader(String filePath) {
        
        this.extension = StringUtils.substringAfterLast(filePath, ".");
        this.pathUpload = filePath;

        if (StringUtils.equals(this.extension, "csv")) {
            this.initializationCsvReader(filePath);
        } else {
            this.initializationExcelReader(filePath);
        }
    }

    private void initializationCsvReader(String filePath) {
        
        //read a CSV file
        Resource resource = new FileSystemResource(filePath);

        //split by separated coma
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
        lineTokenizer.setNames(new String[]{"Nik", "Overtime"});

        //mapped to an object
        BeanWrapperFieldSetMapper<PayTempOvertimeFileModel> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(PayTempOvertimeFileModel.class);

        DefaultLineMapper<PayTempOvertimeFileModel> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        //initial flatFileItemReader
        csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setLineMapper(lineMapper);
        csvFileReader.setResource(resource);
        csvFileReader.setLinesToSkip(1);
        csvFileReader.open(new ExecutionContext());
    }

    private void initializationExcelReader(String filePath) {
        
        //read a Excel file
        Resource resource = new FileSystemResource(filePath);

        try {
            //mapped to an object
            BeanPropertyRowMapper<PayTempOvertimeFileModel> rowMapper = new BeanPropertyRowMapper<>();
            rowMapper.setTargetType(PayTempOvertimeFileModel.class);
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
    public PayTempOvertimeFileModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        PayTempOvertimeFileModel model = null;
        if (StringUtils.equals(extension, "csv")) {
            model = csvFileReader.read();
        } else {
            model = excelFileReader.read();
        }

        //kenapa di cek null, karena tanda batch process telah berakhir ialah read.process == null
        if (model != null) {
            model.setCreateBy(createdBy);
            model.setPathUpload(pathUpload);
        }

        return model;
    }

    public void destroy() throws Exception {
        //should close reader
        if (StringUtils.equals(extension, "csv")) {
            csvFileReader.close();
        } else {
            excelFileReader.close();
        }
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

    public FlatFileItemReader<PayTempOvertimeFileModel> getCsvFileReader() {
        return csvFileReader;
    }

    public void setCsvFileReader(FlatFileItemReader<PayTempOvertimeFileModel> csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public PoiItemReader<PayTempOvertimeFileModel> getExcelFileReader() {
        return excelFileReader;
    }

    public void setExcelFileReader(PoiItemReader<PayTempOvertimeFileModel> excelFileReader) {
        this.excelFileReader = excelFileReader;
    }
    
    
}
