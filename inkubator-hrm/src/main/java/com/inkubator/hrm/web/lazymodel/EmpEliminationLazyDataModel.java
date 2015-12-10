/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;

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
 * @author Ahmad Mudzakkir Amal
 */
public class EmpEliminationLazyDataModel extends LazyDataModel<EmpEliminationViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(EmpEliminationLazyDataModel.class);
    private final EmpEliminationSearchParameter searchParameter;
    private final EmpCareerHistoryService service;
    private List<EmpEliminationViewModel> listEmpEliminationViewModel = new ArrayList<>();
    private Integer jumlahData;

    public EmpEliminationLazyDataModel(EmpEliminationSearchParameter searchParameter, EmpCareerHistoryService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<EmpEliminationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listEmpEliminationViewModel = service.getListEmpEliminationViewModelByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListEmpEliminationViewModelByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listEmpEliminationViewModel = service.getListEmpEliminationViewModelByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListEmpEliminationViewModelByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listEmpEliminationViewModel = service.getListEmpEliminationViewModelByParam(searchParameter, first, pageSize, Order.desc("nik"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalListEmpEliminationViewModelByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listEmpEliminationViewModel;
    }
    
    @Override
    public Object getRowKey(EmpEliminationViewModel empEliminationViewModel) {
        return empEliminationViewModel.getEmpCareerHistoryId();
    }

    @Override
    public EmpEliminationViewModel getRowData(String id) {
        for (EmpEliminationViewModel empEliminationViewModel : listEmpEliminationViewModel) {
            if (id.equals(String.valueOf(empEliminationViewModel.getEmpCareerHistoryId()))) {
                return empEliminationViewModel;
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
