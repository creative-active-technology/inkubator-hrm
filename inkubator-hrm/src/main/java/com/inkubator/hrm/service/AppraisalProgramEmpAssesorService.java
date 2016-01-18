/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;

/**
 *
 * @author denifahri
 */
public interface AppraisalProgramEmpAssesorService extends IService<AppraisalProgramEmpAssesor> {

    public Long getTotalAssesorByAppraisalIAndEmpId(Long appraisalId, Long empId) throws Exception;
}
