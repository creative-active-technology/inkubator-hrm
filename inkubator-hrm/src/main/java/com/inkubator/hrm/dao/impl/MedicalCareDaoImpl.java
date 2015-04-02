package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.web.lazymodel.MedicalCareLazyDataModel;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "medicalCareDao")
@Lazy
public class MedicalCareDaoImpl extends IDAOImpl<MedicalCare> implements MedicalCareDao {

    @Override
    public Class<MedicalCare> getEntityClass() {
        return MedicalCare.class;
    }

    @Override
    public List<MedicalCare> getByParam(MedicalCareSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMedicalCareByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalMedicalCareByParam(MedicalCareSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMedicalCareByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchMedicalCareByParam(MedicalCareSearchParameter parameter, Criteria criteria) {
        if (parameter.getEmployeeName() != null) {
            criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
            criteria.createAlias("empData.bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("ed.nik", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);

        }
        
        if (parameter.getJabatan() != null) {
            criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
            criteria.createAlias("ed.jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jabatan.name", parameter.getJabatan(), MatchMode.ANYWHERE));

        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public MedicalCare getEntityWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("disease", FetchMode.JOIN);
        criteria.setFetchMode("hospital", FetchMode.JOIN);
        return (MedicalCare) criteria.uniqueResult();
    }

    @Override
    public List<MedicalCare> getAllDataWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("disease", FetchMode.JOIN);
        criteria.setFetchMode("hospital", FetchMode.JOIN);
        return criteria.list();
    }

}
