/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.SavingType;
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
public class SearchEmployeeLazyDataModel extends LazyDataModel<EmpData> {

    private static final Logger LOGGER = Logger.getLogger(SearchEmployeeLazyDataModel.class);
    
    private transient EmpDataService service;
    private final List<Department> listDepartment;
    private final List<GolonganJabatan> listGolonganJabatan;
    private final List<String> listNik;
    private final List<Integer> listAge;
    private final List<Integer> listJoinDate;
    private final String[] listEmployeeType;
    private List<EmpData> empDataList = new ArrayList<>();
    private Integer jumlahData;

    public SearchEmployeeLazyDataModel(EmpDataService service, List<Department> listDepartment, List<GolonganJabatan> listGolonganJabatan, String[] listEmployeeType, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik) {
        this.service = service;
        this.listDepartment = listDepartment;
        this.listGolonganJabatan = listGolonganJabatan;
        this.listNik = listNik;
        this.listAge = listAge;
        this.listJoinDate = listJoinDate;
        this.listEmployeeType = listEmployeeType;
    }
    
    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
            Order order = null;
            if (sortField != null) {
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("nik");
            }
            empDataList = service.getAllDataByParamWithDetail(listDepartment, listGolonganJabatan, listEmployeeType, listAge, listJoinDate, listNik, first, pageSize, order);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamWithDetail(listDepartment, listGolonganJabatan, listEmployeeType, listAge, listJoinDate, listNik)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return empDataList;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDataList) {
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
