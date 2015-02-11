package com.inkubator.hrm.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
@Service(value = "payTempUploadDataService")
@Lazy
public class PayTempUploadDataServiceImpl extends IServiceImpl implements PayTempUploadDataService {

	@Autowired
	private PayTempUploadDataDao payTempUploadDataDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private PaySalaryComponentDao paySalaryComponentDao;
	@Autowired
	private LogMonthEndPayrollDao logMonthEndPayrollDao;
	@Autowired
    private FacesIO facesIO;
	
	@Override
	public PayTempUploadData getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public PayTempUploadData getEntiyByPK(Long id) throws Exception {
		return payTempUploadDataDao.getEntiyByPK(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void save(PayTempUploadData entity) throws Exception {
		
		long totalDuplicates = payTempUploadDataDao.getTotalByPaySalaryCompIdAndEmpDataId(entity.getPaySalaryComponent().getId(), entity.getEmpData().getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("paysalaryupload.error_duplicate_employee");
        }
        
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntiyByPK(entity.getPaySalaryComponent().getId());
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setPaySalaryComponent(paySalaryComponent);
		entity.setEmpData(empData);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.payTempUploadDataDao.save(entity);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void update(PayTempUploadData entity) throws Exception {
		
		long totalDuplicates = payTempUploadDataDao.getTotalByPaySalaryCompIdAndEmpDataIdAndNotId(entity.getPaySalaryComponent().getId(), entity.getEmpData().getId(),entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("paysalaryupload.error_duplicate_employee");
        }
        
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		PayTempUploadData payTempUploadData = payTempUploadDataDao.getEntiyByPK(entity.getId());
		payTempUploadData.setEmpData(empData);
		payTempUploadData.setNominalValue(entity.getNominalValue());
		payTempUploadData.setUpdatedBy(UserInfoUtil.getUserName());
		payTempUploadData.setUpdatedOn(new Date());
        this.payTempUploadDataDao.update(payTempUploadData);

	}

	@Override
	public void saveOrUpdate(PayTempUploadData enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData saveData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData updateData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData saveOrUpdateData(PayTempUploadData entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public PayTempUploadData getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void delete(PayTempUploadData entity) throws Exception {
		this.payTempUploadDataDao.delete(entity);

	}

	@Override
	public void softDelete(PayTempUploadData entity) throws Exception {
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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PayTempUploadData> getAllData() throws Exception {
		return this.payTempUploadDataDao.getAllData();

	}

	@Override
	public List<PayTempUploadData> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<PayTempUploadData> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception{
		return this.payTempUploadDataDao.getAllDataByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(PayTempUploadDataSearchParameter parameter) throws Exception{
		return this.payTempUploadDataDao.getTotalByParam(parameter);

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId) throws Exception{
		return this.payTempUploadDataDao.getTotalByPaySalaryComponentId(paySalaryComponentId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public PayTempUploadData getEntityByPkWithDetail(Long id) throws Exception{
		return this.payTempUploadDataDao.getEntityByPkWithDetail(id);

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId) throws Exception{
		return this.payTempUploadDataDao.getTotalSalaryByPaySalaryComponentId(paySalaryComponentId);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void executeBatchFileUpload(PaySalaryUploadFileModel model) throws Exception{
		Boolean isInsertable = this.payTempUploadDataDao.getEntityByNikAndComponentId(model.getNik(), model.getPaySalaryComponentId()) == null;
		
		//skip jika data sudah ada di database(tidak boleh duplikat)
		if(isInsertable) {
			EmpData empData = empDataDao.getEntityByNik(model.getNik());
			PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntiyByPK(model.getPaySalaryComponentId());
			
			if(empData!= null && paySalaryComponent != null) {
				PayTempUploadData entity = new PayTempUploadData();
				entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				entity.setPaySalaryComponent(paySalaryComponent);
				entity.setEmpData(empData);
				entity.setNominalValue(Double.parseDouble(model.getNominal()));
				entity.setPathUpload(model.getPathUpload());
		        entity.setCreatedBy(model.getCreatedBy());
		        entity.setCreatedOn(new Date());
		        this.payTempUploadDataDao.save(entity);
			}
		}		
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public void reuse(Long paySalaryComponentId, Long periodeId) throws Exception{
		PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntiyByPK(paySalaryComponentId);
		
		/** cari di logmonthEnd, setelah itu di cek ke payTempUploadData, update nominal jika sudah ada atau insert baru jika belum ada data */
		List<LogMonthEndPayroll> listMonthEnd = logMonthEndPayrollDao.getAllDataByPaySalaryCompAndPeriodeId(paySalaryComponent.getId(), paySalaryComponent.getCode(), paySalaryComponent.getName(), periodeId);
		for(LogMonthEndPayroll logMonthEndPayroll : listMonthEnd){			 
			PayTempUploadData payUpload = payTempUploadDataDao.getEntityByNikAndComponentId(logMonthEndPayroll.getEmpNik(), paySalaryComponent.getId());
			if(payUpload == null){
				//Saving process
				PayTempUploadData entity = new PayTempUploadData();
				entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				entity.setPaySalaryComponent(paySalaryComponent);
				EmpData empData = empDataDao.getEntiyByPK(logMonthEndPayroll.getEmpDataId());
				entity.setEmpData(empData);
				entity.setNominalValue(logMonthEndPayroll.getNominal().doubleValue());
		        entity.setCreatedBy(UserInfoUtil.getUserName());
		        entity.setCreatedOn(new Date());
		        this.payTempUploadDataDao.save(entity);
			} else {
				//Updating process
				payUpload.setNominalValue(logMonthEndPayroll.getNominal().doubleValue());
				payUpload.setUpdatedBy(UserInfoUtil.getUserName());
				payUpload.setUpdatedOn(new Date());
				this.payTempUploadDataDao.update(payUpload);
			}
		}
	}

	@Override	
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
	public String updateFileAndDeleteData(long paySalaryComponentId, UploadedFile documentFile) throws Exception {
		String uploadPath = this.getUploadPath(paySalaryComponentId, documentFile);
		
		if (documentFile != null) {        
            //remove old file
            Files.deleteIfExists(Paths.get(uploadPath));

            //added new file            
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }
		
		payTempUploadDataDao.deleteByPaySalaryComponentId(paySalaryComponentId);
		
		return uploadPath;
	}
	
	private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "paysalaryupload_" + id + "." + extension;
        return uploadPath;
    }

}
