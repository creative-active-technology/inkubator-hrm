/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;
import java.util.List;

/**
 *
 * @author denifahri
 */
public interface AppraisalProgramEmpAssesorDao extends IDAO<AppraisalProgramEmpAssesor>{
    public  Long getTotalAssesorByAppraisalIAndEmpId(Long appraisalId, Long empId);
    public List<AppraisalProgramEmpAssesor> getAllBy(Long appraisalId, Long empId);
}
