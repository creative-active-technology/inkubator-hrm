/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface PayTempAttendanceStatusDao extends IDAO<PayTempAttendanceStatus> {
    /*public List<PayTempAttendanceStatus> getByParam(String parameter, InclusionReimbursmentModel inclusionReimbursmentModel, int firstResult, int maxResults, Order order);
    
    public List<PayTempAttendanceStatus> getByWtPeriodeWhereComponentPayrollIsActive(InclusionReimbursmentModel inclusionReimbursmentModel);
    
    public Long getTotalResourceTypeByParam(String parameter, InclusionReimbursmentModel inclusionReimbursmentModel);*/
    
    public List<PayTempAttendanceStatus> getAllByNik(String nik);
}
