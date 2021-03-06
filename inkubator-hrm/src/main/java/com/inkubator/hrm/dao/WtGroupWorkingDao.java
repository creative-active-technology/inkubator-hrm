/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtGroupWorkingDao extends IDAO<WtGroupWorking> {

    public List<WtGroupWorking> getByParam(WtGroupWorkingSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalWtGroupWorkingByParam(WtGroupWorkingSearchParameter searchParameter);

    public Long getTotalByCode(String code);

    public WtGroupWorking getByPKIdWithDetail(Long id);

    public WtGroupWorking getByCode(String code);

    public void saveAndMerge(WtGroupWorking groupWorking);

//    public List<WtGroupWorking> workingGroupIsAcive();

}
