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
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.PayTempAttendanceSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface PayTempAttendanceStatusDao extends IDAO<PayTempAttendanceStatus> {
    public List<PayTempAttendanceStatus> getByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel model, int firstResult, int maxResults, Order order);
    
    public Long getTotalResourceTypeByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel model);
    
    public List<PayTempAttendanceStatus> getAllByNik(String nik);
}
