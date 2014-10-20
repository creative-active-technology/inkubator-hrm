/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanProfesi;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanOccupationService extends IService<JabatanProfesi>{
    public List<JabatanProfesi> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
