/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RecruitSelectionTypeTemplatesDao;
import com.inkubator.hrm.dao.RecruitSelectionTypeTemplatesJobTitleDao;
import com.inkubator.hrm.dao.SystemScoringDao;
import com.inkubator.hrm.entity.DepartementUnitLocation;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplatesJobTitle;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.RecruitSelectionTypeTemplatesService;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deni.fahri
 */
@Service(value = "recruitSelectionTypeTemplatesService")
@Lazy
public class RecruitSelectionTypeTemplatesServiceImpl extends IServiceImpl implements RecruitSelectionTypeTemplatesService {

    @Autowired
    private RecruitSelectionTypeTemplatesDao recruitSelectionTypeTemplatesDao;
    @Autowired
    private SystemScoringDao systemScoringDao;
    @Autowired
    private RecruitSelectionTypeTemplatesJobTitleDao recruitSelectionTypeTemplatesJobTitleDao;

    @Override
    public RecruitSelectionTypeTemplates getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitSelectionTypeTemplates entity) throws Exception {
        long totalDuplicates = recruitSelectionTypeTemplatesDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }
        RecruitSelectionTypeTemplates parent = this.recruitSelectionTypeTemplatesDao.getEntiyByPK(entity.getRecruitSelectionTypeTemplates().getId());
        SystemScoring scoring = this.systemScoringDao.getEntiyByPK(entity.getSystemScoring().getId());
        entity.setRecruitSelectionTypeTemplates(parent);
        entity.setSystemScoring(scoring);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.recruitSelectionTypeTemplatesDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitSelectionTypeTemplates entity) throws Exception {
        long totalDuplicates = recruitSelectionTypeTemplatesDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());

        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }
        RecruitSelectionTypeTemplates parent = this.recruitSelectionTypeTemplatesDao.getEntiyByPK(entity.getRecruitSelectionTypeTemplates().getId());
        SystemScoring scoring = this.systemScoringDao.getEntiyByPK(entity.getSystemScoring().getId());
        RecruitSelectionTypeTemplates rstt = this.recruitSelectionTypeTemplatesDao.getEntiyByPK(entity.getId());
        rstt.getRecruitSelectionTypeTemplatesJobTitles().clear();
        rstt.setCode(entity.getCode());
        rstt.setDescription(entity.getDescription());
        rstt.setIsActive(entity.getIsActive());
        rstt.setIsCategory(entity.getIsCategory());
        rstt.setName(entity.getName());
        rstt.setRecruitSelectionTypeTemplates(parent);
        rstt.setSystemScoring(scoring);
        rstt.setTargetNilai(entity.getTargetNilai());
        rstt.setUpdatedBy(UserInfoUtil.getUserName());
        rstt.setUpdatedOn(new Date());
        this.recruitSelectionTypeTemplatesDao.saveAndMerge(rstt);
        Set<RecruitSelectionTypeTemplatesJobTitle> dataToSave = entity.getRecruitSelectionTypeTemplatesJobTitles();
        for (RecruitSelectionTypeTemplatesJobTitle templates : dataToSave) {
            templates.setRecruitSelectionTypeTemplates(rstt);
            this.recruitSelectionTypeTemplatesJobTitleDao.save(templates);
        }

    }

    @Override
    public void saveOrUpdate(RecruitSelectionTypeTemplates enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates saveData(RecruitSelectionTypeTemplates entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates updateData(RecruitSelectionTypeTemplates entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates saveOrUpdateData(RecruitSelectionTypeTemplates entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionTypeTemplates getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(RecruitSelectionTypeTemplates entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitSelectionTypeTemplates entity) throws Exception {
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
    public List<RecruitSelectionTypeTemplates> getAllData() throws Exception {
        return this.recruitSelectionTypeTemplatesDao.getAllData();
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionTypeTemplates> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RecruitSelectionTypeTemplates getLevelOne(String root) throws Exception {
        RecruitSelectionTypeTemplates data = recruitSelectionTypeTemplatesDao.getLevelOne(root);

        if (data == null) {
            RecruitSelectionTypeTemplates rstt = new RecruitSelectionTypeTemplates();
            rstt.setId(Long.parseLong("1"));
            rstt.setCode("ROOT");
            rstt.setName("ROOT");
            rstt.setTargetNilai(0.0);
            System.out.println(" ini di keseskkseeksk");
            rstt.setCreatedBy(UserInfoUtil.getUserName());
            rstt.setCreatedOn(new Date());
            recruitSelectionTypeTemplatesDao.save(rstt);
            RecruitSelectionTypeTemplates st = new RecruitSelectionTypeTemplates();
            st.setId(Long.parseLong("2"));
            st.setCode("MASTER");
            st.setName("TYPE SELECTION");
            st.setTargetNilai(0.0);
            st.setIsCategory(Boolean.TRUE);
            st.setCreatedBy(UserInfoUtil.getUserName());
            st.setCreatedOn(new Date());
            st.setRecruitSelectionTypeTemplates(rstt);
            recruitSelectionTypeTemplatesDao.save(st);
            data = recruitSelectionTypeTemplatesDao.getLevelOne(root);
        }
        return data;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitSelectionTypeTemplates> getByParentId(long id) throws Exception {
        return this.recruitSelectionTypeTemplatesDao.getByParentId(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public TreeNode cretaeNodeBreakEndPoint(String param) throws Exception {
        TreeNode root;
        RecruitSelectionTypeTemplates endPointTipeTemplate = recruitSelectionTypeTemplatesDao.getEntiyByPK(Long.parseLong(param.substring(1)));
        List<RecruitSelectionTypeTemplates> data = new ArrayList<>();
        data.add(endPointTipeTemplate);
        gerParent(endPointTipeTemplate, data);
        Collections.reverse(data);
        RecruitSelectionTypeTemplates master = data.get(0);
        data.remove(0);
        root = new DefaultTreeNode(master, null);
        int i = 1;
        TreeNode before = new DefaultTreeNode();
        for (RecruitSelectionTypeTemplates data1 : data) {
            if (i == 1) {
                TreeNode node = new DefaultTreeNode(data1, root);
                node.setExpanded(true);
                before = node;
            } else {
                TreeNode node = new DefaultTreeNode(data1, before);
                node.setExpanded(true);
                before = node;
            }
            i++;
        }
        return root;

    }

    private void gerParent(RecruitSelectionTypeTemplates rstt, List<RecruitSelectionTypeTemplates> list) {
        RecruitSelectionTypeTemplates data = rstt.getRecruitSelectionTypeTemplates();
        list.add(data);
        if (data.getRecruitSelectionTypeTemplates() != null) {
            gerParent(data, list);
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitSelectionTypeTemplates getWithJabatan(long id) throws Exception {
        RecruitSelectionTypeTemplates recruitSelectionTypeTemplates = this.recruitSelectionTypeTemplatesDao.getEntiyByPK(id);
        List<Jabatan> dataToShow = new ArrayList<>();
        for (RecruitSelectionTypeTemplatesJobTitle jobTitle : recruitSelectionTypeTemplatesJobTitleDao.getByRecruitSelectionTypeTemplatesId(id)) {
            jobTitle.getJabatan().getName();
            dataToShow.add(jobTitle.getJabatan());
        }
        recruitSelectionTypeTemplates.setListJabatan(dataToShow);
        recruitSelectionTypeTemplates.getRecruitSelectionTypeTemplates().getCode();
        return recruitSelectionTypeTemplates;
    }

}
