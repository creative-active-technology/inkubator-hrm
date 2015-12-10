/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.model.EmpCareerHistoryModel;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpCareerHistoryService extends IService<EmpCareerHistory>, BaseApprovalService {

    public List<EmpCareerHistory> getEmployeeCareerByBioId(long id) throws Exception;
    
    public List<EmpCareerHistory> getByParamReport(ReportEmpMutationParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter);
    
    public String saveTransitionWithApproval(EmpCareerHistoryModel model) throws Exception;
    
    public String saveTransitionWithRevised(EmpCareerHistoryModel model, Long approvalActivityId) throws Exception;

    public List<CareerTransitionInboxViewModel> getEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalgetEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter) throws Exception;
    
    public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) throws Exception;

    public EmpCareerHistory getEntityByApprovalActivityNumber(String approvalActivityNumber) throws Exception;
    
    public List<EmpCareerHistory> getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(Long bioDataId, Date currentCreatedOn) throws Exception;
}
