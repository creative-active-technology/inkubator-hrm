/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewCancelation;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewCancelationService extends IService<LoanNewCancelation> {

   public Long getCurrentMaxId() throws Exception;
}
