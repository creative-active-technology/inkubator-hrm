package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class BankLazyDataModel extends LazyDataModel<Bank> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BankLazyDataModel.class);
    private final BankSearchParameter bankSearchParameter;
    private final BankService bankService;
    private List<Bank> banks = new ArrayList<>();
    private Integer total;

    public BankLazyDataModel(BankSearchParameter bankSearchParameter, BankService bankService) {
        this.bankSearchParameter = bankSearchParameter;
        this.bankService = bankService;
    }

    @Override
    public List<Bank> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    banks = bankService.getByParam(bankSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(bankService.getTotalByParam(bankSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    banks = bankService.getByParam(bankSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(bankService.getTotalByParam(bankSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                banks = bankService.getByParam(bankSearchParameter, first, pageSize, Order.asc("bankName"));
                total = Integer.parseInt(String.valueOf(bankService.getTotalByParam(bankSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return banks;
    }

    @Override
    public Object getRowKey(Bank bank) {
        return bank.getId();
    }

    @Override
    public Bank getRowData(String id) {
        for (Bank bank : banks) {
            if (id.equals(String.valueOf(bank.getId()))) {
                return bank;
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
