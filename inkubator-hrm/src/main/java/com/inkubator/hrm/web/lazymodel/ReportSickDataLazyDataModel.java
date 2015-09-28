/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.web.model.ReportSickDataModel;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;

/**
 *
 * @author arsyad_
 */
public class ReportSickDataLazyDataModel extends LazyDataModel<MedicalCare> implements Serializable{
    
    

	private static final Logger LOGGER = Logger.getLogger(ReportSickDataLazyDataModel.class);
    private final ReportSickDataSearchParameter parameter;
    private final MedicalCareService medicalCareService;
    private List<MedicalCare> list = new ArrayList<>();
    private Integer total;
    
    public ReportSickDataLazyDataModel(ReportSickDataSearchParameter parameter, MedicalCareService medicalCareService){
        this.parameter = parameter;
        this.medicalCareService = medicalCareService;
    }
    
    @Override
    public List<MedicalCare> load(int firstResult, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    	LOGGER.info("Step load lazy data Model");
    	try{
    			Order orderable = null;
    			if(sortField != null){
    				orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
    			} else {
    				orderable = Order.asc("requestDate");
    			}
    			
    			list = medicalCareService.getByParamForReportSickData(parameter, firstResult, pageSize, orderable);
    			total = Integer.parseInt(String.valueOf(medicalCareService.getTotalByParamForReportSickData(parameter)));
    			LOGGER.info("Success load lazy data model");
    	} catch (Exception ex){
    		LOGGER.error("Failed to load lazy data model");
    		LOGGER.error("Error : ", ex);
    	}
    	
    	setPageSize(pageSize);
    	setRowCount(total);
    	return list;
    }
    
    @Override
    public Object getRowKey(MedicalCare medicalCare){
    	return medicalCare.getId();
    }
    
    @Override
    public MedicalCare getRowData(String id){
    	for (MedicalCare medicalCare : list){
    		if(id.equals(String.valueOf(medicalCare.getId()))){
    			return medicalCare;
    		}
    	}
    	return null;
    }

	@Override
	public void setRowIndex(int rowIndex) {
		if(rowIndex == -1 || getPageSize() == 0){
    		super.setRowIndex(-1);
    	} else {
    		super.setRowIndex(rowIndex % getPageSize());
    	}
	}
}
