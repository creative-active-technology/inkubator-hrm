/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.service;

import java.util.Map;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpDataService extends IService<EmpData>{
    
	public Map<String, Long> getTotalByGender() throws Exception;
	
	public Map<String, Long> getTotalByAge() throws Exception;
	
	public Map<String, Long> getTotalByDepartment() throws Exception;
}
