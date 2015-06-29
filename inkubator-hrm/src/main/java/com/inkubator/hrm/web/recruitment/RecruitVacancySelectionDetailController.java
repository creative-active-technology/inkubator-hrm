package com.inkubator.hrm.web.recruitment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;
import com.inkubator.hrm.service.RecruitVacancySelectionDetailPicService;
import com.inkubator.hrm.service.RecruitVacancySelectionDetailService;
import com.inkubator.hrm.service.RecruitVacancySelectionService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "recruitVacancySelectionDetailController")
@ViewScoped
public class RecruitVacancySelectionDetailController extends BaseController{
	@ManagedProperty(value = "#{recruitVacancySelectionService}")
	private RecruitVacancySelectionService recruitVacancySelectionService;
	@ManagedProperty(value = "#{recruitVacancySelectionDetailService}")
	private RecruitVacancySelectionDetailService recruitVacancySelectionDetailService;
	@ManagedProperty(value = "#{recruitVacancySelectionDetailPicService}")
	private RecruitVacancySelectionDetailPicService recruitVacancySelectionDetailPicService;
	private RecruitVacancySelection recruitVacancySelection;
	private RecruitVacancySelectionDetail selectedRecruitVacancySelectionDetail;
	private List<RecruitVacancySelectionDetailPic> listRecruitVacancySelectionDetailPic;
	private List<RecruitVacancySelectionDetail> listVacancySelectionDetail;
	
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String recruitVacancySelectionId = FacesUtil.getRequestParameter("execution");
        try {
			recruitVacancySelection = recruitVacancySelectionService.getEntityByPkWithDetail(Long.valueOf(recruitVacancySelectionId.substring(1)));
			listVacancySelectionDetail = recruitVacancySelectionDetailService.getAllDataByRecruitVacancySelection(recruitVacancySelection.getId());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    private void cleanAndExit() {
    	recruitVacancySelectionService = null;
    	recruitVacancySelection = null;
    	recruitVacancySelectionDetailService = null;
    	listVacancySelectionDetail = null;
    	recruitVacancySelectionDetailPicService = null;
    	listRecruitVacancySelectionDetailPic = null;
    }

	public RecruitVacancySelectionService getRecruitVacancySelectionService() {
		return recruitVacancySelectionService;
	}

	public void setRecruitVacancySelectionService(
			RecruitVacancySelectionService recruitVacancySelectionService) {
		this.recruitVacancySelectionService = recruitVacancySelectionService;
	}

	public RecruitVacancySelection getRecruitVacancySelection() {
		return recruitVacancySelection;
	}

	public void setRecruitVacancySelection(
			RecruitVacancySelection recruitVacancySelection) {
		this.recruitVacancySelection = recruitVacancySelection;
	}

	public RecruitVacancySelectionDetailService getRecruitVacancySelectionDetailService() {
		return recruitVacancySelectionDetailService;
	}

	public void setRecruitVacancySelectionDetailService(
			RecruitVacancySelectionDetailService recruitVacancySelectionDetailService) {
		this.recruitVacancySelectionDetailService = recruitVacancySelectionDetailService;
	}

	public List<RecruitVacancySelectionDetail> getListVacancySelectionDetail() {
		return listVacancySelectionDetail;
	}

	public void setListVacancySelectionDetail(
			List<RecruitVacancySelectionDetail> listVacancySelectionDetail) {
		this.listVacancySelectionDetail = listVacancySelectionDetail;
	}

	public RecruitVacancySelectionDetail getSelectedRecruitVacancySelectionDetail() {
		return selectedRecruitVacancySelectionDetail;
	}

	public void setSelectedRecruitVacancySelectionDetail(
			RecruitVacancySelectionDetail selectedRecruitVacancySelectionDetail) {
		this.selectedRecruitVacancySelectionDetail = selectedRecruitVacancySelectionDetail;
	}

	public void doSelectDetailEntity(){
		try {
			selectedRecruitVacancySelectionDetail = this.recruitVacancySelectionDetailService.getEntiyByPK(selectedRecruitVacancySelectionDetail.getId());
			listRecruitVacancySelectionDetailPic = recruitVacancySelectionDetailPicService.getAllDataByRecruitVacancySelectionDetailId(selectedRecruitVacancySelectionDetail.getRecruitVacancySelection().getId());
		} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
	}

	public RecruitVacancySelectionDetailPicService getRecruitVacancySelectionDetailPicService() {
		return recruitVacancySelectionDetailPicService;
	}

	public void setRecruitVacancySelectionDetailPicService(
			RecruitVacancySelectionDetailPicService recruitVacancySelectionDetailPicService) {
		this.recruitVacancySelectionDetailPicService = recruitVacancySelectionDetailPicService;
	}

	public List<RecruitVacancySelectionDetailPic> getListRecruitVacancySelectionDetailPic() {
		return listRecruitVacancySelectionDetailPic;
	}

	public void setListRecruitVacancySelectionDetailPic(
			List<RecruitVacancySelectionDetailPic> listRecruitVacancySelectionDetailPic) {
		this.listRecruitVacancySelectionDetailPic = listRecruitVacancySelectionDetailPic;
	}
	
	
    
    
}