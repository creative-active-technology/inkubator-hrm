/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
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
 * @author Deni Husni FR
 */
public class ApprovalDefinitionLazyDataModel extends LazyDataModel<ApprovalDefinition> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ApprovalDefinitionLazyDataModel.class);
    private final ApprovalDefinitionSearchParameter approvalDefinitionSearchParameter;
    private final ApprovalDefinitionService approvalDefinitionService;
    private List<ApprovalDefinition> approvalDefinitions = new ArrayList<>();
    private Integer jumlahData;

    public ApprovalDefinitionLazyDataModel(ApprovalDefinitionSearchParameter approvalDefinitionSearchParameter, ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionSearchParameter = approvalDefinitionSearchParameter;
        this.approvalDefinitionService = approvalDefinitionService;
    }

    @Override
    public List<ApprovalDefinition> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    approvalDefinitions = approvalDefinitionService.getByParam(approvalDefinitionSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(approvalDefinitionService.getTotalApprovalDefinitionByParam(approvalDefinitionSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    approvalDefinitions = approvalDefinitionService.getByParam(approvalDefinitionSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(approvalDefinitionService.getTotalApprovalDefinitionByParam(approvalDefinitionSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                approvalDefinitions = approvalDefinitionService.getByParam(approvalDefinitionSearchParameter, first, pageSize, Order.desc("name"));
                jumlahData = Integer.parseInt(String.valueOf(approvalDefinitionService.getTotalApprovalDefinitionByParam(approvalDefinitionSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return approvalDefinitions;
    }

    @Override
    public Object getRowKey(ApprovalDefinition approvalDefinition) {
        return approvalDefinition.getId();
    }

    @Override
    public ApprovalDefinition getRowData(String id) {
        for (ApprovalDefinition approvalDefinition : approvalDefinitions) {
            if (id.equals(String.valueOf(approvalDefinition.getId()))) {
                return approvalDefinition;
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
