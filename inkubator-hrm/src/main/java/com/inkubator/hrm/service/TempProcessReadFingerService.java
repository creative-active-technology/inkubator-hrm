package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface TempProcessReadFingerService extends IService<TempProcessReadFinger> {
	
	//pageable(paging)
	public List<TempProcessReadFinger> getByParam(Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(Long empDataId) throws Exception;
	
	public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter) throws Exception;
	
	
	
	//functions
	public void synchDataFingerRealization() throws Exception;
	
	public void synchDataFingerRealization(EmpData empData, WtPeriode periode, String createdBy, Date createdOn) throws Exception;

	public void doCorrectionIn(Long id, Boolean isCorrection) throws Exception;
	
	public void doCorrectionOut(Long id, Boolean isCorrection) throws Exception;
	
	public void deleteByScheduleDateAndIsNotCorrection(Date fromPeriode, Date untilPeriode) throws Exception;
	
	
	
	//return collections
	public List<TempProcessReadFinger> getAllDataByEmpDataIdAndScheduleDate(Long empDataID, Date startDate, Date endDate) throws Exception;
	
}
