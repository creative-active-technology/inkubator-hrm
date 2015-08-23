package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.dao.UnregDepartementDao;
import com.inkubator.hrm.dao.UnregEmpReligionDao;
import com.inkubator.hrm.dao.UnregEmpTypeDao;
import com.inkubator.hrm.dao.UnregGoljabDao;
import com.inkubator.hrm.dao.UnregPayComponentsExceptionDao;
import com.inkubator.hrm.dao.UnregSalaryDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.entity.UnregDepartement;
import com.inkubator.hrm.entity.UnregEmpReligion;
import com.inkubator.hrm.entity.UnregEmpType;
import com.inkubator.hrm.entity.UnregGoljab;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;
import com.inkubator.hrm.web.search.UnregCalculationSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Service(value = "tempUnregPayrollService")
@Lazy
public class TempUnregPayrollServiceImpl extends IServiceImpl implements TempUnregPayrollService {

	@Autowired
	private TempUnregPayrollDao tempUnregPayrollDao;
	@Autowired
	private TempUnregPayrollEmpPajakDao tempUnregPayrollEmpPajakDao;
	@Autowired
	private UnregSalaryDao unregSalaryDao;
	@Autowired
	private UnregPayComponentsExceptionDao unregPayComponentsExceptionDao;
	@Autowired
	private UnregDepartementDao unregDepartementDao;
	@Autowired
	private UnregEmpReligionDao unregEmpReligionDao;
	@Autowired
	private UnregGoljabDao unregGoljabDao;
	@Autowired
	private UnregEmpTypeDao unregEmpTypeDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private PaySalaryComponentDao paySalaryComponentDao;
	@Autowired
	private LogMonthEndPayrollDao logMonthEndPayrollDao;
	
