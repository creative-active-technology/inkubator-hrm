package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioAddressDao;
import com.inkubator.hrm.entity.BioAddress;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "bioAddressDao")
@Lazy
public class BioAddressDaoImpl extends IDAOImpl<BioAddress> implements BioAddressDao {

	@Override
	public Class<BioAddress> getEntityClass() {
		return BioAddress.class;		
	}
	
}
