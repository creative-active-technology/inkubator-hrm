/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
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
public class HrmRoleLazyModel extends LazyDataModel<HrmRole> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(HrmRoleLazyModel.class);
    private final HrmRoleSearchParameter hrmRoleSearchParameter;
    private final HrmRoleService hrmRoleService;
    private List<HrmRole> hrmRoles = new ArrayList<>();
    private Integer jumlahData;

    public HrmRoleLazyModel(HrmRoleSearchParameter hrmRoleSearchParameter, HrmRoleService hrmRoleService) {
        this.hrmRoleSearchParameter = hrmRoleSearchParameter;
        this.hrmRoleService = hrmRoleService;
    }

    @Override
    public List<HrmRole> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    hrmRoles = hrmRoleService.getByParam(hrmRoleSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(hrmRoleService.getTotalHrmRoleByParam(hrmRoleSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    hrmRoles = hrmRoleService.getByParam(hrmRoleSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(hrmRoleService.getTotalHrmRoleByParam(hrmRoleSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                hrmRoles = hrmRoleService.getByParam(hrmRoleSearchParameter, first, pageSize, Order.desc("roleName"));
                jumlahData = Integer.parseInt(String.valueOf(hrmRoleService.getTotalHrmRoleByParam(hrmRoleSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return hrmRoles;
    }

    @Override
    public Object getRowKey(HrmRole hrmRole) {
        return hrmRole.getId();
    }

    @Override
    public HrmRole getRowData(String id) {
        for (HrmRole hrmRole : hrmRoles) {
            if (id.equals(String.valueOf(hrmRole.getId()))) {
                return hrmRole;
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
