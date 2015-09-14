/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface KlasifikasiKerjaLevelDao extends IDAO<KlasifikasiKerjaLevel>{
    public List<KlasifikasiKerjaLevel> getByParam(KlasifikasiKerjaLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalKlasifikasiKerjaLevelByParam(KlasifikasiKerjaLevelSearchParameter searchParameter);
    
//    public KlasifikasiKerjaLevel getKlasifKerjaNameByKlasifKerjaLevelId(Long id);
    
    public KlasifikasiKerjaLevel getEntityWithDetail(Long id);
}
