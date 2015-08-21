/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanEdukasiId;

import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanEdukasiDao extends IDAO<JabatanEdukasi>{
    public List<JabatanEdukasi> getAllDataByJabatanId(Long jabatanId);
    
    public void deleteAllDataByJabatanId(Long jabatanId);
    
    public JabatanEdukasi getEntityByJabatanEdukasiId(JabatanEdukasiId jabatanEdukasiId);
}
