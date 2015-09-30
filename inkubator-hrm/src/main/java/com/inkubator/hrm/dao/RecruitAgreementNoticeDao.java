/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitAgreementNotice;

/**
 *
 * @author Deni
 */
public interface RecruitAgreementNoticeDao extends IDAO<RecruitAgreementNotice>{
	public RecruitAgreementNotice getEntityByBioDataId(Long id);
}
