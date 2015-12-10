/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpCareerHistoryDao extends IDAO<EmpCareerHistory> {

    public List<EmpCareerHistory> getEmployeeCareerByBioId(long id);
    
    public EmpCareerHistory getByBioIdandStatus(long id, String status);
    
    public List<EmpCareerHistory> getByParamReport(ReportEmpMutationParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter);
    
    public List<CareerTransitionInboxViewModel> getEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalgetEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter);
    
    public EmpCareerHistory getEntityByApprovalActivityNumber(String approvalActivityNumber);
    
    public List<EmpCareerHistory> getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(Long bioDataId, Date currentCreatedOn);
    
    public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter);
}
