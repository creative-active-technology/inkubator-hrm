package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.OccupationTypeService;

/**
*
* @author Taufik Hidayat
*/
public class OccupationTypeLazyDataModel extends LazyDataModel<OccupationType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(OccupationTypeLazyDataModel.class);
    private final String parameter;
    private final OccupationTypeService occupationTypeService;
    private List<OccupationType> occupationTypes = new ArrayList<>();
    private Integer total;

    public OccupationTypeLazyDataModel(String parameter, OccupationTypeService occupationTypeService) {
        this.parameter = parameter;
        this.occupationTypeService = occupationTypeService;
    }

    @Override
    public List<OccupationType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("occupationTypeName");
	        }
	        
	        occupationTypes = occupationTypeService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(occupationTypeService.getTotalOccupationTypeByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return occupationTypes;
    }

    @Override
    public Object getRowKey(OccupationType occupationType) {
        return occupationType.getId();
    }

    @Override
    public OccupationType getRowData(String id) {
        for (OccupationType occupationType : occupationTypes) {
            if (id.equals(String.valueOf(occupationType.getId()))) {
                return occupationType;
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
