/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.web.search.SystemScoringSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface SystemScoringDao extends IDAO<SystemScoring>{
    public List<SystemScoring> getByParam(SystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(SystemScoringSearchParameter searchParameter);
}
