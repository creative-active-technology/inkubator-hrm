package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.EducationNonFormalDao;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.service.EducationNonFormalService;
import com.inkubator.hrm.web.search.EducationNonFormalSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "educationNonFormalService")
@Lazy
public class EducationNonFormalServiceImpl extends IServiceImpl implements
		EducationNonFormalService {

	@Autowired
	private EducationNonFormalDao educationNonFormalDao;
	@Autowired
	private CityDao cityDao;
	
	@Override
	public EducationNonFormal getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)	
	public EducationNonFormal getEntiyByPK(Long id) throws Exception {
		return educationNonFormalDao.getEntiyByPK(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(EducationNonFormal entity) throws Exception {
		// check duplicate
		long totalDuplicates = educationNonFormalDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("educationnonformal.error_duplicate_code");
		}
		totalDuplicates = educationNonFormalDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("educationnonformal.error_duplicate_name");
		}

		City city = cityDao.getEntiyByPK(entity.getCity().getId());
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCity(city);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		educationNonFormalDao.save(entity);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(EducationNonFormal entity) throws Exception {
		//check duplicate name
		long totalDuplicates = educationNonFormalDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if(totalDuplicates > 0){
			throw new BussinessException("educationnonformal.error_duplicate_code");
		}
		totalDuplicates = educationNonFormalDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
		if(totalDuplicates > 0){
			throw new BussinessException("educationnonformal.error_duplicate_name");
		}
		
		City city = cityDao.getEntiyByPK(entity.getCity().getId());
		
		EducationNonFormal educationNonFormal = educationNonFormalDao.getEntiyByPK(entity.getId());
		educationNonFormal.setCode(entity.getCode());
		educationNonFormal.setName(entity.getName());
		educationNonFormal.setDescription(entity.getDescription());
		educationNonFormal.setAddress(entity.getAddress());
		educationNonFormal.setCity(city);
		educationNonFormal.setPostalCode(entity.getPostalCode());
		educationNonFormal.setOfficialPhoneNo(entity.getOfficialPhoneNo());
		educationNonFormal.setOfficialEmail(entity.getOfficialEmail());
		educationNonFormal.setUpdatedBy(UserInfoUtil.getUserName());
		educationNonFormal.setUpdatedOn(new Date());
		educationNonFormalDao.update(educationNonFormal);		
	}

	@Override
	public void saveOrUpdate(EducationNonFormal enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal saveData(EducationNonFormal entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal updateData(EducationNonFormal entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal saveOrUpdateData(EducationNonFormal entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public EducationNonFormal getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(EducationNonFormal entity) throws Exception {
		educationNonFormalDao.delete(entity);

	}

	@Override
	public void softDelete(EducationNonFormal entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EducationNonFormal> getAllData() throws Exception {
		return educationNonFormalDao.getAllData();

	}

	@Override
	public List<EducationNonFormal> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<EducationNonFormal> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EducationNonFormal> getByParam(EducationNonFormalSearchParameter parameter,
			int firstResult, int maxResults, Order orderable) throws Exception {
		return educationNonFormalDao.getByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(EducationNonFormalSearchParameter parameter) throws Exception {
		return educationNonFormalDao.getTotalByParam(parameter);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public EducationNonFormal getEntityByPkWithDetail(Long id) throws Exception {
		return educationNonFormalDao.getEntityByPkWithDetail(id);

	}

}
