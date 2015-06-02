/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author deni.fahri
 */
public class EmpAttendaceRealizationController extends BaseController {

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private RealizationAttendanceModel attendanceModel;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }
}
