/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.service.LoanPaymentDetailService;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;
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
public class LoanDiscountLazyDataModel extends LazyDataModel<LoanPaymentDetail> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanDiscountLazyDataModel.class);
    private final LoanPaymentDetailService service;
    private LoanPaymentDetailModel loanPaymentDetailModel;
    private List<LoanPaymentDetail> loanPaymentDetailList = new ArrayList<>();
    private final String parameter;
    private Integer jumlahData;

    public LoanDiscountLazyDataModel(LoanPaymentDetailService service, LoanPaymentDetailModel loanPaymentDetailModel, String parameter) {
        this.service = service;
        this.loanPaymentDetailModel = loanPaymentDetailModel;
        this.parameter = parameter;
    }
    
    @Override
    public List<LoanPaymentDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    loanPaymentDetailList = service.getByParam(parameter, loanPaymentDetailModel, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(parameter, loanPaymentDetailModel)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    loanPaymentDetailList = service.getByParam(parameter, loanPaymentDetailModel, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(parameter, loanPaymentDetailModel)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                loanPaymentDetailList = service.getByParam(parameter, loanPaymentDetailModel, first, pageSize, Order.desc("paymentDate"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(parameter, loanPaymentDetailModel)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return loanPaymentDetailList;
    }
    
    @Override
    public Object getRowKey(LoanPaymentDetail loanPaymentDetail) {
        return loanPaymentDetail.getId();
    }

    @Override
    public LoanPaymentDetail getRowData(String id) {
        for (LoanPaymentDetail loanPaymentDetail : loanPaymentDetailList) {
            if (id.equals(String.valueOf(loanPaymentDetail.getId()))) {
                return loanPaymentDetail;
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
