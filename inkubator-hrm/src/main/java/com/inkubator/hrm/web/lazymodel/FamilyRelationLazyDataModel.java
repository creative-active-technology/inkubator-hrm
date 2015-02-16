package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.FamilyRelationService;
import com.inkubator.hrm.web.search.FamilyRelationSearchParameter;
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
 * @author Deni Husni FR
 */
public class FamilyRelationLazyDataModel extends LazyDataModel<FamilyRelation> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FamilyRelationLazyDataModel.class);
    private final FamilyRelationSearchParameter parameter;
    private final FamilyRelationService familyRelationService;
    private List<FamilyRelation> familyRelations = new ArrayList<>();
    private Integer total;

    public FamilyRelationLazyDataModel(FamilyRelationSearchParameter parameter, FamilyRelationService familyRelationService) {
        this.parameter = parameter;
        this.familyRelationService = familyRelationService;
    }

    @Override
    public List<FamilyRelation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    familyRelations = familyRelationService.getByParam(parameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(familyRelationService.getTotalFamilyRelationByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    familyRelations = familyRelationService.getByParam(parameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(familyRelationService.getTotalFamilyRelationByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                familyRelations = familyRelationService.getByParam(parameter, first, pageSize, Order.asc("relasiName"));
                total = Integer.parseInt(String.valueOf(familyRelationService.getTotalFamilyRelationByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(total);
        return familyRelations;
    }

    @Override
    public Object getRowKey(FamilyRelation religion) {
        return religion.getId();
    }

    @Override
    public FamilyRelation getRowData(String id) {
        for (FamilyRelation familyRelation : familyRelations) {
            if (id.equals(String.valueOf(familyRelation.getId()))) {
                return familyRelation;
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
