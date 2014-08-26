/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.web.search.PersonalDisciplineSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PersonalDisciplineService extends IService<PersonalDiscipline>{
    public List<PersonalDiscipline> getAllDataWithAllRelation(PersonalDisciplineSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalPersonalDisciplineByParam(PersonalDisciplineSearchParameter searchParameter) throws Exception;
    
    public PersonalDiscipline getEntityByPkWithAllRelation(Long id) throws Exception;
    
    
}