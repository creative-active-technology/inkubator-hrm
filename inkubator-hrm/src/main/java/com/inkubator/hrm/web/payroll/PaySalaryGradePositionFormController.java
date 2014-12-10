/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.util.RomanovUtil;
import com.inkubator.hrm.web.model.JabatanModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryGradePositionFormController")
@ViewScoped
public class PaySalaryGradePositionFormController extends BaseController{
    @ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService paySalaryGradeService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private JabatanModel jabatanModel;
    private List<PaySalaryGrade> listSalaryGrade = new ArrayList<>();
    private Map<String, Long> listDropDownGrade = new TreeMap<String, Long>();
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("param");
            Jabatan jabatan = jabatanService.getByIdWithSalaryGrade(Long.parseLong(id));
            jabatanModel = getJabatanModelFromEntity(jabatan);
           
            //list dropdown
            listDropDownGrade();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public void listDropDownGrade() throws Exception{
        listSalaryGrade = paySalaryGradeService.getAllData();
        for (PaySalaryGrade paySalaryGrade : listSalaryGrade) {
            listDropDownGrade.put(RomanovUtil.convertToRoman(paySalaryGrade.getGradeSalary())+ ". " + paySalaryGrade.getMinSalary() + " - " + paySalaryGrade.getMaxSalary(), paySalaryGrade.getId());
        }
        MapUtil.sortByValue(listDropDownGrade);
    }

    @PreDestroy
    public void cleanAndExit() {
        jabatanService = null;
        jabatanModel = null;
        listDropDownGrade = null;
        listSalaryGrade = null;
        paySalaryGradeService = null;
    }
    
    public void doSave() throws Exception{
        Jabatan jabatan = getEntityFromView(jabatanModel);
        try {
                jabatanService.updateForSalaryGrade(jabatan);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
                cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private Jabatan getEntityFromView(JabatanModel jabatanModel) {
        Jabatan jabatan = new Jabatan();
        if (jabatanModel.getId() != null) {
            jabatan.setId(jabatanModel.getId());
        }
        jabatan.setPaySalaryGrade(new PaySalaryGrade(jabatanModel.getSalaryGradeId()));
        return jabatan;
    }
    
    public JabatanModel getJabatanModelFromEntity(Jabatan jabatan) {
        JabatanModel jbm = new JabatanModel();
        jbm.setId(jabatan.getId());
        jbm.setNamaJabatan(jabatan.getName());
        jbm.setNIK(jabatan.getCode());
        if(jabatan.getPaySalaryGrade() != null){
            jbm.setSalaryGradeId(jabatan.getPaySalaryGrade().getId());
        }
        return jbm;
    }
    
    public void doReset(){
        jabatanModel.setSalaryGradeId(null);
    }
    
    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public JabatanModel getJabatanModel() {
        return jabatanModel;
    }

    public void setJabatanModel(JabatanModel jabatanModel) {
        this.jabatanModel = jabatanModel;
    }

    public PaySalaryGradeService getPaySalaryGradeService() {
        return paySalaryGradeService;
    }

    public void setPaySalaryGradeService(PaySalaryGradeService paySalaryGradeService) {
        this.paySalaryGradeService = paySalaryGradeService;
    }

    public List<PaySalaryGrade> getListSalaryGrade() {
        return listSalaryGrade;
    }

    public void setListSalaryGrade(List<PaySalaryGrade> listSalaryGrade) {
        this.listSalaryGrade = listSalaryGrade;
    }

    public Map<String, Long> getListDropDownGrade() {
        return listDropDownGrade;
    }

    public void setListDropDownGrade(Map<String, Long> listDropDownGrade) {
        this.listDropDownGrade = listDropDownGrade;
    }
    
    
}
