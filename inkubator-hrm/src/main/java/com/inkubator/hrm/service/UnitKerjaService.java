/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface UnitKerjaService  extends IService<UnitKerja> {
    public List<UnitKerja> getByParam(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalUnitKerjaByParam(UnitKerjaSearchParameter searchParameter) throws Exception;

    public Long getByUnitKerjaCode(String code) throws Exception;
    
    public List<UnitKerja> getAllDataWithCity(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public UnitKerja getEntityByPkWithCity(Long code) throws Exception;
    
}
