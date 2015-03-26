package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author WebGenX
 */
public class OrgTypeOfSpecModel implements Serializable {

    private Long id;
    private String description;
    private String name;
    private String code;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
