/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
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
public class EmpDataAnnouncementLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmpDataAnnouncementLazyDataModel.class);
    private final EmpDataService service;
    private final List<Long> empTypeId;
    private final List<Long> unitKerjaId;
    private final List<Long> golJabId;
    private List<EmpData> list = new ArrayList<>();
    private Integer total;

    public EmpDataAnnouncementLazyDataModel(EmpDataService service, List<Long> empTypeId, List<Long> unitKerjaId, List<Long> golJabId) {
        this.service = service;
        this.empTypeId = empTypeId;
        this.unitKerjaId = unitKerjaId;
        this.golJabId = golJabId;
    }

    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
            Order orderable = null;
            if (sortField != null) {
                orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                orderable = Order.desc("jabatanByJabatanId");
            }

            list = service.getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(empTypeId, golJabId, unitKerjaId, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(empTypeId, golJabId, unitKerjaId)));
            LOGGER.info("Success Load Lazy data Model");

        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : list) {
            if (id.equals(String.valueOf(empData.getId()))) {
                return empData;
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
