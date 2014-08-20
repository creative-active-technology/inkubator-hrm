/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioKeahlian;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioKeahlianDao extends IDAO<BioKeahlian>{
    public BioKeahlian getAllDataByPK(Long id);
    
    public List<BioKeahlian> getAllDataByBioDataId(Long bioDataId);
}
