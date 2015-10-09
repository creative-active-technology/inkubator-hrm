package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OrgTypeOfSpecListKlasifikasiDao;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;

@Repository(value = "orgTypeOfSpecListKlasifikasiDao")
@Lazy
public class OrgTypeOfSpecListKlasifikasiDaoImpl extends IDAOImpl<OrgTypeOfSpecListKlasifikasi> implements OrgTypeOfSpecListKlasifikasiDao{

	@Override
	public Class<OrgTypeOfSpecListKlasifikasi> getEntityClass() {
		return OrgTypeOfSpecListKlasifikasi.class;
	}

	@Override
	public List<OrgTypeOfSpecListKlasifikasi> getByorgTypeOfSpecListId(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("orgTypeOfSpecList", "orgTypeOfSpecList");
        criteria.add(Restrictions.eq("orgTypeOfSpecList.id", id));
        criteria.addOrder(Order.desc("klasifikasiKerja"));
        criteria.setFetchMode("klasifikasiKerja", FetchMode.JOIN);
        return criteria.list();
	}

}
