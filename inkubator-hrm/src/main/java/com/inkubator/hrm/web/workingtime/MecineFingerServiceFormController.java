/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.model.MecineFingerServiceModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "mecineFingerServiceFormController")
@ViewScoped
public class MecineFingerServiceFormController extends BaseController {

    private MecineFingerServiceModel mecineFingerServiceModel;
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;
    private MecineFinger mecineFinger;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String param = FacesUtil.getRequestParameter("execution");
            mecineFinger = mecineFingerService.getMecineFingerAndDetaiUploadByFK(Long.parseLong(param.substring(1)));
            mecineFingerServiceModel = new MecineFingerServiceModel();
            mecineFingerServiceModel.setId(mecineFinger.getId());
            mecineFingerServiceModel.setPort(Integer.parseInt(mecineFinger.getServicePort()));
            if (mecineFinger.getServiceHost() != null) {
                String hostIp = mecineFinger.getServiceHost();
                String[] partIp = hostIp.replace(".", "-").split("-");
                mecineFingerServiceModel.setHost1(Integer.parseInt(partIp[0]));
                mecineFingerServiceModel.setHost2(Integer.parseInt(partIp[1]));
                mecineFingerServiceModel.setHost3(Integer.parseInt(partIp[2]));
                mecineFingerServiceModel.setHost4(Integer.parseInt(partIp[3]));
            }

            if (mecineFinger.getServiceType() != null) {
                mecineFingerServiceModel.setServiceData(mecineFinger.getServiceType());
            }

            if (mecineFinger.getServiceOpenProtocol() != null) {
                mecineFingerServiceModel.setProtocolData(mecineFinger.getServiceOpenProtocol());
            }

            if (mecineFinger.getMatchBase() != null) {
                mecineFingerServiceModel.setEmployeeBaseId(mecineFinger.getMatchBase());
            }

            if (mecineFinger.getServiceOpenProtocolPassword() != null) {
                mecineFingerServiceModel.setOpenProtocolPassword(mecineFinger.getServiceOpenProtocolPassword());
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        mecineFinger = null;
        mecineFingerService = null;
        mecineFingerServiceModel = null;
    }

    public String doSave() {
        try {
            mecineFingerService.saveMesineService(mecineFingerServiceModel);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

        return null;
    }

    public void doReset() {
        cleanAndExit();
    }

    public String doBack() {
        return "/protected/working_time/mecine_finger_view.htm?faces-redirect=true";
    }

    public MecineFingerServiceModel getMecineFingerServiceModel() {
        return mecineFingerServiceModel;
    }

    public void setMecineFingerServiceModel(MecineFingerServiceModel mecineFingerServiceModel) {
        this.mecineFingerServiceModel = mecineFingerServiceModel;
    }

    public void setMecineFingerService(MecineFingerService mecineFingerService) {
        this.mecineFingerService = mecineFingerService;
    }

}
