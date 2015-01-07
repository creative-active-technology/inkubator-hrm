/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface ModemDefinitionDao extends IDAO<ModemDefinition> {

    public Long getTotalByModemId(String modemId);

    public long getTotalByModemIdAndNotId(String modemId, Long id);

    public List<ModemDefinition> getAllByFullText(String param);

    public ModemDefinition getByFullText(Long id);
}
