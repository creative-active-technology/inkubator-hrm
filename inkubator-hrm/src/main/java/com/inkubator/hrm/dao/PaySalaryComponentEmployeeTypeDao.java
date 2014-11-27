/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface PaySalaryComponentEmployeeTypeDao extends IDAO<PaySalaryEmpType> {
    public List<PaySalaryEmpType> getEntityByPaySalaryComponentId(Long id);

}
