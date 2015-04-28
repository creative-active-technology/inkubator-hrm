package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class HrmMenuLazyDataModel extends LazyDataModel<HrmMenu> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(HrmMenuLazyDataModel.class);
    private final HrmMenuSearchParameter parameter;
    private final HrmMenuService service;
    private List<HrmMenu> menus = new ArrayList<>();
    private Integer total;

    public HrmMenuLazyDataModel(HrmMenuSearchParameter parameter, HrmMenuService service) {
        this.parameter = parameter;
        this.service = service;
    }

    @Override
    public List<HrmMenu> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        }
	        
	        menus = service.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        setPageSize(pageSize);
        setRowCount(total);
        return menus;
    }

    @Override
    public Object getRowKey(HrmMenu menu) {
        return menu.getId();
    }

    @Override
    public HrmMenu getRowData(String id) {
        for (HrmMenu menu : menus) {
            if (id.equals(String.valueOf(menu.getId()))) {
                return menu;
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
