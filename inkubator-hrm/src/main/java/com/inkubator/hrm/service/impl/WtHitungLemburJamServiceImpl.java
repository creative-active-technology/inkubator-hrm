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
import com.inkubator.hrm.dao.WtHitungLemburJamDao;
import com.inkubator.hrm.entity.WtHitungLemburJam;
import com.inkubator.hrm.service.WtHitungLemburJamService;

@Service(value = "wtHitungLemburJamService")
@Lazy
public class WtHitungLemburJamServiceImpl extends IServiceImpl implements WtHitungLemburJamService{
	
	@Autowired
	private WtHitungLemburJamDao wtHitungLemburJamDao;

	@Override
	public void delete(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WtHitungLemburJam> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllDataPageAble(int arg0, int arg1,
			Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Boolean arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Integer arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLemburJam> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Byte arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLemburJam getEntiyByPK(Long arg0) throws Exception {
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
	public void save(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLemburJam saveData(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLemburJam saveOrUpdateData(WtHitungLemburJam arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(WtHitungLemburJam arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLemburJam updateData(WtHitungLemburJam arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<WtHitungLemburJam> getListByWtHitungLemburId(Long wtHitungLemburId) throws Exception {
		return wtHitungLemburJamDao.getListByWtHitungLemburId(wtHitungLemburId);
	}

}
