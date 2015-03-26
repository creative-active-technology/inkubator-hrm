/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class LoanApplicationFormModel implements Serializable {

    private String nomor;
    private Long loanId;
    private Long loanNewTypeId;
    private Date loanDate;
    private Date expectedDisbursementDate;
    private EmpData empData;
    private LoanNewSchemaListOfEmp loanNewSchemaListOfEmp;
    private LoanNewSchemaListOfType selectedLoanNewSchemaListOfType;
    private List<LoanNewSchemaListOfType> listLoanNewSchemaListOfType;
    private List<LoanPaymentDetail> loanPaymentDetails;
    private LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId;
    private String purpose;
    private String description;
    private Integer rangeFirstInstallmentToDisbursement;
    private String namakaryawan;
    private Double nominalLoan;
    private Double maxLoanAmount;
    private Double minLoanAmount;
    private Double usedLoanAmount;
    private Double availableLoanAmount;
    private Double minimumInstallment;
    private Integer loanPeriod;
    private Boolean isSubsidi;
    private String subsidiType;

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
    
    
    public List<LoanPaymentDetail> getLoanPaymentDetails() {
        return loanPaymentDetails;
    }

    public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
        this.loanPaymentDetails = loanPaymentDetails;
    }

    public LoanNewSchemaListOfTypeId getLoanNewSchemaListOfTypeId() {
        return loanNewSchemaListOfTypeId;
    }

    public void setLoanNewSchemaListOfTypeId(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId) {
        this.loanNewSchemaListOfTypeId = loanNewSchemaListOfTypeId;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Double getUsedLoanAmount() {
        return usedLoanAmount;
    }

    public void setUsedLoanAmount(Double usedLoanAmount) {
        this.usedLoanAmount = usedLoanAmount;
    }

    public Double getAvailableLoanAmount() {
        return availableLoanAmount;
    }

    public void setAvailableLoanAmount(Double availableLoanAmount) {
        this.availableLoanAmount = availableLoanAmount;
    }

    public List<LoanNewSchemaListOfType> getListLoanNewSchemaListOfType() {
        return listLoanNewSchemaListOfType;
    }

    public void setListLoanNewSchemaListOfType(List<LoanNewSchemaListOfType> listLoanNewSchemaListOfType) {
        this.listLoanNewSchemaListOfType = listLoanNewSchemaListOfType;
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

    public Long getLoanNewTypeId() {
        return loanNewTypeId;
    }

    public void setLoanNewTypeId(Long loanNewTypeId) {
        this.loanNewTypeId = loanNewTypeId;
    }

    public Integer getRangeFirstInstallmentToDisbursement() {
        return rangeFirstInstallmentToDisbursement;
    }

    public void setRangeFirstInstallmentToDisbursement(Integer rangeFirstInstallmentToDisbursement) {
        this.rangeFirstInstallmentToDisbursement = rangeFirstInstallmentToDisbursement;
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

    public Boolean getIsPaginator() {
        return loanPaymentDetails.size() > 11;
    }

}
