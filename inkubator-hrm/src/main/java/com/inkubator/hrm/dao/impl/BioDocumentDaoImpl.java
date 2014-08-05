package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioDocumentDao;
import com.inkubator.hrm.entity.BioDocument;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "bioDocumentDao")
@Lazy
public class BioDocumentDaoImpl extends IDAOImpl<BioDocument> implements BioDocumentDao {

	@Override
	public Class<BioDocument> getEntityClass() {
		return BioDocument.class;		
	}

	@Override
	public List<BioDocument> getAllDataByBioDataId(Long bioDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("bioData.id", bioDataId));
		return criteria.list();
	}
		
}
