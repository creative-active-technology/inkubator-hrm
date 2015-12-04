package com.inkubator.hrm.entity;
// Generated Jun 17, 2014 4:36:40 PM by Hibernate Tools 3.6.0

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * GolonganJabatan generated by hbm2java
 */
@Entity
@Table(name = "golongan_jabatan", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class GolonganJabatan implements java.io.Serializable {

    private long id;
    private Integer version;
    private Pangkat pangkat;
    private PaySalaryGrade paySalaryGrade;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date upatedOn;
    private String code;
    private Boolean overtime;
    private Integer pointMin;
    private Integer pointMid;
    private Integer pointMax;
    private BigDecimal ratioCompact;
    private Date updatedOn;
    private List<GolonganJabatan> listGolonganJabatans = new ArrayList<>(0);
    private Set<AppraisalCompetencyTypeGolJab> appraisalCompetencyTypeGolJabs = new HashSet<AppraisalCompetencyTypeGolJab>(0);

    public GolonganJabatan() {
    }

    public GolonganJabatan(long id) {
        this.id = id;
    }
    
    public GolonganJabatan(long id, String code) {
        this.id = id;
        this.code = code;
    }

    public GolonganJabatan(long id, Pangkat pangkat, PaySalaryGrade paySalaryGrade, String createdBy, Date createdOn, String updatedBy, Date upatedOn, String code, Boolean overtime, Integer pointMin, Integer pointMid, Integer pointMax, BigDecimal ratioCompact, Date updatedOn) {
        this.id = id;
        this.pangkat = pangkat;
        this.paySalaryGrade = paySalaryGrade;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.upatedOn = upatedOn;
        this.code = code;
        this.overtime = overtime;
        this.pointMin = pointMin;
        this.pointMid = pointMid;
        this.pointMax = pointMax;
        this.ratioCompact = ratioCompact;
        this.updatedOn = updatedOn;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
    @JoinColumn(name = "pangkat_id")
    public Pangkat getPangkat() {
        return this.pangkat;
    }

    public void setPangkat(Pangkat pangkat) {
        this.pangkat = pangkat;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_salary_grade_id")
    public PaySalaryGrade getPaySalaryGrade() {
        return this.paySalaryGrade;
    }

    public void setPaySalaryGrade(PaySalaryGrade paySalaryGrade) {
        this.paySalaryGrade = paySalaryGrade;
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
    @Column(name = "upated_on", length = 19)
    public Date getUpatedOn() {
        return this.upatedOn;
    }

    public void setUpatedOn(Date upatedOn) {
        this.upatedOn = upatedOn;
    }

    @Column(name = "code", unique = true, nullable = false, length = 4)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "overtime")
    public Boolean getOvertime() {
        return this.overtime;
    }

    public void setOvertime(Boolean overtime) {
        this.overtime = overtime;
    }

    @Column(name = "point_min")
    public Integer getPointMin() {
        return this.pointMin;
    }

    public void setPointMin(Integer pointMin) {
        this.pointMin = pointMin;
    }

    @Column(name = "point_mid")
    public Integer getPointMid() {
        return this.pointMid;
    }

    public void setPointMid(Integer pointMid) {
        this.pointMid = pointMid;
    }

    @Column(name = "point_max")
    public Integer getPointMax() {
        return this.pointMax;
    }

    public void setPointMax(Integer pointMax) {
        this.pointMax = pointMax;
    }

    @Column(name = "ratio_compact", precision = 10, scale = 0)
    public BigDecimal getRatioCompact() {
        return this.ratioCompact;
    }

    public void setRatioCompact(BigDecimal ratioCompact) {
        this.ratioCompact = ratioCompact;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Transient
    public List<GolonganJabatan> getListGolonganJabatans() {
        return listGolonganJabatans;
    }

    public void setListGolonganJabatans(List<GolonganJabatan> listGolonganJabatans) {
        this.listGolonganJabatans = listGolonganJabatans;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="golonganJabatan")
    public Set<AppraisalCompetencyTypeGolJab> getAppraisalCompetencyTypeGolJabs() {
        return this.appraisalCompetencyTypeGolJabs;
    }
    
    public void setAppraisalCompetencyTypeGolJabs(Set<AppraisalCompetencyTypeGolJab> appraisalCompetencyTypeGolJabs) {
        this.appraisalCompetencyTypeGolJabs = appraisalCompetencyTypeGolJabs;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result
                + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result
                + ((overtime == null) ? 0 : overtime.hashCode());
        result = prime * result
                + ((updatedBy == null) ? 0 : updatedBy.hashCode());
        result = prime * result
                + ((updatedOn == null) ? 0 : updatedOn.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GolonganJabatan other = (GolonganJabatan) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (createdBy == null) {
            if (other.createdBy != null) {
                return false;
            }
        } else if (!createdBy.equals(other.createdBy)) {
            return false;
        }
        if (createdOn == null) {
            if (other.createdOn != null) {
                return false;
            }
        } else if (!createdOn.equals(other.createdOn)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (overtime == null) {
            if (other.overtime != null) {
                return false;
            }
        } else if (!overtime.equals(other.overtime)) {
            return false;
        }
        if (updatedBy == null) {
            if (other.updatedBy != null) {
                return false;
            }
        } else if (!updatedBy.equals(other.updatedBy)) {
            return false;
        }
        if (updatedOn == null) {
            if (other.updatedOn != null) {
                return false;
            }
        } else if (!updatedOn.equals(other.updatedOn)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }

}
