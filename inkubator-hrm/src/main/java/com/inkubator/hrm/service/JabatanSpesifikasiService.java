/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface JabatanSpesifikasiService extends IService<JabatanSpesifikasi> {
    public List<JabatanSpesifikasi> getByParam(JabatanSpesifikasiSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalJabatanSpesifikasiByParam(JabatanSpesifikasiSearchParameter searchParameter) throws Exception;
    
    public List<JabatanSpesifikasi> getByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalJabatanSpesifikasiByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id)  throws Exception;
    
    public JabatanSpesifikasi getDataByPK(Long id) throws Exception;
}
