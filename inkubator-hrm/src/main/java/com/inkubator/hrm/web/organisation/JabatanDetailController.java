/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanFakulty;
import com.inkubator.hrm.entity.JabatanMajor;
import com.inkubator.hrm.entity.JabatanProfesi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.service.JabatanEdukasiService;
import com.inkubator.hrm.service.JabatanFacultyService;
import com.inkubator.hrm.service.JabatanMajorService;
import com.inkubator.hrm.service.JabatanProfesiService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.service.KlasifikasiKerjaJabatanService;
import com.inkubator.hrm.service.OrgTypeOfSpecJabatanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDetailController")
@ViewScoped
public class JabatanDetailController extends BaseController {
	
	// Jabatan 
    private Jabatan selectedJabatan;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    
    // Jabatan Edukasi
    @ManagedProperty(value = "#{jabatanEdukasiService}")
    private JabatanEdukasiService jabatanEdukasiService;
    private List<JabatanEdukasi> listJabatanEdukasi;
    
    // Jabatan Profesi
    @ManagedProperty(value = "#{jabatanProfesiService}")
    private JabatanProfesiService jabatanProfesiService;
    private List<JabatanProfesi> listJabatanProfesi;
    
    // Jabatan Major
    @ManagedProperty(value = "#{jabatanMajorService}")
    private JabatanMajorService jabatanMajorService;
    private List<JabatanMajor> listJabatanMajor;
    
    // Jabatan Fakulty
    @ManagedProperty(value = "#{jabatanFakultyService}")
    private JabatanFacultyService jabatanFacultyService;
    private List<JabatanFakulty> listJabatanFakulties;
    
    //Klasifikasi Kerja Jabatan 
    @ManagedProperty(value = "#{klasifikasiKerjaJabatanService}")
    private KlasifikasiKerjaJabatanService klasifikasiKerjaJabatanService;
    private List<KlasifikasiKerjaJabatan> listKlasifikasiKerjaJabatan;
    
