/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Termination;
import com.inkubator.hrm.web.search.TerminationSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface TerminationDao extends IDAO<Termination>{
    public List<Termination> getAllDataWithAllRelation(TerminationSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalTerminationByParam(TerminationSearchParameter searchParameter);
    
    public Termination getEntityByPkWithAllRelation(Long id);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Long getByTerminationCode(String code);
}
