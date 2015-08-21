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

import org.hibernate.criterion.Order;
import org.jboss.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.InsuranceService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;

/**
 *
 * @author Deni
 */
public class InsuranceLazyDataModel extends LazyDataModel<Insurance> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(InsuranceLazyDataModel.class);
    private final InsuranceSearchParameter insuranceSearchParameter;
    private final InsuranceService insuranceService;
    private List<Insurance> insuranceList = new ArrayList<>();
    private Integer jumlahData;


    
    public InsuranceLazyDataModel(InsuranceSearchParameter insuranceSearchParameter, InsuranceService insuranceService) {
		this.insuranceSearchParameter = insuranceSearchParameter;
		this.insuranceService = insuranceService;
	}

	@Override
    public List<Insurance> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                insuranceList = insuranceService.getByParam(insuranceSearchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(insuranceService.getTotalByParam(insuranceSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return insuranceList;
    }
    
    @Override
    public Object getRowKey(Insurance insurance) {
        return insurance.getId();
    }

    @Override
    public Insurance getRowData(String id) {
        for (Insurance insurance : insuranceList) {
            if (id.equals(String.valueOf(insurance.getId()))) {
                return insurance;
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
