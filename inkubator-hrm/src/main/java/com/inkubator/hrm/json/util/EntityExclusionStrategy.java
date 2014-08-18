package com.inkubator.hrm.json.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 *
 * @author rizkykojek
 */
public class EntityExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		Type type = f.getDeclaredType();
		
		//this will skip Set<T>, List<T> etc, that will cause error when parsing to json 
		if(type instanceof ParameterizedType){
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		clazz.getFields();
		return false;

	}

}
