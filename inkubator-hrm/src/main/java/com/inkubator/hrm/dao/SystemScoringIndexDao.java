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
    public List<SystemScoringIndex> getByParam(int firstResult, int maxResults, Order order);

    public Long getTotalByParam();
    
    public Long getTotalBylabelMask(String labelMask);
    
    public Long getTotalBylabelMaskAndNotId(String labelMask, Long id);
    
    public Long getTotalByValue(Integer value);
    
    public Long getTotalByValueAndNotId(Integer value, Long id);
    
    public Integer getLastOrderScala();
    
    public SystemScoringIndex getByGradeNumber(int number);
}

