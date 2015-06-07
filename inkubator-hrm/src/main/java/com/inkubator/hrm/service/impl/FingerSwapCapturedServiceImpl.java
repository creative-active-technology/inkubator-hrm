
package com.inkubator.hrm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.inkubator.hrm.dao.FingerMatchEmpDao;
import com.inkubator.hrm.dao.FingerSwapCapturedDao;
import com.inkubator.hrm.dao.MecineFingerDao;
import com.inkubator.hrm.entity.FingerMatchEmp;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.FingerSwapCapturedService;
import com.inkubator.hrm.web.model.FingerSwapCapturedUploadFileModel;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "fingerSwapCapturedService")
@Lazy
public class FingerSwapCapturedServiceImpl extends IServiceImpl implements FingerSwapCapturedService {

	@Autowired
	private FingerSwapCapturedDao fingerSwapCapturedDao;	
	@Autowired
	private MecineFingerDao mecineFingerDao;
	@Autowired
	private FingerMatchEmpDao fingerMatchEmpDao;
	
	@Override
	public FingerSwapCaptured getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void save(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void update(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void saveOrUpdate(FingerSwapCaptured enntity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured saveData(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured updateData(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured saveOrUpdateData(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public FingerSwapCaptured getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void delete(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public void softDelete(FingerSwapCaptured entity) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllData() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	public List<FingerSwapCaptured> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public List<FingerSwapCapturedViewModel> getAllDataByParam(FingerSwapCapturedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return fingerSwapCapturedDao.getAllDataByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(FingerSwapCapturedSearchParameter parameter) throws Exception {
		return fingerSwapCapturedDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void executeBatchFileUpload(FingerSwapCapturedUploadFileModel model) throws Exception {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date dateTime = dateFormat.parse(model.getDateTime());
			FingerMatchEmp fingerMatchEmp = fingerMatchEmpDao.getEntityByNikAndMachineId(model.getNik(), model.getMachineId());		
			if(fingerMatchEmp != null) {				
				FingerSwapCaptured fingerSwapCaptured =  new FingerSwapCaptured();
				fingerSwapCaptured.setFingerIndexId(fingerMatchEmp.getFingerIndexId());
				fingerSwapCaptured.setMecineFinger(fingerMatchEmp.getMecineFinger());
				fingerSwapCaptured.setDatetimeAdded(new Date());
				fingerSwapCaptured.setSwapDatetimeLog(dateTime);
				fingerSwapCaptured.setDataSource(0);
				fingerSwapCaptured.setIsAlreadyProcessed(Boolean.FALSE);
				fingerSwapCaptured.setCreatedBy(model.getCreatedBy());
				fingerSwapCaptured.setCreatedOn(new Date());
				fingerSwapCapturedDao.save(fingerSwapCaptured);
			}
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
