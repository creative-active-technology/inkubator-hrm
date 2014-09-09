/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
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
public class ReimbursmentSchemaLazyDataModel extends LazyDataModel<ReimbursmentSchema> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReimbursmentSchemaLazyDataModel.class);
    private final ReimbursmentSchemaSearchParameter parameter;
    private final ReimbursmentSchemaService service;
    private List<ReimbursmentSchema> reimbursmentSchemaList = new ArrayList<>();
    private Integer jumlahData;

    public ReimbursmentSchemaLazyDataModel(ReimbursmentSchemaSearchParameter parameter, ReimbursmentSchemaService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<ReimbursmentSchema> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    reimbursmentSchemaList = service.getAllDataWithAllRelation(parameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalReimbursmentByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    reimbursmentSchemaList = service.getAllDataWithAllRelation(parameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalReimbursmentByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                reimbursmentSchemaList = service.getAllDataWithAllRelation(parameter, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalReimbursmentByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return reimbursmentSchemaList;
    }
    
    @Override
    public Object getRowKey(ReimbursmentSchema reimbursmentSchema) {
        return reimbursmentSchema.getId();
    }

    @Override
    public ReimbursmentSchema getRowData(String id) {
        for (ReimbursmentSchema reimbursmentSchema : reimbursmentSchemaList) {
            if (id.equals(String.valueOf(reimbursmentSchema.getId()))) {
                return reimbursmentSchema;
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