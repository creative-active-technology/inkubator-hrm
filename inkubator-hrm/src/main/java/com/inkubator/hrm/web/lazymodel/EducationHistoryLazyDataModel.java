/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.service.EducationHistoryService;
import com.inkubator.hrm.web.search.EducationHistorySearchParameter;
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
public class EducationHistoryLazyDataModel extends LazyDataModel<EducationHistory> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(MaritalStatusLazyDataModel.class);
    private final EducationHistorySearchParameter parameter;
    private final EducationHistoryService service;
    private List<EducationHistory> educationHistoryList = new ArrayList<>();
    private Integer jumlahData;

    public EducationHistoryLazyDataModel(EducationHistorySearchParameter parameter, EducationHistoryService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<EducationHistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    educationHistoryList = service.getByParam(parameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalEducationHistoryByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    educationHistoryList = service.getByParam(parameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalEducationHistoryByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                educationHistoryList = service.getByParam(parameter, first, pageSize, Order.desc("name"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalEducationHistoryByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return educationHistoryList;
    }
    
    @Override
    public Object getRowKey(EducationHistory educationHistory) {
        return educationHistory.getId();
    }

    @Override
    public EducationHistory getRowData(String id) {
        for (EducationHistory educationHistory : educationHistoryList) {
            if (id.equals(String.valueOf(educationHistory.getId()))) {
                return educationHistory;
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
