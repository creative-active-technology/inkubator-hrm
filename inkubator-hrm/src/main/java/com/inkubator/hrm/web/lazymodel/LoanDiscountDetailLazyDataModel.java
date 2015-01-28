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
import java.util.Date;
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
public class LoanDiscountDetailLazyDataModel extends LazyDataModel<LoanPaymentDetail> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanDiscountLazyDataModel.class);
    private final LoanPaymentDetailService service;
    private LoanPaymentDetailModel loanPaymentDetailModel;
    private List<LoanPaymentDetail> loanPaymentDetailList = new ArrayList<>();
    private final Long empDataId;
    private final Long loanId;
    private final Date endDatePeriod;
    private Integer jumlahData;

    public LoanDiscountDetailLazyDataModel(LoanPaymentDetailService service, Long empDataId, Long loanId, Date endDatePeriod) {
        this.service = service;
        this.empDataId = empDataId;
        this.loanId = loanId;
        this.endDatePeriod = endDatePeriod;
    }
    
    @Override
    public List<LoanPaymentDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("paymentDate");
                }
                loanPaymentDetailList = service.getAllDataPaymentWithEmpIdAndLoanId(empDataId, loanId, endDatePeriod, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataPaymentWithEmpIdAndLoanId(empDataId, loanId, endDatePeriod)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
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
