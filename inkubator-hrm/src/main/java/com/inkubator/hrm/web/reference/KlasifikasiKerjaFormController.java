package com.inkubator.hrm.web.reference;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OrgKlasifikasiJobFamily;
import com.inkubator.hrm.entity.OrgKlasifikasiJobFamilyId;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.model.KlasifikasiKerjaModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "klasifikasiKerjaFormController")
@ViewScoped
public class KlasifikasiKerjaFormController extends BaseController {

    private KlasifikasiKerjaModel klasifikasiKerjaModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private DualListModel<GolonganJabatan> dualListModelGolJab = new DualListModel<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        klasifikasiKerjaModel = new KlasifikasiKerjaModel();
        isUpdate = Boolean.FALSE;

        List<GolonganJabatan> listGolJab;
        try {
            listGolJab = golonganJabatanService.getAllData();

            if (StringUtils.isNotEmpty(param)) {
                KlasifikasiKerja klasifikasiKerja = klasifikasiKerjaService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
                if (klasifikasiKerja != null) {
                    klasifikasiKerjaModel.setId(klasifikasiKerja.getId());
                    klasifikasiKerjaModel.setKlasifikasiKerjaCode(klasifikasiKerja.getCode());
                    klasifikasiKerjaModel.setKlasifikasiKerjaName(klasifikasiKerja.getName());
                    klasifikasiKerjaModel.setDescription(klasifikasiKerja.getDescription());
                    isUpdate = Boolean.TRUE;
                    List<GolonganJabatan> sourceGolJab = this.golonganJabatanService.getAllData();
                    List<GolonganJabatan> targetRole = klasifikasiKerja.getListGolJab();
                    sourceGolJab.removeAll(targetRole);

                    dualListModelGolJab = new DualListModel<>(sourceGolJab, targetRole);
                }
            } else {
                dualListModelGolJab.setSource(listGolJab);
            }
        } catch (Exception ex) {
            Logger.getLogger(KlasifikasiKerjaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        klasifikasiKerjaService = null;
        dualListModelGolJab = null;
        klasifikasiKerjaModel = null;
        isUpdate = null;
    }

    public String doSave() {

        KlasifikasiKerja klasifikasiKerja = getEntityFromViewModel(klasifikasiKerjaModel);
        if (isUpdate) {
            return doUpdate(klasifikasiKerja);
//            return null;
        } else {
            return doInsert(klasifikasiKerja);
        }
    }

    private String doUpdate(KlasifikasiKerja klasifikasiKerja) {
        try {

            Set<OrgKlasifikasiJobFamily> dataToSave = new HashSet<>();
            List<GolonganJabatan> listGolJab = dualListModelGolJab.getTarget();
            for (GolonganJabatan golJab : listGolJab) {
                OrgKlasifikasiJobFamily orgKlasifikasiJobFamily = new OrgKlasifikasiJobFamily();
                orgKlasifikasiJobFamily.setId(new OrgKlasifikasiJobFamilyId(golJab.getId(), klasifikasiKerja.getId()));
                orgKlasifikasiJobFamily.setGolonganJabatan(golJab);
                orgKlasifikasiJobFamily.setKlasifikasiKerja(klasifikasiKerja);
                dataToSave.add(orgKlasifikasiJobFamily);
            }
            klasifikasiKerja.setOrgKlasifikasiJobFamilies(dataToSave);
            klasifikasiKerjaService.update(klasifikasiKerja);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/reference/job_family_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private String doInsert(KlasifikasiKerja klasifikasiKerja) {
        klasifikasiKerja.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        try {
            Set<OrgKlasifikasiJobFamily> dataToSave = new HashSet<>();
            List<GolonganJabatan> listGolJab = dualListModelGolJab.getTarget();
            for (GolonganJabatan golJab : listGolJab) {
                OrgKlasifikasiJobFamily orgKlasifikasiJobFamily = new OrgKlasifikasiJobFamily();
                orgKlasifikasiJobFamily.setId(new OrgKlasifikasiJobFamilyId(golJab.getId(), klasifikasiKerja.getId()));
                orgKlasifikasiJobFamily.setGolonganJabatan(golJab);
                orgKlasifikasiJobFamily.setKlasifikasiKerja(klasifikasiKerja);
                dataToSave.add(orgKlasifikasiJobFamily);
            }
            klasifikasiKerja.setOrgKlasifikasiJobFamilies(dataToSave);
            klasifikasiKerjaService.save(klasifikasiKerja);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            return "/protected/reference/job_family_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public DualListModel<GolonganJabatan> getDualListModelGolJab() {
        return dualListModelGolJab;
    }

    public void setDualListModelGolJab(DualListModel<GolonganJabatan> dualListModelGolJab) {
        this.dualListModelGolJab = dualListModelGolJab;
    }

    public KlasifikasiKerjaModel getKlasifikasiKerjaModel() {
        return klasifikasiKerjaModel;
    }

    public void setKlasifikasiKerjaModel(KlasifikasiKerjaModel klasifikasiKerjaModel) {
        this.klasifikasiKerjaModel = klasifikasiKerjaModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

//    public void doSave() {
//        KlasifikasiKerja klasifikasiKerja = getEntityFromViewModel(klasifikasiKerjaModel);
//        try {
//            if (isUpdate) {
//                klasifikasiKerjaService.update(klasifikasiKerja);
//                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
//            } else {
//                klasifikasiKerjaService.save(klasifikasiKerja);
//                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
//            }
//            cleanAndExit();
//        } catch (BussinessException ex) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//    }
    private KlasifikasiKerja getEntityFromViewModel(KlasifikasiKerjaModel klasifikasiKerjaModel) {
        KlasifikasiKerja klasifikasiKerja = new KlasifikasiKerja();
        if (klasifikasiKerjaModel.getId() != null) {
            klasifikasiKerja.setId(klasifikasiKerjaModel.getId());
        }
        klasifikasiKerja.setCode(klasifikasiKerjaModel.getKlasifikasiKerjaCode());
        klasifikasiKerja.setName(klasifikasiKerjaModel.getKlasifikasiKerjaName());
        klasifikasiKerja.setDescription(klasifikasiKerjaModel.getDescription());
        return klasifikasiKerja;
    }
    
    public void doReset() {
        klasifikasiKerjaModel.setDescription(null);
        klasifikasiKerjaModel.setKlasifikasiKerjaCode(null);
        klasifikasiKerjaModel.setKlasifikasiKerjaName(null);
    }
    
    public String doBack() {
        return "/protected/reference/job_family_view.htm?faces-redirect=true";
    }
}
