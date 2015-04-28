/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.SystemLetterReference;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "systemLetterReferenceDao")
@Lazy
public class SystemLetterReferenceDaoImpl extends IDAOImpl<SystemLetterReference> implements SystemLetterReferenceDao {

    @Override
    public Class<SystemLetterReference> getEntityClass() {
        return SystemLetterReference.class;
    }
    
}
