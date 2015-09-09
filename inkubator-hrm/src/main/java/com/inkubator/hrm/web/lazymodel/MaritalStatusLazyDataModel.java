/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.service.MaritalStatusService;
import com.inkubator.hrm.web.search.MaritalStatusSearchParameter;
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
public class MaritalStatusLazyDataModel extends LazyDataModel<MaritalStatus> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(MaritalStatusLazyDataModel.class);
    private final MaritalStatusSearchParameter parameter;
    private final MaritalStatusService service;
    private List<MaritalStatus> maritalStatusList = new ArrayList<>();
    private Integer jumlahData;

    public MaritalStatusLazyDataModel(MaritalStatusSearchParameter parameter, MaritalStatusService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<MaritalStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                maritalStatusList = service.getByParam(parameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalMaritalStatusByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return maritalStatusList;
    }
    
    @Override
    public Object getRowKey(MaritalStatus maritalStatus) {
        return maritalStatus.getId();
    }

    @Override
    public MaritalStatus getRowData(String id) {
        for (MaritalStatus maritalStatus : maritalStatusList) {
            if (id.equals(String.valueOf(maritalStatus.getId()))) {
                return maritalStatus;
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
