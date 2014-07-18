package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.web.search.BioAddressSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class BioAddressLazyDataModel extends LazyDataModel<BioAddress> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BioAddressLazyDataModel.class);
    private final BioAddressSearchParameter parameter;
    private final BioAddressService service;
    private List<BioAddress> list = new ArrayList<>();
    private Integer total;

    public BioAddressLazyDataModel(BioAddressSearchParameter parameter, BioAddressService service) {
        this.parameter = parameter;
        this.service = service;
    }

    @Override
    public List<BioAddress> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        list = service.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(parameter)));            
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
    public Object getRowKey(BioAddress bioAddress) {
        return bioAddress.getId();
    }

    @Override
    public BioAddress getRowData(String id) {
        for (BioAddress bioAddress : list) {
            if (id.equals(String.valueOf(bioAddress.getId()))) {
                return bioAddress;
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
