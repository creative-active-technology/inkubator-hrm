/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web.lazymodel;

import com.inkubator.sms.gateway.entity.TaskDefinition;
import com.inkubator.sms.gateway.service.TaskDefinitionService;
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
 * @author denifahri
 */
public class TaskDefinitionLazy extends LazyDataModel<TaskDefinition> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TaskDefinitionLazy.class);
    private final String parameter;
    private final TaskDefinitionService taskDefinitionService;
    private List<TaskDefinition> listTaskDefinitions = new ArrayList<>();
    private int total;

    public TaskDefinitionLazy(String parameter, TaskDefinitionService taskDefinitionService) {
        this.parameter = parameter;
        this.taskDefinitionService = taskDefinitionService;
    }

    @Override
    public List<TaskDefinition> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            if (sortField != null) {
                if (sortOrder == SortOrder.ASCENDING) {
                    System.out.println(" Order dieksekusi");
                    listTaskDefinitions = taskDefinitionService.getAllByFullTextService(parameter, first, pageSize, Order.asc(sortField));
                } else {
                    listTaskDefinitions = taskDefinitionService.getAllByFullTextService(parameter, first, pageSize, Order.desc(sortField));
                }
            } else {
                listTaskDefinitions = taskDefinitionService.getAllByFullTextService(parameter, first, pageSize, Order.asc("name"));
            }
            total = taskDefinitionService.getTotalByFullTextService(parameter);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        setPageSize(pageSize);
        setRowCount(total);
        return listTaskDefinitions;
    }

    @Override
    public Object getRowKey(TaskDefinition definition) {
        return definition.getId();
    }

    @Override
    public TaskDefinition getRowData(String id) {
        for (TaskDefinition definition : listTaskDefinitions) {
            if (id.equals(String.valueOf(definition.getId()))) {
                return definition;
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
