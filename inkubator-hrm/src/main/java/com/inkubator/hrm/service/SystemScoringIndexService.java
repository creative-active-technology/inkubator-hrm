/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SystemScoringIndex;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface SystemScoringIndexService extends IService<SystemScoringIndex> {
    public List<SystemScoringIndex> getAllByParam(Long systemScoringId, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(Long systemScoringId) throws Exception;
    
    public void doChangerOrderScala(Long systemScoringId, int newGradeLevel, long oldId) throws Exception;
}
