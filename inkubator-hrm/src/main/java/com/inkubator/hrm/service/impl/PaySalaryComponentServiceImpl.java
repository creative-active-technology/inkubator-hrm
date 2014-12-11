/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BenefitGroupDao;
import com.inkubator.hrm.dao.LoanSchemaDao;
import com.inkubator.hrm.dao.ModelComponentDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PaySalaryEmpTypeDao;
import com.inkubator.hrm.dao.PaySalaryJurnalDao;
import com.inkubator.hrm.dao.ReimbursmentSchemaDao;
import com.inkubator.hrm.dao.TaxComponentDao;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "paySalaryComponentService")
@Lazy
public class PaySalaryComponentServiceImpl extends IServiceImpl implements PaySalaryComponentService {

    @Autowired
    private PaySalaryComponentDao paySalaryComponentDao;
    @Autowired
    private ModelComponentDao modelComponentDao;
    @Autowired
    private LoanSchemaDao loanSchemaDao;
    @Autowired
    private ReimbursmentSchemaDao reimbursmentSchemaDao;
    @Autowired
    private BenefitGroupDao benefitGroupDao;
    @Autowired
    private PaySalaryJurnalDao paySalaryJurnalDao;
    @Autowired
    private TaxComponentDao taxComponentDao;
    @Autowired
    private PaySalaryEmpTypeDao paySalaryEmpTypeDao;

    @Override
    public PaySalaryComponent getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaySalaryComponent getEntiyByPK(Long id) throws Exception {
        return paySalaryComponentDao.getEntiyByPK(id);
    }

    @Override
    public void save(PaySalaryComponent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PaySalaryComponent entity) throws Exception {
        PaySalaryComponent update = this.getEntityByPkWithDetail(entity.getId());
        update.getPaySalaryEmpTypes().clear();
        update.setCode(entity.getCode());
        update.setName(entity.getName());
        update.setRenumeration(entity.getRenumeration());
        update.setFormula(entity.getFormula());
        update.setComponentCategory(entity.getComponentCategory());
        update.setResetData(entity.getResetData());
        update.setModelReffernsil(entity.getModelReffernsil());
        update.setActiveFromTmb(entity.getActiveFromTmb());
        if(entity.getModelComponent() != null){
            update.setModelComponent(modelComponentDao.getEntiyByPK(entity.getModelComponent().getId()));
        }
        if(entity.getPaySalaryJurnal() != null){
            update.setPaySalaryJurnal(paySalaryJurnalDao.getEntiyByPK(entity.getPaySalaryJurnal().getId()));
        }
        if(entity.getTaxComponent() != null){
            update.setTaxComponent(taxComponentDao.getEntiyByPK(entity.getTaxComponent().getId()));
        }
        this.paySalaryComponentDao.saveAndMerge(update);
        Set<PaySalaryEmpType> dataToSave = entity.getPaySalaryEmpTypes();
        for (PaySalaryEmpType paySalaryEmpType : dataToSave) {
            paySalaryEmpType.setPaySalaryComponent(update);
            this.paySalaryEmpTypeDao.save(paySalaryEmpType);
        }
    }

    @Override
    public void saveOrUpdate(PaySalaryComponent enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent saveData(PaySalaryComponent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent updateData(PaySalaryComponent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent saveOrUpdateData(PaySalaryComponent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaySalaryComponent getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PaySalaryComponent entity) throws Exception {
        this.paySalaryComponentDao.delete(entity);
    }

    @Override
    public void softDelete(PaySalaryComponent entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaySalaryComponent> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryComponent> getByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return paySalaryComponentDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalResourceTypeByParam(PaySalaryComponentSearchParameter searchParameter) throws Exception {
        return paySalaryComponentDao.getTotalResourceTypeByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaySalaryComponent getEntityByPkWithDetail(Long id) throws Exception {
        PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntityByPkWithDetail(id);
        List<EmployeeType> listEmployee = new ArrayList<>();
        for(PaySalaryEmpType paySalaryEmpType : this.paySalaryEmpTypeDao.getByUserId(id)){
            listEmployee.add(paySalaryEmpType.getEmployeeType());
        }
        paySalaryComponent.setEmployeeTypes(listEmployee);
        return paySalaryComponent;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
    	List<PaySalaryComponent> list = paySalaryComponentDao.getAllDataComponentUploadByParam(searchParameter, firstResult, maxResults, order);    	
    	for(PaySalaryComponent paySalaryComponent :list){
    		paySalaryComponent.setTotalPayTempUploadDatas(paySalaryComponent.getPayTempUploadDatas().size());
    	}
    	return list;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter) throws Exception {
        return paySalaryComponentDao.getTotalComponentUploadByParam(searchParameter);
    }

    @Override
    public Map<String, Long> returnComponentChange(Long id) throws Exception {
        ModelComponent component = this.modelComponentDao.getEntiyByPK(id);
        Map<String, Long> data = new HashMap();
        if (Objects.equals(component.getSpesific(), HRMConstant.MODEL_COMP_LOAN)) {
            List<LoanSchema> dataToSend = loanSchemaDao.getEntityIsPayRollComponent();
            for (LoanSchema loanSchema : dataToSend) {
                data.put(loanSchema.getName(), loanSchema.getId());
            }
        }

        if (component.getSpesific().equals(HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
            List<ReimbursmentSchema> dataToSend = this.reimbursmentSchemaDao.isPayrollComponent();
            for (ReimbursmentSchema rs : dataToSend) {
                data.put(rs.getName(), rs.getId());
            }
        }

        if (component.getSpesific().equals(HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
            List<BenefitGroup> dataToSend = this.benefitGroupDao.getAllData();
            for (BenefitGroup bg : dataToSend) {
                data.put(bg.getName(), bg.getId());
            }
        }
        return data;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveWithEmployeeType(PaySalaryComponent paySalaryComponent) throws Exception {

        if (paySalaryComponent.getModelComponent() != null) {
            paySalaryComponent.setModelComponent(modelComponentDao.getEntiyByPK(paySalaryComponent.getModelComponent().getId()));
        }
        if (paySalaryComponent.getPaySalaryJurnal() != null) {
            paySalaryComponent.setPaySalaryJurnal(paySalaryJurnalDao.getEntiyByPK(paySalaryComponent.getPaySalaryJurnal().getId()));
        }
        if (paySalaryComponent.getTaxComponent() != null) {
            paySalaryComponent.setTaxComponent(taxComponentDao.getEntiyByPK(paySalaryComponent.getTaxComponent().getId()));
        }
        paySalaryComponent.setCreatedBy(UserInfoUtil.getUserName());
        paySalaryComponent.setCreatedOn(new Date());
        paySalaryComponentDao.save(paySalaryComponent);
    }

}