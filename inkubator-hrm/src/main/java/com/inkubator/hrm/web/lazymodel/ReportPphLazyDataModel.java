/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.LogMonthEndTaxesService;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;
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
public class ReportPphLazyDataModel extends LazyDataModel<PphReportModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReportPphLazyDataModel.class);
    private final LogMonthEndTaxesSearchParameter searchParameter;
    private final LogMonthEndTaxesService service;
    private List<PphReportModel> logMonthEndTaxexList = new ArrayList<>();
    private Integer jumlahData;

    public ReportPphLazyDataModel(LogMonthEndTaxesSearchParameter searchParameter, LogMonthEndTaxesService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PphReportModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("empData");
                }
                logMonthEndTaxexList = service.getAllDataByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return logMonthEndTaxexList;
    }
    
    @Override
    public Object getRowKey(PphReportModel pphReportModel) {
        return pphReportModel.getId();
    }

    @Override
    public PphReportModel getRowData(String id) {
        for (PphReportModel pphReportModel : logMonthEndTaxexList) {
            if (id.equals(String.valueOf(pphReportModel.getId()))) {
                return pphReportModel;
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