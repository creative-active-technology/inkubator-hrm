/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface IpPermitDao extends IDAO<IpPermit>{
    public List<IpPermit> getByParam(IpPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalIpPermitByParam(IpPermitSearchParameter searchParameter);

    public Long getByIpPermitLocation(String location);
}
