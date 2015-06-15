package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.BankSearchParameter;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.model.DetilRealizationAttendanceModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface TempAttendanceRealizationService extends IService<TempAttendanceRealization> {    

    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter searchParameter) throws Exception;

    public RealizationAttendanceModel getStatisticEmpAttendaceRealization() throws Exception;

    public Long getTotalEmpLeav() throws Exception;

    public Long getTotalEmpPermit() throws Exception;

    public Long gettotalEmpOnDuty() throws Exception;

    public DetilRealizationAttendanceModel getStatisticEmpAttendaceDetil(long empId) throws Exception;
    
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception;
    
    public TempAttendanceRealizationViewModel calculateEmpTempAttendanceRealization(Long empDataId, Long wtPeriodId) throws Exception;
    
    public void executeBatchFileUpload(TempAttendanceRealizationViewModel model) throws Exception;
    
    public void deleteAllData() throws Exception;
}
