/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.web.search.EmpPersonAchievementSearchParameter;
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
public class EmpPersonAchievementLazyDataModel extends LazyDataModel<EmpPersonAchievement> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(MaritalStatusLazyDataModel.class);
    private final EmpPersonAchievementSearchParameter parameter;
    private final EmpPersonAchievementService service;
    private List<EmpPersonAchievement> empPersonAchievementList = new ArrayList<>();
    private Integer jumlahData;

    public EmpPersonAchievementLazyDataModel(EmpPersonAchievementSearchParameter parameter, EmpPersonAchievementService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<EmpPersonAchievement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    empPersonAchievementList = service.getAllDataWithEmployee(parameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalEmpPersonAchievementByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    empPersonAchievementList = service.getAllDataWithEmployee(parameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalEmpPersonAchievementByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                empPersonAchievementList = service.getAllDataWithEmployee(parameter, first, pageSize, Order.desc("achievementName"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalEmpPersonAchievementByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return empPersonAchievementList;
    }
    
    @Override
    public Object getRowKey(EmpPersonAchievement empPersonAchievement) {
        return empPersonAchievement.getId();
    }

    @Override
    public EmpPersonAchievement getRowData(String id) {
        for (EmpPersonAchievement empPersonAchievement : empPersonAchievementList) {
            if (id.equals(String.valueOf(empPersonAchievement.getId()))) {
                return empPersonAchievement;
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
