/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.web.model.SpecificationAbilityModelView;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;

/**
 *
 * @author rizkykojek
 */
public class SpecificationAbilityLazyDataModel extends LazyDataModel<SpecificationAbilityModelView> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SpecificationAbilityLazyDataModel.class);
    private final SpecificationAbilitySearchParameter specAbilitySearchParameter;
    private final SpecificationAbilityService specAbilityService;
    private List<SpecificationAbilityModelView> specAbilities = new ArrayList<>();
    private Integer total;

    
    public SpecificationAbilityLazyDataModel(SpecificationAbilitySearchParameter specAbilitySearchParameter, SpecificationAbilityService specAbilityService) {
        this.specAbilitySearchParameter = specAbilitySearchParameter;
        this.specAbilityService = specAbilityService;
    }

    @Override
    public List<SpecificationAbilityModelView> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
            
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	specAbilities = specAbilityService.getDataBySpecAbilityNotExistInJabatanSpec(specAbilitySearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(specAbilityService.getTotalByParam(specAbilitySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	specAbilities = specAbilityService.getDataBySpecAbilityNotExistInJabatanSpec(specAbilitySearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(specAbilityService.getTotalByParam(specAbilitySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	specAbilities = specAbilityService.getDataBySpecAbilityNotExistInJabatanSpec(specAbilitySearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(specAbilityService.getTotalByParam(specAbilitySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return specAbilities;
    }

    @Override
    public Object getRowKey(SpecificationAbilityModelView specificationAbility) {
        return specificationAbility.getId();
    }

    @Override
    public SpecificationAbilityModelView getRowData(String id) {
        for (SpecificationAbilityModelView specAbility : specAbilities) {
            if (id.equals(String.valueOf(specAbility.getId()))) {
                return specAbility;
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
