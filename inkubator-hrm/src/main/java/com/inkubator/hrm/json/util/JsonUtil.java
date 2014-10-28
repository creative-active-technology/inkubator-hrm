package com.inkubator.hrm.json.util;

import com.google.gson.GsonBuilder;

/**
 *
 * @author rizkykojek
 */
public class JsonUtil {

	public static GsonBuilder getHibernateEntityGsonBuilder(){
		GsonBuilder gsonBuilder = new GsonBuilder();
    	gsonBuilder.serializeNulls();
		gsonBuilder.setDateFormat("dd MMMM yyyy hh:mm:ss a");
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyIdOnlyTypeAdapter.FACTORY);
		gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
		return gsonBuilder;
	}
}
