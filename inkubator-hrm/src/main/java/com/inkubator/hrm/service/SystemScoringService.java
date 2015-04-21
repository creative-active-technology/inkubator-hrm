/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.web.search.SystemScoringSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface SystemScoringService extends IService<SystemScoring> {
    public List<SystemScoring> getByParam(SystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(SystemScoringSearchParameter searchParameter) throws Exception;
}
