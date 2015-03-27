package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.dao.RmbsSchemaListOfEmpDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
@Service(value = "rmbsApplicationService")
@Lazy
public class RmbsApplicationServiceImpl extends BaseApprovalServiceImpl implements RmbsApplicationService {

	@Autowired
	private RmbsApplicationDao rmbsApplicationDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private RmbsTypeDao rmbsTypeDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private RmbsSchemaListOfEmpDao rmbsSchemaListOfEmpDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private FacesIO facesIO;
	
	@Override
	public RmbsApplication getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(RmbsApplication enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication saveData(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication updateData(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication saveOrUpdateData(RmbsApplication entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public RmbsApplication getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(RmbsApplication entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<RmbsApplication> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public void rejected(long approvalActivityId, String comment) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public void diverted(long approvalActivityId) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	protected void sendingEmailApprovalNotif(ApprovalActivity appActivity)throws Exception {
		
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String save(RmbsApplication entity, UploadedFile reimbursmentFile, boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
		
		// check duplicate code
		Long totalDuplicates = rmbsApplicationDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("rmbs_type.error_duplicate_code");
		}			
		
		//initial
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(entity.getRmbsType().getId());
		Currency currency = currencyDao.getEntiyByPK(entity.getCurrency().getId());
		String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();       
        
        //set data
        entity.setEmpData(empData);
        entity.setRmbsType(rmbsType);
        entity.setCurrency(currency);
        entity.setApplicationStatus(HRMConstant.RMBS_STATUS_UNDISBURSED);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        
        //start saving data and approval checking
        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empData.getId()).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(rmbsSchema.getApprovalDefinitionRmbsSchemas(), Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
		if(approvalActivity == null){
			//save entity to DB
			if (reimbursmentFile != null) {
                InputStream inputStream = null;
                byte[] buffer = null;
                inputStream = reimbursmentFile.getInputstream();
                buffer = IOUtils.toByteArray(inputStream);
                entity.setReceiptAttachment(buffer);
            }
            rmbsApplicationDao.save(entity);
            
            message = "success_without_approval";
            
		} else {
			String uploadPath = null;
            if (reimbursmentFile != null) {
                uploadPath = getUploadPath(Long.parseLong(RandomNumberUtil.getRandomNumber(9)), reimbursmentFile);
                facesIO.transferFile(reimbursmentFile);
                File file = new File(facesIO.getPathUpload() + reimbursmentFile.getFileName());
                file.renameTo(new File(uploadPath));
            }
            
			//parsing object to json and save to approval activity 
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
            jsonObject.addProperty("reimbursmentFileName", uploadPath);            
            approvalActivity.setPendingData(gson.toJson(jsonObject));
            approvalActivity.setTypeSpecific(rmbsType.getId());
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);
            
            message = "success_need_approval";
		}
		
		return message; 		
	}
	
	private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "reimburstment_" + id + "." + extension;
        return uploadPath;
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BigDecimal getTotalNominalByThisMonth(Long empDataId, Long rmbsTypeId) throws Exception {
		DateTime now = new DateTime();
		DateTime startDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.dayOfMonth().getMinimumValue(), 0, 0);
		DateTime endDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.dayOfMonth().getMaximumValue(), 23, 59);
		return rmbsApplicationDao.getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(empDataId, rmbsTypeId, startDate.toDate() , endDate.toDate());
		
	}
	

}
