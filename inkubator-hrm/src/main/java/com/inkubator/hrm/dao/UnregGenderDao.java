/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnregEmpType;
import com.inkubator.hrm.entity.UnregGender;

/**
 *
 * @author Deni
 */
public interface UnregGenderDao extends IDAO<UnregGender>{
	public List<UnregGender> getAllDataByUnregSalaryId(Long unregSalaryId);
}
