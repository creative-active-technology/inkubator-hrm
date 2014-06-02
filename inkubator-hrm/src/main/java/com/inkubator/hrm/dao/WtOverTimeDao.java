/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtOverTimeDao extends IDAO<WtOverTime> {

    public List<WtOverTime> getByParam(WtOverTimeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalWtOverTimeByParam(WtOverTimeSearchParameter searchParameter);
}
