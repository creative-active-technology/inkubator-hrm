package com.inkubator.hrm.entity;
// Generated Jun 18, 2014 6:21:53 AM by Hibernate Tools 3.6.0


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * OccupationType generated by hbm2java
 */
@Entity
@Table(name="occupation_type"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="occupation_type_name") 
)
public class OccupationType  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String occupationTypeName;
     private String description;
     private Set<BioSertifikasi> bioSertifikasis = new HashSet<BioSertifikasi>(0);
     private List<OccupationType> listOccupationTypes = new ArrayList<>(0);

    public OccupationType() {
    }

	
    public OccupationType(long id) {
        this.id = id;
    }
    public OccupationType(long id, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String occupationTypeName, String description) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.occupationTypeName = occupationTypeName;
       this.description = description;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="occupation_type_name", unique=true, length=60)
    public String getOccupationTypeName() {
        return this.occupationTypeName;
    }
    
    public void setOccupationTypeName(String occupationTypeName) {
        this.occupationTypeName = occupationTypeName;
    }

    
     @Column(name="description",length=65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public List<OccupationType> getListOccupationTypes() {
        return listOccupationTypes;
    }

    public void setListOccupationTypes(List<OccupationType> listOccupationTypes) {
        this.listOccupationTypes = listOccupationTypes;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="occupationType")
    public Set<BioSertifikasi> getBioSertifikasis() {
        return this.bioSertifikasis;
    }
    
    public void setBioSertifikasis(Set<BioSertifikasi> bioSertifikasis) {
        this.bioSertifikasis = bioSertifikasis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.version);
        hash = 79 * hash + Objects.hashCode(this.createdBy);
        hash = 79 * hash + Objects.hashCode(this.createdOn);
        hash = 79 * hash + Objects.hashCode(this.updatedBy);
        hash = 79 * hash + Objects.hashCode(this.updatedOn);
        hash = 79 * hash + Objects.hashCode(this.occupationTypeName);
        hash = 79 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OccupationType other = (OccupationType) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        if (!Objects.equals(this.updatedBy, other.updatedBy)) {
            return false;
        }
        if (!Objects.equals(this.updatedOn, other.updatedOn)) {
            return false;
        }
        if (!Objects.equals(this.occupationTypeName, other.occupationTypeName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    


}


