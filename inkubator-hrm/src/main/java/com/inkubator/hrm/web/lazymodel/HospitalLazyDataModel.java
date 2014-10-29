package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.service.HospitalService;
import com.inkubator.hrm.web.search.HospitalSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Taufik Hidayat
 */
public class HospitalLazyDataModel extends LazyDataModel<Hospital> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(HospitalLazyDataModel.class);
    private final HospitalSearchParameter hospitalSearchParameter;
    private final HospitalService hospitalService;
    private List<Hospital> hospitals = new ArrayList<>();
    private Integer total;

    public HospitalLazyDataModel(HospitalSearchParameter hospitalSearchParameter, HospitalService hospitalService) {
        this.hospitalSearchParameter = hospitalSearchParameter;
        this.hospitalService = hospitalService;
    }

 
    @Override
    public List<Hospital> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    hospitals = hospitalService.getByParam(hospitalSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(hospitalService.getTotalByParam(hospitalSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    hospitals = hospitalService.getByParam(hospitalSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(hospitalService.getTotalByParam(hospitalSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                hospitals = hospitalService.getByParam(hospitalSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(hospitalService.getTotalByParam(hospitalSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return hospitals;
    }

    @Override
    public Object getRowKey(Hospital hospital) {
        return hospital.getId();
    }

    @Override
    public Hospital getRowData(String id) {
        for (Hospital hospital : hospitals) {
            if (id.equals(String.valueOf(hospital.getId()))) {
                return hospital;
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
