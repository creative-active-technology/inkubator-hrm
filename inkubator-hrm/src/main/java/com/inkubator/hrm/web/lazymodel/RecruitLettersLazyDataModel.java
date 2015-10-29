package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;

/**
 *
 * @author deni.fahri
 */
public class RecruitLettersLazyDataModel extends LazyDataModel<RecruitLetters> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(RecruitLettersLazyDataModel.class);
    private final RecrutimentLetterSearchParameter parameter;
    private final RecruitLettersService recruitLettersService;
    private List<RecruitLetters> recruitLetterss = new ArrayList<>();
    private Integer total;

    public RecruitLettersLazyDataModel(RecrutimentLetterSearchParameter parameter, RecruitLettersService recruitLettersService) {
        this.parameter = parameter;
        this.recruitLettersService = recruitLettersService;
    }

    @Override
    public List<RecruitLetters> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    recruitLetterss = recruitLettersService.getByParam(parameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(recruitLettersService.getTotalByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    recruitLetterss = recruitLettersService.getByParam(parameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(recruitLettersService.getTotalByParam(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                recruitLetterss = recruitLettersService.getByParam(parameter, first, pageSize, Order.asc("code"));
                total = Integer.parseInt(String.valueOf(recruitLettersService.getTotalByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return recruitLetterss;
    }

    @Override
    public Object getRowKey(RecruitLetters recruitLetters) {
        return recruitLetters.getId();
    }

    @Override
    public RecruitLetters getRowData(String id) {
        for (RecruitLetters recruitLetters : recruitLetterss) {
            if (id.equals(String.valueOf(recruitLetters.getId()))) {
                return recruitLetters;
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
