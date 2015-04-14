/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.google.common.collect.HashBiMap;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanNewCancellationFormModel implements Serializable {

    private Long id;
    private String nomor;
    private Long loanId;
    private Date loanDate;
    private Date expectedDisbursementDate;
    private Date loanCancellationDate;
    private EmpData empData;
    private LoanNewSchema loanNewSchema;
    private LoanNewType loanNewType;
    private LoanNewSchemaListOfEmp loanNewSchemaListOfEmp;
    private LoanNewSchemaListOfType selectedLoanNewSchemaListOfType;
    private Date firstPaymentDate;
    private Date lastPaymentDate;
    private String purpose;
    private String description;
    private Integer termin;
    private String namakaryawan;
    private Double nominalLoan;
    private Double nominalUsedLoan;
    private Double totalNominalUsedLoan;
    private Double maxLoanAmount;
    private Double minLoanAmount;
    private Double minimumInstallment;
    private Integer loanPeriod;
    private Boolean isSubsidi;
    private String subsidiType;
    private Double subsidiCicilan;
    private Integer subsidiBunga;
    private Map<String, Long> mapNomorActivity;

    public LoanNewCancellationFormModel() {
        this.mapNomorActivity = new HashMap<>();

    }

    public Map<String, Long> getMapNomorActivity() {
        return mapNomorActivity;
    }

    public void setMapNomorActivity(Map<String, Long> mapNomorActivity) {
        this.mapNomorActivity = mapNomorActivity;
    }
    
    

    public Date getLoanCancellationDate() {
        return loanCancellationDate;
    }

    public void setLoanCancellationDate(Date loanCancellationDate) {
        this.loanCancellationDate = loanCancellationDate;
    }

    public Integer getTermin() {
        return termin;
    }

    public void setTermin(Integer termin) {
        this.termin = termin;
    }

    public Double getSubsidiCicilan() {
        return subsidiCicilan;
    }

    public void setSubsidiCicilan(Double subsidiCicilan) {
        this.subsidiCicilan = subsidiCicilan;
    }

    public Integer getSubsidiBunga() {
        return subsidiBunga;
    }

    public void setSubsidiBunga(Integer subsidiBunga) {
        this.subsidiBunga = subsidiBunga;
    }

    public String getSubsidiType() {
        return subsidiType;
    }

    public void setSubsidiType(String subsidiType) {
        this.subsidiType = subsidiType;
    }

    public Boolean getIsSubsidi() {
        return isSubsidi;
    }

    public void setIsSubsidi(Boolean isSubsidi) {
        this.isSubsidi = isSubsidi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinimumInstallment() {
        return minimumInstallment;
    }

    public void setMinimumInstallment(Double minimumInstallment) {
        this.minimumInstallment = minimumInstallment;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(Double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public Double getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(Double minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public LoanNewSchemaListOfType getSelectedLoanNewSchemaListOfType() {
        return selectedLoanNewSchemaListOfType;
    }

    public void setSelectedLoanNewSchemaListOfType(LoanNewSchemaListOfType selectedLoanNewSchemaListOfType) {
        this.selectedLoanNewSchemaListOfType = selectedLoanNewSchemaListOfType;
    }

    public LoanNewSchemaListOfEmp getLoanNewSchemaListOfEmp() {
        return loanNewSchemaListOfEmp;
    }

    public void setLoanNewSchemaListOfEmp(LoanNewSchemaListOfEmp loanNewSchemaListOfEmp) {
        this.loanNewSchemaListOfEmp = loanNewSchemaListOfEmp;
    }

    public String getNamakaryawan() {
        return namakaryawan;
    }

    public void setNamakaryawan(String namakaryawan) {
        this.namakaryawan = namakaryawan;
    }

    public Double getNominalLoan() {
        return nominalLoan;
    }

    public void setNominalLoan(Double nominalLoan) {
        this.nominalLoan = nominalLoan;
    }

    public Date getExpectedDisbursementDate() {
        return expectedDisbursementDate;
    }

    public void setExpectedDisbursementDate(Date expectedDisbursementDate) {
        this.expectedDisbursementDate = expectedDisbursementDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanNewSchema getLoanNewSchema() {
        return loanNewSchema;
    }

    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }

    public LoanNewType getLoanNewType() {
        return loanNewType;
    }

    public void setLoanNewType(LoanNewType loanNewType) {
        this.loanNewType = loanNewType;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Double getNominalUsedLoan() {
        return nominalUsedLoan;
    }

    public void setNominalUsedLoan(Double nominalUsedLoan) {
        this.nominalUsedLoan = nominalUsedLoan;
    }

    public Double getTotalNominalUsedLoan() {
        return totalNominalUsedLoan;
    }

    public void setTotalNominalUsedLoan(Double totalNominalUsedLoan) {
        this.totalNominalUsedLoan = totalNominalUsedLoan;
    }

}
