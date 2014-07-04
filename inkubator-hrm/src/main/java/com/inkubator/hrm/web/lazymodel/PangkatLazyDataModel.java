package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.service.PangkatService;
import com.inkubator.hrm.web.search.PangkatSearchParameter;


/**
*
* @author rizkykojek
*/
public class PangkatLazyDataModel extends LazyDataModel<Pangkat> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PangkatLazyDataModel.class);
    private final PangkatSearchParameter parameter;
    private final PangkatService pangkatService;
    private List<Pangkat> pangkats = new ArrayList<>();
    private Integer total;

    public PangkatLazyDataModel(PangkatSearchParameter parameter, PangkatService pangkatService) {
        this.parameter = parameter;
        this.pangkatService = pangkatService;
    }

    @Override
    public List<Pangkat> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("pangkatName");
	        }
	        
	        pangkats = pangkatService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(pangkatService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return pangkats;
    }

    @Override
    public Object getRowKey(Pangkat pangkat) {
        return pangkat.getId();
    }

    @Override
    public Pangkat getRowData(String id) {
        for (Pangkat pangkat : pangkats) {
            if (id.equals(String.valueOf(pangkat.getId()))) {
                return pangkat;
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
