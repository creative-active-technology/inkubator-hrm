/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.web.search.JabatanDeskripsiSearcParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanDeskripsiService extends IService<JabatanDeskripsi> {

    public List<JabatanDeskripsi> getByParam(JabatanDeskripsiSearcParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalJabatanByParam(JabatanDeskripsiSearcParameter searchParameter) throws Exception;
}
