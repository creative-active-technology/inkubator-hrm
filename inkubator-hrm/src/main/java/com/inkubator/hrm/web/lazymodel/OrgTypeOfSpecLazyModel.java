package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
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
 * @author WebGenX
 */
public class OrgTypeOfSpecLazyModel extends LazyDataModel<OrgTypeOfSpec> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(OrgTypeOfSpecLazyModel.class);
    private final OrgTypeOfSpecSearchParameter orgTypeOfSpecSearchParameter;
    private final OrgTypeOfSpecService orgTypeOfSpecService;
    private List<OrgTypeOfSpec> orgTypeOfSpecs = new ArrayList<>();
    private Integer totalData;

    public OrgTypeOfSpecLazyModel(OrgTypeOfSpecSearchParameter searchParameter, OrgTypeOfSpecService orgTypeOfSpecService) {
        this.orgTypeOfSpecSearchParameter = searchParameter;
        this.orgTypeOfSpecService = orgTypeOfSpecService;
    }

    @Override
    public List<OrgTypeOfSpec> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    orgTypeOfSpecs = orgTypeOfSpecService.getByParam(orgTypeOfSpecSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(orgTypeOfSpecService.getTotalOrgTypeOfSpecByParam(orgTypeOfSpecSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    orgTypeOfSpecs = orgTypeOfSpecService.getByParam(orgTypeOfSpecSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(orgTypeOfSpecService.getTotalOrgTypeOfSpecByParam(orgTypeOfSpecSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                orgTypeOfSpecs = orgTypeOfSpecService.getByParam(orgTypeOfSpecSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(orgTypeOfSpecService.getTotalOrgTypeOfSpecByParam(orgTypeOfSpecSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return orgTypeOfSpecs;
    }

    @Override
    public Object getRowKey(OrgTypeOfSpec orgTypeOfSpec) {
        return orgTypeOfSpec.getId();
    }

    @Override
    public OrgTypeOfSpec getRowData(String id) {
        for (OrgTypeOfSpec orgTypeOfSpec : orgTypeOfSpecs) {
            if (id.equals(String.valueOf(orgTypeOfSpec.getId()))) {
                return orgTypeOfSpec;
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
