package com.inkubator.hrm.json.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 *
 * @author rizkykojek
 */
public class HibernateProxyIdOnlyTypeAdapter extends TypeAdapter<HibernateProxy> {
	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) new HibernateProxyIdOnlyTypeAdapter() : null);
        }
    };

    @Override
    public HibernateProxy read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    
    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        // Get a filled instance of the original class
        //Object unproxiedValue = value.getHibernateLazyInitializer().getImplementation();        
        
        try {
        	out.beginObject();
			out.name("id").value(BeanUtils.getProperty(value, "id"));
			out.endObject();
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			out.nullValue();
            return;
		}        
    }

}
