package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni Husni FR
 */
public class CheckInOutModel implements Serializable {

    private Long id;
    private Long empId;
    private String userName;
    private Date jadwalKerja;
    private Date beginHour;
    private Date endHour;
    private String breakTime;
    private Date timeCheckIn;
    private Date timeCheckOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJadwalKerja() {
        return jadwalKerja;
    }

    public void setJadwalKerja(Date jadwalKerja) {
        this.jadwalKerja = jadwalKerja;
    }

   

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public Date getTimeCheckIn() {
        return timeCheckIn;
    }

    public void setTimeCheckIn(Date timeCheckIn) {
        this.timeCheckIn = timeCheckIn;
    }

    public Date getTimeCheckOut() {
        return timeCheckOut;
    }

    public void setTimeCheckOut(Date timeCheckOut) {
        this.timeCheckOut = timeCheckOut;
    }

    public Date getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Date beginHour) {
        this.beginHour = beginHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }
    
    
    
    
   

   
    

}
