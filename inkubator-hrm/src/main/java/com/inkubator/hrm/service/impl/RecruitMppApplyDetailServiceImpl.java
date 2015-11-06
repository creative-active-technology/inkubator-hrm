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

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailTimeDao;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
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
    @Autowired
    private RecruitMppApplyDetailTimeDao recruitMppApplyDetailTimeDao;

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
		
		RecruitMppApplyDetail recruitMppApplyDetail = recruitMppApplyDetailDao.getEntityByJabatanIdAndMppPeriodId(jabatanId, mppPeriodId);
		Boolean isAlreadyCreated = recruitMppApplyDetailTimeDao.isMppDetailTimeAlreadyCreatedForMppDetailId(recruitMppApplyDetail.getId());
		
		//Jika belum di generate, generate dahulu detail mpp tiap bulan dari jabatan terpilih
		if(!isAlreadyCreated){
			generateListMppDetailTime(recruitMppApplyDetail, mppPeriodId, startPeriod, endPeriod);
		}
		
		List<RecruitMppApplyDetailTime> listMppDetailTime = recruitMppApplyDetailTimeDao.getListByMppApplyDetailId(recruitMppApplyDetail.getId());
		listRecruitMppApplyDetailViewModel = convertListMppDetailTimeToModel(listMppDetailTime, mppPeriodId);
		
		return listRecruitMppApplyDetailViewModel;
	}
	
	private List<RecruitMppApplyDetailViewModel> convertListMppDetailTimeToModel(List<RecruitMppApplyDetailTime> listMppApplyDetailTime, Long mppPeriodId){
		List<RecruitMppApplyDetailViewModel> listMppDetailTimeModel = new ArrayList<RecruitMppApplyDetailViewModel>();
		
		for(RecruitMppApplyDetailTime mppDetailTime : listMppApplyDetailTime){
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(mppDetailTime.getMppMonthApply());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			RecruitMppApplyDetailViewModel model = new RecruitMppApplyDetailViewModel();
			model.setId(mppDetailTime.getId());
			model.setActual(mppDetailTime.getActual());
			model.setDifference(mppDetailTime.getDifference());
			model.setMpp(mppDetailTime.getPlanningPerson().longValue());
			model.setId(mppDetailTime.getId());
			model.setJabatanId(mppDetailTime.getRecruitMppApplyDetail().getJabatan().getId());
			model.setJabatanKode(mppDetailTime.getRecruitMppApplyDetail().getJabatan().getCode());
			model.setJabatanName(mppDetailTime.getRecruitMppApplyDetail().getJabatan().getName());
			model.setMppPeriodId(mppPeriodId);
			model.setPeriodeStart(mppDetailTime.getMppMonthApply());
			model.setPeriodeEnd(calendar.getTime());
			model.setRecruitMppApplyId(mppDetailTime.getRecruitMppApplyDetail().getRecruitMppApply().getId());
			listMppDetailTimeModel.add(model);
		}
		
		return listMppDetailTimeModel;
	}
	
	private void generateListMppDetailTime(RecruitMppApplyDetail mppApplyDetail, Long mppPeriodId, Date startDateMppPeriode, Date endDateMppPeriode){
		
			//inisialiasi calendar dengan startPeriod dan endPeriod dari selectedMppPeriod
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDateMppPeriode);
			
			Calendar calendarEndDate = Calendar.getInstance();
			calendarEndDate.setTime(endDateMppPeriode);
			
			String createBy = HrmUserInfoUtil.getUserName();
			Date createOn = new Date();
			
			Boolean isFound = Boolean.FALSE;// flag untuk penanda di bulan apa sebenarnya mpp dilaksanakan.
			Integer tempDifference = 0;// temporary penampung dari tiap bulan yang akan di looping.
			
			//Looping tiap bulan dari periode MPP terpilih.
			do {
				
				// set tgl awal dari current month
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				Date startDateInSelectedMonth = calendar.getTime();
				
				// set tgl akhir dari current month
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date endDateInSelectedMonth = calendar.getTime();
				
				RecruitMppApplyDetailTime mppApplyDetailTime = new RecruitMppApplyDetailTime();
				mppApplyDetailTime.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				mppApplyDetailTime.setCreatedBy(createBy);
				mppApplyDetailTime.setCreatedOn(createOn);
				mppApplyDetailTime.setRecruitMppApplyDetail(mppApplyDetail);
				mppApplyDetailTime.setMppMonthApply(startDateInSelectedMonth);
				
				Integer recruitPlan = 0;
				Integer actual = 0;
				
				RecruitMppApplyDetail recruitMppApplyDetail = recruitMppApplyDetailDao.getEntityByDateRangeAndJabatanId(mppApplyDetail.getJabatan().getId(), startDateMppPeriode, endDateMppPeriode);
				if(null != recruitMppApplyDetail){
					
					recruitPlan = recruitMppApplyDetail.getRecruitPlan();
					actual =  empDataDao.getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(mppApplyDetail.getJabatan().getId(), startDateInSelectedMonth).intValue();
					Integer difference = recruitPlan == actual ? 0 : recruitPlan > actual ? (recruitPlan - actual) : (actual - recruitPlan);
					
					mppApplyDetailTime.setActual(actual);
					mppApplyDetailTime.setPlanningPerson(recruitPlan);
					mppApplyDetailTime.setDifference(difference);
					tempDifference = difference;
					
					isFound = Boolean.TRUE; // jika recruitMppApplyDetail != null, berarti bulan real di laksanakan mpp sudah ketemu.
					tempDifference = difference; // Tampung nilai difference dari bulan tsb untuk di jadikan sebagai acuan nilai aktual di bulan selanjutnya.
					
				}else{
					
					//Jika isFound == true, actual diambil dari tempDifference (penampung difference bulan sebelumnya), jika masih false, get real actual karyawan yang menduduki jabatan tesebut di bulan tersebut.
					actual = isFound ? tempDifference : empDataDao.getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(mppApplyDetail.getJabatan().getId(), startDateInSelectedMonth).intValue();
					Integer difference = recruitPlan == actual ? 0 : recruitPlan > actual ? (recruitPlan - actual) : (actual - recruitPlan);
					mppApplyDetailTime.setActual(actual);
					mppApplyDetailTime.setPlanningPerson(recruitPlan);
					mppApplyDetailTime.setDifference(difference);
				}
				
				recruitMppApplyDetailTimeDao.saveAndFlush(mppApplyDetailTime);
				
				//Lompat ke bulan berikutnya
				calendar.add(Calendar.MONTH, 1);
				
			}while(calendar.before(calendarEndDate));
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
