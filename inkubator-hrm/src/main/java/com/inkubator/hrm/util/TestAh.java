/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.util;

/**
 *
 * @author Deni Husni FR
 */
public class TestAh {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        System.out.println(HRMFinanceLib.MainPaymmentFlatMode(24000000, 24));
//        
//        System.out.println(HRMFinanceLib.interestRatePaymentFlateMode(24000000,  5));
//        
//        
//        System.out.println(HRMFinanceLib.anuitas(50000000, 24, 24,6));
        
        LoanPayment loanPayment=new LoanPayment();
        loanPayment.setBungaPertahun(20);
        loanPayment.setLamaPinjaman(36);
        loanPayment.setPaymentPeriod(4);
        loanPayment.setTotalPinjaman(10000000);
        HRMFinanceLib.getLoanPaymentFlateMode(loanPayment);
        
        System.out.println(HRMFinanceLib.getLoanPaymentFlateMode(loanPayment).getJadwalPembayarans().get(0).getPokok());
         System.out.println(HRMFinanceLib.getLoanPaymentFlateMode(loanPayment).getJadwalPembayarans().get(0).getBunga());
        
    }
    
}
