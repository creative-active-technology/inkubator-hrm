package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface TempAttendanceRealizationService extends IService<TempAttendanceRealization> {

    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception;
    
    public TempAttendanceRealizationViewModel calculateEmpTempAttendanceRealization(Long empDataId, Long wtPeriodId) throws Exception;
    
    public void executeBatchFileUpload(TempAttendanceRealizationViewModel model) throws Exception;
    
    public void deleteAllData() throws Exception;

}
