/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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
        criteria.setFetchMode("wtWorkingHour.attendanceStatus", FetchMode.JOIN);
        criteria.setFetchMode("wtWorkingHour", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tanggalWaktuKerja"));
        return criteria.list();
    }

    @Override
    public List<TempJadwalKaryawan> getByGroupKerjadId(long kerjaId) {
//        Criteria criteria = getCurrentSession().createCriteria(EmpData.class);

        ProjectionList proList = Projections.projectionList();
//        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("id"));
        DetachedCriteria kelompokData = DetachedCriteria.forClass(EmpData.class)
                .createAlias("wtGroupWorking", "wt")
                .add(Restrictions.eq("wt.id", kerjaId))
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "em");
        criteria.add(Property.forName("em.id").in(kelompokData));
        return criteria.list();

    }

    @Override
    public void deleteBacth(List<TempJadwalKaryawan> jadwalKaryawans) {
        int counter = 0;
        for (TempJadwalKaryawan jadwalKaryawan : jadwalKaryawans) {
            getCurrentSession().delete(jadwalKaryawan);
            System.out.println(" Deleting data karyawan");
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public void saveBatch(List<TempJadwalKaryawan> jadwalKaryawans) {

        int counter = 0;
        for (TempJadwalKaryawan jadwalKaryawan : jadwalKaryawans) {
            getCurrentSession().save(jadwalKaryawan);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<TempJadwalKaryawan> getAllByMaxEndDate(Date date) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("tanggalWaktuKerja").max());
        proList.add(Projections.groupProperty("empData"));
        DetachedCriteria data = DetachedCriteria.forClass(getEntityClass())
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        String[] var = {"tanggalWaktuKerja", "empData"};
        criteria.add(Subqueries.propertiesIn(var, data));
        criteria.add(Restrictions.eq("tanggalWaktuKerja", date));
        return criteria.list();

    }

}
