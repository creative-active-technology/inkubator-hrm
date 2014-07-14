/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.HrmRoleDao;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.entity.HrmMenuRole;
import com.inkubator.hrm.entity.HrmMenuRoleId;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.HashSet;
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
 * @author Deni Husni FR
 */
@Service(value = "hrmRoleService")
@Lazy
public class HrmRoleServiceImpl extends IServiceImpl implements HrmRoleService {

    @Autowired
    private HrmRoleDao hrmRoleDao;

    @Override
    public HrmRole getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmRole getEntiyByPK(Long id) throws Exception {
        return this.hrmRoleDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(HrmRole entity) throws Exception {
        entity.setId(Integer.parseInt(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.hrmRoleDao.save(entity);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(HrmRole role, List<HrmMenu> menus) throws Exception {
    	
        role.setId(Integer.parseInt(RandomNumberUtil.getRandomNumber(9)));
        role.setCreatedBy(UserInfoUtil.getUserName());
        role.setCreatedOn(new Date());
        
        //set children (menuRoles)
        Set<HrmMenuRole> listMenuRoles = new HashSet<HrmMenuRole>();
        for(HrmMenu menu:menus){
        	HrmMenuRole menuRole = new HrmMenuRole();
        	menuRole.setId(new HrmMenuRoleId(role.getId(), menu.getId()));
        	menuRole.setHrmMenu(menu);
        	menuRole.setHrmRole(role);
        	listMenuRoles.add(menuRole);
        }
        
        role.setHrmMenuRoles(listMenuRoles);        
        this.hrmRoleDao.save(role);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(HrmRole entity) throws Exception {
        HrmRole hrmRoleToUpdate=this.hrmRoleDao.getEntiyByPK(entity.getId());
        hrmRoleToUpdate.setDescription(entity.getDescription());
        hrmRoleToUpdate.setRoleName(entity.getRoleName());
        hrmRoleToUpdate.setUpdatedBy(UserInfoUtil.getUserName());
        hrmRoleToUpdate.setUpdatedOn(new Date());
        this.hrmRoleDao.update(hrmRoleToUpdate);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(HrmRole r, List<HrmMenu> menus) throws Exception {
        HrmRole role=this.hrmRoleDao.getEntiyByPK(r.getId());
        role.setDescription(r.getDescription());
        role.setRoleName(r.getRoleName());
        role.setUpdatedBy(UserInfoUtil.getUserName());
        role.setUpdatedOn(new Date());
        
        //set children (menuRoles)
        Set<HrmMenuRole> listMenuRoles = new HashSet<HrmMenuRole>();
        for(HrmMenu menu:menus){
        	HrmMenuRole menuRole = new HrmMenuRole();
        	menuRole.setId(new HrmMenuRoleId(role.getId(), menu.getId()));
        	menuRole.setHrmMenu(menu);
        	menuRole.setHrmRole(role);
        	listMenuRoles.add(menuRole);
        }
        
        /* 
         * role.setHrmMenuRoles(listMenuRoles);
         * when saving many to many or childrens objects, if you do (like above) code, it will shown an error
         * Instead of replacing the set by another one, clear the set and add the new children to the cleared set (like below code)           
        */
        role.getHrmMenuRoles().clear();
        role.getHrmMenuRoles().addAll(listMenuRoles);
        
        /* Hibernate merge method will force Hibernate to copy any changes from other detached instances onto the instance you want to save, 
         * and thus merges all the changes in memory before the save. It use when dealing with many to many saving relationship */
        this.hrmRoleDao.updateAndMerge(role);
    }

    @Override
    public void saveOrUpdate(HrmRole enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole saveData(HrmRole entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole updateData(HrmRole entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole saveOrUpdateData(HrmRole entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmRole getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(HrmRole entity) throws Exception {
        this.hrmRoleDao.delete(entity);
    }

    @Override
    public void softDelete(HrmRole entity) throws Exception {
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
    public List<HrmRole> getAllData() throws Exception {
      return this.hrmRoleDao.getAllData();
    }

    @Override
    public List<HrmRole> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmRole> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmRole> getByParam(HrmRoleSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.hrmRoleDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalHrmRoleByParam(HrmRoleSearchParameter searchParameter) throws Exception {
        return this.hrmRoleDao.getTotalHrmRoleByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmRole getByRoleName(String roleName) throws Exception {
        return this.hrmRoleDao.getByRoleName(roleName);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public HrmRole getEntityByPkWithMenus(long id) throws Exception {
		return this.hrmRoleDao.getEntityByPkWithMenus(id);
		
	}

}
