/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.service.InclusionReimbursmentService;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
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
public class InclusionReimbursmentLazyDataModel extends LazyDataModel<Reimbursment> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(InclusionReimbursmentLazyDataModel.class);
    private final InclusionReimbursmentService service;
    private InclusionReimbursmentModel inclusionReimbursmentModel;
    private List<Reimbursment> inclusionReimbursmentList = new ArrayList<>();
    private final String parameter;
    private Integer jumlahData;

    public InclusionReimbursmentLazyDataModel(InclusionReimbursmentService service, InclusionReimbursmentModel inclusionReimbursmentModel, String parameter) {
        this.service = service;
        this.inclusionReimbursmentModel = inclusionReimbursmentModel;
        this.parameter = parameter;
    }

    
    
    @Override
    public List<Reimbursment> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                inclusionReimbursmentList = service.getByParam(parameter, inclusionReimbursmentModel, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(parameter, inclusionReimbursmentModel)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return inclusionReimbursmentList;
    }
    
    @Override
    public Object getRowKey(Reimbursment reimbursment) {
        return reimbursment.getId();
    }

    @Override
    public Reimbursment getRowData(String id) {
        for (Reimbursment reimbursment : inclusionReimbursmentList) {
            if (id.equals(String.valueOf(reimbursment.getId()))) {
                return reimbursment;
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
