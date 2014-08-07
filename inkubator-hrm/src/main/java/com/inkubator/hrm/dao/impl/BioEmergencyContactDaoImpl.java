/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioEmergencyContactDao;
import com.inkubator.hrm.entity.BioEmergencyContact;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "bioEmergencyContactDao")
@Lazy
public class BioEmergencyContactDaoImpl extends IDAOImpl<BioEmergencyContact> implements BioEmergencyContactDao {

    @Override
    public Class<BioEmergencyContact> getEntityClass() {
        return BioEmergencyContact.class;
    }

}
