/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface UnitKerjaDao extends IDAO<UnitKerja>{
    public List<UnitKerja> getByParam(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalUnitKerjaByParam(UnitKerjaSearchParameter searchParameter);

    public Long getByUnitKerjaCode(String code);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public List<UnitKerja> getAllDataWithCity(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public UnitKerja getEntityByPkWithCity(Long id);
}
