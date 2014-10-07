/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OverTimeDistributionDao;
import com.inkubator.hrm.entity.OverTimeDistribution;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "overTimeDistributionDao")
@Lazy
public class OverTimeDistributionDaoImpl extends IDAOImpl<OverTimeDistribution> implements OverTimeDistributionDao{

    @Override
    public Class<OverTimeDistribution> getEntityClass() {
        return OverTimeDistribution.class;
    }
    
}
