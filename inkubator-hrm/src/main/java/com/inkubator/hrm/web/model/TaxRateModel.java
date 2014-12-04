/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class TaxRateModel implements Serializable{
	
    private Long id;
    private Double lowRate;
    private Double topRate;
    private Double percentRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Double getLowRate() {
		return lowRate;
	}

	public void setLowRate(Double lowRate) {
		this.lowRate = lowRate;
	}

	public Double getTopRate() {
		return topRate;
	}

	public void setTopRate(Double topRate) {
		this.topRate = topRate;
	}

	public Double getPercentRate() {
		return percentRate;
	}

	public void setPercentRate(Double percentRate) {
		this.percentRate = percentRate;
	}
    
}
