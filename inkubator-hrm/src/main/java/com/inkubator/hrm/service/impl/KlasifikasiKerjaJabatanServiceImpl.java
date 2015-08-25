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
import com.inkubator.hrm.dao.KlasifikasiKerjaJabatanDao;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import com.inkubator.hrm.service.KlasifikasiKerjaJabatanService;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "klasifikasiKerjaJabatanService")
@Lazy
public class KlasifikasiKerjaJabatanServiceImpl extends IServiceImpl implements KlasifikasiKerjaJabatanService {
	
	@Autowired
    private KlasifikasiKerjaJabatanDao klasifikasiKerjaJabatanDao;

	@Override
	public void delete(KlasifikasiKerjaJabatan arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllData(Boolean arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllData(Integer arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllDataPageAble(int arg0, int arg1,
			Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Boolean arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Integer arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlasifikasiKerjaJabatan> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Byte arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(String arg0,
			Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(String arg0,
			Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Integer arg0,
			Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Integer arg0,
			Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KlasifikasiKerjaJabatan getEntiyByPK(Long arg0) throws Exception {
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
	public void save(KlasifikasiKerjaJabatan arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KlasifikasiKerjaJabatan saveData(KlasifikasiKerjaJabatan arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(KlasifikasiKerjaJabatan arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KlasifikasiKerjaJabatan saveOrUpdateData(KlasifikasiKerjaJabatan arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(KlasifikasiKerjaJabatan arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(KlasifikasiKerjaJabatan arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KlasifikasiKerjaJabatan updateData(KlasifikasiKerjaJabatan arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public List<KlasifikasiKerjaJabatan> getAllDataByJabatanId(Long jabatanId) throws Exception {
		return klasifikasiKerjaJabatanDao.getByJabatanId(jabatanId);
	}

}
