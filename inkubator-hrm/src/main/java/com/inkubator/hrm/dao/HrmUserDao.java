/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface HrmUserDao extends IDAO<HrmUser> {

    public HrmUser getByUserName(String userName);

    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter);

    public HrmUser getByEmailAddress(String emailAddress);

    public void saveAndMerge(HrmUser hrmUser);

    public HrmUser getByUserIdOrEmail(String param);
}
