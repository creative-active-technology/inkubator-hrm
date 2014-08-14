package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
*
* @author rizkykojek
*/
@Service(value = "employeeTypeService")
@Lazy
public class EmployeeTypeServiceImpl extends IServiceImpl implements EmployeeTypeService {

	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(EmployeeType employeeType) throws Exception {
		employeeTypeDao.delete(employeeType);
	}

	@Override
        @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmployeeType> getAllData() throws Exception {
		return this.employeeTypeDao.getAllData();

	}

	@Override
	public List<EmployeeType> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EmployeeType> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public EmployeeType getEntiyByPK(Long id) throws Exception {
		return employeeTypeDao.getEntiyByPK(id);
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(EmployeeType employeeType) throws Exception {
		// check duplicate name
		long totalDuplicates = employeeTypeDao.getTotalByName(employeeType.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("employeetype.error_duplicate_name");
		}
				
		employeeType.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		employeeType.setCreatedBy(UserInfoUtil.getUserName());
		employeeType.setCreatedOn(new Date());
		employeeTypeDao.save(employeeType);
	}

	@Override
	public EmployeeType saveData(EmployeeType arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(EmployeeType arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EmployeeType saveOrUpdateData(EmployeeType arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(EmployeeType arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(EmployeeType e) throws Exception {
		// check duplicate name
		long totalDuplicates = employeeTypeDao.getTotalByNameAndNotId(e.getName(), e.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("employeetype.error_duplicate_name");
		}
		
		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(e.getId());
		employeeType.setName(e.getName());
		employeeType.setUpdatedBy(UserInfoUtil.getUserName());
		employeeType.setUpdatedOn(new Date());
		employeeTypeDao.update(employeeType);
	}

	@Override
	public EmployeeType updateData(EmployeeType arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmployeeType> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return this.employeeTypeDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(String parameter) throws Exception {
		return this.employeeTypeDao.getTotalByParam(parameter);
	}

}
