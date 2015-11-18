package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.service.CareerEmpStatusService;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;


/**
*
* @author rizkykojek
*/
public class CareerEmpStatusLazyDataModel extends LazyDataModel<CareerEmpStatus> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CareerEmpStatusLazyDataModel.class);
    private final CareerEmpStatusSearchParameter parameter;
    private final CareerEmpStatusService careerEmpStatusService;
    private List<CareerEmpStatus> list = new ArrayList<>();
    private Integer total;

    public CareerEmpStatusLazyDataModel(CareerEmpStatusSearchParameter parameter, CareerEmpStatusService careerEmpStatusService) {
        this.parameter = parameter;
        this.careerEmpStatusService = careerEmpStatusService;
    }

    @Override
    public List<CareerEmpStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        list = careerEmpStatusService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(careerEmpStatusService.getTotalByParam(parameter)));            
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
    public Object getRowKey(CareerEmpStatus entity) {
        return entity.getId();
    }

    @Override
    public CareerEmpStatus getRowData(String id) {
        for (CareerEmpStatus entity : list) {
            if (id.equals(String.valueOf(entity.getId()))) {
                return entity;
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
