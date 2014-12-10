package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.MedicalCareService;

/**
*
* @author Taufik Hidayat
*/
public class MedicalCareLazyDataModel extends LazyDataModel<MedicalCare> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MedicalCareLazyDataModel.class);
    private final String medicalCareSearchParameter;
    private final MedicalCareService medicalCareService;
    private List<MedicalCare> medicalCares = new ArrayList<>();
    private Integer total;

    public MedicalCareLazyDataModel(String medicalCareSearchParameter, MedicalCareService medicalCareService) {
        this.medicalCareSearchParameter = medicalCareSearchParameter;
        this.medicalCareService = medicalCareService;
    }

    @Override
    public List<MedicalCare> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	medicalCares = medicalCareService.getByParam(medicalCareSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(medicalCareService.getTotalByParam(medicalCareSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	medicalCares = medicalCareService.getByParam(medicalCareSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(medicalCareService.getTotalByParam(medicalCareSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	medicalCares = medicalCareService.getByParam(medicalCareSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(medicalCareService.getTotalByParam(medicalCareSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return medicalCares;
    }

    @Override
    public Object getRowKey(MedicalCare medicalCare) {
        return medicalCare.getId();
    }

    @Override
    public MedicalCare getRowData(String id) {
        for (MedicalCare medicalCare : medicalCares) {
            if (id.equals(String.valueOf(medicalCare.getId()))) {
                return medicalCare;
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
