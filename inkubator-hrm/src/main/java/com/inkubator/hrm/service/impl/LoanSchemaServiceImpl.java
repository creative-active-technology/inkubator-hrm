/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.LoanSchemaDao;
import com.inkubator.hrm.dao.LoanSchemaEmployeeTypeDao;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.LoanSchemaEmployeeType;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
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
@Service(value = "loanSchemaService")
@Lazy
public class LoanSchemaServiceImpl extends IServiceImpl implements LoanSchemaService{

    @Autowired
    private LoanSchemaDao loanSchemaDao;
    @Autowired
    private CostCenterDao costCenterDao;
    @Autowired 
    private LoanSchemaEmployeeTypeDao loanSchemaEmployeeTypeDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanSchema> getAllDataWithAllRelation(LoanSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return loanSchemaDao.getAllDataWithAllRelation(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(LoanSchemaSearchParameter searchParameter) throws Exception {
        return loanSchemaDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanSchema getEntityByPkWithAllRelation(Long id) throws Exception {
        LoanSchema loanSchema = loanSchemaDao.getEntityByPkWithAllRelation(id);
        List<EmployeeType> employeeType = new ArrayList<>();
        for (LoanSchemaEmployeeType loanSchemaEmployeeType : this.loanSchemaEmployeeTypeDao.getByUserId(id)) {
            employeeType.add(loanSchemaEmployeeType.getEmployeeType());
        }
     
        loanSchema.setEmployeeTypes(employeeType);
        return loanSchema;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveAndNotification(LoanSchema loanSchema) throws Exception {
        long totalDuplicates = loanSchemaDao.getTotalByCode(loanSchema.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("costcenter.error_duplicate_cost_center_code");
        }
        if(loanSchema.getCostCenter()!= null){
    		CostCenter costCenter = costCenterDao.getEntiyByPK(loanSchema.getCostCenter().getId());
    		loanSchema.setCostCenter(costCenter);
    	}
        loanSchema.setCreatedBy(UserInfoUtil.getUserName());
        loanSchema.setCreatedOn(new Date());
        this.loanSchemaDao.save(loanSchema);
    }

    @Override
    public LoanSchema getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanSchema getEntiyByPK(Long id) throws Exception {
        return loanSchemaDao.getEntiyByPK(id);
    }

    @Override
    public void save(LoanSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanSchema entity) throws Exception {
        long totalDuplicates = loanSchemaDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("costcenter.error_duplicate_cost_center_code");
        }
        LoanSchema update = this.loanSchemaDao.getEntiyByPK(entity.getId());
        update.getLoanSchemaEmployeeTypes().clear();
        if(entity.getCostCenter()!= null){
            CostCenter costCenter = costCenterDao.getEntiyByPK(entity.getCostCenter().getId());
            update.setCostCenter(costCenter);
        }
        update.setBasicValue(entity.getBasicValue());
        update.setCode(entity.getCode());
        update.setMaxNominal(entity.getMaxNominal());
        update.setMaxPaymentOfSalary(entity.getMaxPaymentOfSalary());
        update.setMaxPeriode(entity.getMaxPeriode());
        update.setName(entity.getName());
        update.setPenaltyOfNonComplance(entity.getPenaltyOfNonComplance());
        update.setTypeOfInterest(entity.getTypeOfInterest());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.loanSchemaDao.saveAndMerge(update);
        Set<LoanSchemaEmployeeType> dataToSave = entity.getLoanSchemaEmployeeTypes();
        for (LoanSchemaEmployeeType loanSchemaEmployeeType : dataToSave) {
            System.out.println("eksekusi");
            loanSchemaEmployeeType.setLoanSchema(update);
            this.loanSchemaEmployeeTypeDao.save(loanSchemaEmployeeType);
        }
    }

    @Override
    public void saveOrUpdate(LoanSchema enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema saveData(LoanSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema updateData(LoanSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema saveOrUpdateData(LoanSchema entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanSchema getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LoanSchema entity) throws Exception {
        this.loanSchemaDao.delete(entity);
    }

    @Override
    public void softDelete(LoanSchema entity) throws Exception {
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
    public List<LoanSchema> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanSchema> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LoanSchema> getAllDataByEmployeeTypeId(Long employeeTypeId) throws Exception {
		return loanSchemaDao.getAllDataByEmployeeTypeId(employeeTypeId);
	}
    
}
