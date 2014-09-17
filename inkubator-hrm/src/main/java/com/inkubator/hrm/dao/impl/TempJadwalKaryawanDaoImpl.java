/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "tempJadwalKaryawanDao")
@Lazy
public class TempJadwalKaryawanDaoImpl extends IDAOImpl<TempJadwalKaryawan> implements TempJadwalKaryawanDao {

    @Override
    public Class<TempJadwalKaryawan> getEntityClass() {
        return TempJadwalKaryawan.class;
    }

    @Override
    public List<TempJadwalKaryawan> getAllByEmpId(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("e.id", empId));
        return criteria.list();
    }

    @Override
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("e.id", empId));
        criteria.setFetchMode("attendanceStatus", FetchMode.JOIN);
        criteria.setFetchMode("wtWorkingHour", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tanggalWaktuKerja"));
        return criteria.list();
    }

}
