package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.AppraisalProgramEmpId;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalProgramEmpDao extends IDAO<AppraisalProgramEmp> {

    public List<EmpData> getAllEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter, int firstResult, int maxResult, Order order);

    public Long getTotalEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter);

    public Long totalEmpBYAppraisalProgram(long AppraisalProgramId);

    public AppraisalProgramEmp getByIdWithDetail(AppraisalProgramEmpId id);

}
