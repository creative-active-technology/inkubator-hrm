package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.PaCompetencyPoint;
import com.inkubator.hrm.dao.PaCompetencyPointDao;
import com.inkubator.hrm.service.PaCompetencyPointService;
import com.inkubator.hrm.web.search.PaCompetencyPointSearchParameter;
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
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.common.util.RandomNumberUtil;

/**
 *
 * @author WebGenX
 */
@Service(value = "paCompetencyPointService")
@Lazy
public class PaCompetencyPointServiceImpl extends IServiceImpl implements PaCompetencyPointService {

    @Autowired
    private PaCompetencyPointDao paCompetencyPointDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PaCompetencyPoint paCompetencyPoint) throws Exception {
        paCompetencyPointDao.delete(paCompetencyPoint);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(PaCompetencyPoint paCompetencyPoint) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = paCompetencyPointDao.getTotalByCode(paCompetencyPoint.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("paCompetencyPoint.error_duplicate_code")
//}
        paCompetencyPoint.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        paCompetencyPoint.setCreatedBy(UserInfoUtil.getUserName());
        paCompetencyPoint.setCreatedOn(new Date());
        paCompetencyPointDao.save(paCompetencyPoint);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PaCompetencyPoint paCompetencyPoint) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = paCompetencyPointDao.getTotalByCodeAndNotId(paCompetencyPoint.getCode(),paCompetencyPoint.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("paCompetencyPoint.error_duplicate_code")
//}
        PaCompetencyPoint paCompetencyPoint1 = paCompetencyPointDao.getEntiyByPK(paCompetencyPoint.getId());
        paCompetencyPoint1.setUpdatedBy(UserInfoUtil.getUserName());
        paCompetencyPoint1.setUpdatedOn(new Date());
        paCompetencyPoint1.setPaCompetencies(paCompetencyPoint.getPaCompetencies());
        paCompetencyPoint1.setCompetencyPointScore(paCompetencyPoint.getCompetencyPointScore());
        paCompetencyPoint1.setDescription(paCompetencyPoint.getDescription());
        paCompetencyPoint1.setName(paCompetencyPoint.getName());
        paCompetencyPoint1.setCode(paCompetencyPoint.getCode());
        paCompetencyPointDao.update(paCompetencyPoint1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaCompetencyPoint getEntiyByPK(Long id) {
        return this.paCompetencyPointDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaCompetencyPoint> getByParam(PaCompetencyPointSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.paCompetencyPointDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPaCompetencyPointByParam(PaCompetencyPointSearchParameter searchParameter) throws Exception {
        return this.paCompetencyPointDao.getTotalPaCompetencyPointByParam(searchParameter);
    }

    @Override
    public PaCompetencyPoint getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(PaCompetencyPoint enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint saveData(PaCompetencyPoint entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint updateData(PaCompetencyPoint entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint saveOrUpdateData(PaCompetencyPoint entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaCompetencyPoint getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(PaCompetencyPoint entity) throws Exception {
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
    public List<PaCompetencyPoint> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaCompetencyPoint> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
