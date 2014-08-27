/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.service.PersonalDisciplineService;
import com.inkubator.hrm.web.search.PersonalDisciplineSearchParameter;
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
 * @author Deni
 */
public class PersonalDisciplineLazyDataModel extends LazyDataModel<PersonalDiscipline> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PersonalDiscipline.class);
    private final PersonalDisciplineSearchParameter searchParameter;
    private final PersonalDisciplineService service;
    private List<PersonalDiscipline> personalDisciplineList = new ArrayList<>();
    private Integer jumlahData;

    public PersonalDisciplineLazyDataModel(PersonalDisciplineSearchParameter searchParameter, PersonalDisciplineService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PersonalDiscipline> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    personalDisciplineList = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPersonalDisciplineByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    personalDisciplineList = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPersonalDisciplineByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                personalDisciplineList = service.getAllDataWithAllRelation(searchParameter, first, pageSize, Order.desc("empData"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPersonalDisciplineByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return personalDisciplineList;
    }
    
    @Override
    public Object getRowKey(PersonalDiscipline personalDiscipline) {
        return personalDiscipline.getId();
    }

    @Override
    public PersonalDiscipline getRowData(String id) {
        for (PersonalDiscipline personalDiscipline : personalDisciplineList) {
            if (id.equals(String.valueOf(personalDiscipline.getId()))) {
                return personalDiscipline;
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
