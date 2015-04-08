package com.inkubator.hrm.web.model;
import java.io.Serializable;
/**
 *
 * @author WebGenX
 */
public class OrgJobFamilyLevelModel implements Serializable{
private Long id;
private Integer pointScore;
public Long getId(){
return id;
}
public Integer getPointScore(){
return pointScore;
}
public void setId(Long id){
this.id = id;
}
public void setPointScore(Integer pointScore){
this.pointScore = pointScore;
}
}
