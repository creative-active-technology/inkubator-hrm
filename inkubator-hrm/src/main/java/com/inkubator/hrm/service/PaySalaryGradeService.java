/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.web.search.PaySalaryGradeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PaySalaryGradeService extends IService<PaySalaryGrade>{
    public List<PaySalaryGrade> getByParam(PaySalaryGradeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalPaySalaryGradeByParam(PaySalaryGradeSearchParameter searchParameter) throws Exception;
    
    public PaySalaryGrade getByPaySalaryGradeId(Long id) throws Exception;
}
