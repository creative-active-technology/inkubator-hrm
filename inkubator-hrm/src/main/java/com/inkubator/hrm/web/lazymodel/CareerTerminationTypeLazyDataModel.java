package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.TerminationTypeService;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;
import com.inkubator.hrm.web.search.TerminationTypeSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class CareerTerminationTypeLazyDataModel extends LazyDataModel<CareerTerminationType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CareerTerminationTypeLazyDataModel.class);
    private final CareerTerminationTypeSearchParameter careerTerminationTypeSearchParameter;
    private final CareerTerminationTypeService  careerTerminationTypeService;
    private List<CareerTerminationType> listCareerTerminationTypes = new ArrayList<>();
    private Integer total;

    public CareerTerminationTypeLazyDataModel(CareerTerminationTypeSearchParameter careerTerminationTypeSearchParameter, CareerTerminationTypeService careerTerminationTypeService) {
        this.careerTerminationTypeSearchParameter = careerTerminationTypeSearchParameter;
        this.careerTerminationTypeService = careerTerminationTypeService;
    }

    @Override
    public List<CareerTerminationType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	listCareerTerminationTypes = careerTerminationTypeService.getListByParam(careerTerminationTypeSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(careerTerminationTypeService.getTotalByParam(careerTerminationTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	listCareerTerminationTypes = careerTerminationTypeService.getListByParam(careerTerminationTypeSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(careerTerminationTypeService.getTotalByParam(careerTerminationTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	listCareerTerminationTypes = careerTerminationTypeService.getListByParam(careerTerminationTypeSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(careerTerminationTypeService.getTotalByParam(careerTerminationTypeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return listCareerTerminationTypes;
    }

    @Override
    public Object getRowKey(CareerTerminationType careerTerminationType) {
        return careerTerminationType.getId();
    }

    @Override
    public CareerTerminationType getRowData(String id) {
        for (CareerTerminationType careerTerminationType : listCareerTerminationTypes) {
            if (StringUtils.equals(id, String.valueOf(careerTerminationType.getId()))) {
                return careerTerminationType;
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
