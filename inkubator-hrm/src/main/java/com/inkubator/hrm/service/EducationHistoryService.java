/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.web.search.EducationHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface EducationHistoryService extends IService<EducationHistory>{
    public List<EducationHistory> getByParam(EducationHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalEducationHistoryByParam(EducationHistorySearchParameter searchParameter) throws Exception;
    
    public EducationHistory getAllDataByPK(Long id);
}
