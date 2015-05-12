/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.web.model.RecruitVacancySelectionModel;
import java.io.Serializable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author Deni
 */
@Component(value = "recruitVacancySelectionFormController")
@Lazy
public class RecruitVacancySelectionFormController implements Serializable {

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
    
    public RecruitVacancySelectionModel initSearchRecruitVacancySelectionFormFlow(RequestContext context) throws Exception {
        RecruitVacancySelectionModel recruitVacancySelectionModel = new RecruitVacancySelectionModel();
        System.out.println("init");
        recruitVacancySelectionModel.setCode("ladskjfl");
        
        return recruitVacancySelectionModel;
    }
}
