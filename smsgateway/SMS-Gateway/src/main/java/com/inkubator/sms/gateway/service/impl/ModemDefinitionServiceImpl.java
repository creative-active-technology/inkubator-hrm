/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.sms.gateway.BussinessException;
import com.inkubator.sms.gateway.dao.ModemDefinitionDao;
import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.service.ModemDefinitionService;
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
@Service(value = "modemDefinitionService")
@Lazy
public class ModemDefinitionServiceImpl extends IServiceImpl implements ModemDefinitionService {

    @Autowired
    private ModemDefinitionDao modemDefinitionDao;

    @Override
    public ModemDefinition getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public ModemDefinition getEntiyByPK(Long id) throws Exception {
        return this.modemDefinitionDao.getByFullText(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ModemDefinition entity) throws Exception {
        long totalDuplicate = this.modemDefinitionDao.getTotalByModemId(entity.getModemId());
        if (totalDuplicate > 0) {
            throw new BussinessException("Data dengan modem id :" + entity.getModemId() + " telah ada di database. Silahkan pilih modem id yang lain");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
//        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedBy("System");
        entity.setCreatedOn(new Date());
        this.modemDefinitionDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(ModemDefinition entity) throws Exception {
        long totalDuplicate = this.modemDefinitionDao.getTotalByModemIdAndNotId(entity.getModemId(), entity.getId());
        if (totalDuplicate > 0) {
            throw new BussinessException("Data dengan modem id :" + entity.getModemId() + " telah ada di database. Silahkan pilih modem id yang lain");
        }
        ModemDefinition definition = this.modemDefinitionDao.getEntiyByPK(entity.getId());
        definition.setBaudRate(entity.getBaudRate());
        definition.setComId(entity.getComId());
        definition.setCurrentValue(entity.getCurrentValue());
        definition.setManufacture(entity.getManufacture());
        definition.setModel(entity.getModel());
        definition.setModemId(entity.getModemId());
        definition.setPinNumber(entity.getPinNumber());
        definition.setPricePerSms(entity.getPricePerSms());
        definition.setSmscNumber(entity.getSmscNumber());
        definition.setUpdatedBy("System");
        definition.setUpdatedOn(new Date());
        definition.setCheckPulsa(entity.getCheckPulsa());
        definition.setPhoneNumber(entity.getPhoneNumber());
        this.modemDefinitionDao.update(definition);
    }

    @Override
    public void saveOrUpdate(ModemDefinition enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition saveData(ModemDefinition entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition updateData(ModemDefinition entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition saveOrUpdateData(ModemDefinition entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModemDefinition getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ModemDefinition entity) throws Exception {
        this.modemDefinitionDao.delete(entity);
    }

    @Override
    public void softDelete(ModemDefinition entity) throws Exception {
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
    public List<ModemDefinition> getAllData() throws Exception {
        return this.modemDefinitionDao.getAllData();
    }

    @Override
    public List<ModemDefinition> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModemDefinition> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
     @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ModemDefinition> getAllByFullText(String param) throws Exception {
     return this.modemDefinitionDao.getAllByFullText(param);
    }

}
