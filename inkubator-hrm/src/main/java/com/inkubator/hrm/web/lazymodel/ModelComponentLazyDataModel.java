package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.web.search.ModelComponentSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class ModelComponentLazyDataModel extends LazyDataModel<ModelComponent> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ModelComponentLazyDataModel.class);
    private final ModelComponentSearchParameter modelComponentSearchParameter;
    private final ModelComponentService modelComponentService;
    private List<ModelComponent> modelComponents = new ArrayList<>();
    private Integer total;

    public ModelComponentLazyDataModel(ModelComponentSearchParameter modelComponentSearchParameter, ModelComponentService modelComponentService) {
        this.modelComponentSearchParameter = modelComponentSearchParameter;
        this.modelComponentService = modelComponentService;
    }

    @Override
    public List<ModelComponent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	modelComponents = modelComponentService.getByParam(modelComponentSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(modelComponentService.getTotalByParam(modelComponentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	modelComponents = modelComponentService.getByParam(modelComponentSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(modelComponentService.getTotalByParam(modelComponentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	modelComponents = modelComponentService.getByParam(modelComponentSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(modelComponentService.getTotalByParam(modelComponentSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return modelComponents;
    }

    @Override
    public Object getRowKey(ModelComponent modelComponent) {
        return modelComponent.getId();
    }

    @Override
    public ModelComponent getRowData(String id) {
        for (ModelComponent modelComponent : modelComponents) {
            if (id.equals(String.valueOf(modelComponent.getId()))) {
                return modelComponent;
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
