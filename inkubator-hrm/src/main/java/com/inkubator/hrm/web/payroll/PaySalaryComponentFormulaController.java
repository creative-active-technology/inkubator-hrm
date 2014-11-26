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

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "paySalaryComponentFormulaController")
@ViewScoped
public class PaySalaryComponentFormulaController extends BaseController {

    private String formulaOne;

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
            formulaOne = getFormulaOne() + ":";
        } else {
            formulaOne = ":";
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
            formulaOne = "bs";
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

    public void doAddClear() {
        formulaOne = null;

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
        jsEngine.put("bS", 2.0);
        jsEngine.put("wD", 2.0);
        jsEngine.put("lT", 2.0);
        jsEngine.put("mT", 2.0);
        jsEngine.put("oT", 2.0);
        jsEngine.put("tD", 2.0);
        try {
            jsEngine.eval(formulaOne);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "formula.ok", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ScriptException ex) {
            System.out.println(" nilai " + ex.getMessage());
            MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", ex.getMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
}