	@Override
	public TempUnregPayroll getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(TempUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(TempUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(TempUnregPayroll enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll saveData(TempUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll updateData(TempUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll saveOrUpdateData(TempUnregPayroll entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public TempUnregPayroll getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(TempUnregPayroll entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(TempUnregPayroll entity) throws Exception {
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
	public List<TempUnregPayroll> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<TempUnregPayroll> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@SuppressWarnings({ "unchecked"})
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<TempUnregPayroll> getAllDataCalculatedPayment(Long unregSalaryId, Date createdOn, String createdBy) throws Exception {
		
		
		//initial        
        List<TempUnregPayroll> datas = new ArrayList<TempUnregPayroll>(); 
        PaySalaryComponent totalIncomeComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
        PaySalaryComponent taxComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_TAX);
        PaySalaryComponent ceilComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_CEIL);
		UnregSalary unregSalary = unregSalaryDao.getEntiyByPK(unregSalaryId);
		List<UnregPayComponents> listUnregPayComponents = new ArrayList<>(unregSalary.getUnregPayComponentses());
		
		List<UnregPayComponentsException> listException = unregPayComponentsExceptionDao.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregDepartement> listDepartements = unregDepartementDao.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregEmpReligion> listReligions = unregEmpReligionDao.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregGoljab> listGoljabs = unregGoljabDao.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregEmpType> listEmpTypes = unregEmpTypeDao.getAllDataByUnregSalaryId(unregSalaryId);
		
		List<Long> departmentIds = Lambda.extract(listDepartements, Lambda.on(UnregDepartement.class).getId().getUnregDepartementId());
		List<Long> religionIds = Lambda.extract(listReligions, Lambda.on(UnregEmpReligion.class).getId().getReligionId());
		List<Long> golJabIds = Lambda.extract(listGoljabs, Lambda.on(UnregGoljab.class).getId().getGolonganJabatanId());
		List<Long> empTypeIds = Lambda.extract(listEmpTypes, Lambda.on(UnregEmpType.class).getId().getUnregEmpTypeId());
		List<EmpData> empDatas = empDataDao.getAllDataByDepartmentAndReligionAndGolJabAndEmpType(departmentIds, religionIds, golJabIds, empTypeIds);		
		
		
		/** 1. Lakukan proses default, looping berdasarkan filter employee */
		for(EmpData empData : empDatas){
			LOGGER.info(" ============= EMPLOYEE : " + empData.getBioData().getFirstName() + " =====================");

            /**
             * Saat ini totalIncome masih temporary, karena belum dikurangi
             * pajak dan pembulatan CSR Sedangkan untuk final totalIncome (take
             * home pay) ada di proses(step) selanjutnya di batch proses,
             * silahkan lihat batch-config.xml
             */
            BigDecimal totalIncome = new BigDecimal(0);
            
            for(UnregPayComponents unregComp: listUnregPayComponents){
            	UnregPayComponentsException exception = Lambda.selectUnique(listException, Matchers.allOf(
            			Lambda.having(Lambda.on(UnregPayComponentsException.class).getId().getEmpDataId(), Matchers.equalTo(empData.getId())),
            			Lambda.having(Lambda.on(UnregPayComponentsException.class).getId().getUnregPayComponentsId(), Matchers.equalTo(unregComp.getId()))));
            	
            	BigDecimal nominal = new BigDecimal(0);
            	if(exception != null){
            		nominal = new BigDecimal(exception.getNominal());
            		listException.remove(exception);
            	} else {
            		LogMonthEndPayroll monthEnd =  logMonthEndPayrollDao.getEntityByEmpDataIdAndPeriodIdAndPaySalaryCompId(empData.getId(), unregSalary.getWtPeriode().getId(), unregComp.getPaySalaryComponent().getId());
            		if(monthEnd != null) {
            			nominal = monthEnd.getNominal();
            		}
            	}
            	
            	TempUnregPayroll tempUnregPayroll = new TempUnregPayroll();
                tempUnregPayroll.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                tempUnregPayroll.setEmpData(empData);
                tempUnregPayroll.setPaySalaryComponent(unregComp.getPaySalaryComponent());
                tempUnregPayroll.setUnregSalary(unregSalary);
                tempUnregPayroll.setFactor(this.getFactorBasedCategory(unregComp.getPaySalaryComponent().getComponentCategory()));
                tempUnregPayroll.setNominal(nominal);
                tempUnregPayroll.setCreatedBy(createdBy);
                tempUnregPayroll.setCreatedOn(createdOn);
                datas.add(tempUnregPayroll);
                
                totalIncome = this.calculateTotalIncome(totalIncome, tempUnregPayroll); //calculate totalIncome temporary            	
            }
            
            //added default paySalaryComponent that should be exist(mandatory)
			datas = this.defaultUnregPayrollComp(datas, unregSalary, empData, ceilComponent, taxComponent, totalIncomeComponent, totalIncome, createdBy, createdOn);
		}
		
		
		/** 2. Lakukan proses diluar default, looping untuk exception employee */
		Group<UnregPayComponentsException> groupByEmpData = Lambda.group(listException, Lambda.by(Lambda.on(UnregPayComponentsException.class).getEmpData().getId()));
		for (String key : groupByEmpData.keySet()) {						
			
			/**
             * Saat ini totalIncome masih temporary, karena belum dikurangi
             * pajak dan pembulatan CSR Sedangkan untuk final totalIncome (take
             * home pay) ada di proses(step) selanjutnya di batch proses,
             * silahkan lihat batch-config.xml
             */
			BigDecimal totalIncome = new BigDecimal(0);
			
			List<UnregPayComponentsException> list = groupByEmpData.find(key);
			EmpData empData = list.get(0).getEmpData();
			LOGGER.info(" ============= EMPLOYEE EXCEPTION : " + empData.getBioData().getFirstName() + " =====================");
			for(UnregPayComponentsException unregPayComponentsException : list){
				UnregPayComponents unregComp = unregPayComponentsException.getUnregPayComponents();
				
				TempUnregPayroll tempUnregPayroll = new TempUnregPayroll();
                tempUnregPayroll.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                tempUnregPayroll.setEmpData(empData);
                tempUnregPayroll.setPaySalaryComponent(unregComp.getPaySalaryComponent());
                tempUnregPayroll.setUnregSalary(unregSalary);
                tempUnregPayroll.setFactor(this.getFactorBasedCategory(unregComp.getPaySalaryComponent().getComponentCategory()));
                tempUnregPayroll.setNominal(new BigDecimal(unregPayComponentsException.getNominal()));
                tempUnregPayroll.setCreatedBy(createdBy);
                tempUnregPayroll.setCreatedOn(createdOn);
                datas.add(tempUnregPayroll);
                
                totalIncome = this.calculateTotalIncome(totalIncome, tempUnregPayroll); //calculate totalIncome temporary
			}
			
			//added default paySalaryComponent that should be exist(mandatory)
			datas = this.defaultUnregPayrollComp(datas, unregSalary, empData, ceilComponent, taxComponent, totalIncomeComponent, totalIncome, createdBy, createdOn);
		}		
		
	
		return datas;
	}
	
	private List<TempUnregPayroll> defaultUnregPayrollComp(List<TempUnregPayroll> datas, UnregSalary unregSalary, EmpData empData, PaySalaryComponent ceilComponent, PaySalaryComponent taxComponent, 
			PaySalaryComponent totalIncomeComponent, BigDecimal totalIncome, String createdBy, Date createdOn){
		
		//create totalIncome Kalkulasi, hasil penjumlahan nominal dari semua component di atas            
        TempUnregPayroll totalIncomeKalkulasi = new TempUnregPayroll();
        totalIncomeKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        totalIncomeKalkulasi.setEmpData(empData);
        totalIncomeKalkulasi.setPaySalaryComponent(totalIncomeComponent);
        totalIncomeKalkulasi.setUnregSalary(unregSalary);
        totalIncomeKalkulasi.setFactor(this.getFactorBasedCategory(totalIncomeComponent.getComponentCategory()));
        totalIncomeKalkulasi.setNominal(totalIncome);
        totalIncomeKalkulasi.setCreatedBy(createdBy);
        totalIncomeKalkulasi.setCreatedOn(createdOn);
        datas.add(totalIncomeKalkulasi);

        //create initial tax Kalkulasi, set nominal 0. Akan dibutuhkan di batch proses step selanjutnya            
        TempUnregPayroll taxKalkulasi = new TempUnregPayroll();
        taxKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        taxKalkulasi.setEmpData(empData);
        taxKalkulasi.setPaySalaryComponent(taxComponent);
        taxKalkulasi.setUnregSalary(unregSalary);
        taxKalkulasi.setFactor(this.getFactorBasedCategory(taxComponent.getComponentCategory()));
        taxKalkulasi.setNominal(new BigDecimal(0));
        taxKalkulasi.setCreatedBy(createdBy);
        taxKalkulasi.setCreatedOn(createdOn);
        datas.add(taxKalkulasi);

        //create initial ceil Kalkulasi, set nominal 0. Akan dibutuhkan di batch proses step selanjutnya             
        TempUnregPayroll ceilKalkulasi = new TempUnregPayroll();
        ceilKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        ceilKalkulasi.setEmpData(empData);
        ceilKalkulasi.setPaySalaryComponent(ceilComponent);
        ceilKalkulasi.setUnregSalary(unregSalary);
        ceilKalkulasi.setFactor(this.getFactorBasedCategory(ceilComponent.getComponentCategory()));
        ceilKalkulasi.setNominal(new BigDecimal(0));
        ceilKalkulasi.setCreatedBy(createdBy);
        ceilKalkulasi.setCreatedOn(createdOn);
        datas.add(ceilKalkulasi);
        
        return datas;
	}

	private Integer getFactorBasedCategory(Integer componentStrategy) {
        Integer factor = 1;

        if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_POTONGAN)) {
            factor = -1;
        } else if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_SUBSIDI)) {
            factor = 0;
        } else if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_TUNJANGAN)) {
            factor = 1;
        }

        return factor;
    }
	
