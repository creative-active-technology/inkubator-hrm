package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class PaySalaryJurnalLazyDataModel extends LazyDataModel<PaySalaryJurnal> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PaySalaryJurnalLazyDataModel.class);
    private final PaySalaryJurnalSearchParameter paySalaryJurnalSearchParameter;
    private final PaySalaryJurnalService paySalaryJurnalService;
    private List<PaySalaryJurnal> paySalaryJurnals = new ArrayList<>();
    private Integer total;

    public PaySalaryJurnalLazyDataModel(PaySalaryJurnalSearchParameter paySalaryJurnalSearchParameter, PaySalaryJurnalService paySalaryJurnalService) {
        this.paySalaryJurnalSearchParameter = paySalaryJurnalSearchParameter;
        this.paySalaryJurnalService = paySalaryJurnalService;
    }

    @Override
    public List<PaySalaryJurnal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	paySalaryJurnals = paySalaryJurnalService.getByParam(paySalaryJurnalSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(paySalaryJurnalService.getTotalByParam(paySalaryJurnalSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	paySalaryJurnals = paySalaryJurnalService.getByParam(paySalaryJurnalSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(paySalaryJurnalService.getTotalByParam(paySalaryJurnalSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	paySalaryJurnals = paySalaryJurnalService.getByParam(paySalaryJurnalSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(paySalaryJurnalService.getTotalByParam(paySalaryJurnalSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return paySalaryJurnals;
    }

    @Override
    public Object getRowKey(PaySalaryJurnal paySalaryJurnal) {
        return paySalaryJurnal.getId();
    }

    @Override
    public PaySalaryJurnal getRowData(String id) {
        for (PaySalaryJurnal paySalaryJurnal : paySalaryJurnals) {
            if (id.equals(String.valueOf(paySalaryJurnal.getId()))) {
                return paySalaryJurnal;
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
