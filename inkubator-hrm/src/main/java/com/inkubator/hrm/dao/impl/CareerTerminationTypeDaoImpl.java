/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerTerminationTypeDao;
import com.inkubator.hrm.entity.CareerTerminationType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "careerTerminationTypeDao")
@Lazy
public class CareerTerminationTypeDaoImpl extends IDAOImpl<CareerTerminationType> implements CareerTerminationTypeDao {

    @Override
    public Class<CareerTerminationType> getEntityClass() {
        return CareerTerminationType.class;
    }
    
}
