package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;
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
 * @author Deni Husni FR
 */
public class PayReceiverBankAccountLazyDataModel extends LazyDataModel<PayReceiverBankAccountModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PayReceiverBankAccountLazyDataModel.class);
    private final PayReceiverBankAccountSearchParameter payReceiverBankAccountSearchParameter;
    private final PayReceiverBankAccountService payReceiverBankAccountService;
    private List<PayReceiverBankAccountModel> bankAccountModels = new ArrayList<>();
    private Integer total;

    public PayReceiverBankAccountLazyDataModel(PayReceiverBankAccountSearchParameter payReceiverBankAccountSearchParameter, PayReceiverBankAccountService payReceiverBankAccountService) {
        this.payReceiverBankAccountSearchParameter = payReceiverBankAccountSearchParameter;
        this.payReceiverBankAccountService = payReceiverBankAccountService;
    }

 
    @Override
    public List<PayReceiverBankAccountModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    bankAccountModels = payReceiverBankAccountService.getByParam(payReceiverBankAccountSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(payReceiverBankAccountService.getTotalByParam(payReceiverBankAccountSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    bankAccountModels = payReceiverBankAccountService.getByParam(payReceiverBankAccountSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(payReceiverBankAccountService.getTotalByParam(payReceiverBankAccountSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                bankAccountModels = payReceiverBankAccountService.getByParam(payReceiverBankAccountSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(payReceiverBankAccountService.getTotalByParam(payReceiverBankAccountSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
       LOGGER.info("Jumlah nya "+total);
        setPageSize(pageSize);
        setRowCount(total);
        return bankAccountModels;
    }

    @Override
    public Object getRowKey(PayReceiverBankAccountModel admonitionType) {
        return admonitionType.getId();
    }

    @Override
    public PayReceiverBankAccountModel getRowData(String id) {
        for (PayReceiverBankAccountModel admonitionType : bankAccountModels) {
            if (id.equals(String.valueOf(admonitionType.getId()))) {
                return admonitionType;
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
