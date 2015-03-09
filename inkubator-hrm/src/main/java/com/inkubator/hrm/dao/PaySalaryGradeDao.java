/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.web.search.PaySalaryGradeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PaySalaryGradeDao extends IDAO<PaySalaryGrade> {

    public List<PaySalaryGrade> getByParam(PaySalaryGradeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPaySalaryGradeByParam(PaySalaryGradeSearchParameter searchParameter);

    public PaySalaryGrade getByPaySalaryGradeId(Long id);

    public PaySalaryGrade getByGradeNumber(int number);
}
