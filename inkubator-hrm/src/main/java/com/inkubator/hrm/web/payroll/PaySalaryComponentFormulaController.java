/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "paySalaryComponentFormulaController")
@ViewScoped
public class PaySalaryComponentFormulaController extends BaseController {

    private String formulaOne;
    private Double basicSalary;
    private Double workingDay;
    private Double lessTime;
    private Double moreTime;
    private Double overTIme;
    private Double totalDay;
    private Double outPut;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    public void doAddOne() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "1";
        } else {
            formulaOne = "1";
        }

    }

    public void doAddTwo() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "2";
        } else {
            formulaOne = "2";
        }

    }

    public void doAddThree() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "3";
        } else {
            formulaOne = "3";
        }

    }

    public void doAddFour() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "4";
        } else {
            formulaOne = "4";
        }

    }

    public void doAddFive() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "5";
        } else {
            formulaOne = "5";
        }

    }

    public void doAddSix() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "6";
        } else {
            formulaOne = "6";
        }

    }

    public void doAddSeven() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "7";
        } else {
            formulaOne = "7";
        }

    }

    public void doAddEight() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "8";
        } else {
            formulaOne = "8";
        }

    }

    public void doAddNine() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "9";
        } else {
            formulaOne = "9";
        }

    }

    public void doAddZero() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "0";
        } else {
            formulaOne = "0";
        }

    }

    public void doAddPlus() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "+";
        } else {
            formulaOne = "+";
        }

    }

    public void doAddMinus() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "-";
        } else {
            formulaOne = "-";
        }

    }

    public void doAddCross() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "*";
        } else {
            formulaOne = "*";
        }

    }

    public void doAddDevide() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "/";
        } else {
            formulaOne = "/";
        }

    }

    public void doAddDecimal() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ".";
        } else {
            formulaOne = ".";
        }

    }

    public void doAddOr() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "||";
        } else {
            formulaOne = ".";
        }

    }

    public void doAddAnd() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "&&";
        } else {
            formulaOne = "&&";
        }

    }

    public void doAddNegasi() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "!";
        } else {
            formulaOne = "!";
        }

    }

    public void doAddLess() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "<";
        } else {
            formulaOne = "<";
        }

    }

    public void doAddMore() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ">";
        } else {
            formulaOne = ">";
        }

    }

    public void doAddLessEq() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "<=";
        } else {
            formulaOne = "<=";
        }

    }

    public void doAddMoreEq() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ">=";
        } else {
            formulaOne = ">=";
        }

    }

    public void doAddOpen() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "(";
        } else {
            formulaOne = "(";
        }

    }

    public void doAddClose() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ")";
        } else {
            formulaOne = ")";
        }

    }

    public void doAddParentisOpen() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "{";
        } else {
            formulaOne = "}";
        }

    }

    public void doAddParentisClose() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "}";
        } else {
            formulaOne = "}";
        }

    }

    public void doAddIf() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "if";
        } else {
            formulaOne = "if";
        }

    }

    public void doAddElse() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "else";
        } else {
            formulaOne = "else";
        }

    }

    public void doAddBasicSalary() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "bS";
        } else {
            formulaOne = "bS";
        }
    }

    public void doAddWorkingDay() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "wD";
        } else {
            formulaOne = "wD";
        }
    }

    public void doAddLessTime() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "lT";
        } else {
            formulaOne = "lT";
        }
    }

    public void doAddMoreTime() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "mT";
        } else {
            formulaOne = "mT";
        }
    }

    public void doAddOverTime() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "oT";
        } else {
            formulaOne = "oT";
        }
    }

    public void doAddTotalWorkingDay() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "tD";
        } else {
            formulaOne = "tD";
        }
    }

    public void doAddQuestion() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "?";
        } else {
            formulaOne = "?";
        }
    }

    public void doAddDoubleDot() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ":";
        } else {
            formulaOne = ":";
        }
    }

    public void doAddMathMax() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "Math.max";
        } else {
            formulaOne = "Math.max";
        }
    }

    public void doAddMathMin() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "Math.min";
        } else {
            formulaOne = "Math.min";
        }
    }

    public void doAddMathCeil() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "Math.ceil";
        } else {
            formulaOne = "Math.ceil";
        }
    }

    public void doAddMathRound() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "Math.round";
        } else {
            formulaOne = "Math.round";
        }
    }

    public void doAddMathFloor() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "Math.floor";
        } else {
            formulaOne = "Math.floor";
        }
    }

    public void doAddMathDelimeter() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + ",";
        } else {
            formulaOne = ",";
        }
    }

    public void doAddTmb() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "tmb";
        } else {
            formulaOne = "tmb";
        }
    }

    public void doAddPayDate() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "payDate";
        } else {
            formulaOne = "payDate";
        }
    }

    public void doAddRangPeriode() {
        if (getFormulaOne() != null) {
            formulaOne = getFormulaOne() + "range";
        } else {
            formulaOne = "range";
        }
    }

    public void doAddClear() {
        formulaOne = null;
        basicSalary = null;
        workingDay = null;
        lessTime = null;
        moreTime = null;
        overTIme = null;
        moreTime = null;
        overTIme = null;
        totalDay = null;

    }

    public String getFormulaOne() {
        return formulaOne;
    }

    public void setFormulaOne(String formulaOne) {
        this.formulaOne = formulaOne;
    }

    public void doCheck() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        jsEngine.put("bS", basicSalary);
        jsEngine.put("wD", workingDay);
        jsEngine.put("lT", lessTime);
        jsEngine.put("mT", moreTime);
        jsEngine.put("oT", overTIme);
        jsEngine.put("tD", totalDay);
        try {
            outPut = (Double) jsEngine.eval(formulaOne);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.ok", "formula_ok", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ScriptException ex) {
            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "formula_error", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(Double workingDay) {
        this.workingDay = workingDay;
    }

    public Double getLessTime() {
        return lessTime;
    }

    public void setLessTime(Double lessTime) {
        this.lessTime = lessTime;
    }

    public Double getMoreTime() {
        return moreTime;
    }

    public void setMoreTime(Double moreTime) {
        this.moreTime = moreTime;
    }

    public Double getOverTIme() {
        return overTIme;
    }

    public void setOverTIme(Double overTIme) {
        this.overTIme = overTIme;
    }

    public Double getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Double totalDay) {
        this.totalDay = totalDay;
    }

    public Double getOutPut() {
        return outPut;
    }

    public void setOutPut(Double outPut) {
        this.outPut = outPut;
    }

    public void doOkFormula() {
        RequestContext.getCurrentInstance().closeDialog(formulaOne);
    }
}
