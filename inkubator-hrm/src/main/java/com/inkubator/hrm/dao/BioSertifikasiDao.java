package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioSertifikasi;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface BioSertifikasiDao extends IDAO<BioSertifikasi> {

    public List<BioSertifikasi> getAllDataByBioDataId(Long bioDataId);
    
    public BioSertifikasi getEntityByPKWithDetail(Long id) throws Exception;

}
