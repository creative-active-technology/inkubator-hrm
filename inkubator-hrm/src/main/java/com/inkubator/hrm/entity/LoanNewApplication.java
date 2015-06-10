package com.inkubator.hrm.entity;
// Generated Mar 26, 2015 1:07:27 PM by Hibernate Tools 4.3.1

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * LoanNewApplication generated by hbm2java
 */
@Entity
@Table(name = "loan_new_application", catalog = "hrm"
)
public class LoanNewApplication implements java.io.Serializable {

    private int id;
    private Integer version;
    private EmpData empData;
    private LoanNewType loanNewType;
    private LoanNewSchema loanNewSchema;
    private Date applicationDate;
    private String purposeNote;
    private Date dibursementDate;
    private Integer bufferTime;
    private String description;
    private Double nominalPrincipal;
    private Double subsidizedNominal;
    private Integer subsidizedDiscOfInterest;
    private Integer loanStatus;
    private String nomor;
    private Integer termin;
    private String approvalActivityNumber;
    private Date createdOn;
    private Date updateOn;
    private String createdBy;
    private String updatedBy;
    private String noPencairan;
    private Boolean isSelected = Boolean.TRUE;
    private Set<LoanNewApplicationInstallment> loanNewApplicationInstallments = new HashSet<LoanNewApplicationInstallment>(0);
    private Set<LoanNewDisbursementList> loanNewDibursementLists = new HashSet<LoanNewDisbursementList>(0);

    public LoanNewApplication() {
    }

    public LoanNewApplication(int id) {
        this.id = id;
    }

    public LoanNewApplication(int id, EmpData empData, LoanNewType loanNewType, Date applicationDate, String purposeNote, Date dibursementDate, Integer bufferTime, String description, Double subsidizedNominal, Integer subsidizedDiscOfInterest, Date createdOn, Date updateOn, String createdBy, String updatedBy) {
        this.id = id;
        this.empData = empData;
        this.loanNewType = loanNewType;
        this.applicationDate = applicationDate;
        this.purposeNote = purposeNote;
        this.dibursementDate = dibursementDate;
        this.bufferTime = bufferTime;
        this.description = description;
        this.subsidizedNominal = subsidizedNominal;
        this.subsidizedDiscOfInterest = subsidizedDiscOfInterest;
        this.createdOn = createdOn;
        this.updateOn = updateOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    public EmpData getEmpData() {
        return this.empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }
    
    @Column(name="nomor", unique=true, length = 45)
    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_new_type")
    public LoanNewType getLoanNewType() {
        return this.loanNewType;
    }

    public void setLoanNewType(LoanNewType loanNewType) {
        this.loanNewType = loanNewType;
    }
    
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_new_schema")
    public LoanNewSchema getLoanNewSchema() {
        return loanNewSchema;
    }

    public void setLoanNewSchema(LoanNewSchema loanNewSchema) {
        this.loanNewSchema = loanNewSchema;
    }
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "application_date", length = 10)
    public Date getApplicationDate() {
        return this.applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Column(name = "purpose_note", length = 150)
    public String getPurposeNote() {
        return this.purposeNote;
    }

    public void setPurposeNote(String purposeNote) {
        this.purposeNote = purposeNote;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dibursement_date", length = 10)
    public Date getDibursementDate() {
        return this.dibursementDate;
    }

    public void setDibursementDate(Date dibursementDate) {
        this.dibursementDate = dibursementDate;
    }

    @Column(name = "buffer_time")
    public Integer getBufferTime() {
        return this.bufferTime;
    }

    public void setBufferTime(Integer bufferTime) {
        this.bufferTime = bufferTime;
    }
    
    @Column(name = "termin")
    public Integer getTermin() {
        return termin;
    }

    public void setTermin(Integer termin) {
        this.termin = termin;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name = "nominal_principal", nullable = false)
    public Double getNominalPrincipal() {
        return nominalPrincipal;
    }

    public void setNominalPrincipal(Double nominalPrincipal) {
        this.nominalPrincipal = nominalPrincipal;
    }

    @Column(name = "subsidized_nominal", precision = 22, scale = 0)
    public Double getSubsidizedNominal() {
        return this.subsidizedNominal;
    }

    public void setSubsidizedNominal(Double subsidizedNominal) {
        this.subsidizedNominal = subsidizedNominal;
    }

    @Column(name = "subsidized_disc_of_interest")
    public Integer getSubsidizedDiscOfInterest() {
        return this.subsidizedDiscOfInterest;
    }

    public void setSubsidizedDiscOfInterest(Integer subsidizedDiscOfInterest) {
        this.subsidizedDiscOfInterest = subsidizedDiscOfInterest;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_on", length = 19)
    public Date getUpdateOn() {
        return this.updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "loan_status")
    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }
    
    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanNewApplication")
    public Set<LoanNewApplicationInstallment> getLoanNewApplicationInstallments() {
        return this.loanNewApplicationInstallments;
    }

    public void setLoanNewApplicationInstallments(Set<LoanNewApplicationInstallment> loanNewApplicationInstallments) {
        this.loanNewApplicationInstallments = loanNewApplicationInstallments;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="loanNewApplication")
    public Set<LoanNewDisbursementList> getLoanNewDibursementLists() {
        return this.loanNewDibursementLists;
    }
    
    public void setLoanNewDibursementLists(Set<LoanNewDisbursementList> loanNewDibursementLists) {
        this.loanNewDibursementLists = loanNewDibursementLists;
    }
    
    @Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }
    
    @Transient
    public Date getFirstLoanPaymentDate() {        
        return DateTimeUtil.getDateFrom(dibursementDate, bufferTime, CommonUtilConstant.DATE_FORMAT_MONTH);
    }
    
    @Transient
    public Date getMaxLoanPaymentDate() {
        return DateTimeUtil.getDateFrom(getFirstLoanPaymentDate(), termin - 1, CommonUtilConstant.DATE_FORMAT_MONTH);
    }
    
     @Transient
    public String getNoPencairan() {
        return noPencairan;
    }    
   
    public void setNoPencairan(String noPencairan) {
        this.noPencairan = noPencairan;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    
}