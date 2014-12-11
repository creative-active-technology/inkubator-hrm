/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.lazymodel.PaySalaryExecuteLazyDataModel;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.PaySalaryExecuteModel;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryExecuteController")
@ViewScoped
public class PaySalaryExecuteController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    private PayTempKalkulasiSearchParameter searchParameter;
    private LazyDataModel<PayTempKalkulasiModel> lazyDataModel;
    private PayTempKalkulasi selected;
    private String parameter;
    private Integer jumlahKaryawan;
    private Double jumlahNominal;
    @ManagedProperty(value = "#{jobLauncher}")
    private JobLauncher jobLauncher;
    @ManagedProperty(value = "#{jobPayEmployeeCalculation}")
    private Job jobPayEmployeeCalculation;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private Long getTotalKaryawan;
    private PayTempKalkulasiModel payTempKalkulasiModel;
    private Integer progress;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PayTempKalkulasiSearchParameter();
        payTempKalkulasiModel = new PayTempKalkulasiModel();
        progress = null;
        WtPeriode wtPeriode;
        try {
            wtPeriode = wtPeriodeService.getEntityByStatusActive();
            List<EmpData> getAllDataNotTerminate = empDataService.getAllDataNotTerminate();
            getTotalKaryawan = Long.valueOf(getAllDataNotTerminate.size());
            if (wtPeriode != null) {
                payTempKalkulasiModel.setStartDate(wtPeriode.getFromPeriode());
                payTempKalkulasiModel.setEndDate(wtPeriode.getUntilPeriode());
            }
        } catch (Exception ex) {
            Logger.getLogger(PaySalaryExecuteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        lazyDataModel = null;
        searchParameter = null;
        payTempKalkulasiService = null;
        empDataService = null;
        parameter = null;
        getTotalKaryawan = null;
    }

    public void doSearch() {
        System.out.println(searchParameter.getPaySalaryComponent() + " hohohohooho" + parameter);
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.payTempKalkulasiService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void calculatePayRoll() {
        try {
            payTempKalkulasiService.deleteAllData();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("timeInMilis", String.valueOf(System.currentTimeMillis())).toJobParameters();
            JobExecution jobExecution = jobLauncher.run(jobPayEmployeeCalculation, jobParameters);

            //payTempKalkulasiService.calculatePayRoll();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doDetail() {
        return "/protected/payroll/salary_execution_detail.htm?faces-redirect=true&execution=e" + payTempKalkulasiModel.getPaySalaryComponentId();
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public PayTempKalkulasiSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayTempKalkulasiSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayTempKalkulasiModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PaySalaryExecuteLazyDataModel(parameter, payTempKalkulasiService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempKalkulasiModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasi getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasi selected) {
        this.selected = selected;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public Job getJobPayEmployeeCalculation() {
        return jobPayEmployeeCalculation;
    }

    public void setJobPayEmployeeCalculation(Job jobPayEmployeeCalculation) {
        this.jobPayEmployeeCalculation = jobPayEmployeeCalculation;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public Double getJumlahNominal() {
        return jumlahNominal;
    }

    public void setJumlahNominal(Double jumlahNominal) {
        this.jumlahNominal = jumlahNominal;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Long getGetTotalKaryawan() {
        return getTotalKaryawan;
    }

    public void setGetTotalKaryawan(Long getTotalKaryawan) {
        this.getTotalKaryawan = getTotalKaryawan;
    }

    public PayTempKalkulasiModel getPayTempKalkulasiModel() {
        return payTempKalkulasiModel;
    }

    public void setPayTempKalkulasiModel(PayTempKalkulasiModel payTempKalkulasiModel) {
        this.payTempKalkulasiModel = payTempKalkulasiModel;
    }

    public Integer getProgress() {
//        if (progress == null) {
//            progress = 0;
//        } else {
//            progress = progress + (int) (Math.random() * 35);
//
//            if (progress > 100) {
//                progress = 100;
//            }
//        }
//        System.out.println(progress);
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }

    public void startProgress() {

        for (int i = 0; i < 100; i++) {
            setProgress(i);
            System.out.println("TestFunction - setting progress to: " + i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
        setProgress(100);
    }
}
