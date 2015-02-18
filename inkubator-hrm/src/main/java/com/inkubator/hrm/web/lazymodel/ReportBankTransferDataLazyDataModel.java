package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;


/**
*
* @author rizkykojek
*/
public class ReportBankTransferDataLazyDataModel extends LazyDataModel<LogListOfTransfer> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportBankTransferDataLazyDataModel.class);
    private final LogListOfTransferService logListOfTransferService;
    private List<LogListOfTransfer> list = new ArrayList<>();
    private Integer total;
    private ReportBankTransferDataSearchParameter parameter;

    public ReportBankTransferDataLazyDataModel(LogListOfTransferService logListOfTransferService, ReportBankTransferDataSearchParameter parameter) {
        this.logListOfTransferService = logListOfTransferService;
        this.parameter = parameter;
    }

    @Override
    public List<LogListOfTransfer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("empName");
	        }
	        
	        list = logListOfTransferService.getAllDataReportBankTransferData(parameter, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(logListOfTransferService.getTotalReportBankTransferData(parameter)));            
	        LOGGER.info("Success Load Lazy data Model");

        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(LogListOfTransfer model) {
        return model.getId();
    }

    @Override
    public LogListOfTransfer getRowData(String id) {
        for (LogListOfTransfer model : list) {
            if (id.equals(String.valueOf(model.getId()))) {
                return model;
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
