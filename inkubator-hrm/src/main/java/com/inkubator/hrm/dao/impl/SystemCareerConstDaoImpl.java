/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SystemCareerConstDao;
import com.inkubator.hrm.entity.SystemCareerConst;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "systemCarreerConstDao")
@Lazy
public class SystemCareerConstDaoImpl extends IDAOImpl<SystemCareerConst> implements SystemCareerConstDao {

    @Override
    public Class<SystemCareerConst> getEntityClass() {
        return SystemCareerConst.class;
    }
    
}
