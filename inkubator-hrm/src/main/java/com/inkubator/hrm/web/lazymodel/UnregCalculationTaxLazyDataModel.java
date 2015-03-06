package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.service.TempUnregPayrollEmpPajakService;
import com.inkubator.hrm.web.search.UnregPayrollEmpPajakSearchParameter;


/**
*
* @author rizkykojek
*/
public class UnregCalculationTaxLazyDataModel extends LazyDataModel<TempUnregPayrollEmpPajak> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UnregCalculationTaxLazyDataModel.class);
    private final TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService;
    private List<TempUnregPayrollEmpPajak> list = new ArrayList<>();
    private Integer total;
    private UnregPayrollEmpPajakSearchParameter parameter;

    public UnregCalculationTaxLazyDataModel(UnregPayrollEmpPajakSearchParameter parameter, TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService) {
        this.tempUnregPayrollEmpPajakService = tempUnregPayrollEmpPajakService;
        this.parameter = parameter;
    }

    @Override
    public List<TempUnregPayrollEmpPajak> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = tempUnregPayrollEmpPajakService.getByParam(parameter, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(tempUnregPayrollEmpPajakService.getTotalByParam(parameter)));            
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
    public Object getRowKey(TempUnregPayrollEmpPajak model) {
        return model.getId();
    }

    @Override
    public TempUnregPayrollEmpPajak getRowData(String id) {
        for (TempUnregPayrollEmpPajak model : list) {
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
