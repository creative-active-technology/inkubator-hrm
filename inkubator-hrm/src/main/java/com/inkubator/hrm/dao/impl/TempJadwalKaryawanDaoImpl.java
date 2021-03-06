/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

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

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import org.hibernate.transform.Transformers;

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
            getCurrentSession().saveOrUpdate(jadwalKaryawan);
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
        criteria.add(Restrictions.le("tanggalWaktuKerja", date));
        return criteria.list();

    }

    @Override
    public TempJadwalKaryawan getEntityByEmpDataIdAndTanggalWaktuKerja(Long id, Date implementationDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", id));
        criteria.add(Restrictions.eq("tanggalWaktuKerja", implementationDate));
        criteria.setFetchMode("wtWorkingHour", FetchMode.JOIN);
        return (TempJadwalKaryawan) criteria.uniqueResult();
    }

    @Override
    public void saveOrUpdateAndMerge(TempJadwalKaryawan jadwalKaryawan) {
        getCurrentSession().saveOrUpdate(jadwalKaryawan);
        getCurrentSession().flush();
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDate(Long empDataId, Date startDate, Date endDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.ge("tanggalWaktuKerja", startDate));
        criteria.add(Restrictions.le("tanggalWaktuKerja", endDate));
        return criteria.list();
    }

    @Override
    public TempJadwalKaryawan getByEmpId(Long id, Date implementationDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", id));
        criteria.add(Restrictions.eq("tanggalWaktuKerja", implementationDate));
        criteria.setFetchMode("wtWorkingHour", FetchMode.JOIN);
        return (TempJadwalKaryawan) criteria.uniqueResult();
    }

    @Override
    public List<TempJadwalKaryawan> getByMonthDif(int value) {
        Date dateUntil = new Date();
        Date dateFrom = DateTimeUtil.getDateFrom(dateUntil, -value, CommonUtilConstant.DATE_FORMAT_MONTH);

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.lt("tanggalWaktuKerja", dateFrom));
        return criteria.list();
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDateAndNotOffDay(Long empDataId, Date startDate, Date endDate) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.ge("tanggalWaktuKerja", startDate));
        criteria.add(Restrictions.le("tanggalWaktuKerja", endDate));
        criteria.createAlias("wtWorkingHour", "wtWorkingHour", JoinType.INNER_JOIN);
        criteria.add(Restrictions.ne("wtWorkingHour.code", "OFF"));
        return criteria.list();
    }

    @Override
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetailWithFromAndUntilPeriod(long empId, Date from, Date until) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("e.id", empId));
        criteria.add(Restrictions.ge("tanggalWaktuKerja", from));
        criteria.add(Restrictions.le("tanggalWaktuKerja", until));
        criteria.setFetchMode("wtWorkingHour.attendanceStatus", FetchMode.JOIN);
        criteria.setFetchMode("wtWorkingHour", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tanggalWaktuKerja"));
        return criteria.list();
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDateAndOffDay(
            Long empDataid, Date startDate, Date endDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataid));
        criteria.add(Restrictions.ge("tanggalWaktuKerja", startDate));
        criteria.add(Restrictions.le("tanggalWaktuKerja", endDate));
        criteria.createAlias("wtWorkingHour", "wtWorkingHour", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("wtWorkingHour.code", "OFF"));
        return criteria.list();
    }

    @Override
    public Long getTotalByTanggalWaktuKerja(Date date, Long companyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("tanggalWaktuKerja", date));
        criteria.add(Restrictions.eq("company.id", companyId));
        criteria.add(Restrictions.not(Restrictions.eq("empData.status", HRMConstant.EMP_TERMINATION)));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataByTanggalWaktuKerjaAndCompanyId(Date tanggalWaktuKerja, Long companyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("tanggalWaktuKerja", tanggalWaktuKerja));
        criteria.add(Restrictions.eq("company.id", companyId));
        return criteria.list();
    }

    @Override
    public void deleteTempJadwalBeforeTMB() {
        final StringBuilder query = new StringBuilder("DELETE j.* FROM temp_jadwal_karyawan j ");
        query.append("LEFT join emp_data d on j.emp_id=d.id ");
        query.append("where j.tanggal_waktu_kerja < d.join_date");
        getCurrentSession().createSQLQuery(query.toString()).executeUpdate();
    }

}
