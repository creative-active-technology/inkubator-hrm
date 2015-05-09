package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

/**
 *
 * @author WebGenX
 */
@Service(value = "recruitHireApplyService")
@Lazy
public class RecruitHireApplyServiceImpl extends BaseApprovalServiceImpl implements RecruitHireApplyService {

    @Autowired
    private RecruitHireApplyDao recruitHireApplyDao;
    
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    
    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitHireApply recruitHireApply) throws Exception {
        recruitHireApplyDao.delete(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCode(recruitHireApply.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        recruitHireApply.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        recruitHireApply.setCreatedBy(UserInfoUtil.getUserName());
        recruitHireApply.setCreatedOn(new Date());
        recruitHireApplyDao.save(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCodeAndNotId(recruitHireApply.getCode(),recruitHireApply.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        RecruitHireApply recruitHireApply1 = recruitHireApplyDao.getEntiyByPK(recruitHireApply.getId());
        recruitHireApply1.setUpdatedBy(UserInfoUtil.getUserName());
        recruitHireApply1.setUpdatedOn(new Date());
        recruitHireApply1.setEmployeeType(recruitHireApply.getEmployeeType());
        recruitHireApply1.setSalaryMax(recruitHireApply.getSalaryMax());
        recruitHireApply1.setReason(recruitHireApply.getReason());
        recruitHireApply1.setProposeDate(recruitHireApply.getProposeDate());
        recruitHireApply1.setReqHireCode(recruitHireApply.getReqHireCode());
        recruitHireApply1.setAgeMax(recruitHireApply.getAgeMax());
        recruitHireApply1.setRecruitMppPeriod(recruitHireApply.getRecruitMppPeriod());
        recruitHireApply1.setMaritalStatus(recruitHireApply.getMaritalStatus());
        recruitHireApply1.setGpaMin(recruitHireApply.getGpaMin());
        recruitHireApply1.setGpaMax(recruitHireApply.getGpaMax());
        recruitHireApply1.setCurrency(recruitHireApply.getCurrency());
        recruitHireApply1.setJabatan(recruitHireApply.getJabatan());
        recruitHireApply1.setAgeMin(recruitHireApply.getAgeMin());
        recruitHireApply1.setEfectiveDate(recruitHireApply.getEfectiveDate());
        recruitHireApply1.setCandidateCountRequest(recruitHireApply.getCandidateCountRequest());
        recruitHireApply1.setGender(recruitHireApply.getGender());
        recruitHireApply1.setSalaryMin(recruitHireApply.getSalaryMin());
        recruitHireApplyDao.update(recruitHireApply1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitHireApply getEntiyByPK(Long id) {
        return this.recruitHireApplyDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.recruitHireApplyDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception {
        return this.recruitHireApplyDao.getTotalRecruitHireApplyByParam(searchParameter);
    }

    @Override
    public RecruitHireApply getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitHireApply enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply updateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveOrUpdateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitHireApply entity) throws Exception {
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
    public List<RecruitHireApply> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<RecruitReqHistoryViewModel> listRecruitReqHistoryViewModel = recruitHireApplyDao.getRecruitmentReqActivityByParam(parameter, firstResult, maxResults, orderable);
        setRecruitmentActivityComplexData(listRecruitReqHistoryViewModel);
        return listRecruitReqHistoryViewModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) throws Exception {
        return this.recruitHireApplyDao.getTotalRecruitmentReqActivityByParam(parameter);
    }
    
     private void setRecruitmentActivityComplexData(List<RecruitReqHistoryViewModel> listRecruitReqHistoryViewModels) {
        for (RecruitReqHistoryViewModel recruitReqHistoryViewModel : listRecruitReqHistoryViewModels) {

            if (recruitReqHistoryViewModel.getRhaId() == null) {

                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                String jsonData = recruitReqHistoryViewModel.getJsonData();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = (JsonObject) parser.parse(jsonData);

                RecruitHireApply recruitHireApplyTemp = gson.fromJson(jsonObject, RecruitHireApply.class);
                //JsonArray arrayDetailMpp = jsonObject.getAsJsonArray("listRecruitMppHireApplyDetail");

                recruitReqHistoryViewModel.setRecHireCode(recruitHireApplyTemp.getReqHireCode());
                recruitReqHistoryViewModel.setEfectiveDate(recruitHireApplyTemp.getEfectiveDate());
                recruitReqHistoryViewModel.setTotalReq(recruitHireApplyTemp.getCandidateCountRequest());                

            } else {
                
                RecruitHireApply recruitHireApply = recruitHireApplyDao.getEntiyByPK(recruitReqHistoryViewModel.getRhaId());
                recruitReqHistoryViewModel.setRecHireCode(recruitHireApply.getReqHireCode());
                recruitReqHistoryViewModel.setEfectiveDate(recruitHireApply.getEfectiveDate());
                recruitReqHistoryViewModel.setTotalReq(recruitHireApply.getCandidateCountRequest());  

            }
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception {
        Long totalRecruitApprovalDef = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.RECRUITMENT_REQUEST);
        String reqHireCode = "REQ-" + RandomNumberUtil.getRandomNumber(6);
        
        //If Approval Defintion for RECRUITMENT_REQUEST Process have not been created, throw Exception
        if (totalRecruitApprovalDef <= 0) {
            throw new BussinessException("mppRecruitmentHist.error_approval_def_not_found");
        }        
        
        //If reqHireCode duplicate, re-generate reqHireCode
        while(isRecruitmentHireCodeDuplicate(reqHireCode, null)) {
            reqHireCode = "REQ-" + RandomNumberUtil.getRandomNumber(6);
        }
        
        String createdBy = StringUtils.isEmpty(recruitHireApply.getCreatedBy()) ? UserInfoUtil.getUserName() : recruitHireApply.getCreatedBy();
        Date createdOn = recruitHireApply.getCreatedOn() == null ? new Date() : recruitHireApply.getCreatedOn();
        
        recruitHireApply.setReqHireCode(reqHireCode); 
        recruitHireApply.setCreatedBy(createdBy);
        recruitHireApply.setCreatedOn(createdOn);
        
        //parsing recruitHireApply to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(recruitHireApply));
        String jsonPendingData = gson.toJson(jsonObject);
        
        ApprovalActivity approvalActivity = super.checkApprovalProcess(HRMConstant.RECRUITMENT_REQUEST, createdBy);
        approvalActivity.setPendingData(jsonPendingData);
        approvalActivityDao.save(approvalActivity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     private Boolean isRecruitmentHireCodeDuplicate(String reqHireCode, String activityNumber) {
        Boolean result = Boolean.FALSE;

        Long totalDuplicateFromEntity = this.recruitHireApplyDao.getTotalDataByReqHireCode(reqHireCode);
        Long totalDuplicateFormWaitingApprovalActivity = 0l;
        
        //Get List waiting approval activity
        List<ApprovalActivity> listWaitingApproval = this.approvalActivityDao.getAllDataWaitingStatusApprovalFetchedApprovalDef();
        
        //Filter only activity that comes from RECRUITMENT_REQUEST process
        listWaitingApproval = Lambda.select(listWaitingApproval, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.RECRUITMENT_REQUEST)));

        //grouping list by ActivityNumber
         Group<ApprovalActivity> groupWaitingApprovalActivity = Lambda.group(listWaitingApproval, Lambda.by(Lambda.on(ApprovalActivity.class).getActivityNumber()));

        //iterate each group list element
        for (String key : groupWaitingApprovalActivity.keySet()) {
            List<ApprovalActivity> listGroupedActivity = groupWaitingApprovalActivity.find(key);

            // Get activity with highest sequence from each grouped list activities
            ApprovalActivity approvalActivityWithHighestSequence = Lambda.selectMax(listGroupedActivity, Lambda.on(ApprovalDefinition.class).getSequence());
            
            // convert json pendingData into RecruitHireApply
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String jsonData = approvalActivityWithHighestSequence.getPendingData();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
            RecruitHireApply recruitHireApply = gson.fromJson(jsonObject, RecruitHireApply.class);
            
            // chek whether reqHireCode already used
            if(StringUtils.equals(reqHireCode, recruitHireApply.getReqHireCode()) && 
                    !StringUtils.equals(activityNumber, approvalActivityWithHighestSequence.getActivityNumber())){
                totalDuplicateFormWaitingApprovalActivity ++; 
                break; // Break immediately when duplicate found.
            }
            
        }
        
        if (totalDuplicateFromEntity > 0 || totalDuplicateFormWaitingApprovalActivity > 0) {
            result = Boolean.TRUE;
        }
        return result;
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
