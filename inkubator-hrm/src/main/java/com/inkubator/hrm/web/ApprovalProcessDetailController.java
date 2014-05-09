/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;
//
//import com.inkubator.hrm.entity.ProscessToApprove;
//import com.inkubator.hrm.service.ProscessToApproveService;
//import com.inkubator.webcore.controller.BaseController;
//import com.inkubator.webcore.util.FacesUtil;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
//
///**
// *
// * @author Deni Husni FR
// */
//@ManagedBean(name = "approvalProcessDetailController")
//@RequestScoped
//public class ApprovalProcessDetailController extends BaseController {
//
//    private ProscessToApprove proscessToApprove;
//    @ManagedProperty(value = "#{proscessToApproveService}")
//    private ProscessToApproveService proscessToApproveService;
//
//    public void setProscessToApproveService(ProscessToApproveService proscessToApproveService) {
//        this.proscessToApproveService = proscessToApproveService;
//    }
//
//    public ProscessToApprove getProscessToApprove() {
//        return proscessToApprove;
//    }
//
//    public void setProscessToApprove(ProscessToApprove proscessToApprove) {
//        this.proscessToApprove = proscessToApprove;
//    }
//
//    @PostConstruct
//    @Override
//    public void initialization() {
//        super.initialization();
//        System.out.println(" shdfdsfjksdhfdsjfh");
//        String redirectParam = FacesUtil.getRequestParameter("execution");
//        if (redirectParam != null) {
//            try {
//                proscessToApprove = proscessToApproveService.getEntiyByPK(Long.parseLong(redirectParam.substring(1)));
//            } catch (Exception ex) {
//                LOGGER.error("Error", ex);
//            }
//        }
//
//    }
//
//    public String backToView() {
//        return "/protected/approval/approval_process.htm?faces-redirect=true";
//    }
//
//    @PreDestroy
//    public void onPostClose() {
//       proscessToApprove=null;
//       proscessToApproveService=null;
//    }
//}
