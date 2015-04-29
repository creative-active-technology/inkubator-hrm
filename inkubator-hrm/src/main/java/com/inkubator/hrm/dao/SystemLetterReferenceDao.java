/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;


import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;

/**
 *
 * @author Deni
 */
public interface SystemLetterReferenceDao extends IDAO<SystemLetterReference>{

    public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter searchParameter);
}
