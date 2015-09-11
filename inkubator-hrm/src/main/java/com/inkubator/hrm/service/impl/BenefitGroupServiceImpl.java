package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BenefitGroupDao;
import com.inkubator.hrm.dao.BenefitGroupRateDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik Hidayat
 */
@Service(value = "benefitGroupService")
@Lazy
public class BenefitGroupServiceImpl extends IServiceImpl implements BenefitGroupService {

    @Autowired
    private BenefitGroupDao benefitGroupDao;
    @Autowired
    private PaySalaryComponentDao paySalaryComponentDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private BenefitGroupRateDao benefitGroupRateDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BenefitGroup benefitGroup) throws Exception {
        benefitGroupDao.delete(benefitGroup);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BenefitGroup> getAllData() throws Exception {
        return this.benefitGroupDao.getAllData();
    }

    @Override
    public List<BenefitGroup> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroup> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BenefitGroup getEntiyByPK(Long id) throws Exception {
        return benefitGroupDao.getEntiyByPK(id);
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
    public void save(BenefitGroup benefitGroup) throws Exception {
        // check duplicate name
        long totalDuplicates = benefitGroupDao.getTotalByCode(benefitGroup.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("benefitGroup.error_duplicate_benefitGroup_code");
        }

        benefitGroup.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        benefitGroup.setCreatedBy(UserInfoUtil.getUserName());
        benefitGroup.setCreatedOn(new Date());
        benefitGroupDao.save(benefitGroup);
    }

    @Override
    public BenefitGroup saveData(BenefitGroup arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(BenefitGroup arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroup saveOrUpdateData(BenefitGroup arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(BenefitGroup arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BenefitGroup b) throws Exception {
        // check duplicate name
        long totalDuplicates = benefitGroupDao.getTotalByCodeAndNotId(b.getCode(), b.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("benefitGroup.error_duplicate_benefitGroup_code");
        }

        BenefitGroup benefitGroup = benefitGroupDao.getEntiyByPK(b.getId());
        benefitGroup.setCode(b.getCode());
        benefitGroup.setName(b.getName());
        benefitGroup.setValidDate(b.getValidDate());
        benefitGroup.setDescription(b.getDescription());
        benefitGroup.setMeasurement(b.getMeasurement());
        benefitGroup.setUpdatedBy(UserInfoUtil.getUserName());
        benefitGroup.setUpdatedOn(new Date());
        benefitGroupDao.update(benefitGroup);
    }

    @Override
    public BenefitGroup updateData(BenefitGroup arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BenefitGroup> getByParam(BenefitGroupSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.benefitGroupDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(BenefitGroupSearchParameter parameter) throws Exception {
        return this.benefitGroupDao.getTotalBenefitGroupByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public String getBenefitGroupNameByPk(Long id) throws Exception {
        return benefitGroupDao.getBenefitGroupNameByPk(id);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public List<BenefitGroupRenumerationModel> getAllDataRenumeration(Long empDataId) throws Exception {
		EmpData empData = empDataDao.getEntiyByPK(empDataId);
		
		List<BenefitGroupRenumerationModel> listRenumeration = new ArrayList<BenefitGroupRenumerationModel>();
		List<PaySalaryComponent> listComponent =  paySalaryComponentDao.getAllDataRenumerationByEmployeeTypeId(empData.getEmployeeType().getId());
		for(PaySalaryComponent component : listComponent) {			
			BenefitGroupRenumerationModel model = new BenefitGroupRenumerationModel();
			if(ObjectUtils.equals(component.getModelComponent().getSpesific(),HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
				//Tunjangan
				BenefitGroup benefitGroup = benefitGroupDao.getEntiyByPK((long)component.getModelReffernsil());
				BenefitGroupRate benefitGroupRate =  benefitGroupRateDao.getEntityByBenefitGroupIdAndGolJabatanId(benefitGroup.getId(),empData.getGolonganJabatan().getId());			
				
				model.setName(benefitGroup.getName());
				model.setComponentCategory(component.getComponentCategory());
				model.setNominal(benefitGroupRate != null ? benefitGroupRate.getNominal() : 0.0);
				model.setIsBasicSalary(Boolean.FALSE);
				
			} else if(ObjectUtils.equals(component.getModelComponent().getSpesific(),HRMConstant.MODEL_COMP_BASIC_SALARY)) {
				//Basic Salary
				model.setName(component.getName());
				model.setComponentCategory(component.getComponentCategory());
				model.setNominal(Double.parseDouble(empData.getBasicSalaryDecrypted()));
				model.setIsBasicSalary(Boolean.TRUE);
			}
			
			listRenumeration.add(model);
		}
		
		return listRenumeration;
	}

}
