package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.JabatanProfesiDao;
import com.inkubator.hrm.entity.JabatanProfesi;
import com.inkubator.hrm.service.JabatanProfesiService;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "jabatanProfesiService")
@Lazy
public class JabatanProfesiServiceImpl extends IServiceImpl implements JabatanProfesiService {
	
	@Autowired
	private JabatanProfesiDao jabatanProfesiDao;

	@Override
	public void delete(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JabatanProfesi> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllDataPageAble(int arg0, int arg1,
			Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JabatanProfesi> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JabatanProfesi getEntiyByPK(Long arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JabatanProfesi saveData(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JabatanProfesi saveOrUpdateData(JabatanProfesi arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JabatanProfesi updateData(JabatanProfesi arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public List<JabatanProfesi> getAllDataByJabatanId(Long jabatanId) throws Exception {
		return jabatanProfesiDao.getAllDataByJabatanId(jabatanId);
	}

}
