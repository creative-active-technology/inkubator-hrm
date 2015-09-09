package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.JabatanMajorDao;
import com.inkubator.hrm.dao.MajorDao;
import com.inkubator.hrm.entity.JabatanMajor;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
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
 * @author Taufik Hidayat
 */
@Service(value = "majorService")
@Lazy
public class MajorServiceImpl extends IServiceImpl implements MajorService {

    @Autowired
    private MajorDao majorDao;
    @Autowired
    private JabatanMajorDao jabatanMajorDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Major major) throws Exception {
        majorDao.delete(major);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Major> getAllData() throws Exception {
        return this.majorDao.getAllData();
    }

    @Override
    public List<Major> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Major> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Major getEntiyByPK(Long id) throws Exception {
        return majorDao.getEntiyByPK(id);
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Major major) throws Exception {
        // check duplicate name
        long totalDuplicates = majorDao.getTotalByName(major.getName());
        if (totalDuplicates > 0) {
            throw new BussinessException("major.error_duplicate_major_name");
        }

        major.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        major.setCreatedBy(UserInfoUtil.getUserName());
        major.setCreatedOn(new Date());
        majorDao.save(major);
    }

    @Override
    public Major saveData(Major arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(Major arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Major saveOrUpdateData(Major arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(Major arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Major r) throws Exception {
        // check duplicate name
        long totalDuplicates = majorDao.getTotalByNameAndNotId(r.getName(), r.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("major.error_duplicate_major_name");
        }

        Major major = majorDao.getEntiyByPK(r.getId());
        major.setName(r.getName());
        major.setDescription(r.getDescription());
        major.setUpdatedBy(UserInfoUtil.getUserName());
        major.setUpdatedOn(new Date());
        majorDao.update(major);
    }

    @Override
    public Major updateData(Major arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Major> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.majorDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalMajorByParam(String parameter) throws Exception {
        return this.majorDao.getTotalMajorByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Major getEntityByPkWithDetail(Long id) throws Exception {
        Major major = new Major();
        List<Major> listMajors = new ArrayList<>();
        for (JabatanMajor jabatanMajor : this.jabatanMajorDao.getAllDataByJabatanId(id)) {
            listMajors.add(jabatanMajor.getMajor());
        }
     
        major.setListMajors(listMajors);
        return major;
    }

}
