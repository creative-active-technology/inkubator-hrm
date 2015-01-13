package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "payTempAttendanceStatusService")
@Lazy
public class PayTempAttendanceStatusServiceImpl extends IServiceImpl implements
		PayTempAttendanceStatusService {
	@Autowired
    private WtPeriodeDao wtPeriodeDao;
	
	@Autowired
    private PayTempAttendanceStatusDao payTempAttendanceStatusDao;

	@Override
	public void delete(PayTempAttendanceStatus arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllData(Boolean arg0)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllData(Integer arg0)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllDataPageAble(int arg0, int arg1,
			Order arg2) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(String arg0,
			Integer arg1) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(String arg0,
			Boolean arg1) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0,
			Integer arg1) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0,
			Boolean arg1) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus getEntiyByPK(Long arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void save(PayTempAttendanceStatus arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus saveData(PayTempAttendanceStatus arg0)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void saveOrUpdate(PayTempAttendanceStatus arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus saveOrUpdateData(PayTempAttendanceStatus arg0)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void softDelete(PayTempAttendanceStatus arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void update(PayTempAttendanceStatus arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PayTempAttendanceStatus updateData(PayTempAttendanceStatus arg0)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<PayTempAttendanceStatus> getByParam(String parameter,
			PayTempAttendanceStatusModel payTempAttendanceStatusModel,
			int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PayTempAttendanceStatus> getByWtPeriodeWhereComponentPayrollIsActive(
			PayTempAttendanceStatusModel payTempAttendanceStatusModel)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalResourceTypeByParam(String parameter,
			PayTempAttendanceStatusModel payTempAttendanceStatusModel)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
