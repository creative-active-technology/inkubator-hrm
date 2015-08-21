/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanEdukasiDao;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanEdukasiId;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "jabatanEdukasiDao")
@Lazy
public class JabatanEdukasiDaoImpl extends IDAOImpl<JabatanEdukasi> implements JabatanEdukasiDao{

    @Override
    public Class<JabatanEdukasi> getEntityClass() {
        return JabatanEdukasi.class;
    }

    @Override
    public List<JabatanEdukasi> getAllDataByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb");
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.add(Restrictions.eq("jb.id", jabatanId));
        return criteria.list();
    }

    @Override
    public void deleteAllDataByJabatanId(Long jabatanId) {
        String hqlDeleteJabatanEdukasi = "delete from JabatanEdukasi je where je.jabatan.id = :jabatanId";
        String hqlDeleteJabatanMajor = "delete from JabatanMajor jm where jm.jabatan.id = :jabatanId";
        String hqlDeleteJabatanFaculty = "delete from JabatanFakulty jf where jf.jabatan.id = :jabatanId";
        String hqlDeleteJabatanOccupation = "delete from JabatanProfesi jp where jp.jabatan.id = :jabatanId";
        int deletedJabatanEdukasi = getCurrentSession().createQuery( hqlDeleteJabatanEdukasi ).setString( "jabatanId", String.valueOf(jabatanId) ).executeUpdate();
        int deletedJabatanMajor = getCurrentSession().createQuery( hqlDeleteJabatanMajor ).setString( "jabatanId", String.valueOf(jabatanId) ).executeUpdate();
        int deletedJabatanFaculty = getCurrentSession().createQuery( hqlDeleteJabatanFaculty ).setString( "jabatanId", String.valueOf(jabatanId) ).executeUpdate();
        int deletedJabatanOccupation = getCurrentSession().createQuery( hqlDeleteJabatanOccupation ).setString( "jabatanId", String.valueOf(jabatanId) ).executeUpdate();
    }

	@Override
	public JabatanEdukasi getEntityByJabatanEdukasiId(JabatanEdukasiId jabatanEdukasiId) {
		// TODO Auto-generated method stub
		return null;
	}   
    
}
