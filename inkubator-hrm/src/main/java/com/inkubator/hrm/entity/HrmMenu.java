package com.inkubator.hrm.entity;
// Generated Jun 16, 2014 8:44:01 PM by Hibernate Tools 3.6.0

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
import javax.persistence.Version;

/**
 * HrmMenu generated by hbm2java
 */
@Entity
@Table(name = "hrm_menu", catalog="hrm_payroll_backup"
)
public class HrmMenu implements java.io.Serializable {

    private long id;
    private Integer version;
    private HrmMenu hrmMenu;
    private String name;
    private String urlName;
    private String iconName;
    private Integer menuLevel;
    private String menuStyle;
    private String menuStyleClass;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date upatedOn;
    private Set<HrmMenu> hrmMenus = new HashSet<HrmMenu>(0);
    private Set<HrmMenuRole> hrmMenuRoles = new HashSet<HrmMenuRole>(0);
    private Set<FavoriteMenu> favoriteMenus = new HashSet<FavoriteMenu>(0);

    public HrmMenu() {
    }

    public HrmMenu(long id) {
        this.id = id;
    }

    public HrmMenu(long id, HrmMenu hrmMenu, String name, String urlName, String iconName, Integer menuLevel, String createdBy, Date createdOn, String updatedBy, Date upatedOn, Set<HrmMenu> hrmMenus, Set<HrmMenuRole> hrmMenuRoles) {
        this.id = id;
        this.hrmMenu = hrmMenu;
        this.name = name;
        this.urlName = urlName;
        this.iconName = iconName;
        this.menuLevel = menuLevel;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.upatedOn = upatedOn;
        this.hrmMenus = hrmMenus;
        this.hrmMenuRoles = hrmMenuRoles;
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
    @JoinColumn(name = "parent_id")
    public HrmMenu getHrmMenu() {
        return this.hrmMenu;
    }

    public void setHrmMenu(HrmMenu hrmMenu) {
        this.hrmMenu = hrmMenu;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url_name", length = 45)
    public String getUrlName() {
        return this.urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Column(name = "icon_name", length = 45)
    public String getIconName() {
        return this.iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    @Column(name = "menu_level")
    public Integer getMenuLevel() {
        return this.menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hrmMenu")
    public Set<HrmMenu> getHrmMenus() {
        return this.hrmMenus;
    }

    public void setHrmMenus(Set<HrmMenu> hrmMenus) {
        this.hrmMenus = hrmMenus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hrmMenu")
    public Set<HrmMenuRole> getHrmMenuRoles() {
        return this.hrmMenuRoles;
    }

    public void setHrmMenuRoles(Set<HrmMenuRole> hrmMenuRoles) {
        this.hrmMenuRoles = hrmMenuRoles;
    }

    @Column(name = "menu_style", length = 1000)
    public String getMenuStyle() {
        return this.menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    @Column(name = "menu_style_class", length = 45)
    public String getMenuStyleClass() {
        return this.menuStyleClass;
    }

    public void setMenuStyleClass(String menuStyleClass) {
        this.menuStyleClass = menuStyleClass;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof HrmMenu)) {
            return false;
        } else {
            HrmMenu castOther = (HrmMenu) other;
            return this.getId() == castOther.getId();
        }
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="hrmMenu")
    public Set<FavoriteMenu> getFavoriteMenus() {
        return this.favoriteMenus;
    }
    
    public void setFavoriteMenus(Set<FavoriteMenu> favoriteMenus) {
        this.favoriteMenus = favoriteMenus;
    }

}
