/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.json.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class DateJsonDeserializer implements JsonDeserializer<Date>{

    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss a");
         SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
         Date date = new Date();
         try {
            date = je == null ? null : dateFormat.parse(je.getAsString());
         } catch (ParseException e) {
          // TODO Auto-generated catch block
            try {
              date = timeFormat.parse(je.getAsString());
            } catch (ParseException e1) {
             // TODO Auto-generated catch block
              e1.printStackTrace();
            }
         }
         return date;
    }
}
