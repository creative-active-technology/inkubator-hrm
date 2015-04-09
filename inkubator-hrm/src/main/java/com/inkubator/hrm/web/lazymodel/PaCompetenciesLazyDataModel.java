package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.PaCompetencies;
import com.inkubator.hrm.service.PaCompetenciesService;
import com.inkubator.hrm.web.search.PaCompetenciesSearchParameter;

/**
 *
 * @author WebGenX
 */
public class PaCompetenciesLazyDataModel extends LazyDataModel<PaCompetencies> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PaCompetenciesLazyDataModel.class);
    private final PaCompetenciesSearchParameter paCompetenciesSearchParameter;
    private final PaCompetenciesService paCompetenciesService;
    private List<PaCompetencies> paCompetenciess = new ArrayList<>();
    private Integer totalData;

    public PaCompetenciesLazyDataModel(PaCompetenciesSearchParameter searchParameter, PaCompetenciesService paCompetenciesService) {
        this.paCompetenciesSearchParameter = searchParameter;
        this.paCompetenciesService = paCompetenciesService;
    }

    @Override
    public List<PaCompetencies> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    paCompetenciess = paCompetenciesService.getByParam(paCompetenciesSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(paCompetenciesService.getTotalPaCompetenciesByParam(paCompetenciesSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    paCompetenciess = paCompetenciesService.getByParam(paCompetenciesSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(paCompetenciesService.getTotalPaCompetenciesByParam(paCompetenciesSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                paCompetenciess = paCompetenciesService.getByParam(paCompetenciesSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(paCompetenciesService.getTotalPaCompetenciesByParam(paCompetenciesSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return paCompetenciess;
    }

    @Override
    public Object getRowKey(PaCompetencies paCompetencies) {
        return paCompetencies.getId();
    }

    @Override
    public PaCompetencies getRowData(String id) {
        for (PaCompetencies paCompetencies : paCompetenciess) {
            if (id.equals(String.valueOf(paCompetencies.getId()))) {
                return paCompetencies;
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
