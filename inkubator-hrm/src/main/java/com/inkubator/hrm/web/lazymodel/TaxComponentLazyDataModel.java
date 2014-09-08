package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.service.TaxComponentService;

/**
*
* @author Taufik Hidayat
*/
public class TaxComponentLazyDataModel extends LazyDataModel<TaxComponent> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TaxComponentLazyDataModel.class);
    private final String parameter;
    private final TaxComponentService taxComponentService;
    private List<TaxComponent> taxComponents = new ArrayList<>();
    private Integer total;

    public TaxComponentLazyDataModel(String parameter, TaxComponentService taxComponentService) {
        this.parameter = parameter;
        this.taxComponentService = taxComponentService;
    }

    @Override
    public List<TaxComponent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        taxComponents = taxComponentService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(taxComponentService.getTotalTaxComponentByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return taxComponents;
    }

    @Override
    public Object getRowKey(TaxComponent taxComponent) {
        return taxComponent.getId();
    }

    @Override
    public TaxComponent getRowData(String id) {
        for (TaxComponent taxComponent : taxComponents) {
            if (id.equals(String.valueOf(taxComponent.getId()))) {
                return taxComponent;
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
