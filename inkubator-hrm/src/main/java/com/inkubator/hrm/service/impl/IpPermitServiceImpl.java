/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.IpPermitDao;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.service.IpPermitService;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
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
@Service(value = "ipPermitService")
@Lazy
public class IpPermitServiceImpl extends IServiceImpl implements IpPermitService {

    @Autowired
    private IpPermitDao ipPermitDao;

    @Override
    public IpPermit getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public IpPermit getEntiyByPK(Long id) throws Exception {
        return ipPermitDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(IpPermit entity) throws Exception {
        if (entity.getFromAddress1() == entity.getFromAddress2() && entity.getUntilAddress1() == entity.getUntilAddress2()) {
            throw new BussinessException("ippermit.ip_permit_is_same_value");
        }
        List<IpPermit> listIpPermit = ipPermitDao.getAllData();
        for (IpPermit ipPermit : listIpPermit) {
            if (entity.getFromAddress1().equals(ipPermit.getFromAddress1()) && entity.getUntilAddress1() <= ipPermit.getUntilAddress1()) {
                throw new BussinessException("ippermit.range_api_adress_sudah_ada");
            }

            if (entity.getFromAddress2().equals(ipPermit.getFromAddress2()) && entity.getUntilAddress2() <= ipPermit.getUntilAddress2()) {
                throw new BussinessException("ippermit.range_api_adress_sudah_ada");
            }
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.ipPermitDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(IpPermit entity) throws Exception {
        if (entity.getFromAddress1() == entity.getFromAddress2() && entity.getUntilAddress1() == entity.getUntilAddress2()) {
            throw new BussinessException("ippermit.ip_permit_is_same_value");
        }
//        List<IpPermit> listIpPermit = ipPermitDao.getAllData();
//        for (IpPermit ipPermit : listIpPermit) {
//            if(entity.getFromAddress1().equals(ipPermit.getFromAddress1()) && entity.getUntilAddress1() <= ipPermit.getUntilAddress1()){
//                throw new BussinessException("ippermit.range_api_adress_sudah_ada");
//            }
//            
//            if(entity.getFromAddress2().equals(ipPermit.getFromAddress2()) && entity.getUntilAddress2() <= ipPermit.getUntilAddress2()){
//                throw new BussinessException("ippermit.range_api_adress_sudah_ada");
//            }
//        }

        IpPermit update = ipPermitDao.getEntiyByPK(entity.getId());
        update.setFromAddress1(entity.getFromAddress1());
        update.setFromAddress2(entity.getFromAddress2());
        update.setUntilAddress1(entity.getUntilAddress1());
        update.setUntilAddress2(entity.getUntilAddress2());
        update.setLokasi(entity.getLokasi());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        ipPermitDao.update(update);
    }

    @Override
    public void saveOrUpdate(IpPermit enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit saveData(IpPermit entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit updateData(IpPermit entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit saveOrUpdateData(IpPermit entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IpPermit getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(IpPermit entity) throws Exception {
        this.ipPermitDao.delete(entity);
    }

    @Override
    public void softDelete(IpPermit entity) throws Exception {
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
    public List<IpPermit> getAllData() throws Exception {
        return ipPermitDao.getAllData();
    }

    @Override
    public List<IpPermit> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IpPermit> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<IpPermit> getByParam(IpPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return ipPermitDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalIpPermitByParam(IpPermitSearchParameter searchParameter) throws Exception {
        return ipPermitDao.getTotalIpPermitByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getByIpPermitLocation(String location) throws Exception {
        return this.ipPermitDao.getByIpPermitLocation(location);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)

    public List<IpPermit> getByIpHeader(int ipHeader) throws Exception {
        return this.ipPermitDao.getByIpHeader(ipHeader);
    }

}
