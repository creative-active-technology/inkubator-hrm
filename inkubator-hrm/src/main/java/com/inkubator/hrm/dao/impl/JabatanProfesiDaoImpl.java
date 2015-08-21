package com.inkubator.hrm.dao.impl;

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

}
