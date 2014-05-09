///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.inkubator.hrm.web;
//
//import com.inkubator.hrm.entity.Position;
//import com.inkubator.hrm.service.PositionService;
//import com.inkubator.hrm.web.lazymodel.PositionLazyDataModel;
//import com.inkubator.hrm.web.search.PositionSearchParameter;
//import com.inkubator.webcore.controller.BaseController;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import org.primefaces.model.LazyDataModel;
//
///**
// *
// * @author Deni Husni FR
// */
//@ManagedBean(name = "positionViewController")
//@ViewScoped
//public class PositionViewController extends BaseController {
//
//    private PositionSearchParameter positionSearchParameter;
//    private LazyDataModel<Position> lazyDataModel;
//    @ManagedProperty(value = "#{positionService}")
//    private PositionService positionService;
//
//    public void setPositionService(PositionService positionService) {
//        this.positionService = positionService;
//    }
//
//    @PostConstruct
//    @Override
//    public void initialization() {
//        super.initialization();
//        positionSearchParameter = new PositionSearchParameter();
//    }
//
//    public PositionSearchParameter getPositionSearchParameter() {
//        return positionSearchParameter;
//    }
//
//    public void setPositionSearchParameter(PositionSearchParameter positionSearchParameter) {
//        this.positionSearchParameter = positionSearchParameter;
//    }
//
//    public LazyDataModel<Position> getLazyDataModel() {
//        if (lazyDataModel == null) {
//            lazyDataModel = new PositionLazyDataModel(positionSearchParameter, positionService);
//        }
//        return lazyDataModel;
//    }
//
//    public void setLazyDataModel(LazyDataModel<Position> lazyDataModel) {
//        this.lazyDataModel = lazyDataModel;
//    }
//
//}
