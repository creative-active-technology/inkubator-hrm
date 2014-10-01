/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.NeracaCuti;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "neracaCutiDao")
@Lazy
public class NeracaCutiDaoImpl extends IDAOImpl<NeracaCuti> implements NeracaCutiDao {

    @Override
    public Class<NeracaCuti> getEntityClass() {
        return NeracaCuti.class;
    }

    @Override
    public void saveBacth(List<NeracaCuti> data) {
       int counter = 0;
        for (NeracaCuti neracaCuti : data) {
            getCurrentSession().save(neracaCuti);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

}
