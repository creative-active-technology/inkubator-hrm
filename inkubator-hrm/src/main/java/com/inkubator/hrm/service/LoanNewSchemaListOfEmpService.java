/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaListOfEmpService extends IService<LoanNewSchemaListOfEmp>{
    public List<LoanNewSchemaListOfEmpViewModel> getByParam(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(LoanNewSchemaListOfEmpSearchParameter parameter) throws Exception;
}
