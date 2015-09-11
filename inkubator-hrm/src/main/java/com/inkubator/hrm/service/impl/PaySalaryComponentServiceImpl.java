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
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BenefitGroupDao;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.dao.ModelComponentDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PaySalaryEmpTypeDao;
import com.inkubator.hrm.dao.PaySalaryJurnalDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.dao.TaxComponentDao;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModelView;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
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
    private BenefitGroupDao benefitGroupDao;
    @Autowired
    private PaySalaryJurnalDao paySalaryJurnalDao;
    @Autowired
    private TaxComponentDao taxComponentDao;
    @Autowired
    private PaySalaryEmpTypeDao paySalaryEmpTypeDao;
    @Autowired
    private LoanNewTypeDao loanNewTypeDao;
    @Autowired
    private RmbsTypeDao rmbsTypeDao;

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
        // check duplicate model component and referensi dan not id
        Long totalDuplicates = paySalaryComponentDao.getTotalByModelComponentAndModelReferensiAndNotId(entity.getModelComponent().getId(), entity.getModelReffernsil(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("paySalaryComponent.error_duplicate_model_component_and_referensi");
        }
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
        if (entity.getModelComponent() != null) {
            update.setModelComponent(modelComponentDao.getEntiyByPK(entity.getModelComponent().getId()));
        }
        if (entity.getPaySalaryJurnal() != null) {
            update.setPaySalaryJurnal(paySalaryJurnalDao.getEntiyByPK(entity.getPaySalaryJurnal().getId()));
        }
        if (entity.getTaxComponent() != null) {
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
        PaySalaryComponent data=this.paySalaryComponentDao.getEntiyByPK(entity.getId());
        this.paySalaryComponentDao.delete(data);
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryComponent> getAllData() throws Exception {
        return paySalaryComponentDao.getAllData();
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
    public List<PaySalaryComponent> getAllDataByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return paySalaryComponentDao.getAllDataByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalParamWithDetail(PaySalaryComponentSearchParameter searchParameter) throws Exception {
        return paySalaryComponentDao.getTotalByParamWithDetail(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaySalaryComponent getEntityByPkWithDetail(Long id) throws Exception {
        PaySalaryComponent paySalaryComponent = paySalaryComponentDao.getEntityByPkWithDetail(id);
        List<EmployeeType> listEmployee = new ArrayList<>();
        for (PaySalaryEmpType paySalaryEmpType : this.paySalaryEmpTypeDao.getByUserId(id)) {
            listEmployee.add(paySalaryEmpType.getEmployeeType());
        }
        paySalaryComponent.setEmployeeTypes(listEmployee);
        return paySalaryComponent;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        List<PaySalaryComponent> list = paySalaryComponentDao.getAllDataComponentUploadByParam(searchParameter, firstResult, maxResults, order);
        for (PaySalaryComponent paySalaryComponent : list) {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> returnComponentChange(Long id) throws Exception {
        ModelComponent component = this.modelComponentDao.getEntiyByPK(id);
//        List<Integer> listModelReferensi = paySalaryComponentDao.getAllModelReferensiId();
        Map<String, Long> data = new HashMap();
        if (Objects.equals(component.getSpesific(), HRMConstant.MODEL_COMP_LOAN)) {
            List<LoanNewType> dataToSend = loanNewTypeDao.getAllDataPayrollComponent(component.getId());
            for (LoanNewType loanType : dataToSend) {
                data.put(loanType.getLoanTypeName(), loanType.getId());
            }
        }

        if (component.getSpesific().equals(HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
            List<RmbsType> dataToSend = rmbsTypeDao.getAllDataPayrollComponent(component.getId());
            for (RmbsType rmbsType : dataToSend) {
                data.put(rmbsType.getName(), rmbsType.getId());
            }
        }
        if (component.getSpesific().equals(HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
            List<BenefitGroup> dataToSend = this.benefitGroupDao.getBenefitGroupData(component.getId());
            for (BenefitGroup bg : dataToSend) {
                data.put(bg.getName(), bg.getId());
            }
        }
        return data;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveWithEmployeeType(PaySalaryComponent paySalaryComponent) throws Exception {
        // check duplicate model component and referensi
        Long totalDuplicates = paySalaryComponentDao.getTotalByModelComponentAndModelReferensi(paySalaryComponent.getModelComponent().getId(), paySalaryComponent.getModelReffernsil());
        if (totalDuplicates > 0) {
            throw new BussinessException("paySalaryComponent.error_duplicate_model_component_and_referensi");
        }
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

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayComponentDataExceptionModelView> getAllDataByParamDataException(PayComponentDataExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return paySalaryComponentDao.getAllDataByParamDataException(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParamDataException(PayComponentDataExceptionSearchParameter searchParameter) throws Exception {
        return paySalaryComponentDao.getTotalByParamDataException(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryComponent> getAllDataByComponentCategoryZeroOrOne() throws Exception {
        return paySalaryComponentDao.getAllDataByComponentCategoryZeroOrOne();
    }

}
