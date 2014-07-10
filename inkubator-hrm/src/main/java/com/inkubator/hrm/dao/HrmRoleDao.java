/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface HrmRoleDao extends IDAO<HrmRole> {

    public List<HrmRole> getByParam(HrmRoleSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalHrmRoleByParam(HrmRoleSearchParameter searchParameter);

    public HrmRole getByRoleName(String name);

	public HrmRole getEntityByPkWithMenus(long id);

}
