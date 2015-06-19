/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import java.util.ArrayList;
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
    public @ResponseBody EmployeeRestHeader getAllData(){
    	EmployeeRestHeader header = new EmployeeRestHeader();
    	List<EmployeeRestModel> listEmpData = new ArrayList<EmployeeRestModel>();
    	try {
    		listEmpData = empDataService.getAllDataRestModel(StringUtils.EMPTY);
    		header.setStatus(0);
        	header.setErrorMessage("");
        	header.setNumberOfProfiles(listEmpData.size());
        	header.setProfiles(listEmpData);        		        	
        	
    	} catch (Exception ex) {
            LOGGER.error("error", ex);
            header.setStatus(1);
   			header.setErrorMessage(ex.getMessage());
        }
    	return header;
    }
    
    @RequestMapping(method = RequestMethod.GET, value ="/get_all/{nikOrName}")
    public @ResponseBody EmployeeRestHeader getAllData(@PathVariable String nikOrName){
    	EmployeeRestHeader header = new EmployeeRestHeader();
    	List<EmployeeRestModel> listEmpData = new ArrayList<EmployeeRestModel>();
    	try {
    		listEmpData = empDataService.getAllDataRestModel(nikOrName);
    		if(!listEmpData.isEmpty()){
    			header.setStatus(0);
            	header.setErrorMessage("");
            	header.setNumberOfProfiles(listEmpData.size());
            	header.setProfiles(listEmpData);
            	
    		} else {
    			header.setStatus(1);
        		header.setErrorMessage("Tidak terdapat data karyawan untuk nik/name : " + nikOrName);
        		header.setNumberOfProfiles(0);
    		}
    		        		        	
        	
    	} catch (Exception ex) {
            LOGGER.error("error", ex);
            header.setStatus(1);
   			header.setErrorMessage(ex.getMessage());
        }
    	return header;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/get_detail/{nik}")
    public @ResponseBody EmployeeRestHeader getDetailByNik(@PathVariable String nik) {
        EmployeeRestHeader header = new EmployeeRestHeader();
        try {
        	
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
        	
        } catch (Exception ex) {
        	LOGGER.error("error", ex);
           	header.setStatus(1);
   			header.setErrorMessage(ex.getMessage());
        }
        return header;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload_photo/{nik}")
    public @ResponseBody EmployeeRestHeader updatePhoto(@PathVariable String nik, @RequestParam("file") MultipartFile file) {
        EmployeeRestHeader header = new EmployeeRestHeader();
        try {
        	bioDataService.updatePhoto(nik, file);
        	header.setStatus(0);
    		header.setErrorMessage("");
    		
        } catch (Exception ex) {
            LOGGER.error("error", ex);
            header.setStatus(1);
    		header.setErrorMessage(ex.getMessage());
         }
        return header;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload_signature/{nik}")
    public @ResponseBody EmployeeRestHeader updateSignature(@PathVariable String nik, @RequestParam("file") MultipartFile file) {
        EmployeeRestHeader header = new EmployeeRestHeader();
        try {
        	bioDataService.updateSignature(nik, file);
        	header.setStatus(0);
    		header.setErrorMessage("");
    		
        } catch (Exception ex) {
            LOGGER.error("error", ex);
            header.setStatus(1);
    		header.setErrorMessage(ex.getMessage());
         }
        return header;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload_finger_print/{nik}")
    public @ResponseBody EmployeeRestHeader updateFingerPrint(@PathVariable String nik, @RequestParam("file") MultipartFile file) {
        EmployeeRestHeader header = new EmployeeRestHeader();
        try {
        	bioDataService.updateFingerPrint(nik, file);
        	header.setStatus(0);
    		header.setErrorMessage("");
    		
        } catch (Exception ex) {
            LOGGER.error("error", ex);
            header.setStatus(1);
    		header.setErrorMessage(ex.getMessage());
         }
        return header;
    }

}
