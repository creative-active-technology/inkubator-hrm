/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.model.PayTempOvertimeFileModel;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface PayTempOvertimeService extends IService<PayTempOvertime>{

    public List<PayTempOvertime> getByParam(PayTempOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(PayTempOvertimeSearchParameter searchParameter) throws Exception;
    
    public void executeBatchFileUpload(PayTempOvertimeFileModel entity) throws Exception;
    
    public PayTempOvertime getEntityByPkWithDetail(Long id) throws Exception;
}
