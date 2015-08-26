package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanProfesiDao;
import com.inkubator.hrm.entity.JabatanProfesi;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value = "jabatanProfesiDao")
@Lazy
public class JabatanProfesiDaoImpl extends IDAOImpl<JabatanProfesi> implements JabatanProfesiDao {

	@Override
	public Class<JabatanProfesi> getEntityClass() {
		return JabatanProfesi.class;
	}

	@Override
	public List<JabatanProfesi> getAllDataByJabatanId(Long jabatanId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jabatan");
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.add(Restrictions.eq("jabatan.id", jabatanId));
        return criteria.list();
	}

}
