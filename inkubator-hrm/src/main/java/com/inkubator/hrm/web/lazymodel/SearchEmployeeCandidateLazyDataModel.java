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
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
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
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateLazyDataModel extends LazyDataModel<SearchEmployeeCandidateViewModel> {

    private static final Logger LOGGER = Logger.getLogger(SearchEmployeeCandidateLazyDataModel.class);
    
    private transient EmpDataService service;
    private final List<Long> listJabatanId;
    private final List<Long> listReligionId;   
    private final List<Integer> listAge;
    private final List<Integer> listJoinDate;
    private final Double gpa;
    private final Long educationLevelId;
    private final String gender;
    private List<SearchEmployeeCandidateViewModel> empDataList = new ArrayList<>();
    private Integer jumlahData;

    public SearchEmployeeCandidateLazyDataModel(EmpDataService service, List<Long> listJabatanId, List<Long> listReligionId, List<Integer> listAge, List<Integer> listJoinDate, Double gpa, Long educationLevelId, String gender) {
        this.service = service;
        this.listJabatanId = listJabatanId;
        this.listReligionId = listReligionId;       
        this.listAge = listAge;
        this.listJoinDate = listJoinDate;
        this.gpa = gpa;
        this.gender = gender;
        this.educationLevelId = educationLevelId;        
    }
    
    @Override
    public List<SearchEmployeeCandidateViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
            Order order = null;
            if (sortField != null) {
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("nik");
            }
            empDataList = service.getAllDataEmpCandidateByParamWithDetail(listJabatanId, listReligionId, listAge, listJoinDate, gpa, educationLevelId,gender, first, pageSize, order);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalEmpCandidateByParamWithDetail(listJabatanId, listReligionId, listAge, listJoinDate, gpa, educationLevelId, gender)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return empDataList;
    }

    @Override
    public Object getRowKey(SearchEmployeeCandidateViewModel empData) {
        return empData.getEmpDataId();
    }

    @Override
    public SearchEmployeeCandidateViewModel getRowData(String id) {
        for (SearchEmployeeCandidateViewModel empData : empDataList) {
            if (id.equals(String.valueOf(empData.getEmpDataId()))) {
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
