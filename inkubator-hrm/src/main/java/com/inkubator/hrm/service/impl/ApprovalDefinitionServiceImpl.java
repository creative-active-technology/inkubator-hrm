/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
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
 * @author Deni Husni FR
 */
@Service(value = "approvalDefinitionService")
@Lazy
public class ApprovalDefinitionServiceImpl extends IServiceImpl implements ApprovalDefinitionService {

    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private JabatanDao jabatanDao;

    @Override
    public ApprovalDefinition getEntiyByPK(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntiyByPK(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public ApprovalDefinition getEntiyByPK(Long id) throws Exception {
        ApprovalDefinition approvalDefinition = this.approvalDefinitionDao.getEntiyByPK(id);
        if (approvalDefinition.getHrmUserByApproverIndividual() != null) {
            approvalDefinition.getHrmUserByApproverIndividual().getRealName();
        }

        if (approvalDefinition.getHrmUserByOnBehalfIndividual() != null) {
            approvalDefinition.getHrmUserByOnBehalfIndividual().getRealName();
        }

        if (approvalDefinition.getJabatanByApproverPosition() != null) {
            approvalDefinition.getJabatanByApproverPosition().getName();
        }

        if (approvalDefinition.getJabatanByOnBehalfPosition() != null) {
            approvalDefinition.getJabatanByOnBehalfPosition().getName();
        }

        return approvalDefinition;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ApprovalDefinition entity) throws Exception {

        long totalExist = this.approvalDefinitionDao.getTotalSameAprrovalProsesExist(entity.getName(), entity.getProcessType(), entity.getSequence());
        if (totalExist > 0) {
            throw new BussinessException("approval.error_unik");
        }

        if (entity.getSequence() > 1) {
            long totalApprovalaNameWithSeq1 = this.approvalDefinitionDao.getTotalAprpovalExistWithSequenceOne(entity.getName());
            if (totalApprovalaNameWithSeq1 < 1) {
                throw new BussinessException("approval.error_first");
            }
        }

        if (entity.getProcessType().equalsIgnoreCase(HRMConstant.APPROVAL_PROCESS)) {
            long totalLower = this.approvalDefinitionDao.getTotalDataWithSequenceLower(entity.getName(), entity.getSequence());
            if (totalLower > 0) {
                throw new Exception("jajajajaj");
            }
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        if (entity.getHrmUserByApproverIndividual() != null) {
            entity.setHrmUserByApproverIndividual(hrmUserDao.getEntiyByPK(entity.getHrmUserByApproverIndividual().getId()));
        }
        if (entity.getHrmUserByOnBehalfIndividual() != null) {
            entity.setHrmUserByOnBehalfIndividual(hrmUserDao.getEntiyByPK(entity.getHrmUserByOnBehalfIndividual().getId()));
        }

        if (entity.getJabatanByApproverPosition() != null) {
            entity.setJabatanByApproverPosition(jabatanDao.getEntiyByPK(entity.getJabatanByApproverPosition().getId()));
        }
        if (entity.getJabatanByOnBehalfPosition() != null) {
            entity.setJabatanByOnBehalfPosition(jabatanDao.getEntiyByPK(entity.getJabatanByOnBehalfPosition().getId()));
        }

        this.approvalDefinitionDao.save(entity);

    }

    @Override
    public void update(ApprovalDefinition entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(ApprovalDefinition enntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition saveData(ApprovalDefinition entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition updateData(ApprovalDefinition entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition saveOrUpdateData(ApprovalDefinition entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(String id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(String id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(String id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Integer id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Integer id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Integer id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Long id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Long id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApprovalDefinition getEntityByPkIsActive(Long id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ApprovalDefinition entity) {
        this.approvalDefinitionDao.delete(entity);
    }

    @Override
    public void softDelete(ApprovalDefinition entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllData(Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllData(Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllData(Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllDataPageAble(int firstResult, int maxResults, Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ApprovalDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ApprovalDefinition> getByParam(ApprovalDefinitionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.approvalDefinitionDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter) throws Exception {
        return this.approvalDefinitionDao.getTotalApprovalDefinitionByParam(searchParameter);
    }

    public Long getTotalByCode(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getTotalByCodeAndNotId(String arg0, Long arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getTotalByName(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
