/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FamilyRelation;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface FamilyRelationService extends IService<FamilyRelation> {

    public List<FamilyRelation> getByParam(String parameter, int firstResult, int maxResults, Order ordertable) throws Exception;

    public Long getTotalFamilyRelationByParam(String parameter) throws Exception;

    public Long getTotalByName(String name) throws Exception;

    public Long getTotalByNameAndNotId(String name, Long id) throws Exception;
}
