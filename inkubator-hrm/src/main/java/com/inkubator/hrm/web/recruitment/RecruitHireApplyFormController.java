/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "recruitHireApplyFormController")
@ViewScoped
public class RecruitHireApplyFormController extends BaseController {

  
    @PostConstruct
    @Override
    public void initialization() {

    }
}
