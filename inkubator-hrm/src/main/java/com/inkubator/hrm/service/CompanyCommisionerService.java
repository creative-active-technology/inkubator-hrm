/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CompanyCommisioner;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface CompanyCommisionerService extends IService<CompanyCommisioner>{
    public List<CompanyCommisioner> getEntityByCompanyId(Long id) throws Exception;
}
