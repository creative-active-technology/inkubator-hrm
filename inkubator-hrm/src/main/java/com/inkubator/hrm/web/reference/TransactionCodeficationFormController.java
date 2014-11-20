package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.web.model.TransactionCodeficationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "transactionCodeficationFormController")
@ViewScoped
public class TransactionCodeficationFormController extends BaseController {

    private TransactionCodeficationModel transactionCodeficationModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        transactionCodeficationModel = new TransactionCodeficationModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                TransactionCodefication transactionCodefication = transactionCodeficationService.getEntiyByPK(Long.parseLong(param));
                if (transactionCodefication != null) {
                    transactionCodeficationModel.setId(transactionCodefication.getId());
                    transactionCodeficationModel.setCode(transactionCodefication.getCode());
                    transactionCodeficationModel.setName(transactionCodefication.getName());
                    transactionCodeficationModel.setDescription(transactionCodefication.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        transactionCodeficationService = null;
//        transactionCodeficationModel = null;
        isUpdate = null;
    }

    public TransactionCodeficationModel getTransactionCodeficationModel() {
        return transactionCodeficationModel;
    }

    public void setTransactionCodeficationModel(TransactionCodeficationModel transactionCodeficationModel) {
        this.transactionCodeficationModel = transactionCodeficationModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
        this.transactionCodeficationService = transactionCodeficationService;
    }

    public void doSave() {

        try {
            TransactionCodefication transactionCodefication = getEntityFromViewModel(transactionCodeficationModel);
            if (isUpdate) {
                transactionCodeficationService.update(transactionCodefication);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                transactionCodeficationService.save(transactionCodefication);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private TransactionCodefication getEntityFromViewModel(TransactionCodeficationModel transactionCodeficationModel) throws BussinessException {
        TransactionCodefication transactionCodefication = new TransactionCodefication();
        codeValidation(transactionCodeficationModel.getCode());
        if (transactionCodeficationModel.getId() != null) {
            transactionCodefication.setId(transactionCodeficationModel.getId());
        }
        transactionCodefication.setCode(transactionCodeficationModel.getCode());
        transactionCodefication.setName(transactionCodeficationModel.getName());
        transactionCodefication.setDescription(transactionCodeficationModel.getDescription());
        return transactionCodefication;
    }

    private void codeValidation(String code) throws BussinessException {

        Pattern patronValidity = Pattern.compile("\\[([^\\]]+)]");
        Matcher matcherValidity = patronValidity.matcher(code);

        String[] validCode = {"DD", "MM", "YY", "YYYY", "N", "NN", "NNN", "NNNN",
            "NNNNN", "NNNNNN", "NNNNNNN", "NNNNNNNN"};

        while (matcherValidity.find()) {
            if (Arrays.asList(validCode).contains(matcherValidity.group(1))) {
                System.out.println(matcherValidity.group(1));
            } else {
                throw new BussinessException("transactionCodefication.error_code");
            }
        }
        code = code.replaceAll("\\[([^\\]]+)]", "X-X");

        Pattern pattern = Pattern.compile("([A-Za-z0-9-/]+)");
        Matcher matcher = pattern.matcher(code);
        if (matcher.matches()) {
//            System.out.println("Input String matches regex - " + matcher.matches());
        } else {
            throw new BussinessException("transactionCodefication.error_invalid_character");
        }
    }
}
