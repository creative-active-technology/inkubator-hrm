/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.web.search.EducationHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface EducationHistoryDao extends IDAO<EducationHistory>{
    public List<EducationHistory> getByParam(EducationHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEducationHistoryByParam(EducationHistorySearchParameter searchParameter);
    
    public EducationHistory getAllDataByPK(Long id);
}
