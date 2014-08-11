/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.EmpData;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "empDataDao")
@Lazy
public class EmpDataDaoImpl extends IDAOImpl<EmpData> implements EmpDataDao{

    
    @Override
    public Class<EmpData> getEntityClass() {
      return EmpData.class;
    }
    
}
