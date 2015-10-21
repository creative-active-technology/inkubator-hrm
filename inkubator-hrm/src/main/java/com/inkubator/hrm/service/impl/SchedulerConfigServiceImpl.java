/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
@Service(value = "schedulerConfigService")
@Lazy
public class SchedulerConfigServiceImpl extends IServiceImpl implements SchedulerConfigService {

    @Autowired
    private SchedulerConfigDao schedulerConfigDao;

    @Override
    public SchedulerConfig getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public SchedulerConfig getEntiyByPK(Long id) throws Exception {
        return this.schedulerConfigDao.getEntiyByPK(id);
    }

    @Override// Harus serialiasai agar tidak tumpang tindih dengan proses schedulerr yang berjalan
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(SchedulerConfig entity) throws Exception {
        long totalDuplicates = schedulerConfigDao.getTotalByName(entity.getName());
        if (totalDuplicates > 0) {
            throw new BussinessException("scheduler_config.error_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCretedOn(new Date());
        if (entity.getIsTimeDiv()) {
            Date now=new Date();
            String nowString=new SimpleDateFormat("dd MM yyyy HH:mm").format(now);
//            entity.setLastExecution(new SimpleDateFormat("dd MM yyyy HH:mm").parse(nowString));
               entity.setLastExecution(DateUtils.truncate(now, Calendar.MINUTE));
        }
        schedulerConfigDao.save(entity);
    }

    @Override //Harus serialiasai agar tidak tumpang tindih dengan proses schedulerr yang berjalan
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(SchedulerConfig entity) throws Exception {
        long totalDuplicate = schedulerConfigDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicate > 0) {
            throw new BussinessException("scheduler_config.error_name");
        }
        SchedulerConfig schedulerConfig = schedulerConfigDao.getEntiyByPK(entity.getId());
        schedulerConfig.setDateStartExecution(entity.getDateStartExecution());
        schedulerConfig.setEndDate(entity.getEndDate());
        schedulerConfig.setIsTimeDiv(entity.getIsTimeDiv());
//        schedulerConfig.setLastExecution(entity.getLastExecution());
        schedulerConfig.setName(entity.getName());
        schedulerConfig.setRepeateNumber(entity.getRepeateNumber());
        schedulerConfig.setRepeateType(entity.getRepeateType());
        schedulerConfig.setSchedullerTime(entity.getSchedullerTime());
        schedulerConfig.setSchedullerType(entity.getSchedullerType());
        schedulerConfig.setStartDate(entity.getStartDate());
        schedulerConfig.setHourDiv(entity.getHourDiv());
        schedulerConfig.setMinuteDiv(entity.getMinuteDiv());
        schedulerConfig.setUpdatedBy(UserInfoUtil.getUserName());
        schedulerConfig.setUpdatedOn(new Date());
        schedulerConfig.setIsActive(entity.getIsActive());
        if (entity.getIsTimeDiv()) {
            Date now=new Date();
            String nowString=new SimpleDateFormat("dd MM yyyy HH:mm").format(now);
//            schedulerConfig.setLastExecution(new SimpleDateFormat("dd MM yyyy HH:mm").parse(nowString));
                schedulerConfig.setLastExecution(DateUtils.truncate(now, Calendar.MINUTE));
        }
        schedulerConfigDao.update(schedulerConfig);
    }

    @Override
    public void saveOrUpdate(SchedulerConfig enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig saveData(SchedulerConfig entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig updateData(SchedulerConfig entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig saveOrUpdateData(SchedulerConfig entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SchedulerConfig getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(SchedulerConfig entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(SchedulerConfig entity) throws Exception {
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
    public List<SchedulerConfig> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SchedulerConfig> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SchedulerConfig> getByParam(SchedulerConfigSearchParameter searchParameter, int firstResult,
            int maxResults, Order order) throws Exception {
        return this.schedulerConfigDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(SchedulerConfigSearchParameter searchParameter) throws Exception {
        return this.schedulerConfigDao.getTotalByParam(searchParameter);
    }

}
