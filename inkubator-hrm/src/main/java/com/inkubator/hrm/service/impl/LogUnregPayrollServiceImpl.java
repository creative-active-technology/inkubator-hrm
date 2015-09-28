package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LogUnregPayrollDao;
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.dao.UnregSalaryDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogUnregPayroll;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "logUnregPayrollService")
@Lazy
public class LogUnregPayrollServiceImpl extends IServiceImpl implements LogUnregPayrollService {

	@Autowired
	private LogUnregPayrollDao logUnregPayrollDao;
	@Autowired
	private TempUnregPayrollDao tempUnregPayrollDao;
	@Autowired
	private TempUnregPayrollEmpPajakDao tempUnregPayrollEmpPajakDao;
	@Autowired
	private UnregSalaryDao unregSalaryDao;
	@Autowired
	private EmpDataDao empDataDao;
	
	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(String id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(Integer id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntiyByPK(Long id)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(com.inkubator.hrm.entity.LogUnregPayroll enntity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll saveData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll updateData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll saveOrUpdateData(
			com.inkubator.hrm.entity.LogUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			String id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Integer id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public com.inkubator.hrm.entity.LogUnregPayroll getEntityByPkIsActive(
			Long id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(com.inkubator.hrm.entity.LogUnregPayroll entity)
			throws Exception {
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
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData()
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllData(
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAble(
			int firstResult, int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<com.inkubator.hrm.entity.LogUnregPayroll> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception {
		logUnregPayrollDao.deleteByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void afterPayrollProcess(Long unregSalaryId) throws Exception {
		
		/** update status unreg salary isAlreadyPaid process */
		UnregSalary unregSalary = unregSalaryDao.getEntiyByPK(unregSalaryId);
		unregSalary.setIsAlreadyPaid(true);
		unregSalary.setUpdatedOn(new Date());
		unregSalary.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
		unregSalaryDao.save(unregSalary);
		
		/** delete all the record in the temporary table **/
		tempUnregPayrollDao.deleteByUnregSalaryId(unregSalaryId);
		tempUnregPayrollEmpPajakDao.deleteByUnregSalaryId(unregSalaryId);		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalEmployeeByUnregSalaryId(Long unregSalaryId) {
		return logUnregPayrollDao.getTotalEmployeeByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalTakeHomePayByUnregSalaryId(Long unregSalaryId) {
		return logUnregPayrollDao.getTotalTakeHomePayByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<UnregPayrollViewModel> getByParam(UnregPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		return logUnregPayrollDao.getByParam(parameter, firstResult, maxResults, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(UnregPayrollSearchParameter parameter) {
		return logUnregPayrollDao.getTotalByParam(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LogUnregPayroll> getAllDataByEmpDataIdAndUnregSalaryId(Long empDataId, Long unregSalaryId) throws Exception {
		return logUnregPayrollDao.getAllDataByEmpDataIdAndUnregSalaryId(empDataId, unregSalaryId);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public StreamedContent generatePersonalSalarySlip(Long unregSalaryId, Long empDataId) throws Exception {
		EmpData empData = empDataDao.getEntiyByPK(empDataId);
		Collection<Long> arrayEmpDataId = new ArrayList<>();
		arrayEmpDataId.add(empDataId);		
        return generateSalarySlip(unregSalaryId, arrayEmpDataId, empData.getNik());
		
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public StreamedContent generateMassSalarySlip(UnregPayrollSearchParameter searchParameter) throws Exception {
		Collection<Long> arrayEmpDataId = logUnregPayrollDao.getAllDataEmpIdByParam(searchParameter);
        return generateSalarySlip(searchParameter.getUnregSalaryId(), arrayEmpDataId, "print_mass");
		
	}
	
	private StreamedContent generateSalarySlip(Long unregSalaryId, Collection<Long> arrayEmpDataId, String pdf) throws Exception {
		Map<String, Object> params = new HashMap<>();
        params.put("ARRAY_EMP_DATA_ID", arrayEmpDataId);
        params.put("UNREG_SALARY_ID", unregSalaryId);
        params.put("SUBREPORT_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/"));
        StreamedContent file = CommonReportUtil.exportReportToPDFStream("unreg_salary_slip.jasper", params, pdf + ".pdf");
        return file;
	}

}
