package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class BenefitGroupLazyDataModel extends LazyDataModel<BenefitGroup> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(BenefitGroupLazyDataModel.class);
    private final BenefitGroupSearchParameter benefitGroupSearchParameter;
    private final BenefitGroupService benefitGroupService;
    private List<BenefitGroup> benefitGroups = new ArrayList<>();
    private Integer total;

    public BenefitGroupLazyDataModel(BenefitGroupSearchParameter benefitGroupSearchParameter, BenefitGroupService benefitGroupService) {
        this.benefitGroupSearchParameter = benefitGroupSearchParameter;
        this.benefitGroupService = benefitGroupService;
    }

    @Override
    public List<BenefitGroup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	benefitGroups = benefitGroupService.getByParam(benefitGroupSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(benefitGroupService.getTotalByParam(benefitGroupSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	benefitGroups = benefitGroupService.getByParam(benefitGroupSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(benefitGroupService.getTotalByParam(benefitGroupSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	benefitGroups = benefitGroupService.getByParam(benefitGroupSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(benefitGroupService.getTotalByParam(benefitGroupSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return benefitGroups;
    }

    @Override
    public Object getRowKey(BenefitGroup benefitGroup) {
        return benefitGroup.getId();
    }

    @Override
    public BenefitGroup getRowData(String id) {
        for (BenefitGroup benefitGroup : benefitGroups) {
            if (id.equals(String.valueOf(benefitGroup.getId()))) {
                return benefitGroup;
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
