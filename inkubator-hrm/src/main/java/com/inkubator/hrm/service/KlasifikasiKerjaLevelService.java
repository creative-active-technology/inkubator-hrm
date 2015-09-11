/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface KlasifikasiKerjaLevelService extends IService<KlasifikasiKerjaLevel>{
    public List<KlasifikasiKerjaLevel> getByParam(KlasifikasiKerjaLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalKlasifikasiKerjaLevelByParam(KlasifikasiKerjaLevelSearchParameter searchParameter) throws Exception;
    
//    public KlasifikasiKerjaLevel getKlasifKerjaNameByKlasifKerjaLevelId(Long id);
    
    public KlasifikasiKerjaLevel getEntityWithDetail(Long id) throws Exception;
}
