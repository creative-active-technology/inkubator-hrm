/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SystemScoringIndex;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface SystemScoringIndexDao extends IDAO<SystemScoringIndex>{
    public List<SystemScoringIndex> getAllByParam(Long systemScoringId, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(Long systemScoringId);
    
    public Long getTotalBylabelMask(String labelMask);
    
    public Long getTotalBylabelMaskAndNotId(String labelMask, Long id);
    
    public Long getTotalByValue(Integer value);
    
    public Long getTotalByValueAndNotId(Integer value, Long id);
    
    public Integer getLastOrderScala(Long systemScoringId);
    
    public SystemScoringIndex getEntityBySystemScoringIdAndOrderScala(Long systemScoringId, int number);

	public List<SystemScoringIndex> getAllDataBySystemScoringIdAndGreaterOrderScala(Long systemScoringId, int orderScala);
}

