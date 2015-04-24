package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AnnouncementGoljabDao;
import com.inkubator.hrm.entity.AnnouncementGoljab;

/**
 *
 * @author rizkykojek
 */
@Repository( value = "announcementGoljabDao")
@Lazy
public class AnnouncementGoljabDaoImpl extends IDAOImpl<AnnouncementGoljab> implements AnnouncementGoljabDao {

	@Override
	public Class<AnnouncementGoljab> getEntityClass() {
		return AnnouncementGoljab.class;
	}
	
}
