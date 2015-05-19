package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;


/**
*
* @author rizkykojek
*/
public class UnregCalculationExecuteLazyDataModel extends LazyDataModel<UnregSalaryCalculationExecuteModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UnregCalculationExecuteLazyDataModel.class);
    private final TempUnregPayrollService tempUnregPayrollService;
    private List<UnregSalaryCalculationExecuteModel> list = new ArrayList<>();
    private Integer total;
    private Long unregSalaryId;

    public UnregCalculationExecuteLazyDataModel(Long unregSalaryId, TempUnregPayrollService tempUnregPayrollService) {
        this.tempUnregPayrollService = tempUnregPayrollService;
        this.unregSalaryId = unregSalaryId;
    }

    @Override
    public List<UnregSalaryCalculationExecuteModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = tempUnregPayrollService.getByParamUnregSalaryId(unregSalaryId, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(tempUnregPayrollService.getTotalByParamUnregSalaryId(unregSalaryId)));            
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
    public Object getRowKey(UnregSalaryCalculationExecuteModel model) {
        return model.getPaySalaryCompId();
    }

    @Override
    public UnregSalaryCalculationExecuteModel getRowData(String id) {
        for (UnregSalaryCalculationExecuteModel model : list) {
            if (id.equals(String.valueOf(model.getPaySalaryCompId()))) {
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
