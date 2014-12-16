/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.web.model.PayTempKalkulasiEmpPajakModel;
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
 * @author deni
 */
public class PayTempKalkulasiEmpPajakLazyDataModel extends LazyDataModel<PayTempKalkulasiEmpPajak> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PayTempKalkulasiEmpPajakLazyDataModel.class);
    private final String searchParameter;
    private final PayTempKalkulasiEmpPajakService service;
    private List<PayTempKalkulasiEmpPajak> payTempKalkulasiPajakList = new ArrayList<>();
    private Integer jumlahData;
    private Long taxComponentId;

    public PayTempKalkulasiEmpPajakLazyDataModel(String searchParameter, PayTempKalkulasiEmpPajakService service, Long taxComponentId) {
        this.searchParameter = searchParameter;
        this.service = service;
        this.taxComponentId = taxComponentId;
    }

    @Override
    public List<PayTempKalkulasiEmpPajak> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("taxComponent.name");
            }
            payTempKalkulasiPajakList = service.getByParamForDetail(searchParameter, first, pageSize, order, taxComponentId);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiEmpPajakByParamForDetail(searchParameter, taxComponentId)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payTempKalkulasiPajakList;
    }

    @Override
    public Object getRowKey(PayTempKalkulasiEmpPajak payTempKalkulasiEmpPajak) {
        return payTempKalkulasiEmpPajak.getId();
    }

    @Override
    public PayTempKalkulasiEmpPajak getRowData(String id) {
        for (PayTempKalkulasiEmpPajak payTempKalkulasiEmpPajak : payTempKalkulasiPajakList) {
            if (id.equals(String.valueOf(payTempKalkulasiEmpPajak.getId()))) {
                return payTempKalkulasiEmpPajak;
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
