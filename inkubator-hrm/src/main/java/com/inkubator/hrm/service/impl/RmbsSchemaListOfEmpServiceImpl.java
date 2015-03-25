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

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RmbsSchemaListOfEmpDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmpId;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "rmbsSchemaListOfEmpService")
@Lazy
public class RmbsSchemaListOfEmpServiceImpl extends IServiceImpl implements RmbsSchemaListOfEmpService {

	@Autowired
	private RmbsSchemaListOfEmpDao rmbsSchemaListOfEmpDao;
	@Autowired
	private EmpDataService empDataService;
	@Autowired
	private RmbsSchemaService rmbsSchemaService;
	
	@Override
	public RmbsSchemaListOfEmp getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(RmbsSchemaListOfEmp entity) throws Exception {
		// check duplicate nomor SK
		Long totalDuplicates = rmbsSchemaListOfEmpDao.getTotalByNomorSk(entity.getNomorSk());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_schema.error_duplicate_no_sk");
		}		
		
		EmpData empData = empDataService.getEntiyByPK(entity.getId().getEmpDataId());
		RmbsSchema rmbsSchema = rmbsSchemaService.getEntiyByPK(entity.getId().getRmbsSchemaId());
		
		entity.setId(entity.getId());
		entity.setEmpData(empData);
		entity.setRmbsSchema(rmbsSchema);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		rmbsSchemaListOfEmpDao.save(entity);

	}

	@Override
	public void update(RmbsSchemaListOfEmp entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(RmbsSchemaListOfEmpId id, RmbsSchemaListOfEmp entity) throws Exception {
		
		// check duplicate nomor SK
		Long totalDuplicates = rmbsSchemaListOfEmpDao.getTotalByNomorSkAndNotId(entity.getNomorSk(), id.getEmpDataId(), id.getRmbsSchemaId());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_schema.error_duplicate_no_sk");
		}
		
		RmbsSchemaListOfEmp rmbsSchemaListOfEmp = rmbsSchemaListOfEmpDao.getEntityByPk(id.getEmpDataId(), id.getRmbsSchemaId());
		if(id.getRmbsSchemaId() != entity.getId().getRmbsSchemaId()){
			rmbsSchemaListOfEmpDao.delete(rmbsSchemaListOfEmp);
			
			EmpData empData = empDataService.getEntiyByPK(entity.getId().getEmpDataId());
			RmbsSchema rmbsSchema = rmbsSchemaService.getEntiyByPK(entity.getId().getRmbsSchemaId());
			rmbsSchemaListOfEmp = new RmbsSchemaListOfEmp();
			rmbsSchemaListOfEmp.setId(entity.getId());
			rmbsSchemaListOfEmp.setEmpData(empData);
			rmbsSchemaListOfEmp.setRmbsSchema(rmbsSchema);
			rmbsSchemaListOfEmp.setDescription(entity.getDescription());
			rmbsSchemaListOfEmp.setNomorSk(entity.getNomorSk());
			rmbsSchemaListOfEmp.setCreatedBy(UserInfoUtil.getUserName());
			rmbsSchemaListOfEmp.setCreatedOn(new Date());
			rmbsSchemaListOfEmpDao.save(entity);
			
		} else {
			rmbsSchemaListOfEmp.setDescription(entity.getDescription());
			rmbsSchemaListOfEmp.setNomorSk(entity.getNomorSk());
			rmbsSchemaListOfEmp.setUpdatedBy(UserInfoUtil.getUserName());
			rmbsSchemaListOfEmp.setUpdatedOn(new Date());
			rmbsSchemaListOfEmpDao.updateData(rmbsSchemaListOfEmp);	
		}			
	}

	@Override
	public void saveOrUpdate(RmbsSchemaListOfEmp enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp saveData(RmbsSchemaListOfEmp entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp updateData(RmbsSchemaListOfEmp entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp saveOrUpdateData(RmbsSchemaListOfEmp entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsSchemaListOfEmp getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(RmbsSchemaListOfEmp entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(RmbsSchemaListOfEmp entity) throws Exception {
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
	public List<RmbsSchemaListOfEmp> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsSchemaListOfEmp> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsSchemaEmpViewModel> getByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return rmbsSchemaListOfEmpDao.getByParamEmployeeSchema(parameter, firstResult, maxResults, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter) throws Exception {
		return rmbsSchemaListOfEmpDao.getTotalByParamEmployeeSchema(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsSchemaListOfEmp getEntityByEmpDataId(Long empDataId) throws Exception {	
		
		List<RmbsSchemaListOfEmp> listEntity = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empDataId);		
		return listEntity.isEmpty() ? null : listEntity.get(0);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsSchemaListOfEmp getEntityByPkWithDetail(Long empDataId, Long rmbsSchemaId) {
		return rmbsSchemaListOfEmpDao.getEntityByPkWithDetail(empDataId, rmbsSchemaId);
		
	}

}
