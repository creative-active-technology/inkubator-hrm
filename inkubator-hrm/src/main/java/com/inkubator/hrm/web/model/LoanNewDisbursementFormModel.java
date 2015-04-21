/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewDisbursementFormModel implements Serializable {

    private Long id;
    private String disbursementCode;
    private Long coaId;
    private Date disbursementDate;
    private String description;
    private Map<String, Long> mapCoa;
    private Map<Integer, Boolean> selectedIds = new HashMap<Integer, Boolean>();
    private List<Integer> listLoanNewApplicationId;

    public LoanNewDisbursementFormModel() {
        this.mapCoa = new HashMap<>();
    }

    public Map<String, Long> getMapCoa() {
        return mapCoa;
    }

    public void setMapCoa(Map<String, Long> mapCoa) {
        this.mapCoa = mapCoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisbursementCode() {
        return disbursementCode;
    }

    public void setDisbursementCode(String disbursementCode) {
        this.disbursementCode = disbursementCode;
    }

    public Long getCoaId() {
        return coaId;
    }

    public void setCoaId(Long coaId) {
        this.coaId = coaId;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, Boolean> getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(Map<Integer, Boolean> selectedIds) {
        this.selectedIds = selectedIds;
    }

    public List<Integer> getListLoanNewApplicationId() {
        listLoanNewApplicationId = new ArrayList<Integer>();
        for (Map.Entry<Integer, Boolean> selected : selectedIds.entrySet()) {
            if (StringUtils.equals(String.valueOf(selected.getValue()), "true")) {
                listLoanNewApplicationId.add(selected.getKey());
            }
        }
        return listLoanNewApplicationId;
    }

    public void setListLoanNewApplicationId(List<Integer> listLoanNewApplicationId) {
        this.listLoanNewApplicationId = listLoanNewApplicationId;
    }

}
