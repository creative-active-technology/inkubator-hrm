/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.ApprovalDefinitionLoanDao;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanId;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
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
 * @author Deni
 */
@Service(value = "loanNewSchemaService")
@Lazy
public class LoanNewSchemaServiceImpl extends BaseApprovalConfigurationServiceImpl<LoanNewSchema> implements LoanNewSchemaService {

    @Autowired
    private LoanNewSchemaDao loanNewSchemaDao;
    @Autowired
    private ApprovalDefinitionLoanDao approvalDefinitionLoanDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewSchema> getAllDataByParam(LoanNewSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return loanNewSchemaDao.getAllDataByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalDataByParam(LoanNewSchemaSearchParameter searchParameter) throws Exception {
        return loanNewSchemaDao.getTotalDataByParam(searchParameter);
    }

    @Override
    public LoanNewSchema getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanNewSchema getEntiyByPK(Long id) throws Exception {
        return loanNewSchemaDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanNewSchema entity) throws Exception {
        long totalLoanCodeDuplicates = loanNewSchemaDao.getTotalByLoanNewCode(entity.getLoanSchemaCode());
        if (totalLoanCodeDuplicates > 0) {
            throw new BussinessException("loanschema.error_duplicate_loan_schema_code");
        }
        long totalLoanNameDuplicates = loanNewSchemaDao.getTotalByLoanNewName(entity.getLoanSchemaName());
        if (totalLoanNameDuplicates > 0) {
            throw new BussinessException("loanschema.error_duplicate_loan_schema_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.loanNewSchemaDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanNewSchema entity) throws Exception {
        long totalLoanCodeDuplicates = loanNewSchemaDao.getTotalByLoanNewCodeAndNotId(entity.getLoanSchemaCode(), entity.getId());
        if (totalLoanCodeDuplicates > 0) {
            throw new BussinessException("loanschema.error_duplicate_loan_schema_code");
        }
        long totalLoanNameDuplicates = loanNewSchemaDao.getTotalByLoanNewNameAndNotId(entity.getLoanSchemaName(), entity.getId());
        if (totalLoanNameDuplicates > 0) {
            throw new BussinessException("loanschema.error_duplicate_loan_schema_name");
        }
        LoanNewSchema update = loanNewSchemaDao.getEntiyByPK(entity.getId());
        update.setLoanSchemaCode(entity.getLoanSchemaCode());
        update.setLoanSchemaName(entity.getLoanSchemaName());
        update.setNomorSk(entity.getNomorSk());
        update.setTotalMaximumInstallment(entity.getTotalMaximumInstallment());
        update.setTotalMaximumLoan(entity.getTotalMaximumLoan());
        update.setDescription(entity.getDescription());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.loanNewSchemaDao.update(update);
    }

    @Override
    public void saveOrUpdate(LoanNewSchema enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema saveData(LoanNewSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema updateData(LoanNewSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema saveOrUpdateData(LoanNewSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchema getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LoanNewSchema entity) throws Exception {
        this.loanNewSchemaDao.delete(entity);
    }

    @Override
    public void softDelete(LoanNewSchema entity) throws Exception {
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
    public List<LoanNewSchema> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void saveManyToMany(ApprovalDefinition appDef, LoanNewSchema entity) {
        ApprovalDefinitionLoan approvalDefinitionLoan = new ApprovalDefinitionLoan();
        approvalDefinitionLoan.setId(new ApprovalDefinitionLoanId(appDef.getId(), entity.getId()));
        approvalDefinitionLoan.setApprovalDefinition(appDef);
        approvalDefinitionLoan.setLoanNewSchema(entity);
        approvalDefinitionLoanDao.save(approvalDefinitionLoan);
    }

    @Override
    protected void deleteManyToMany(Object entity) {
        approvalDefinitionLoanDao.delete((ApprovalDefinitionLoan) entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanNewSchema entity, List<ApprovalDefinition> appDefs) throws Exception {

        /**
         * validasi approval definition conf
         */
        super.validateApprovalConf(appDefs);

        List<ApprovalDefinitionLoan> approvalDefinitionLoan = approvalDefinitionLoanDao.getByLoanId(entity.getId());
        if (approvalDefinitionLoan.isEmpty() == Boolean.FALSE) {
            for (ApprovalDefinitionLoan appDefLoan : approvalDefinitionLoan) {
                approvalDefinitionLoanDao.delete(appDefLoan);
            }
        }

        /**
         * saving approval definition conf manyToMany
         */
        super.saveApprovalConf(appDefs, entity);
    }

    @Override
    public void update(LoanNewSchema entity, List<ApprovalDefinition> appDefs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public LoanNewSchema getEntityByPkFetchApprovalDefinition(Long id) throws Exception {
        return loanNewSchemaDao.getEntityByPkFetchApprovalDefinition(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteApprovalconf(Long appDefId, Long rmbsSchemaId) throws Exception {
        ApprovalDefinitionLoan approvalDefinitionLoan = approvalDefinitionLoanDao.getEntityByPk(appDefId, rmbsSchemaId);

        /**
         * deleting process
         */
        super.deleteApprovalConf(approvalDefinitionLoan, appDefId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveApprovalConf(ApprovalDefinition appDef, Long loanNewSchema) throws Exception {
        LoanNewSchema entity = loanNewSchemaDao.getEntiyByPK(loanNewSchema);
        List<ApprovalDefinitionLoan> approvalDefinitionRmbsSchemas = approvalDefinitionLoanDao.getByLoanId(loanNewSchema);
        List<ApprovalDefinition> listAppDef = Lambda.extract(approvalDefinitionRmbsSchemas, Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
        listAppDef.add(appDef);

        /**
         * validasi approval definition conf
         */
        super.validateApprovalConf(listAppDef);

        /**
         * saving process
         */
        super.saveApprovalConf(appDef, entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateApprovalConf(ApprovalDefinition appDef, Long loanNewSchema) throws Exception {
        List<ApprovalDefinitionLoan> approvalDefinitionLoans = approvalDefinitionLoanDao.getByLoanId(loanNewSchema);
        List<ApprovalDefinition> listAppDef = Lambda.extract(approvalDefinitionLoans, Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
        listAppDef.remove(appDef);
        listAppDef.add(appDef);

        /**
         * validasi approval definition conf
         */
        super.validateApprovalConf(listAppDef);

        /**
         * updating process
         */
        super.updateApprovalDefinition(appDef);
    }

}
