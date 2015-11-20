package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.CarreerTransition;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.CareerTransitionService;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

public class CareerTransitionLazyDataModel extends LazyDataModel<CarreerTransition> implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(CareerTransitionLazyDataModel.class);
    private final CareerTransitionSearchParameter searchParameter;
    private final CareerTransitionService careerTransitionService;
    private List<CarreerTransition> careerTransitionList = new ArrayList<>();
    private Integer jumlahData;
    
	public CareerTransitionLazyDataModel(CareerTransitionSearchParameter searchParameter, CareerTransitionService careerTransitionService) {
		this.searchParameter = searchParameter;
		this.careerTransitionService = careerTransitionService;
	}
    
	@Override
    public List<CarreerTransition> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                careerTransitionList = careerTransitionService.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(careerTransitionService.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return careerTransitionList;
    }
    
    @Override
    public Object getRowKey(CarreerTransition carreerTransition) {
        return carreerTransition.getId();
    }

    @Override
    public CarreerTransition getRowData(String id) {
        for (CarreerTransition carreerTransition : careerTransitionList) {
            if (id.equals(String.valueOf(carreerTransition.getId()))) {
                return carreerTransition;
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
