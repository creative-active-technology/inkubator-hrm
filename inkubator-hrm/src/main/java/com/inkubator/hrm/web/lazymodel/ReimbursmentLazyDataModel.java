/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;
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
public class ReimbursmentLazyDataModel extends LazyDataModel<Reimbursment> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReimbursmentLazyDataModel.class);
    private final ReimbursmentSearchParameter parameter;
    private final ReimbursmentService service;
    private List<Reimbursment> reimbursmentList = new ArrayList<>();
    private Integer jumlahData;

    public ReimbursmentLazyDataModel(ReimbursmentSearchParameter parameter, ReimbursmentService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<Reimbursment> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                reimbursmentList = service.getAllDataWithDetail(parameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalReimbursmentByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return reimbursmentList;
    }
    
    @Override
    public Object getRowKey(Reimbursment reimbursment) {
        return reimbursment.getId();
    }

    @Override
    public Reimbursment getRowData(String id) {
        for (Reimbursment reimbursment : reimbursmentList) {
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