	private BigDecimal calculateTotalIncome(BigDecimal totalIncome, TempUnregPayroll tempUnregPayroll) {

        return totalIncome.add((tempUnregPayroll.getNominal().multiply(new BigDecimal(tempUnregPayroll.getFactor()))));
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<TempUnregPayroll> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) throws Exception {
		return tempUnregPayrollDao.getAllDataByEmpDataIdAndTaxNotNull(empDataId);
		
	}
	
	@Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchUnregCalculationFinal(HashMap<String, Long> map) throws ScriptException {
		Long empDataId = map.get("empDataId");
		Long unregSalaryId = map.get("unregSalaryId");
		
        TempUnregPayrollEmpPajak tax_23 = tempUnregPayrollEmpPajakDao.getEntityByEmpDataIdAndUnregSalaryIdAndTaxComponentId(empDataId, unregSalaryId, 23L);
        TempUnregPayroll taxKalkulasi = tempUnregPayrollDao.getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(empDataId, unregSalaryId, HRMConstant.MODEL_COMP_TAX);
        //jika pajak PPh pasal 21 kurang dari 0(minus), maka di set 0 saja di payTempKalkulasi
        if(tax_23.getNominal() > 0){
        	taxKalkulasi.setNominal(new BigDecimal(tax_23.getNominal()));
        } else {
        	taxKalkulasi.setNominal(new BigDecimal(0));
        }
        tempUnregPayrollDao.update(taxKalkulasi);

        TempUnregPayroll ceilKalkulasi = tempUnregPayrollDao.getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(empDataId, unregSalaryId, HRMConstant.MODEL_COMP_CEIL);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        double ceiling = (Double) jsEngine.eval(ceilKalkulasi.getPaySalaryComponent().getFormula());

        TempUnregPayroll totalIncomeKalkulasi = tempUnregPayrollDao.getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(empDataId, unregSalaryId, HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
        BigDecimal totalIncome = totalIncomeKalkulasi.getNominal();
        totalIncome = this.calculateTotalIncome(totalIncome, taxKalkulasi);

        //dapatkan nilai sisa/pembulatan
        BigDecimal val[] = totalIncome.divideAndRemainder(new BigDecimal(ceiling));

        ceilKalkulasi.setNominal(val[1]);
        tempUnregPayrollDao.update(ceilKalkulasi);

        totalIncome = this.calculateTotalIncome(totalIncome, ceilKalkulasi);
        totalIncomeKalkulasi.setNominal(totalIncome);
        tempUnregPayrollDao.update(totalIncomeKalkulasi);
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception {
		tempUnregPayrollDao.deleteByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<UnregSalaryCalculationExecuteModel> getByParamUnregSalaryId(Long unregSalaryId, int first, int pageSize, Order orderable) throws Exception {
		return tempUnregPayrollDao.getByParamUnregSalaryId(unregSalaryId, first, pageSize, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public Long getTotalByParamUnregSalaryId(Long unregSalaryId) throws Exception {
		return tempUnregPayrollDao.getTotalByParamUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<TempUnregPayroll> getByParam(UnregCalculationSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		return tempUnregPayrollDao.getByParam(parameter, first, pageSize, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(UnregCalculationSearchParameter parameter) throws Exception {
		return tempUnregPayrollDao.getTotalByParam(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalEmployeeByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId) throws Exception {
		return tempUnregPayrollDao.getTotalEmployeeByUnregSalaryIdAndPaySalaryCompId(unregSalaryId, paySalaryComponentId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalNominalByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId) throws Exception {
		return tempUnregPayrollDao.getTotalNominalByUnregSalaryIdAndPaySalaryCompId(unregSalaryId, paySalaryComponentId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByUnregSalaryId(Long unregSalaryId) throws Exception {
		return tempUnregPayrollDao.getTotalByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpData> getAllDataEmployeeByUnregSalaryId(Long unregSalaryId) throws Exception {
		return tempUnregPayrollDao.getAllDataEmployeeByUnregSalaryId(unregSalaryId);
		
	}
}
