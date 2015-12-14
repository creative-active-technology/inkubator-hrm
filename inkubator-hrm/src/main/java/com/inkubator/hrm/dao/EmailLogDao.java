package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmailLog;

/**
 *
 * @author rizkykojek
 */
public interface EmailLogDao extends IDAO<EmailLog> {

	public List<EmailLog> getAllDataEmailNotSent();
	
}
