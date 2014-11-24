package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.service.DiseaseService;
import com.inkubator.hrm.web.search.DiseaseSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class DiseaseLazyDataModel extends LazyDataModel<Disease> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(DiseaseLazyDataModel.class);
    private final DiseaseSearchParameter diseaseSearchParameter;
    private final DiseaseService diseaseService;
    private List<Disease> diseases = new ArrayList<>();
    private Integer total;

    public DiseaseLazyDataModel(DiseaseSearchParameter diseaseSearchParameter, DiseaseService diseaseService) {
        this.diseaseSearchParameter = diseaseSearchParameter;
        this.diseaseService = diseaseService;
    }

    @Override
    public List<Disease> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	diseases = diseaseService.getByParam(diseaseSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(diseaseService.getTotalByParam(diseaseSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	diseases = diseaseService.getByParam(diseaseSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(diseaseService.getTotalByParam(diseaseSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	diseases = diseaseService.getByParam(diseaseSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(diseaseService.getTotalByParam(diseaseSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return diseases;
    }

    @Override
    public Object getRowKey(Disease disease) {
        return disease.getId();
    }

    @Override
    public Disease getRowData(String id) {
        for (Disease disease : diseases) {
            if (id.equals(String.valueOf(disease.getId()))) {
                return disease;
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
