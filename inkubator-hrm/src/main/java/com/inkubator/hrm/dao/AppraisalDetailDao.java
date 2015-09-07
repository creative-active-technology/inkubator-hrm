package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalDetail;

/**
 *
 * @author Taufik hidayat
 */
public interface AppraisalDetailDao extends IDAO<AppraisalDetail> {

    public List<AppraisalDetail> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalAppraisalDetailByParam(String parameter);

    public AppraisalDetail getEntityByPKWithDetail(Long id);

    
}
