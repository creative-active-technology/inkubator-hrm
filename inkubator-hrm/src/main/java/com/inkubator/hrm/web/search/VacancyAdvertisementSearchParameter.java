package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author rizkykojek
 */
public class VacancyAdvertisementSearchParameter extends SearchParameter {

	private String vacancyAdvertisementCode;
    private String advertisementMediaName;
    private String advertisementCategoryName;

	public String getVacancyAdvertisementCode() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "vacancyAdvertisementCode")) {
			vacancyAdvertisementCode = getParameter();
        } else {
        	vacancyAdvertisementCode = null;
        }
		return vacancyAdvertisementCode;
	}

	public void setVacancyAdvertisementCode(String vacancyAdvertisementCode) {
		this.vacancyAdvertisementCode = vacancyAdvertisementCode;
	}

	public String getAdvertisementMediaName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "advertisementMediaName")) {
			advertisementMediaName = getParameter();
        } else {
        	advertisementMediaName = null;
        }
		return advertisementMediaName;
	}

	public void setAdvertisementMediaName(String advertisementMediaName) {
		this.advertisementMediaName = advertisementMediaName;
	}

	public String getAdvertisementCategoryName() {
		if (StringUtils.equalsIgnoreCase(getKeyParam(), "advertisementCategoryName")) {
			advertisementCategoryName = getParameter();
        } else {
        	advertisementCategoryName = null;
        }
		return advertisementCategoryName;
	}

	public void setAdvertisementCategoryName(String advertisementCategoryName) {
		this.advertisementCategoryName = advertisementCategoryName;
	}
    
}
