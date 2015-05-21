/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
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
public class RecruitVacancyEmployeeLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmpDataLazyDataModel.class);
    private final String nikOrNameSearchParameter;
    private final transient EmpDataService empDataService;
    private List<EmpData> empDatadatas = new ArrayList<>();
    private Integer jumlah;

    public RecruitVacancyEmployeeLazyDataModel(String nikOrNameSearchParameter, EmpDataService empDataService) {
        this.nikOrNameSearchParameter = nikOrNameSearchParameter;
        this.empDataService = empDataService;
    }

    
    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                empDatadatas = empDataService.getByParam(nikOrNameSearchParameter, first, pageSize, order);
                jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalEmpDataByParam(nikOrNameSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return empDatadatas;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDatadatas) {
            if (id.equals(String.valueOf(empData.getId()))) {
                return empData;
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