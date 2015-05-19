/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;
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
public class ApprovalActivityLazyDataModel extends LazyDataModel<ApprovalActivity> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ApprovalActivityLazyDataModel.class);
    private final ApprovalActivitySearchParameter searchParameter;
    private final ApprovalActivityService service;
    private List<ApprovalActivity> listApprovalActivity = new ArrayList<>();
    private Integer jumlahData;

    public ApprovalActivityLazyDataModel(ApprovalActivitySearchParameter searchParameter, ApprovalActivityService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<ApprovalActivity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listApprovalActivity = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listApprovalActivity = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listApprovalActivity = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.desc("requestBy"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listApprovalActivity;
    }
    
    @Override
    public Object getRowKey(ApprovalActivity approvalActivity) {
        return approvalActivity.getId();
    }

    @Override
    public ApprovalActivity getRowData(String id) {
        for (ApprovalActivity approvalActivity : listApprovalActivity) {
            if (id.equals(String.valueOf(approvalActivity.getId()))) {
                return approvalActivity;
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
