/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.entity.PayTempKalkulasi;

import java.util.List;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiDao")
@Lazy
public class PayTempKalkulasiDaoImpl extends IDAOImpl<PayTempKalkulasi> implements PayTempKalkulasiDao {

    @Override
    public Class<PayTempKalkulasi> getEntityClass() {
        return PayTempKalkulasi.class;
    }

    @Override
    public void saveBatch(List<PayTempKalkulasi> data) {
        int counter = 0;
        for (PayTempKalkulasi kalkulasi : data) {
            getCurrentSession().save(kalkulasi);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

	@Override
	public void deleteAllData() {
		Query query = getCurrentSession().createQuery("delete from PayTempKalkulasi");
        query.executeUpdate();
	}

}
