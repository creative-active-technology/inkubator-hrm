/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ReimbursmentSchemaEmployeeTypeDao extends IDAO<ReimbursmentSchemaEmployeeType> {

    List<ReimbursmentSchemaEmployeeType> getByUserId(long id);
}
