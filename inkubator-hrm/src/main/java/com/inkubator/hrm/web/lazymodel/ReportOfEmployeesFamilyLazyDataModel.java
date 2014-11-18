/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Deni
 */
public class ReportOfEmployeesFamilyLazyDataModel extends LazyDataModel<BioFamilyRelationship> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReportOfEmployeesFamilyLazyDataModel.class);
    private final ReportOfEmployeesFamilySearchParameter searchParameter;
    private final BioFamilyRelationshipService service;
    private List<BioFamilyRelationship> bioRelationShipList = new ArrayList<>();
    private Integer jumlahData;

    public ReportOfEmployeesFamilyLazyDataModel(ReportOfEmployeesFamilySearchParameter searchParameter, BioFamilyRelationshipService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<BioFamilyRelationship> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("bioData.firstName");
	        }
	        
	        bioRelationShipList = service.getAllDataReportOfEmployeesFamilyByParam(searchParameter, first, pageSize, orderable);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalReportOfEmployeesFamilyByParam(searchParameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return bioRelationShipList;
    }
    
    @Override
    public Object getRowKey(BioFamilyRelationship bioFamilyRelationship) {
        return bioFamilyRelationship.getId();
    }

    @Override
    public BioFamilyRelationship getRowData(String id) {
        for (BioFamilyRelationship bioFamilyRelationship : bioRelationShipList) {
            if (id.equals(String.valueOf(bioFamilyRelationship.getId()))) {
                return bioFamilyRelationship;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
    
}
