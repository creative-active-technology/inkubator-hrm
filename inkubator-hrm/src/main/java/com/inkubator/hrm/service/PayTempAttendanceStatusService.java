/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.PayTempAttendanceSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface PayTempAttendanceStatusService extends IService<PayTempAttendanceStatus>{
    
    public List<PayTempAttendanceStatus> getByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel, int firstResult, int maxResults, Order order) throws Exception;

    public List<PayTempAttendanceStatus> getByWtPeriodeWhereComponentPayrollIsActive(PayTempAttendanceStatusModel payTempAttendanceStatusModel) throws Exception;
    
    public Long getTotalResourceTypeByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel) throws Exception;
    
    public void executeBatchFileUpload(PayTempAttendanceStatusModel report) throws Exception;

    public String updateFileAndDeleteData(UploadedFile file) throws Exception;
}
