/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface RecruitSelectionApplicantInitialService extends IService<RecruitSelectionApplicantInitial>{
    public void save(List<Long> listApplicantId) throws Exception;
}
