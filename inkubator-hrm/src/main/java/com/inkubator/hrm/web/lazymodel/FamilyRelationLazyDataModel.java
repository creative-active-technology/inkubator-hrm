package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.FamilyRelationService;
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
 * @author rizkykojek
 */
public class FamilyRelationLazyDataModel extends LazyDataModel<FamilyRelation> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FamilyRelationLazyDataModel.class);
    private final String parameter;
    private final FamilyRelationService familyRelationService;
    private List<FamilyRelation> religions = new ArrayList<>();
    private Integer total;

    public FamilyRelationLazyDataModel(String parameter, FamilyRelationService familyRelationService) {
        this.parameter = parameter;
        this.familyRelationService = familyRelationService;
    }

    @Override
    public List<FamilyRelation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
            Order orderable = null;
            if (sortField != null) {
                orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                orderable = Order.desc("name");
            }

            religions = familyRelationService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(familyRelationService.getTotalFamilyRelationByParam(parameter)));
            LOGGER.info("Success Load Lazy data Model");

        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return religions;
    }

    @Override
    public Object getRowKey(FamilyRelation religion) {
        return religion.getId();
    }

    @Override
    public FamilyRelation getRowData(String id) {
        for (FamilyRelation religion : religions) {
            if (id.equals(String.valueOf(religion.getId()))) {
                return religion;
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
