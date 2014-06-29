package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.web.search.InstitutionEducationSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class InstitutionEducationLazyDataModel extends LazyDataModel<InstitutionEducation> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(InstitutionEducationLazyDataModel.class);
    private final InstitutionEducationSearchParameter institutionEducationSearchParameter;
    private final InstitutionEducationService institutionEducationService;
    private List<InstitutionEducation> institutionEducations = new ArrayList<>();
    private Integer total;

    public InstitutionEducationLazyDataModel(InstitutionEducationSearchParameter institutionEducationSearchParameter, InstitutionEducationService institutionEducationService) {
        this.institutionEducationSearchParameter = institutionEducationSearchParameter;
        this.institutionEducationService = institutionEducationService;
    }

    @Override
    public List<InstitutionEducation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	institutionEducations = institutionEducationService.getByParam(institutionEducationSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(institutionEducationService.getTotalByParam(institutionEducationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	institutionEducations = institutionEducationService.getByParam(institutionEducationSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(institutionEducationService.getTotalByParam(institutionEducationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	institutionEducations = institutionEducationService.getByParam(institutionEducationSearchParameter, first, pageSize, Order.asc("institutionEducationName"));
                total = Integer.parseInt(String.valueOf(institutionEducationService.getTotalByParam(institutionEducationSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return institutionEducations;
    }

    @Override
    public Object getRowKey(InstitutionEducation institutionEducation) {
        return institutionEducation.getId();
    }

    @Override
    public InstitutionEducation getRowData(String id) {
        for (InstitutionEducation institutionEducation : institutionEducations) {
            if (id.equals(String.valueOf(institutionEducation.getId()))) {
                return institutionEducation;
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
