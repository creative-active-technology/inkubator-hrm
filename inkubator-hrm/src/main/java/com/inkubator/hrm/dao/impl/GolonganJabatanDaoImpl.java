/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.entity.GolonganJabatan;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "golonganJabatanDao")
public class GolonganJabatanDaoImpl extends IDAOImpl<GolonganJabatan> implements GolonganJabatanDao {

    @Override
    public Class<GolonganJabatan> getEntityClass() {
        return GolonganJabatan.class;
    }

}
