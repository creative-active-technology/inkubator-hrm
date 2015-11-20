package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.service.CareerDisciplineTypeService;
import com.inkubator.hrm.web.search.CareerDisciplineTypeSearchParameter;

public class CareerDisciplineTypeLazyDataModel extends LazyDataModel<CareerDisciplineType> implements Serializable{
	private static final Logger LOGGER = Logger.getLogger(CareerDisciplineTypeLazyDataModel.class);
	private final CareerDisciplineTypeSearchParameter searchParameter;
	private final CareerDisciplineTypeService service;
	private List<CareerDisciplineType> list = new ArrayList<>();
	private Integer total;
	
	public CareerDisciplineTypeLazyDataModel(CareerDisciplineTypeSearchParameter searchParameter, CareerDisciplineTypeService service){
		this.searchParameter = searchParameter;
		this.service = service;
	}
	
	@Override
	public List<CareerDisciplineType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            list = service.getByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(service.getTotalDataByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
	
	@Override
    public Object getRowKey(CareerDisciplineType careerDisciplineType){
        return careerDisciplineType.getId();
    }
	
	@Override
    public CareerDisciplineType getRowData(String id){
        for(CareerDisciplineType careerDisciplineType : list){
            if(id.equals(String.valueOf(careerDisciplineType.getId()))){
                return careerDisciplineType;
            }
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
