/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.util.HRMFinanceLib;
import com.inkubator.hrm.util.JadwalPembayaran;
import com.inkubator.hrm.util.LoanPayment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "loanNewApplicationService")
@Lazy
public class LoanNewApplicationServiceImpl extends BaseApprovalServiceImpl implements LoanNewApplicationService {
    
     @Autowired
    private LoanNewApplicationDao loanNewApplicationDao;

    @Override
    public LoanNewApplication getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication saveData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication updateData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication saveOrUpdateData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewApplicationInstallment> getAllDataLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception {
        return calculateLoanNewApplicationInstallment(interestRate, termin, loanPaymentDate, nominalPrincipal, typeOfInterest);
    }

    private List<LoanNewApplicationInstallment> calculateLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) {
        //set parameter for calculating jadwalPembayaran
        LoanPayment loanPayment = new LoanPayment();
        loanPayment.setBungaPertahun(interestRate);
        loanPayment.setLamaPinjaman(termin);
        loanPayment.setPaymentPeriod(1);
        loanPayment.setTanggalBayar(loanPaymentDate);
        loanPayment.setTotalPinjaman(nominalPrincipal);

        //calculate jadwalPembayaran
        if (typeOfInterest.equals(HRMConstant.ANNUITY)) {
            loanPayment = HRMFinanceLib.getLoanPaymentAnuitas(loanPayment);
        } else if (typeOfInterest.equals(HRMConstant.FLAT)) {
            loanPayment = HRMFinanceLib.getLoanPaymentFlateMode(loanPayment);
        } else if (typeOfInterest.equals(HRMConstant.FLOATING)) {
            loanPayment = HRMFinanceLib.getLoanPaymentEffectiveMode(loanPayment);
        }

        //convert jadwalPembayaran to loanNewApplicationInstallment
        List<LoanNewApplicationInstallment> listLoanNewApplicationInstallments = new ArrayList<>();
        for (JadwalPembayaran jadwalPembayaran : loanPayment.getJadwalPembayarans()) {

            LoanNewApplicationInstallment loanNewApplicationInstallment = new LoanNewApplicationInstallment();
            loanNewApplicationInstallment.setInstallmentDate(jadwalPembayaran.getTanggalPembayaran());
            loanNewApplicationInstallment.setInterestNominal(jadwalPembayaran.getBunga());
            loanNewApplicationInstallment.setBasicNominal(jadwalPembayaran.getPokok());
            loanNewApplicationInstallment.setTotalPayment(jadwalPembayaran.getAngsuran());
            loanNewApplicationInstallment.setRemainingBasic(jadwalPembayaran.getSisaUtang());
            listLoanNewApplicationInstallments.add(loanNewApplicationInstallment);
        }

        return listLoanNewApplicationInstallments;
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejected(long approvalActivityId, String comment) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void diverted(long approvalActivityId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef) throws Exception {
        return super.getListApproverByListAppDef(listAppDef);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getCurrentMaxId() {
        return this.loanNewApplicationDao.getCurrentMaxId();
    }
}
