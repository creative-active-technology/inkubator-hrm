/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanMajor;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanMajorService extends IService<JabatanMajor>{
    public List<JabatanMajor> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
