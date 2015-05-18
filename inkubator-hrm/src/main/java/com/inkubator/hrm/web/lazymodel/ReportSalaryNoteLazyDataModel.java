package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.ReportSalaryNoteModel;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;


/**
*
* @author rizkykojek
*/
public class ReportSalaryNoteLazyDataModel extends LazyDataModel<ReportSalaryNoteModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportSalaryNoteLazyDataModel.class);
    private final ReportSalaryNoteSearchParameter parameter;
    private final LogMonthEndPayrollService logMonthEndPayrollService;
    private List<ReportSalaryNoteModel> list = new ArrayList<>();
    private Integer total;

    public ReportSalaryNoteLazyDataModel(ReportSalaryNoteSearchParameter parameter, LogMonthEndPayrollService logMonthEndPayrollService) {
        this.parameter = parameter;
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }

    @Override
    public List<ReportSalaryNoteModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("empName");
	        }
	        
	        list = logMonthEndPayrollService.getByParamForReportSalaryNote(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(logMonthEndPayrollService.getTotalByParamForReportSalaryNote(parameter)));            
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
    public Object getRowKey(ReportSalaryNoteModel model) {
        return model.getEmpDataId();
    }

    @Override
    public ReportSalaryNoteModel getRowData(String id) {
        for (ReportSalaryNoteModel model : list) {
            if (id.equals(String.valueOf(model.getEmpDataId()))) {
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
