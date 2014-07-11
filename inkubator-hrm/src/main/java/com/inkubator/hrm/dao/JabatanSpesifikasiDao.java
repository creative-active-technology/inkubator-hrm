/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanSpesifikasiDao extends IDAO<JabatanSpesifikasi>{
    public List<JabatanSpesifikasi> getByParam(JabatanSpesifikasiSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public List<JabatanSpesifikasi> getByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id, int firstResult, int maxResults, Order order);
    
    public Long getTotalJabatanSpesifikasiByParam(JabatanSpesifikasiSearchParameter searchParameter);
    
    public Long getTotalJabatanSpesifikasiByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id);
    
    public JabatanSpesifikasi getDataByPK(Long id);
}
