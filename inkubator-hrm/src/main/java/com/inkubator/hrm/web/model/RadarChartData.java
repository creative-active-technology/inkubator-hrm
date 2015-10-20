package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class RadarChartData implements Serializable {
	private String[] labels;
	private RadarDataset[] datasets;

	
	public RadarChartData(int size){
		labels =  new String[size];
	}
	
	public void addLabel(int i, String label) {
		labels[i] = label;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public RadarDataset[] getDatasets() {
		return datasets;
	}

	public void setDatasets(RadarDataset[] datasets) {
		this.datasets = datasets;
	}

}
