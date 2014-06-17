/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.Jabatan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "jabatanDao")
@Lazy
public class JabatanDaoImpl extends IDAOImpl<Jabatan> implements JabatanDao {

    @Override
    public Class<Jabatan> getEntityClass() {
        return Jabatan.class;
    }

}
