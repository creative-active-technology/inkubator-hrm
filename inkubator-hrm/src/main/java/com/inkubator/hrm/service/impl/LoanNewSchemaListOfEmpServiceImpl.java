/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.dao.LoanNewSchemaListOfEmpDao;
import com.inkubator.hrm.dao.LoanNewSchemaListOfTypeDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmpId;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
import java.util.ArrayList;
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
@Service(value = "loanNewSchemaListOfEmpService")
@Lazy
public class LoanNewSchemaListOfEmpServiceImpl extends IServiceImpl implements LoanNewSchemaListOfEmpService {

    @Autowired
    private LoanNewSchemaListOfEmpDao loanNewSchemaListOfEmpDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private LoanNewSchemaDao loanNewSchemaDao;
    @Autowired
    private LoanNewSchemaListOfTypeDao loanNewSchemaListOfTypeDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewSchemaListOfEmpViewModel> getByParam(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return loanNewSchemaListOfEmpDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParam(LoanNewSchemaListOfEmpSearchParameter parameter) throws Exception {
        return loanNewSchemaListOfEmpDao.getTotalByParam(parameter);
    }

    @Override
    public LoanNewSchemaListOfEmp getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanNewSchemaListOfEmp entity) throws Exception {
        entity.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        entity.setLoanNewSchema(loanNewSchemaDao.getEntiyByPK(entity.getLoanNewSchema().getId()));
        loanNewSchemaListOfEmpDao.save(entity);
    }

    @Override
    public void update(LoanNewSchemaListOfEmp entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LoanNewSchemaListOfEmp enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp saveData(LoanNewSchemaListOfEmp entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp updateData(LoanNewSchemaListOfEmp entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp saveOrUpdateData(LoanNewSchemaListOfEmp entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoanNewSchemaListOfEmp entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LoanNewSchemaListOfEmp entity) throws Exception {
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
    public List<LoanNewSchemaListOfEmp> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfEmp> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewSchemaListOfEmpViewModel> getByParamHQL(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        return loanNewSchemaListOfEmpDao.getByParamHQL(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public LoanNewSchemaListOfEmp getEntityByEmpDataId(Long id) throws Exception {
        return loanNewSchemaListOfEmpDao.getEntityByEmpDataId(id);
        
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanNewSchemaListOfEmp entity, Long oldId) throws Exception {
        //get existing data
        LoanNewSchemaListOfEmp delete = loanNewSchemaListOfEmpDao.getEntityByPk(new LoanNewSchemaListOfEmpId(entity.getEmpData().getId(), oldId));
        this.loanNewSchemaListOfEmpDao.delete(delete);
        entity.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        entity.setLoanNewSchema(loanNewSchemaDao.getEntiyByPK(entity.getLoanNewSchema().getId()));
        loanNewSchemaListOfEmpDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public LoanNewSchemaListOfEmp getEntityByEmpDataIdAndLoanSchemaId(Long empDataId, Long loanSchemaId) throws Exception {
        LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpDao.getEntityByEmpDataId(empDataId);
        List<LoanNewSchemaListOfType> listLoanNewType = new ArrayList<LoanNewSchemaListOfType>();
        if (loanNewSchemaListOfEmp.getLoanNewSchema() != null) {
            for (LoanNewSchemaListOfType loanNewSchemaListOfType : this.loanNewSchemaListOfTypeDao.getAllDataByLoanSchemaId(loanNewSchemaListOfEmp.getLoanNewSchema().getId())) {
                listLoanNewType.add(loanNewSchemaListOfType);
            }
            loanNewSchemaListOfEmp.setListLoanNewType(listLoanNewType);
        }
        return loanNewSchemaListOfEmp;
    }

}
