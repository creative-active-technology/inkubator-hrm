package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LanguageUsed;
import com.inkubator.hrm.service.LanguageService;

/**
*
* @author Taufik Hidayat
*/
public class LanguageLazyDataModel extends LazyDataModel<LanguageUsed> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LanguageLazyDataModel.class);
    private final String parameter;
    private final LanguageService languageService;
    private List<LanguageUsed> languages = new ArrayList<>();
    private Integer total;

    public LanguageLazyDataModel(String parameter, LanguageService languageService) {
        this.parameter = parameter;
        this.languageService = languageService;
    }

    @Override
    public List<LanguageUsed> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("languageName");
	        }
	        
	        languages = languageService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(languageService.getTotalLanguageByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return languages;
    }

    @Override
    public Object getRowKey(LanguageUsed language) {
        return language.getId();
    }

    @Override
    public LanguageUsed getRowData(String id) {
        for (LanguageUsed language : languages) {
            if (id.equals(String.valueOf(language.getId()))) {
                return language;
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
