/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DepartementUploadCaptureDao;
import com.inkubator.hrm.entity.DepartementUploadCapture;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "departementUploadCaptureDao")
@Lazy
public class DepartementUploadCaptureDaoImpl extends IDAOImpl<DepartementUploadCapture> implements DepartementUploadCaptureDao {

    @Override
    public Class<DepartementUploadCapture> getEntityClass() {
        return DepartementUploadCapture.class;
    }

    @Override
    public List<DepartementUploadCapture> getByMecineFingerId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("mecineFinger", "d");
        criteria.add(Restrictions.eq("d.id", id));
        criteria.addOrder(Order.asc("department"));
        criteria.setFetchMode("department", FetchMode.JOIN);
        return criteria.list();

    }

}
