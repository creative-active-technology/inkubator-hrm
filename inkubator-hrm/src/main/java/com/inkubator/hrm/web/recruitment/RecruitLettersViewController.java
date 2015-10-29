package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.hrm.web.lazymodel.RecruitLettersLazyDataModel;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author deni,fahri
 */
@ManagedBean(name = "recruitLettersViewController")
@ViewScoped
public class RecruitLettersViewController extends BaseController {

    private RecrutimentLetterSearchParameter parameter;
    private LazyDataModel<RecruitLetters> lazyData;
    private RecruitLetters recruitLettersSelected;
    @ManagedProperty(value = "#{recruitLettersService}")
    private RecruitLettersService recruitLettersService;
    private Boolean isLetterType;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new RecrutimentLetterSearchParameter();
        isLetterType = Boolean.FALSE;
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitLettersService = null;
        parameter = null;
        lazyData = null;
        recruitLettersSelected = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {
        return "/protected/recruitment/offering_prohabit_detail.htm?faces-redirect=true&execution=e" + recruitLettersSelected.getId();
    }

    public String doAdd() {
        return "/protected/recruitment/recruit_applicant_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        if (recruitLettersSelected.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_OFFERING)) {
            return "/protected/recruitment/offering_letter_form.htm?faces-redirect=true&execution=e" + recruitLettersSelected.getId();
        }

        if (recruitLettersSelected.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_PROBATION)) {
            return "/protected/recruitment/probation_letter_form.htm?faces-redirect=true&execution=e" + recruitLettersSelected.getId();
        }

        if (recruitLettersSelected.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_REJECT)) {
            return "/protected/recruitment/reject_letter_form.htm?faces-redirect=true&execution=e" + recruitLettersSelected.getId();
        }
        return null;
    }

    public void doDelete() {
        try {
            recruitLettersService.delete(recruitLettersSelected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error(ex,ex);
        }
    }

    public LazyDataModel<RecruitLetters> getLazyData() {
        if (lazyData == null) {
            lazyData = new RecruitLettersLazyDataModel(parameter, recruitLettersService);
        }
        return lazyData;
    }

    public void setLazyData(LazyDataModel<RecruitLetters> lazyData) {
        this.lazyData = lazyData;
    }

    public RecrutimentLetterSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(RecrutimentLetterSearchParameter parameter) {
        this.parameter = parameter;
    }

    public RecruitLetters getRecruitLettersSelected() {
        return recruitLettersSelected;
    }

    public void setRecruitLettersSelected(RecruitLetters recruitLettersSelected) {
        this.recruitLettersSelected = recruitLettersSelected;
    }

    public void setRecruitLettersService(RecruitLettersService recruitLettersService) {
        this.recruitLettersService = recruitLettersService;
    }

    public Boolean getIsLetterType() {
        return isLetterType;
    }

    public void setIsLetterType(Boolean isLetterType) {
        this.isLetterType = isLetterType;
    }

    public void doChange() {

        if (parameter.getKeyParam().equals("letterType")) {
            isLetterType = Boolean.TRUE;
        } else {
            isLetterType = Boolean.FALSE;
            parameter = new RecrutimentLetterSearchParameter();
        }

    }
}
