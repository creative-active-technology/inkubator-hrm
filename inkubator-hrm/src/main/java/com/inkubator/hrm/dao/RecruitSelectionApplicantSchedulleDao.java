package com.inkubator.hrm.dao;



import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface RecruitSelectionApplicantSchedulleDao extends IDAO<RecruitSelectionApplicantSchedulle> {

	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id);
	
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId);
	
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId);

}
