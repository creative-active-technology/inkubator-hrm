/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.web.model.GroupWorkingModel;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtGroupWorkingService extends IService<WtGroupWorking> {

    public List<WtGroupWorking> getByParam(WtGroupWorkingSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtGroupWorkingByParam(WtGroupWorkingSearchParameter searchParameter) throws Exception;

    public void save(GroupWorkingModel model) throws Exception;

    public WtGroupWorking getByPKIdWithDetail(Long id) throws Exception;

    public WtGroupWorking getByCode(String code) throws Exception;

    public void update(GroupWorkingModel model) throws Exception;
}