    //Jabatan Deskripsi
    @ManagedProperty(value = "#{jabatanDeskripsiService}")
    private JabatanDeskripsiService jabatanDeskripsiService;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    
    //Jabatan Spesifikasi
    @ManagedProperty(value = "#{jabatanSpesifikasiService}")
    private JabatanSpesifikasiService jabatanSpesifikasiService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    
    //Jabatan Type Spec
    @ManagedProperty(value = "#{orgTypeOfSpecJabatanService}")
    private OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService;
    private List<OrgTypeOfSpecJabatan> listOrgTypeOfSpecJabatan;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String jabatanId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(jabatanId.substring(1)));
            if(null != selectedJabatan){
            	listJabatanEdukasi = jabatanEdukasiService.getAllDataByJabatanId(selectedJabatan.getId());
            	listJabatanProfesi = jabatanProfesiService.getAllDataByJabatanId(selectedJabatan.getId());
            	listJabatanMajor = jabatanMajorService.getAllDataByJabatanId(selectedJabatan.getId());
            	listJabatanFakulties = jabatanFacultyService.getAllDataByJabatanId(selectedJabatan.getId());
            	listKlasifikasiKerjaJabatan = klasifikasiKerjaJabatanService.getAllDataByJabatanId(selectedJabatan.getId());
            	jabatanDeskripsis = jabatanDeskripsiService.getAllDataByJabatanId(selectedJabatan.getId());
                listJabatanSpesifikasi = jabatanSpesifikasiService.getAllDataByJabatanId(selectedJabatan.getId());
                listOrgTypeOfSpecJabatan = orgTypeOfSpecJabatanService.getAllDataByJabatanId(selectedJabatan.getId());
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
    	cleanAndExit();
        return "/protected/organisation/job_title_view.htm?faces-redirect=true";
    }

    public void doEdit() {
    	ExternalContext red = FacesUtil.getExternalContext();
        try {
			red.redirect(red.getRequestContextPath() + "/flow-protected/job?id=" + selectedJabatan.getId());
		} catch (IOException ex) {
			LOGGER.error("Error", ex);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	jabatanService = null;
    	jabatanDeskripsiService = null;
    	jabatanEdukasiService = null;
    	jabatanFacultyService = null;
    	jabatanFacultyService = null;
    	jabatanMajorService = null;
    	jabatanProfesiService = null;
    	jabatanSpesifikasiService = null;
    	orgTypeOfSpecJabatanService = null;
        selectedJabatan = null;
        listJabatanEdukasi = null;
        listJabatanFakulties = null;
        listJabatanMajor = null;
        listJabatanProfesi = null;
        listJabatanSpesifikasi = null;
        listKlasifikasiKerjaJabatan = null;
        listOrgTypeOfSpecJabatan = null;
        jabatanDeskripsis = null;
    }

    
    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    public List<OrgTypeOfSpecJabatan> getListOrgTypeOfSpecJabatan() {
		return listOrgTypeOfSpecJabatan;
	}

	public void setListOrgTypeOfSpecJabatan(
			List<OrgTypeOfSpecJabatan> listOrgTypeOfSpecJabatan) {
		this.listOrgTypeOfSpecJabatan = listOrgTypeOfSpecJabatan;
	}

	public OrgTypeOfSpecJabatanService getOrgTypeOfSpecJabatanService() {
        return orgTypeOfSpecJabatanService;
    }

    public void setOrgTypeOfSpecJabatanService(OrgTypeOfSpecJabatanService orgTypeOfSpecJabatanService) {
        this.orgTypeOfSpecJabatanService = orgTypeOfSpecJabatanService;
    }

	public List<JabatanEdukasi> getListJabatanEdukasi() {
		return listJabatanEdukasi;
	}

	public void setListJabatanEdukasi(List<JabatanEdukasi> listJabatanEdukasi) {
		this.listJabatanEdukasi = listJabatanEdukasi;
	}

	public List<JabatanProfesi> getListJabatanProfesi() {
		return listJabatanProfesi;
	}

	public void setListJabatanProfesi(List<JabatanProfesi> listJabatanProfesi) {
		this.listJabatanProfesi = listJabatanProfesi;
	}

	public List<JabatanMajor> getListJabatanMajor() {
		return listJabatanMajor;
	}

	public void setListJabatanMajor(List<JabatanMajor> listJabatanMajor) {
		this.listJabatanMajor = listJabatanMajor;
	}

	public List<JabatanFakulty> getListJabatanFakulties() {
		return listJabatanFakulties;
	}

	public void setListJabatanFakulties(List<JabatanFakulty> listJabatanFakulties) {
		this.listJabatanFakulties = listJabatanFakulties;
	}

	public List<KlasifikasiKerjaJabatan> getListKlasifikasiKerjaJabatan() {
		return listKlasifikasiKerjaJabatan;
	}

	public void setListKlasifikasiKerjaJabatan(
			List<KlasifikasiKerjaJabatan> listKlasifikasiKerjaJabatan) {
		this.listKlasifikasiKerjaJabatan = listKlasifikasiKerjaJabatan;
	}

	public void setJabatanEdukasiService(JabatanEdukasiService jabatanEdukasiService) {
		this.jabatanEdukasiService = jabatanEdukasiService;
	}

	public void setJabatanProfesiService(JabatanProfesiService jabatanProfesiService) {
		this.jabatanProfesiService = jabatanProfesiService;
	}

	public void setJabatanMajorService(JabatanMajorService jabatanMajorService) {
		this.jabatanMajorService = jabatanMajorService;
	}

	public void setJabatanFacultyService(JabatanFacultyService jabatanFacultyService) {
		this.jabatanFacultyService = jabatanFacultyService;
	}

	public void setKlasifikasiKerjaJabatanService(
			KlasifikasiKerjaJabatanService klasifikasiKerjaJabatanService) {
		this.klasifikasiKerjaJabatanService = klasifikasiKerjaJabatanService;
	}

	public void setJabatanDeskripsiService(
			JabatanDeskripsiService jabatanDeskripsiService) {
		this.jabatanDeskripsiService = jabatanDeskripsiService;
	}

	public void setJabatanSpesifikasiService(
			JabatanSpesifikasiService jabatanSpesifikasiService) {
		this.jabatanSpesifikasiService = jabatanSpesifikasiService;
	}
	
    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }
    
    
}
