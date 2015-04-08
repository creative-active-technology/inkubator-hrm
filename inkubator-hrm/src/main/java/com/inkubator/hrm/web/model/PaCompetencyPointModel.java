package com.inkubator.hrm.web.model;
import java.io.Serializable;
/**
 *
 * @author WebGenX
 */
public class PaCompetencyPointModel implements Serializable{
private Long id;
private int competencyPointScore;
private String description;
private String name;
private String code;
public Long getId(){
return id;
}
public int getCompetencyPointScore(){
return competencyPointScore;
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
public void setId(Long id){
this.id = id;
}
public void setCompetencyPointScore(int competencyPointScore){
this.competencyPointScore = competencyPointScore;
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
