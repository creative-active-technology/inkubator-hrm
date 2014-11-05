package com.inkubator.hrm.entity;
// Generated Nov 3, 2014 2:13:41 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompanyPolicyJabatan generated by hbm2java
 */
@Entity
@Table(name="company_policy_jabatan"
    ,catalog="hrm"
)
public class CompanyPolicyJabatan  implements java.io.Serializable {


     private CompanyPolicyJabatanId id;
     private CompanyPolicy companyPolicy;
     private GolonganJabatan golonganJabatan;
     private String description;

    public CompanyPolicyJabatan() {
    }

	
    public CompanyPolicyJabatan(CompanyPolicyJabatanId id, CompanyPolicy companyPolicy, GolonganJabatan golonganJabatan) {
        this.id = id;
        this.companyPolicy = companyPolicy;
        this.golonganJabatan = golonganJabatan;
    }
    public CompanyPolicyJabatan(CompanyPolicyJabatanId id, CompanyPolicy companyPolicy, GolonganJabatan golonganJabatan, String description) {
       this.id = id;
       this.companyPolicy = companyPolicy;
       this.golonganJabatan = golonganJabatan;
       this.description = description;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="companyPolicyId", column=@Column(name="company_policy_id", nullable=false) ), 
        @AttributeOverride(name="golJabId", column=@Column(name="gol_jab_id", nullable=false) ) } )
    public CompanyPolicyJabatanId getId() {
        return this.id;
    }
    
    public void setId(CompanyPolicyJabatanId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_policy_id", nullable=false, insertable=false, updatable=false)
    public CompanyPolicy getCompanyPolicy() {
        return this.companyPolicy;
    }
    
    public void setCompanyPolicy(CompanyPolicy companyPolicy) {
        this.companyPolicy = companyPolicy;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gol_jab_id", nullable=false, insertable=false, updatable=false)
    public GolonganJabatan getGolonganJabatan() {
        return this.golonganJabatan;
    }
    
    public void setGolonganJabatan(GolonganJabatan golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    
    @Column(name="description", length=500)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


