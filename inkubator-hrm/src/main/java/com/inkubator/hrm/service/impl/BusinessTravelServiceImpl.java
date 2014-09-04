package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.BusinessTravelComponentDao;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.TravelTypeDao;
import com.inkubator.hrm.dao.TravelZoneDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "businessTravelService")
@Lazy
public class BusinessTravelServiceImpl extends BaseApprovalServiceImpl implements BusinessTravelService {
	
	@Autowired
	private BusinessTravelDao businessTravelDao;
	@Autowired
	private BusinessTravelComponentDao businessTravelComponentDao;
	@Autowired
	private TravelTypeDao travelTypeDao;
	@Autowired
	private TravelZoneDao travelZoneDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;

	@Override
	public BusinessTravel getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntiyByPK(Long id) throws Exception {
		return businessTravelDao.getEntiyByPK(id);

	}

	@Override
	public void save(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(BusinessTravel enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel saveData(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel updateData(BusinessTravel entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel saveOrUpdateData(BusinessTravel entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public BusinessTravel getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(BusinessTravel entity) throws Exception {
		BusinessTravel businessTravel = businessTravelDao.getEntiyByPK(entity.getId());
		businessTravelDao.delete(businessTravel);
	}

	@Override
	public void softDelete(BusinessTravel entity) throws Exception {
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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<BusinessTravel> getAllData() throws Exception {
		return businessTravelDao.getAllData();

	}

	@Override
	public List<BusinessTravel> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<BusinessTravel> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return businessTravelDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(BusinessTravelSearchParameter parameter) throws Exception {
		return businessTravelDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(BusinessTravel entity, List<BusinessTravelComponent> btcList, boolean isBypassApprovalChecking) throws Exception {
		// check duplicate business travel number
        long totalDuplicates = businessTravelDao.getTotalByBusinessTravelNo(entity.getBusinessTravelNo());
        if (totalDuplicates > 0) {
            throw new BussinessException("businesstravel.error_duplicate_business_travel_no");
        }
        
		EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
		TravelZone travelZone = travelZoneDao.getEntiyByPK(entity.getTravelZone().getId());
		TravelType travelType = travelTypeDao.getEntiyByPK(entity.getTravelType().getId());
		entity.setEmpData(empData);
		entity.setTravelType(travelType);
		entity.setTravelZone(travelZone);		
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		
		Set<BusinessTravelComponent> businessTravelComponents =  new HashSet<BusinessTravelComponent>();
		for(BusinessTravelComponent btc : btcList){
			btc.setBusinessTravel(entity);
			btc.setCreatedBy(UserInfoUtil.getUserName());
			btc.setCreatedOn(new Date());
			businessTravelComponents.add(btc);
		}
		entity.setBusinessTravelComponents(businessTravelComponents);
				
		ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.BUSINESS_TRAVEL);
        if(approvalActivity == null){
        	businessTravelDao.save(entity);
        	
        } else {
        	JsonParser parser = new JsonParser();
    		Gson gson = super.getGsonBuilder().create();
    		JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));    		
    		JsonArray arrayComponents = new JsonArray();
    		for(BusinessTravelComponent btc: businessTravelComponents){
    			JsonObject component = (JsonObject) parser.parse(gson.toJson(btc));
    			arrayComponents.add(component);
    		}
    		jsonObject.add("businessTravelComponents", arrayComponents);
    		
    		approvalActivity.setPendingData(gson.toJson(jsonObject));
    		approvalActivityDao.save(approvalActivity);
        }
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(BusinessTravel bt, List<BusinessTravelComponent> btcList) throws Exception {
		// check duplicate business travel number
        long totalDuplicates = businessTravelDao.getTotalByBusinessTravelNoAndNotId(bt.getBusinessTravelNo(), bt.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("businesstravel.error_duplicate_business_travel_no");
        }
		
		BusinessTravel businessTravel = businessTravelDao.getEntiyByPK(bt.getId());
		businessTravel.setBusinessTravelNo(bt.getBusinessTravelNo());
		businessTravel.setDestination(bt.getDestination());
		businessTravel.setDescription(bt.getDescription());
		businessTravel.setProposeDate(bt.getProposeDate());
		businessTravel.setStartDate(bt.getStartDate());
		businessTravel.setEndDate(bt.getEndDate());
		EmpData empData = empDataDao.getEntiyByPK(bt.getEmpData().getId());
		TravelZone travelZone = travelZoneDao.getEntiyByPK(bt.getTravelZone().getId());
		TravelType travelType = travelTypeDao.getEntiyByPK(bt.getTravelType().getId());
		businessTravel.setEmpData(empData);
		businessTravel.setTravelType(travelType);
		businessTravel.setTravelZone(travelZone);
		businessTravel.setUpdatedBy(UserInfoUtil.getUserName());
		businessTravel.setUpdatedOn(new Date());
		businessTravel.getBusinessTravelComponents().clear(); //clear list one to many
		businessTravelDao.save(businessTravel);
		
		/**
		 * mekanismenya, clear list child-nya, lalu create ulang child-nya 
		 */
		for(BusinessTravelComponent btc : btcList){
			btc.setBusinessTravel(businessTravel);
			btc.setCreatedBy(UserInfoUtil.getUserName());
			btc.setCreatedOn(new Date());
			businessTravelComponentDao.save(btc);
		}		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntityByPkWithDetail(Long id) throws Exception {
		/*BusinessTravel bt = businessTravelDao.getEntiyByPK(id);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("dd MMMM yyyy");
		//gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
		Gson gson = gsonBuilder.create();
		String xx = gson.toJson(bt);
		System.out.println(xx);*/
		return businessTravelDao.getEntityByPkWithDetail(id);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) throws Exception {
		return businessTravelDao.getEntityByBusinessTravelNoWithDetail(businessTravelNo);
	}

}
