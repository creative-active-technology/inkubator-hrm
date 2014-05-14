/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;


import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
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
public class HrmUserLazyDataModel extends LazyDataModel<HrmUser> implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(LoginHistoryLazyModel.class);
    private final HrmUserSearchParameter spiUserSearchParameter;
    private final HrmUserService spiUserService;
    private List<HrmUser> spiUsers = new ArrayList<>();
    private Integer jumlah;

    public HrmUserLazyDataModel(HrmUserSearchParameter spiUserSearchParameter, HrmUserService spiUserService) {
        this.spiUserSearchParameter = spiUserSearchParameter;
        this.spiUserService = spiUserService;
    }

    @Override
    public List<HrmUser> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    spiUsers = spiUserService.getByParam(spiUserSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(spiUserService.getTotalHrmUserByParam(spiUserSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    spiUsers = spiUserService.getByParam(spiUserSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(spiUserService.getTotalHrmUserByParam(spiUserSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                spiUsers = spiUserService.getByParam(spiUserSearchParameter, first, pageSize, Order.asc("userId"));
                jumlah = Integer.parseInt(String.valueOf(spiUserService.getTotalHrmUserByParam(spiUserSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return spiUsers;
    }

    @Override
    public Object getRowKey(HrmUser spiUser) {
        return spiUser.getId();
    }

    @Override
    public HrmUser getRowData(String id) {
        for (HrmUser spiUser : spiUsers) {
            if (id.equals(String.valueOf(spiUser.getId()))) {
                return spiUser;
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
