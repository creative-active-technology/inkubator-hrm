/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
public interface GolonganJabatanDao extends IDAO<GolonganJabatan> {

    public List<GolonganJabatan> getByParam(GolonganJabatanSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(GolonganJabatanSearchParameter parameter);

    public GolonganJabatan getEntityByPkFetchPangkat(Long id);

    public List<GolonganJabatan> getAllWithDetail();
    
    public GolonganJabatan getEntityWithDetailById(Long id);

}
