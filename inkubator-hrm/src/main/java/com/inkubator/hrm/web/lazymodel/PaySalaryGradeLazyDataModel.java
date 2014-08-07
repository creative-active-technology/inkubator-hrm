/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.web.search.PaySalaryGradeSearchParameter;
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
public class PaySalaryGradeLazyDataModel extends LazyDataModel<PaySalaryGrade> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PaySalaryGradeLazyDataModel.class);
    private final PaySalaryGradeSearchParameter searchParameter;
    private final PaySalaryGradeService service;
    private List<PaySalaryGrade> paySalaryGradeList = new ArrayList<>();
    private Integer jumlahData;

    public PaySalaryGradeLazyDataModel(PaySalaryGradeSearchParameter searchParameter, PaySalaryGradeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PaySalaryGrade> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    paySalaryGradeList = service.getByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPaySalaryGradeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    paySalaryGradeList = service.getByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPaySalaryGradeByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                paySalaryGradeList = service.getByParam(searchParameter, first, pageSize, Order.desc("gradeSalary"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPaySalaryGradeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return paySalaryGradeList;
    }
    
    @Override
    public Object getRowKey(PaySalaryGrade paySalaryGrade) {
        return paySalaryGrade.getId();
    }

    @Override
    public PaySalaryGrade getRowData(String id) {
        for (PaySalaryGrade paySalaryGrade : paySalaryGradeList) {
            if (id.equals(String.valueOf(paySalaryGrade.getId()))) {
                return paySalaryGrade;
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
