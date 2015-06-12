/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.EmployeeRestModel;
import com.inkubator.hrm.web.model.EmployeeRestHeader;

/**
 *
 * @author Deni Husni FR
 */
@Controller
@RequestMapping("employee")
public class EmployeeRestController {

    protected transient Logger LOGGER = Logger.getLogger(getClass());
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private EmpDataService empDataService;
    @Autowired
    private BioDataService bioDataService;
    
    @RequestMapping(method = RequestMethod.GET, value ="/get_all")
    public @ResponseBody String getAllData(){
    	String result = StringUtils.EMPTY;
    	try {
    		List<EmployeeRestModel> listEmpData = empDataService.getAllDataRestModel(StringUtils.EMPTY);
    		result = jsonConverter.getJson(listEmpData);
    	} catch (Exception ex) {
            LOGGER.error("error", ex);
        }
    	return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value ="/get_all/{nikOrName}")
    public @ResponseBody String getAllData(@PathVariable String nikOrName){
    	String result = StringUtils.EMPTY;
    	try {
    		List<EmployeeRestModel> listEmpData = empDataService.getAllDataRestModel(nikOrName);
    		result = jsonConverter.getJson(listEmpData);
    	} catch (Exception ex) {
            LOGGER.error("error", ex);
        }
    	return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/get_detail/{nik}")
    public @ResponseBody
    String getDetailByNik(@PathVariable String nik) {
        String result = StringUtils.EMPTY;
        try {
        	EmployeeRestHeader header = new EmployeeRestHeader();
        	EmployeeRestModel model = empDataService.getRestModelByNik(nik);
        	if(model != null) {
        		
        		header.setStatus(0);
        		header.setErrorMessage("");
        		header.setNumberOfProfiles(1);
        		header.setProfile(model);
        		
        		
        	} else {
        		header.setStatus(1);
        		header.setErrorMessage("Tidak terdapat data karyawan untuk nik : " + nik);
        		header.setNumberOfProfiles(0);
        	}
        	
        	result = jsonConverter.getJson(header);
        } catch (Exception ex) {
           LOGGER.error("error", ex);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload_photo/{nik}")
    public @ResponseBody
    String updatePhoto(@PathVariable String nik, @RequestParam("file") MultipartFile file) {
        String result = StringUtils.EMPTY;
        try {
        	bioDataService.updatePhoto(nik, file);
        } catch (Exception ex) {
            LOGGER.error("error", ex);
         }
        return result;
    }

}
