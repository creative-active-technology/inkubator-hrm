package com.inkubator.hrm.web.model;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author WebGenX
 */
public class AnnouncementLogModel implements Serializable{
private Long id;
private Boolean emailIsSend;
private Date dateSend;
private Boolean isAlreadyShow;
public Long getId(){
return id;
}
public Boolean getEmailIsSend(){
return emailIsSend;
}
public Date getDateSend(){
return dateSend;
}
public Boolean getIsAlreadyShow(){
return isAlreadyShow;
}
public void setId(Long id){
this.id = id;
}
public void setEmailIsSend(Boolean emailIsSend){
this.emailIsSend = emailIsSend;
}
public void setDateSend(Date dateSend){
this.dateSend = dateSend;
}
public void setIsAlreadyShow(Boolean isAlreadyShow){
this.isAlreadyShow = isAlreadyShow;
}
}
