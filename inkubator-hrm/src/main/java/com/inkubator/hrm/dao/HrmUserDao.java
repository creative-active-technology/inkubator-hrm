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

    public HrmUser getByUserId(String userId);

    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter);

    public HrmUser getByEmailAddress(String emailAddress);

    public void saveAndMerge(HrmUser hrmUser);

    public HrmUser getByUserIdOrEmail(String param);

    public Long getTotalByEmailAndNotUserId(String emailAddress, String userId);

    public HrmUser getEntityByPkWithDetail(Long id);

    public List<HrmUser> getByName(String name);

    public HrmUser getByEmpDataId(long empDataId);

    public List<HrmUser> getAllDataByNameOrNik(String param);

    public HrmUser getByEmailAddressInNotLock(String emailAddress);

    public HrmUser getUserWithDetailByUserId(String userId);
    
    public HrmUser getEntityByPhoneNumber(String phoneNumber);
 
}
