/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Termination;
import com.inkubator.hrm.service.TerminationService;
import com.inkubator.hrm.web.search.TerminationSearchParameter;
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
public class TerminationLazyDataModel extends LazyDataModel<Termination> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(TerminationLazyDataModel.class);
    private final TerminationSearchParameter searchParameter;
    private final TerminationService service;
    private List<Termination> terminationList = new ArrayList<>();
    private Integer jumlahData;

    public TerminationLazyDataModel(TerminationSearchParameter searchParameter, TerminationService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<Termination> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        
            try {
                Order order = null;
                if(sortField != null ){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                terminationList = service.getAllDataWithAllRelation(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalTerminateByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return terminationList;
    }
    
    @Override
    public Object getRowKey(Termination termination) {
        return termination.getId();
    }

    @Override
    public Termination getRowData(String id) {
        for (Termination termination : terminationList) {
            if (id.equals(String.valueOf(termination.getId()))) {
                return termination;
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