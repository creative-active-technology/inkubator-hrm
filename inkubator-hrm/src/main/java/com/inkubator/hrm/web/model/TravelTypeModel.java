package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class TravelTypeModel implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Long attendanceStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAttendanceStatusId() {
        return attendanceStatusId;
    }

    public void setAttendanceStatusId(Long attendanceStatusId) {
        this.attendanceStatusId = attendanceStatusId;
    }

}
