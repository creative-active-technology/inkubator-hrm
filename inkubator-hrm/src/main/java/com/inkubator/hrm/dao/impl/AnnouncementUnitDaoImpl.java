package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AnnouncementUnitDao;
import com.inkubator.hrm.entity.AnnouncementUnit;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "announcementUnitDao")
@Lazy
public class AnnouncementUnitDaoImpl extends IDAOImpl<AnnouncementUnit>
		implements AnnouncementUnitDao {

	@Override
	public Class<AnnouncementUnit> getEntityClass() {
		return AnnouncementUnit.class;
	}

	
}
