/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MecineFingerDao;
import com.inkubator.hrm.entity.MecineFinger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "mecineFingerDao")
@Lazy
public class MecineFingerDaoImpl extends IDAOImpl<MecineFinger> implements MecineFingerDao {

    @Override
    public Class<MecineFinger> getEntityClass() {
        return MecineFinger.class;
    }

}
