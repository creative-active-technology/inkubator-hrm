package com.inkubator.hrm.web.model;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author rizkykojek
 */
public class SpecAbilityModel implements Serializable {

	private Long id;
	private String name;
	private Integer totalOption;
	private String options[];
	private String scaleValue[];
	
	public SpecAbilityModel(){
		options = new String[20];
		scaleValue = new String[20];
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTotalOption() {
		return totalOption;
	}
	public void setTotalOption(Integer totalOption) {
		this.totalOption = totalOption;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = ArrayUtils.removeAll(this.options, 0, 19);		
		this.options = ArrayUtils.addAll(options, this.options);
		
	}
	public String[] getScaleValue() {
		return scaleValue;
	}
	public void setScaleValue(String[] scaleValue) {
		this.scaleValue = ArrayUtils.removeAll(this.scaleValue, 0, 19);
		this.scaleValue = ArrayUtils.addAll(scaleValue, this.scaleValue);
	}
	
	
	
	
}
