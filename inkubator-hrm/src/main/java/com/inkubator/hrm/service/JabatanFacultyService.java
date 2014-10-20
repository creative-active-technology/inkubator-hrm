/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanFakulty;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanFacultyService extends IService<JabatanFakulty>{
    public List<JabatanFakulty> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
