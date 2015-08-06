package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;


/**
*
* @author rizkykojek
*/
public class VacancyAdvertisementLazyDataModel extends LazyDataModel<RecruitVacancyAdvertisement> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(VacancyAdvertisementLazyDataModel.class);
    private final VacancyAdvertisementSearchParameter parameter;
    private final RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
    private List<RecruitVacancyAdvertisement> list = new ArrayList<>();
    private Integer total;

    public VacancyAdvertisementLazyDataModel(VacancyAdvertisementSearchParameter parameter, RecruitVacancyAdvertisementService recruitVacancyAdvertisementService) {
        this.parameter = parameter;
        this.recruitVacancyAdvertisementService = recruitVacancyAdvertisementService;
    }

    @Override
    public List<RecruitVacancyAdvertisement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("effectiveDate");
	        }
	        
	        list = recruitVacancyAdvertisementService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitVacancyAdvertisementService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RecruitVacancyAdvertisement entity) {
        return entity.getId();
    }

    @Override
    public RecruitVacancyAdvertisement getRowData(String id) {
        for (RecruitVacancyAdvertisement entity : list) {
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
