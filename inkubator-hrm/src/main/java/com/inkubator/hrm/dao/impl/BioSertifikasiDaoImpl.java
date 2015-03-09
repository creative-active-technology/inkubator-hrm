/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioSertifikasiDao;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioProject;
import com.inkubator.hrm.entity.BioSertifikasi;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "bioSertifikasiDao")
@Lazy
public class BioSertifikasiDaoImpl  extends IDAOImpl<BioSertifikasi> implements BioSertifikasiDao{

    @Override
    public Class<BioSertifikasi> getEntityClass() {
        return BioSertifikasi.class;
    }
    
    @Override
    public List<BioSertifikasi> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("educationNonFormal", FetchMode.JOIN);
        criteria.setFetchMode("occupationType", FetchMode.JOIN);        
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        
        return criteria.list();
    }

    @Override
    public BioSertifikasi getEntityByPKWithDetail(Long id) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("educationNonFormal", FetchMode.JOIN);
        criteria.setFetchMode("occupationType", FetchMode.JOIN); 
        criteria.add(Restrictions.eq("id", id));
        return (BioSertifikasi) criteria.uniqueResult();
    }
    
}
