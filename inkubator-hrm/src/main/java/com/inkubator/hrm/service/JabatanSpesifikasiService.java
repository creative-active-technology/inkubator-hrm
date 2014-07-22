/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanSpesifikasiService extends IService<JabatanSpesifikasi> {

    public JabatanSpesifikasi getDataByPK(Long id) throws Exception;
    
    public List<JabatanSpesifikasi> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
