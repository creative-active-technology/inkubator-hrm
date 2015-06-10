package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionLeaveDao;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author rizkykojek
 */
@Repository
@Lazy
public class ApprovalDefinitionLeaveDaoImpl extends IDAOImpl<ApprovalDefinitionLeave> implements ApprovalDefinitionLeaveDao {

	@Override
	public Class<ApprovalDefinitionLeave> getEntityClass() {
		return ApprovalDefinitionLeave.class;
		
	}

    @Override
    public List<ApprovalDefinitionLeave> getByLeaveId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.setFetchMode("leave.attendanceStatus", FetchMode.JOIN);
        criteria.add(Restrictions.eq("leave.id", id));
        return criteria.list();
    }

	

}
