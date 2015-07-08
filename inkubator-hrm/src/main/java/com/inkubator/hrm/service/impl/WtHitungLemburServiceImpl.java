package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.WtHitungLemburDao;
import com.inkubator.hrm.entity.WtHitungLembur;
import com.inkubator.hrm.service.WtHitungLemburService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "wtHitungLemburService")
@Lazy
public class WtHitungLemburServiceImpl extends IServiceImpl implements WtHitungLemburService {
    
        @Autowired
        private WtHitungLemburDao wtHitungLemburDao;

	@Override
	public void delete(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
        @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<WtHitungLembur> getAllData() throws Exception {
            return wtHitungLemburDao.getAllData();
	}

	@Override
	public List<WtHitungLembur> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllDataPageAble(int arg0, int arg1,
			Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtHitungLembur> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WtHitungLembur getEntiyByPK(Long arg0) throws Exception {
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
	public void save(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLembur saveData(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLembur saveOrUpdateData(WtHitungLembur arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WtHitungLembur updateData(WtHitungLembur arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
