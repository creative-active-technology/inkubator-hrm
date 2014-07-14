/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.entity.BioData;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "bioDataDao")
@Lazy
public class BioDataDaoImpl extends IDAOImpl<BioData> implements BioDataDao {

    @Override
    public Class<BioData> getEntityClass() {
        return BioData.class;
    }

}
