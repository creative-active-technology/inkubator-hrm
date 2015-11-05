package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "recruitMppApplyDetailService")
@Lazy
public class RecruitMppApplyDetailServiceImpl extends IServiceImpl implements RecruitMppApplyDetailService {

    @Autowired
    private RecruitMppApplyDao recruitMppApplyDao;
    @Autowired
    private RecruitMppApplyDetailDao recruitMppApplyDetailDao;    
    @Autowired
    private EmpDataDao empDataDao;  
    @Autowired
    private RecruitMppPeriodDao recruitMppPeriodDao;  
    @Autowired
    private JabatanDao jabatanDao; 

    @Override
    public RecruitMppApplyDetail getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail saveData(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail updateData(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail saveOrUpdateData(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApplyDetail getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(RecruitMppApplyDetail t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitMppApplyDetail t) throws Exception {
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
    public List<RecruitMppApplyDetail> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApplyDetail> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitMppApplyDetail> getListWithDetailByRecruitMppApplyId(Long recruitMppApplyId) throws Exception {
      return this.recruitMppApplyDetailDao.getListWithDetailByRecruitMppApplyId(recruitMppApplyId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getRecruitPlanByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId) throws Exception {
        return this.recruitMppApplyDetailDao.getRecruitPlanByJabatanIdAndMppPeriodId(jabatanId, mppPeriodId);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetailViewModel> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
		List<RecruitMppApplyDetailViewModel> listRecruitMppApplyDetailViewModel = recruitMppApplyDetailDao.getAllDataByParam(searchParameter, firstResult, maxResults, order);
		for(RecruitMppApplyDetailViewModel recruitMppApplyDetailViewModel : listRecruitMppApplyDetailViewModel){
			Integer plan = recruitMppApplyDetailViewModel.getMpp().intValue();
			Integer actual = empDataDao.getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(recruitMppApplyDetailViewModel.getJabatanId(), recruitMppApplyDetailViewModel.getPeriodeStart()).intValue();
			Integer difference = plan == actual ? 0 : plan > actual ? (plan - actual) : (actual - plan);
			recruitMppApplyDetailViewModel.setActual(actual);
			recruitMppApplyDetailViewModel.setDifference(difference);
		}
		return listRecruitMppApplyDetailViewModel;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalDataByParam(RecruitMppApplyDetailSearchParameter searchParameter) throws Exception {
		return this.recruitMppApplyDetailDao.getTotalDataByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId) throws Exception {
		return recruitMppApplyDetailDao.getEntityWithDetail(idRecruitMppApplyDetailId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetailViewModel> getListPerMonthByMppPeriodIdAndJabatanId(Long mppPeriodId, Long jabatanId) throws Exception {
		
		List<RecruitMppApplyDetailViewModel> listRecruitMppApplyDetailViewModel = new ArrayList<RecruitMppApplyDetailViewModel>();
		RecruitMppPeriod recruitMppPeriod = recruitMppPeriodDao.getEntiyByPK(mppPeriodId);
		Date startPeriod = recruitMppPeriod.getPeriodeStart();
		Date endPeriod = recruitMppPeriod.getPeriodeEnd();
		
		//inisialiasi calendar dengan startPeriod dan endPeriod dari selectedMppPeriod
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startPeriod);
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(endPeriod);
		
		do {
			
			// set tgl awal dari current month
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			Date startDateInSelectedMonth = calendar.getTime();
			
			// set tgl akhir dari current month
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date endDateInSelectedMonth = calendar.getTime();
			
			
			RecruitMppApplyDetailViewModel model = generateMppDetailModelPerMonth(startDateInSelectedMonth, endDateInSelectedMonth, jabatanId);
			model.setPeriodeStart(startDateInSelectedMonth);
			model.setPeriodeEnd(endDateInSelectedMonth);
			
			listRecruitMppApplyDetailViewModel.add(model);
			
			//Lompat ke bulan berikutnya
			calendar.add(Calendar.MONTH, 1);
			
			
		}while(calendar.before(calendarEndDate));
		
		return listRecruitMppApplyDetailViewModel;
	}
	
	private RecruitMppApplyDetailViewModel generateMppDetailModelPerMonth(Date startDate, Date endDate, Long jabatanId){
		RecruitMppApplyDetailViewModel model = new RecruitMppApplyDetailViewModel();
		RecruitMppApplyDetail recruitMppApplyDetail = recruitMppApplyDetailDao.getEntityByDateRangeAndJabatanId(jabatanId, startDate, endDate);
		Integer plan = 0;
		
		// Cek jika ada Recruitment MPP untuk jabatan yang di pilih pada range date terpilih, maka set Id dan plan dari model dengan data dari recruitMppApplyDetail
		if(null != recruitMppApplyDetail){
			model.setId(recruitMppApplyDetail.getId());
			plan = recruitMppApplyDetail.getRecruitPlan();
		}
		
		Integer actual = empDataDao.getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(jabatanId, startDate).intValue();
		Integer difference = plan == actual ? 0 : plan > actual ? (plan - actual) : (actual - plan);
		model.setJabatanId(jabatanId);
		model.setActual(actual);
		model.setDifference(difference);
		model.setMpp(plan.longValue());
		return model;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetail> getListInSelectedMppPeriodIdWithApprovalStatus(Long recruitMppPeriodId, Integer approvalStatus) throws Exception {
		return recruitMppApplyDetailDao.getListInSelectedMppPeriodIdWithApprovalStatus(recruitMppPeriodId, approvalStatus);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Boolean isJabatanMppExistOnSelectedMppPeriod(Long jabatanId,	Long recruitMppPeriodId) throws Exception {
		return recruitMppApplyDetailDao.isJabatanMppExistOnSelectedMppPeriod(jabatanId, recruitMppPeriodId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetail> getAllDataJabatanByRecruitMppApplyId(Long recruitMppApplyId) throws Exception {
		return recruitMppApplyDetailDao.getAllDataJabatanByRecruitMppApplyId(recruitMppApplyId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetail> getListByJabatanIdAndMppPeriodId(Long jabatanId, Long recruitMppPeriodId) throws Exception {
		return recruitMppApplyDetailDao.getListByJabatanIdAndMppPeriodId(jabatanId, recruitMppPeriodId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitMppApplyDetail getEntityByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId) throws Exception {
		return recruitMppApplyDetailDao.getEntityByJabatanIdAndMppPeriodId(jabatanId, mppPeriodId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitMppApplyDetail> getAllDataWithDetail() throws Exception {
		return recruitMppApplyDetailDao.getAllDataWithDetail();
	}
    
}
