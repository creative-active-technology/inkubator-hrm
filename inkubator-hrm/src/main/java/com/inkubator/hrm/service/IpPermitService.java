/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
import org.hibernate.criterion.Order;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface IpPermitService extends IService<IpPermit> {

    public List<IpPermit> getByIpHeader(int ipHeader) throws Exception;

    public List<IpPermit> getByParam(IpPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalIpPermitByParam(IpPermitSearchParameter searchParameter) throws Exception;

    public Long getByIpPermitLocation(String location) throws Exception;
    
    public List<IpPermit> getByIpHeader(int ipHeader) throws Exception;
}
