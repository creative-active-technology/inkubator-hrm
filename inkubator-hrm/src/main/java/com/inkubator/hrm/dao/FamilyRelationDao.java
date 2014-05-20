/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FamilyRelation;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface FamilyRelationDao extends IDAO<FamilyRelation> {

    public List<FamilyRelation> getByParam(String parameter, int firstResult, int maxResults, Order ordertable);

    public Long getTotalFamilyRelationByParam(String parameter);

    public Long getTotalByName(String name);

    public Long getTotalByNameAndNotId(String name, Long id);

}
