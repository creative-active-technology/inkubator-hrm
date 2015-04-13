package com.inkubator.hrm.web.model;
import java.io.Serializable;
/**
 *
 * @author WebGenX
 */
public class PaCompetenciesModel implements Serializable{
private Boolean isActive;
private Long id;
private Integer modelCompentency;
private int typeOfCompetency;
private String description;
private String name;
private String code;
public Boolean getIsActive(){
return isActive;
}
public Long getId(){
return id;
}
public Integer getModelCompentency(){
return modelCompentency;
}
public int getTypeOfCompetency(){
return typeOfCompetency;
}
public String getDescription(){
return description;
}
public String getName(){
return name;
}
public String getCode(){
return code;
}
public void setIsActive(Boolean isActive){
this.isActive = isActive;
}
public void setId(Long id){
this.id = id;
}
public void setModelCompentency(Integer modelCompentency){
this.modelCompentency = modelCompentency;
}
public void setTypeOfCompetency(int typeOfCompetency){
this.typeOfCompetency = typeOfCompetency;
}
public void setDescription(String description){
this.description = description;
}
public void setName(String name){
this.name = name;
}
public void setCode(String code){
this.code = code;
}
}
