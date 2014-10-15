/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanEdukasi;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanEdukasiService extends IService<JabatanEdukasi>{
    public List<JabatanEdukasi> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
