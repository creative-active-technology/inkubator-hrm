/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.OrgTypeOfSpecDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListKlasifikasiDao;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author EKA
 */
@Service(value = "orgTypeOfSpecListService")
@Lazy
public class OrgTypeOfSpecListServiceImpl extends IServiceImpl implements OrgTypeOfSpecListService {

    @Autowired
    private OrgTypeOfSpecListDao orgTypeOfSpecListDao;

    @Autowired
    private OrgTypeOfSpecDao orgTypeOfSpecDao;
    
    @Autowired
    private OrgTypeOfSpecListKlasifikasiDao orgTypeOfSpecListKlasifikasiDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgTypeOfSpecList> getByParam(OrgTypeOfSpecListSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return orgTypeOfSpecListDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalOrgTypeOfSpecListByParam(OrgTypeOfSpecListSearchParameter searchParameter) throws Exception {
        return orgTypeOfSpecListDao.getTotalOrgTypeOfSpecListByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public OrgTypeOfSpecList getSpecTypeNameByOrgTypeOfSpecListId(Long id) throws Exception {
        return this.orgTypeOfSpecListDao.getSpecTypeNameByOrgTypeOfSpecListId(id);
    }

    @Override
    public OrgTypeOfSpecList getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrgTypeOfSpecList getEntiyByPK(Long id) throws Exception {
        return orgTypeOfSpecListDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OrgTypeOfSpecList entity) throws Exception {
        long totalDuplicates = orgTypeOfSpecListDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("marital.error_duplicate_marital_code");
        }
        /*entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));*/
        entity.setOrgTypeOfSpec(orgTypeOfSpecDao.getEntiyByPK(entity.getOrgTypeOfSpec().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.orgTypeOfSpecListDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OrgTypeOfSpecList entity) throws Exception {
        long totalDuplicates = orgTypeOfSpecListDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("marital.error_duplicate_marital_code");
        }
        OrgTypeOfSpecList update = orgTypeOfSpecListDao.getEntiyByPK(entity.getId());
        update.getOrgTypeOfSpecListKlasifikasis().clear();
        update.setCode(entity.getCode());
        update.setName(entity.getName());
        update.setDescription(entity.getDescription());
        update.setOrgTypeOfSpec(orgTypeOfSpecDao.getEntiyByPK(entity.getOrgTypeOfSpec().getId()));
        update.setUpdatedBy(UserInfoUtil.getUserName());
        this.orgTypeOfSpecListDao.saveAndMerge(update);
        Set<OrgTypeOfSpecListKlasifikasi> dataToSave = entity.getOrgTypeOfSpecListKlasifikasis();
        System.out.println(dataToSave.size() + " size dari service");
        for (OrgTypeOfSpecListKlasifikasi orgTypeOfSpecListKlasifikasi : dataToSave) {
        	orgTypeOfSpecListKlasifikasi.setOrgTypeOfSpecList(update);
            this.orgTypeOfSpecListKlasifikasiDao.save(orgTypeOfSpecListKlasifikasi);
        }
    }

    @Override
    public void saveOrUpdate(OrgTypeOfSpecList t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList saveData(OrgTypeOfSpecList t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList updateData(OrgTypeOfSpecList t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList saveOrUpdateData(OrgTypeOfSpecList t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecList getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OrgTypeOfSpecList entity) throws Exception {
        this.orgTypeOfSpecListDao.delete(entity);
    }

    @Override
    public void softDelete(OrgTypeOfSpecList t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgTypeOfSpecList> getAllData() throws Exception {
        return orgTypeOfSpecListDao.getAllData();
    }

    @Override
    public List<OrgTypeOfSpecList> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecList> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<DualListModel> getAllBySpectJabatan() throws Exception {
        List<DualListModel> dataAll = new ArrayList<>();
        List<OrgTypeOfSpec> listOrgTypeOfSpecs = this.orgTypeOfSpecDao.getAllData();
        for (OrgTypeOfSpec listOrgTypeOfSpec : listOrgTypeOfSpecs) {
            DualListModel<OrgTypeOfSpecList> dualListModel = new DualListModel<>();
            List<OrgTypeOfSpecList> listToInsert = this.orgTypeOfSpecListDao.getOrgTypeOfSpecList(listOrgTypeOfSpec.getId());
            dualListModel.setSource(listToInsert);
            dataAll.add(dualListModel);
        }
        return dataAll;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<String> getOrgTypeSpecName() throws Exception {
        List<String> data = new ArrayList<>();
        List<OrgTypeOfSpec> list = this.orgTypeOfSpecDao.getAllData();
        for (OrgTypeOfSpec ofSpec : list) {
            data.add(ofSpec.getName());
        }
        return data;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgTypeOfSpecList> getAllDataByOrgTypeOfSpecIdAndOrderByCode(Long id) throws Exception {
        return orgTypeOfSpecListDao.getAllDataByOrgTypeOfSpecIdAndOrderByCode(id);
    }

    @Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public OrgTypeOfSpecList getAllDataWithDetail(Long id) throws Exception {
		OrgTypeOfSpecList orgTypeOfSpecList = orgTypeOfSpecListDao.getEntityByPkWithDetail(id);
        List<KlasifikasiKerja> klasifikasiKerja = new ArrayList<>();
        List<OrgTypeOfSpecListKlasifikasi> listOrgTypeOfSpecListKlasifikasi = orgTypeOfSpecListKlasifikasiDao.getByorgTypeOfSpecListId(id);
        for (OrgTypeOfSpecListKlasifikasi orgTypeOfSpecListKlasifikasi : this.orgTypeOfSpecListKlasifikasiDao.getByorgTypeOfSpecListId(id)) {
        	klasifikasiKerja.add(orgTypeOfSpecListKlasifikasi.getKlasifikasiKerja());
        }
        orgTypeOfSpecList.setKlasifikasiKerja(klasifikasiKerja);
        return orgTypeOfSpecList;
	}


}
