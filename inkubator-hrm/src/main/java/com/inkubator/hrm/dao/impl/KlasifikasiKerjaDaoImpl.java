package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.KlasifikasiKerjaDao;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.web.search.KlasifikasiKerjaSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "klasifikasiKerjaDao")
@Lazy
public class KlasifikasiKerjaDaoImpl extends IDAOImpl<KlasifikasiKerja> implements KlasifikasiKerjaDao {

    @Override
    public Class<KlasifikasiKerja> getEntityClass() {
        return KlasifikasiKerja.class;
    }

    @Override
    public List<KlasifikasiKerja> getByParam(KlasifikasiKerjaSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchKlasifikasiKerjaByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalKlasifikasiKerjaByParam(KlasifikasiKerjaSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchKlasifikasiKerjaByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchKlasifikasiKerjaByParam(KlasifikasiKerjaSearchParameter parameter, Criteria criteria) {
        if (parameter.getKlasifikasiKerjaCode() != null) {
            criteria.add(Restrictions.like("klasifikasiKerjaCode", parameter.getKlasifikasiKerjaCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getKlasifikasiKerjaName()!= null) {
            criteria.add(Restrictions.like("klasifikasiKerjaName", parameter.getKlasifikasiKerjaName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("klasifikasiKerjaCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("klasifikasiKerjaCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
