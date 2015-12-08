/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CareerTerminationTypeDao;
import com.inkubator.hrm.dao.SystemCareerConstDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;

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
@Service(value = "careerTerminationTypeService")
@Lazy
public class CareerTerminationTypeServiceImpl extends IServiceImpl implements CareerTerminationTypeService {
    
    @Autowired
    private CareerTerminationTypeDao careerTerminationTypeDao;
    @Autowired
    private SystemLetterReferenceDao systemLetterReferenceDao;
    @Autowired
    private SystemCareerConstDao systemCareerConstDao;
    
    @Override
    public CareerTerminationType getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public CareerTerminationType getEntiyByPK(Long id) throws Exception {
        return careerTerminationTypeDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(CareerTerminationType entity) throws Exception {
    	// check duplicate name
        long totalDuplicates = careerTerminationTypeDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("terminationType.error_duplicate_terminationType_code");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(HrmUserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setSystemLetterReference(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId()));
        entity.setSystemCareerConst(systemCareerConstDao.getEntiyByPK(entity.getSystemCareerConst().getId()));
        careerTerminationTypeDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(CareerTerminationType entity) throws Exception {
    	// check duplicate name
        long totalDuplicates = careerTerminationTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("terminationType.error_duplicate_terminationType_code");
        }
        CareerTerminationType entityToUpdate = careerTerminationTypeDao.getEntiyByPK(entity.getId());
        entityToUpdate.setCode(entity.getCode());
        entityToUpdate.setName(entity.getName());
        entityToUpdate.setDescription(entity.getDescription());
        entityToUpdate.setUpdatedBy(HrmUserInfoUtil.getUserName());
        entityToUpdate.setUpdatedOn(new Date())
        entityToUpdate.setSystemLetterReference(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReference().getId()));
        entityToUpdate.setSystemCareerConst(systemCareerConstDao.getEntiyByPK(entity.getSystemCareerConst().getId()));
        careerTerminationTypeDao.update(entityToUpdate);
    }

    @Override
    public void saveOrUpdate(CareerTerminationType enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType saveData(CareerTerminationType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType updateData(CareerTerminationType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType saveOrUpdateData(CareerTerminationType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CareerTerminationType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(CareerTerminationType entity) throws Exception {
        careerTerminationTypeDao.delete(entity);
    }

    @Override
    public void softDelete(CareerTerminationType entity) throws Exception {
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
    public List<CareerTerminationType> getAllData() throws Exception {
        return careerTerminationTypeDao.getAllData();
    }

    @Override
    public List<CareerTerminationType> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CareerTerminationType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CareerTerminationType> getListByParam(CareerTerminationTypeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return careerTerminationTypeDao.getListByParam(searchParameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CareerTerminationTypeSearchParameter searchParameter) throws Exception {
		return careerTerminationTypeDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public CareerTerminationType getEntityWithDetailById(Long id) throws Exception {
		return careerTerminationTypeDao.getEntityWithDetailById(id);
	}
    
}
