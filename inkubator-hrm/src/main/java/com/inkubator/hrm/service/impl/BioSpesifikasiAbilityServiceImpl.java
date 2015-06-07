/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioSpesifikasiAbilityDao;
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import com.inkubator.hrm.service.BioSpesifikasiAbilityService;
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
@Service(value = "bioSpesifikasiAbilityService")
@Lazy
public class BioSpesifikasiAbilityServiceImpl extends IServiceImpl implements BioSpesifikasiAbilityService{

    @Autowired
    private BioSpesifikasiAbilityDao bioSpesifikasiAbilityDao;
    @Autowired
    private SpecificationAbilityDao specificationAbilityDao;
    @Autowired
    private BioDataDao bioDataDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public BioSpesifikasiAbility getAllDataByPK(Long id) throws Exception {
        return bioSpesifikasiAbilityDao.getAllDataByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioSpesifikasiAbility> getAllDataByBiodataId(Long biodataId) throws Exception {
        return bioSpesifikasiAbilityDao.getAllDataByBiodataId(biodataId);
    }

    @Override
    public BioSpesifikasiAbility getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioSpesifikasiAbility getEntiyByPK(Long id) throws Exception {
        return bioSpesifikasiAbilityDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioSpesifikasiAbility entity) throws Exception {
        //check jika ada duplikat
        Long totalDuplicate = bioSpesifikasiAbilityDao.getTotalEntityByBioBioSpesifikasiAbilityId(entity.getSpecificationAbility().getId(), entity.getBioData().getId());
        if(totalDuplicate > 0){
            throw new BussinessException("jabatanSpesifikasi.error_duplicate");
        }
        entity.setId(new BioSpesifikasiAbilityId(entity.getBioData().getId(), entity.getSpecificationAbility().getId()));
        entity.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        entity.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.bioSpesifikasiAbilityDao.save(entity);
    
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioSpesifikasiAbility entity) throws Exception {
        
        BioSpesifikasiAbility update = this.bioSpesifikasiAbilityDao.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(entity.getBioData().getId(), entity.getSpecificationAbility().getId()));
        update.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        update.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        update.setOptionAbility(entity.getOptionAbility());
        update.setValue(entity.getValue());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.bioSpesifikasiAbilityDao.update(update);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateBioSpecAbility(BioSpesifikasiAbility entity, Long oldId) throws Exception {
        //check jika ada duplikat
        /*Long totalDuplicate = bioSpesifikasiAbilityDao.getTotalEntityByBioBioSpesifikasiAbilityId(entity.getSpecificationAbility().getId(), entity.getBioData().getId());
      
        if(totalDuplicate > 0){
          
            throw new BussinessException("jabatanSpesifikasi.error_duplicate");
        }*/
        BioSpesifikasiAbility update = this.bioSpesifikasiAbilityDao.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(entity.getBioData().getId(), oldId));
        this.bioSpesifikasiAbilityDao.delete(update);
        entity.setId(new BioSpesifikasiAbilityId(entity.getBioData().getId(), entity.getSpecificationAbility().getId()));
        entity.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        entity.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        entity.setOptionAbility(entity.getOptionAbility());
        entity.setValue(entity.getValue());
        entity.setUpdatedBy(UserInfoUtil.getUserName());
        entity.setUpdatedOn(new Date());
        this.bioSpesifikasiAbilityDao.save(entity);    
    }
    
    @Override
    public void saveOrUpdate(BioSpesifikasiAbility enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility saveData(BioSpesifikasiAbility entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility updateData(BioSpesifikasiAbility entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility saveOrUpdateData(BioSpesifikasiAbility entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSpesifikasiAbility getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(BioSpesifikasiAbility entity) throws Exception {
        BioSpesifikasiAbility delete = this.getEntityByBioSpesifikasiAbilityId(new BioSpesifikasiAbilityId(entity.getBioData().getId(), entity.getSpecificationAbility().getId()));
        this.bioSpesifikasiAbilityDao.delete(delete);
    }

    @Override
    public void softDelete(BioSpesifikasiAbility entity) throws Exception {
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
    public List<BioSpesifikasiAbility> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSpesifikasiAbility> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public BioSpesifikasiAbility getEntityByBioSpesifikasiAbilityId(BioSpesifikasiAbilityId id) throws Exception {
        return bioSpesifikasiAbilityDao.getEntityByBioSpesifikasiAbilityId(id);
    }
    
}
