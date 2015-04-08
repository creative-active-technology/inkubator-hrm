package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.OrgJobFamilyLevel;
import com.inkubator.hrm.service.OrgJobFamilyLevelService;
import com.inkubator.hrm.web.search.OrgJobFamilyLevelSearchParameter;

/**
 *
 * @author WebGenX
 */
public class OrgJobFamilyLevelLazyDataModel extends LazyDataModel<OrgJobFamilyLevel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(OrgJobFamilyLevelLazyDataModel.class);
    private final OrgJobFamilyLevelSearchParameter orgJobFamilyLevelSearchParameter;
    private final OrgJobFamilyLevelService orgJobFamilyLevelService;
    private List<OrgJobFamilyLevel> orgJobFamilyLevels = new ArrayList<>();
    private Integer totalData;

    public OrgJobFamilyLevelLazyDataModel(OrgJobFamilyLevelSearchParameter searchParameter, OrgJobFamilyLevelService orgJobFamilyLevelService) {
        this.orgJobFamilyLevelSearchParameter = searchParameter;
        this.orgJobFamilyLevelService = orgJobFamilyLevelService;
    }

    @Override
    public List<OrgJobFamilyLevel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    orgJobFamilyLevels = orgJobFamilyLevelService.getByParam(orgJobFamilyLevelSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(orgJobFamilyLevelService.getTotalOrgJobFamilyLevelByParam(orgJobFamilyLevelSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    orgJobFamilyLevels = orgJobFamilyLevelService.getByParam(orgJobFamilyLevelSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(orgJobFamilyLevelService.getTotalOrgJobFamilyLevelByParam(orgJobFamilyLevelSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                orgJobFamilyLevels = orgJobFamilyLevelService.getByParam(orgJobFamilyLevelSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(orgJobFamilyLevelService.getTotalOrgJobFamilyLevelByParam(orgJobFamilyLevelSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return orgJobFamilyLevels;
    }

    @Override
    public Object getRowKey(OrgJobFamilyLevel orgJobFamilyLevel) {
        return orgJobFamilyLevel.getId();
    }

    @Override
    public OrgJobFamilyLevel getRowData(String id) {
        for (OrgJobFamilyLevel orgJobFamilyLevel : orgJobFamilyLevels) {
            if (id.equals(String.valueOf(orgJobFamilyLevel.getId()))) {
                return orgJobFamilyLevel;
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
