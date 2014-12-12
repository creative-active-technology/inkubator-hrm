/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.ReimbursmentSchemaDao;
import com.inkubator.hrm.dao.ReimbursmentSchemaEmployeeTypeDao;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
@Service(value = "reimbursmentSchemaService")
@Lazy
public class ReimbursmentSchemaServiceImpl extends IServiceImpl implements ReimbursmentSchemaService{

    @Autowired
    private ReimbursmentSchemaDao reimbursmentSchemaDao;
    @Autowired
    private CostCenterDao costCenterDao;
    @Autowired 
    private ReimbursmentSchemaEmployeeTypeDao reimbursmentSchemaEmployeeTypeDao;
    
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ReimbursmentSchema> getAllDataWithAllRelation(ReimbursmentSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return reimbursmentSchemaDao.getAllDataWithAllRelation(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReimbursmentByParam(ReimbursmentSchemaSearchParameter searchParameter) throws Exception {
        return reimbursmentSchemaDao.getTotalReimbursmentByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public ReimbursmentSchema getEntityByPkWithAllRelation(Long id) throws Exception {
        ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntityByPkWithAllRelation(id);
        List<EmployeeType> employeeType = new ArrayList<>();
        for (ReimbursmentSchemaEmployeeType reimbursmentSchemaEmployeeType : this.reimbursmentSchemaEmployeeTypeDao.getByUserId(id)) {
            employeeType.add(reimbursmentSchemaEmployeeType.getEmployeeType());
        }
     
        reimbursmentSchema.setEmployeeTypes(employeeType);
        return reimbursmentSchema;
    }

    @Override
    public ReimbursmentSchema getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public ReimbursmentSchema getEntiyByPK(Long id) throws Exception {
        return reimbursmentSchemaDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ReimbursmentSchema entity) throws Exception {
        long totalDuplicates = reimbursmentSchemaDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("reimbursementschema.error_duplicate_reimbursement_schema_code");
        }
        entity.setBasicValue(entity.getBasicValue());
        entity.setCode(entity.getCode());
        if(entity.getCostCenter() != null){
            entity.setCostCenter(costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        }
        entity.setEffectiveDate(entity.getEffectiveDate());
        entity.setMeasurement(entity.getMeasurement());
        entity.setName(entity.getName());
        entity.setNominalUnit(entity.getNominalUnit());
        entity.setPayrollComponent(entity.getPayrollComponent());
        entity.setQuantity(entity.getQuantity());
        entity.setRatioSalary(entity.getRatioSalary());
        entity.setTimeRange(entity.getTimeRange());
        entity.setIsAttachDocument(entity.getIsAttachDocument());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.reimbursmentSchemaDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(ReimbursmentSchema entity) throws Exception {
        long totalDuplicates = reimbursmentSchemaDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("reimbursementschema.error_duplicate_reimbursement_schema_code");
        }
        ReimbursmentSchema update = reimbursmentSchemaDao.getEntiyByPK(entity.getId());
        update.getReimbursmentSchemaEmployeeTypes().clear();
         if(entity.getCostCenter()!= null){
    		CostCenter costCenter = costCenterDao.getEntiyByPK(entity.getCostCenter().getId());
    		update.setCostCenter(costCenter);
    	}
        update.setCode(entity.getCode());
        update.setEffectiveDate(entity.getEffectiveDate());
        update.setMeasurement(entity.getMeasurement());
        update.setBasicValue(entity.getBasicValue());
        update.setName(entity.getName());
        update.setQuantity(entity.getQuantity());
        update.setNominalUnit(entity.getNominalUnit());
        update.setPayrollComponent(entity.getPayrollComponent());
        update.setRatioSalary(entity.getRatioSalary());
        update.setTimeRange(entity.getTimeRange());
        update.setIsAttachDocument(entity.getIsAttachDocument());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.reimbursmentSchemaDao.saveAndMerge(update);
        Set<ReimbursmentSchemaEmployeeType> dataToSave = entity.getReimbursmentSchemaEmployeeTypes();
        for (ReimbursmentSchemaEmployeeType reimbursmentSchemaEmployeeType : dataToSave) {
            reimbursmentSchemaEmployeeType.setReimbursmentSchema(update);
            this.reimbursmentSchemaEmployeeTypeDao.save(reimbursmentSchemaEmployeeType);
        }
    }

    @Override
    public void saveOrUpdate(ReimbursmentSchema enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema saveData(ReimbursmentSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema updateData(ReimbursmentSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema saveOrUpdateData(ReimbursmentSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReimbursmentSchema getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ReimbursmentSchema entity) throws Exception {
        this.reimbursmentSchemaDao.delete(entity);
    }

    @Override
    public void softDelete(ReimbursmentSchema entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public List<ReimbursmentSchema> getAllData() throws Exception {
        return reimbursmentSchemaDao.getAllData();
    }

    @Override
    public List<ReimbursmentSchema> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReimbursmentSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveAndNotification(ReimbursmentSchema reimbursmentSchema) throws Exception {
        if(reimbursmentSchema.getCostCenter()!= null){
    		CostCenter costCenter = costCenterDao.getEntiyByPK(reimbursmentSchema.getCostCenter().getId());
    		reimbursmentSchema.setCostCenter(costCenter);
    	}else{
            reimbursmentSchema.setCostCenter(null);
        }
        reimbursmentSchema.setCreatedBy(UserInfoUtil.getUserName());
        reimbursmentSchema.setCreatedOn(new Date());
        this.reimbursmentSchemaDao.save(reimbursmentSchema);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public String getReimbursmentSchemaNameByPk(Long id) throws Exception {
        return reimbursmentSchemaDao.getReimbursmentSchemaNameByPk(id);
    }
    
}
