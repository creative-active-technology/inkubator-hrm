package com.inkubator.hrm.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CareerEmpEliminationDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.webcore.util.FacesUtil;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "careerEmpEliminationService")
@Lazy
public class CareerEmpEliminationServiceImpl extends BaseApprovalServiceImpl  implements CareerEmpEliminationService {
	
	@Autowired
	private CareerEmpEliminationDao careerEmpEliminationDao;
	
	@Autowired
	private EmpDataDao empDataDao;

	@Override
	public void delete(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CareerEmpElimination> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(Long arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination saveData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination saveOrUpdateData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination updateData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order)	throws Exception {
		/*List<EmpEliminationViewModel> listModel =  careerEmpEliminationDao.getListEmpEliminationViewModelByParam(searchParameter, firstResult, maxResults, order);
		return listModel;*/
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		List<EmpEliminationViewModel> listEmpEliminationViewModel = careerEmpEliminationDao.getListEmpEliminationViewModelByParam(searchParameter, firstResult, maxResults, order);
		
		for(EmpEliminationViewModel model : listEmpEliminationViewModel){
			EmpData empData = empDataDao.getByEmpDataByBioDataId(model.getBioDataId());
			model.setEmpName(empData.getBioData().getFullName());
			model.setReason(getReasonByEmpCareerHistoryStatus(model.getEmpCareerHistoryStatus(), resourceBundle));
		}
		
		return listEmpEliminationViewModel;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) throws Exception {
		return careerEmpEliminationDao.getTotalListEmpEliminationViewModelByParam(searchParameter);
	}
	
	private String getReasonByEmpCareerHistoryStatus(String status, ResourceBundle resourceBundle){
		String reason = StringUtils.EMPTY;
		
		switch (status) {
		case HRMConstant.EMP_STOP_CONTRACT:
			reason = resourceBundle.getString("career.employee_elimination_status_stop_contract");
			break;
			
		case HRMConstant.EMP_TERMINATION:
			reason = resourceBundle.getString("career.employee_elimination_status_resign");
			break;
			
		case HRMConstant.EMP_LAID_OFF:
			reason = resourceBundle.getString("career.employee_elimination_status_laid_off");
			break;
			
		case HRMConstant.EMP_PENSION:
			reason = resourceBundle.getString("finance.pension");
			break;
			
		case HRMConstant.EMP_DISCHAGED:
			reason = resourceBundle.getString("career.employee_elimination_status_discharge");
			break;

		default:
			break;
		}
		
		return reason;
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveCareerEmpEliminationWithApproval(CareerEmpElimination careerEmpElimination) throws Exception {
		String result = "error";
		return result;
	}
	
	@Override
	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejected(long approvalActivityId, String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void diverted(long approvalActivityId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
