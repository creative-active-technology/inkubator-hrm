package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.LogSalaryJournalService;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;


/**
*
* @author rizkykojek
*/
public class ReportSalaryJournalGroupingLazyDataModel extends LazyDataModel<ReportSalaryJounalModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportSalaryJournalGroupingLazyDataModel.class);
    private final LogSalaryJournalService logSalaryJournalService;
    private List<ReportSalaryJounalModel> list = new ArrayList<>();
    private Integer total;

    public ReportSalaryJournalGroupingLazyDataModel(LogSalaryJournalService logSalaryJournalService) {
        this.logSalaryJournalService = logSalaryJournalService;
    }

    @Override
    public List<ReportSalaryJounalModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("wtPeriode.fromPeriode");
	        }
	        
	        list = logSalaryJournalService.getAllDataReportGroupingByPeriod(first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(logSalaryJournalService.getTotalReportGroupingByPeriod()));            
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
    public Object getRowKey(ReportSalaryJounalModel model) {
        return model.getPeriodId();
    }

    @Override
    public ReportSalaryJounalModel getRowData(String id) {
        for (ReportSalaryJounalModel model : list) {
            if (id.equals(String.valueOf(model.getPeriodId()))) {
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
