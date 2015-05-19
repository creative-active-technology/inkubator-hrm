package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;

/**
 *
 * @author WebGenX
 */
public class RecruitMppPeriodLazyDataModel extends LazyDataModel<RecruitMppPeriod> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(RecruitMppPeriodLazyDataModel.class);
    private final RecruitMppPeriodSearchParameter recruitMppPeriodSearchParameter;
    private final RecruitMppPeriodService recruitMppPeriodService;
    private List<RecruitMppPeriod> recruitMppPeriods = new ArrayList<>();
    private Integer totalData;

    public RecruitMppPeriodLazyDataModel(RecruitMppPeriodSearchParameter searchParameter, RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodSearchParameter = searchParameter;
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    @Override
    public List<RecruitMppPeriod> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    recruitMppPeriods = recruitMppPeriodService.getByParam(recruitMppPeriodSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(recruitMppPeriodService.getTotalRecruitMppPeriodByParam(recruitMppPeriodSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    recruitMppPeriods = recruitMppPeriodService.getByParam(recruitMppPeriodSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(recruitMppPeriodService.getTotalRecruitMppPeriodByParam(recruitMppPeriodSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                recruitMppPeriods = recruitMppPeriodService.getByParam(recruitMppPeriodSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(recruitMppPeriodService.getTotalRecruitMppPeriodByParam(recruitMppPeriodSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return recruitMppPeriods;
    }

    @Override
    public Object getRowKey(RecruitMppPeriod recruitMppPeriod) {
        return recruitMppPeriod.getId();
    }

    @Override
    public RecruitMppPeriod getRowData(String id) {
        for (RecruitMppPeriod recruitMppPeriod : recruitMppPeriods) {
            if (id.equals(String.valueOf(recruitMppPeriod.getId()))) {
                return recruitMppPeriod;
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
