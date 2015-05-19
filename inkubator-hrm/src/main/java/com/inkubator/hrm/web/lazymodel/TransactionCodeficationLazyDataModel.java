package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.web.search.TransactionCodeficationSearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class TransactionCodeficationLazyDataModel extends LazyDataModel<TransactionCodefication> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TransactionCodeficationLazyDataModel.class);
    private final TransactionCodeficationSearchParameter transactionCodeficationSearchParameter;
    private final TransactionCodeficationService transactionCodeficationService;
    private List<TransactionCodefication> transactionCodefications = new ArrayList<>();
    private Integer total;

    public TransactionCodeficationLazyDataModel(TransactionCodeficationSearchParameter transactionCodeficationSearchParameter, TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationSearchParameter = transactionCodeficationSearchParameter;
        this.transactionCodeficationService = transactionCodeficationService;
    }

    @Override
    public List<TransactionCodefication> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    transactionCodefications = transactionCodeficationService.getByParam(transactionCodeficationSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(transactionCodeficationService.getTotalByParam(transactionCodeficationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    transactionCodefications = transactionCodeficationService.getByParam(transactionCodeficationSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(transactionCodeficationService.getTotalByParam(transactionCodeficationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                transactionCodefications = transactionCodeficationService.getByParam(transactionCodeficationSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(transactionCodeficationService.getTotalByParam(transactionCodeficationSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return transactionCodefications;
    }

    @Override
    public Object getRowKey(TransactionCodefication transactionCodefication) {
        return transactionCodefication.getId();
    }

    @Override
    public TransactionCodefication getRowData(String id) {
        for (TransactionCodefication transactionCodefication : transactionCodefications) {
            if (id.equals(String.valueOf(transactionCodefication.getId()))) {
                return transactionCodefication;
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
