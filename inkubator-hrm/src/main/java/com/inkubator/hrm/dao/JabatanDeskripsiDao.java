/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.web.search.JabatanDeskripsiSearcParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanDeskripsiDao extends IDAO<JabatanDeskripsi> {

    public List<JabatanDeskripsi> getByParam(JabatanDeskripsiSearcParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalJabatanByParam(JabatanDeskripsiSearcParameter searchParameter);
}
