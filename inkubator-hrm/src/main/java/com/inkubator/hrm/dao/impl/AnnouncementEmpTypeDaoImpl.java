package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AnnouncementEmpTypeDao;
import com.inkubator.hrm.entity.AnnouncementEmpType;

/**
 *
 * @author rizkykojek
 */
@Repository( value = "announcementEmpTypeDao" )
@Lazy
public class AnnouncementEmpTypeDaoImpl extends IDAOImpl<AnnouncementEmpType>
		implements AnnouncementEmpTypeDao {

	@Override
	public Class<AnnouncementEmpType> getEntityClass() {
		return AnnouncementEmpType.class;
	}

	
}
