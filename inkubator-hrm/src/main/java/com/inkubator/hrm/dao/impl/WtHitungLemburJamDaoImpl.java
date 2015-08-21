package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHitungLemburDao;
import com.inkubator.hrm.dao.WtHitungLemburJamDao;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHitungLembur;
import com.inkubator.hrm.entity.WtHitungLemburJam;

@Repository(value = "wtHitungLemburJamDao")
@Lazy
public class WtHitungLemburJamDaoImpl extends IDAOImpl<WtHitungLemburJam> implements WtHitungLemburJamDao {

	@Override
	public Class<WtHitungLemburJam> getEntityClass() {
		return WtHitungLemburJam.class;
	}

	@Override
	public List<WtHitungLemburJam> getListByWtHitungLemburId(Long wtHitungLemburId) {
		
		try {
			
			Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
			criteria.setFetchMode("wtHitungLembur", FetchMode.JOIN);
			criteria.add(Restrictions.eq("wtHitungLembur.id", wtHitungLemburId));        
			return criteria.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
