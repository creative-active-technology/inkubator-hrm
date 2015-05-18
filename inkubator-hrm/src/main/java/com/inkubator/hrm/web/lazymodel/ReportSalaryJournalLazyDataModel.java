package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.service.LogSalaryJournalService;


/**
*
* @author rizkykojek
*/
public class ReportSalaryJournalLazyDataModel extends LazyDataModel<LogSalaryJournal> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportSalaryJournalLazyDataModel.class);
    private final LogSalaryJournalService logSalaryJournalService;
    private List<LogSalaryJournal> list = new ArrayList<>();
    private Integer total;
    private Long periodId;

    public ReportSalaryJournalLazyDataModel(LogSalaryJournalService logSalaryJournalService, Long periodId) {
        this.logSalaryJournalService = logSalaryJournalService;
        this.periodId = periodId;
    }

    @Override
    public List<LogSalaryJournal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("costCenterCode");
	        }
	        
	        list = logSalaryJournalService.getAllDataReportByParam(periodId, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(logSalaryJournalService.getTotalReportByParam(periodId)));            
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
    public Object getRowKey(LogSalaryJournal model) {
        return model.getId();
    }

    @Override
    public LogSalaryJournal getRowData(String id) {
        for (LogSalaryJournal model : list) {
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
