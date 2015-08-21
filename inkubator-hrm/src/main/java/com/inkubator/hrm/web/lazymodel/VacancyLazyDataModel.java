package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementDetailService;
import com.inkubator.hrm.web.search.VacancySearchParameter;


/**
*
* @author rizkykojek
*/
public class VacancyLazyDataModel extends LazyDataModel<RecruitVacancyAdvertisementDetail> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(VacancyLazyDataModel.class);
    private final VacancySearchParameter parameter;
    private final RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService;
    private List<RecruitVacancyAdvertisementDetail> list = new ArrayList<>();
    private Integer total;

    public VacancyLazyDataModel(VacancySearchParameter parameter, RecruitVacancyAdvertisementDetailService recruitVacancyAdvertisementDetailService) {
        this.parameter = parameter;
        this.recruitVacancyAdvertisementDetailService = recruitVacancyAdvertisementDetailService;
    }

    @Override
    public List<RecruitVacancyAdvertisementDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("vacancyAdvertisement.effectiveDate");
	        }
	        
	        list = recruitVacancyAdvertisementDetailService.getAllDataVacancyByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitVacancyAdvertisementDetailService.getTotalVacancyByParam(parameter)));            
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
    public Object getRowKey(RecruitVacancyAdvertisementDetail entity) {
        return entity.getId();
    }

    @Override
    public RecruitVacancyAdvertisementDetail getRowData(String id) {
        for (RecruitVacancyAdvertisementDetail entity : list) {
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
