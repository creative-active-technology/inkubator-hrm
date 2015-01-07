/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.sms.gateway.entity.ModemDefinition;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface ModemDefinitionService extends IService<ModemDefinition>{
    public List<ModemDefinition>getAllByFullText(String param) throws Exception;
}
