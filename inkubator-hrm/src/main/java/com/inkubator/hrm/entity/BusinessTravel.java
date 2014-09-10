package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "business_travel", catalog = "hrm")
public class BusinessTravel implements Serializable {

    private Long id;
    private Integer version;
    private String businessTravelNo;
    private EmpData empData;
    private TravelZone travelZone;
    private TravelType travelType;
    private String destination;
    private Date proposeDate;
    private Date startDate;
    private Date endDate;
    private String description;
    private String approvalActivityNumber;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<BusinessTravelComponent> businessTravelComponents = new HashSet<BusinessTravelComponent>(0);

    public BusinessTravel() {

    }

    public BusinessTravel(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "business_travel_seq_gen")
    @SequenceGenerator(name = "business_travel_seq_gen", sequenceName = "BUSINESS_TRAVEL_SEQ")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    @Column(name = "business_travel_no", unique = true, nullable = false, length = 45)
    public String getBusinessTravelNo() {
        return businessTravelNo;
    }

    public void setBusinessTravelNo(String businessTravelNo) {
        this.businessTravelNo = businessTravelNo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_data_id", nullable = false)
    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_zone_id", nullable = false)
    public TravelZone getTravelZone() {
        return travelZone;
    }

    public void setTravelZone(TravelZone travelZone) {
        this.travelZone = travelZone;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_type_id", nullable = false)
    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    @Column(name = "destination", nullable = false, length = 45)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "propose_date", length = 19)
    public Date getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(Date proposeDate) {
        this.proposeDate = proposeDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", length = 19)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", length = 19)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "description", length = 65535, columnDefinition = "Text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="approval_activity_number", length=45, unique=true)
    public String getApprovalActivityNumber() {
		return approvalActivityNumber;
	}

	public void setApprovalActivityNumber(String approvalActivityNumber) {
		this.approvalActivityNumber = approvalActivityNumber;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessTravel", orphanRemoval = true)
    public Set<BusinessTravelComponent> getBusinessTravelComponents() {
        return businessTravelComponents;
    }

    public void setBusinessTravelComponents(Set<BusinessTravelComponent> businessTravelComponents) {
        this.businessTravelComponents = businessTravelComponents;
    }
}
