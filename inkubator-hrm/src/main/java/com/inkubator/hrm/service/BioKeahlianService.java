/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioKeahlian;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioKeahlianService extends IService<BioKeahlian>{
    public BioKeahlian getAllDataByPK(Long id) throws Exception;
    
    public List<BioKeahlian> getAllDataByBioDataId(Long bioDataId) throws Exception;
    
}
