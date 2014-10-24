/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.DepartementUploadCaptureDao;
import com.inkubator.hrm.dao.MacineFingerUploadDao;
import com.inkubator.hrm.dao.MecineFingerDao;
import com.inkubator.hrm.entity.DepartementUploadCapture;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.MacineFingerUpload;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.model.FingerUploadModel;
import com.inkubator.hrm.web.model.MecineFingerQueryModel;
import com.inkubator.hrm.web.model.MecineFingerServiceModel;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
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
 * @author Deni Husni FR
 */
@Service(value = "mecineFingerService")
@Lazy
public class MecineFingerServiceImpl extends IServiceImpl implements MecineFingerService {

    @Autowired
    private MecineFingerDao mecineFingerDao;
    @Autowired
    private DepartementUploadCaptureDao departementUploadCaptureDao;
    @Autowired
    private MacineFingerUploadDao macineFingerUploadDao;

    @Override
    public MecineFinger getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public MecineFinger getEntiyByPK(Long id) throws Exception {
        return mecineFingerDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(MecineFinger entity) throws Exception {
        // check duplicate name
        long totalDuplicates = mecineFingerDao.getByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("mecinefinger.error_duplicate_mecinefinger_code");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setName(entity.getName());
        entity.setMecineMethode(entity.getMecineMethode());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.mecineFingerDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(MecineFinger entity) throws Exception {
        // check duplicate name
        long totalDuplicates = mecineFingerDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("mecinefinger.error_duplicate_mecinefinger_code");
        }
        MecineFinger update = mecineFingerDao.getEntiyByPK(entity.getId());
        update.setName(entity.getName());
        update.setCode(entity.getCode());
        update.setDescription(entity.getDescription());
        update.setMecineMethode(entity.getMecineMethode());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.mecineFingerDao.update(update);
    }

    @Override
    public void saveOrUpdate(MecineFinger enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger saveData(MecineFinger entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger updateData(MecineFinger entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger saveOrUpdateData(MecineFinger entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MecineFinger getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(MecineFinger entity) throws Exception {
        this.mecineFingerDao.delete(entity);
    }

    @Override
    public void softDelete(MecineFinger entity) throws Exception {
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
    public List<MecineFinger> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MecineFinger> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<MecineFinger> getByParam(MecineFingerSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return mecineFingerDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(MecineFingerSearchParameter parameter) throws Exception {
        return mecineFingerDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public MecineFinger getMecineFingerAndDetaiUploadByFK(long id) throws Exception {
        MecineFinger mecineFinger = this.mecineFingerDao.getMecineFingerAndDetaiUploadByFK(id);
        List<Department> data = new ArrayList<>();
        for (DepartementUploadCapture departementUploadCapture : departementUploadCaptureDao.getByMecineFingerId(id)) {
            data.add(departementUploadCapture.getDepartment());
        }
        mecineFinger.setDepartments(data);
        return mecineFinger;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveByModel(FingerUploadModel fingerUploadModel, Set<DepartementUploadCapture> dataToSave) throws Exception {
        MecineFinger mecineFinger = this.mecineFingerDao.getEntiyByPK(fingerUploadModel.getId());
        mecineFinger.getMacineFingerUploads().clear();
        mecineFinger.getDepartementUploadCaptures().clear();
        mecineFinger.setFileType(fingerUploadModel.getUploadType());
        mecineFinger.setFileExtension(fingerUploadModel.getExtensionType());
        mecineFinger.setInOutStatus(fingerUploadModel.getItialInOut());
        mecineFinger.setBaseOnField(fingerUploadModel.getFieldNumber());
        mecineFinger.setUpdatedBy(UserInfoUtil.getUserName());
        mecineFinger.setUpdatedOn(new Date());
        mecineFingerDao.saveAndMerge(mecineFinger);

        List<MacineFingerUpload> dataMecineToFingerUpload = fingerUploadModel.getDataToSave();
        for (MacineFingerUpload macineFingerUpload : dataMecineToFingerUpload) {
            macineFingerUpload.setMecineFinger(mecineFinger);
            macineFingerUpload.setCreatedBy(UserInfoUtil.getUserName());
            macineFingerUpload.setCreatedOn(new Date());
            macineFingerUploadDao.save(macineFingerUpload);

        }
        for (DepartementUploadCapture capture : dataToSave) {
            capture.setMecineFinger(mecineFinger);
            departementUploadCaptureDao.save(capture);
        }

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveMesineService(MecineFingerServiceModel serviceModel) throws Exception {
        MecineFinger mecineFinger = this.mecineFingerDao.getEntiyByPK(serviceModel.getId());
        mecineFinger.setServiceHost(serviceModel.getHostIp());
        mecineFinger.setServiceType(serviceModel.getServiceData());
        mecineFinger.setServiceOpenProtocol(serviceModel.getProtocolData());
        mecineFinger.setServiceOpenProtocolPassword(serviceModel.getOpenProtocolPassword());
        mecineFinger.setMatchBase(serviceModel.getEmployeeBaseId());
        mecineFinger.setUpdatedBy(UserInfoUtil.getUserName());
        mecineFinger.setUpdatedOn(new Date());
        mecineFingerDao.update(mecineFinger);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveMesineQuery(MecineFingerQueryModel serviceModel) throws Exception {
        MecineFinger mecineFinger = this.mecineFingerDao.getEntiyByPK(serviceModel.getId());
        mecineFinger.setDbHost(serviceModel.getDbHost());
        mecineFinger.setDbPass(serviceModel.getDbPassword());
        mecineFinger.setDbSwapBase(serviceModel.getSwapTimeFieldName());
        mecineFinger.setDbType(serviceModel.getDbType());
        mecineFinger.setDbUser(serviceModel.getDbUserName());
        mecineFinger.setQuery(serviceModel.getDbQuery());
        mecineFinger.setMatchBase(serviceModel.getEmployeeIdFieldName());
         mecineFinger.setUpdatedBy(UserInfoUtil.getUserName());
        mecineFinger.setUpdatedOn(new Date());
        mecineFingerDao.update(mecineFinger);
    }
}
