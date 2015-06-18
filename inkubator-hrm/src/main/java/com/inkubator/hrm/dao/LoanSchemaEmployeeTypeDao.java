/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanSchemaEmployeeType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface LoanSchemaEmployeeTypeDao extends IDAO<LoanSchemaEmployeeType> {

    List<LoanSchemaEmployeeType> getByUserId(long id);
    
    public List<LoanSchemaEmployeeType> getAllDataByEmpTypeId(Long empTypeId);
}
