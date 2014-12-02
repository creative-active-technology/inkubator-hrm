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
////        
////        System.out.println(HRMFinanceLib.anuitas(50000000, 24, 24,6));
//        LoanPayment loanPayment = new LoanPayment();
//        loanPayment.setBungaPertahun(33.07);
//        loanPayment.setLamaPinjaman(23);
//        loanPayment.setPaymentPeriod(1);
//        loanPayment.setTotalPinjaman(8687004.79);
//        loanPayment.setTanggalBayar(new Date());
////        HRMFinanceLib.getLoanPaymentFlateMode(loanPayment);
////        
////        System.out.println(HRMFinanceLib.getLoanPaymentFlateMode(loanPayment).getJadwalPembayarans().get(0).getPokok());
////         System.out.println(HRMFinanceLib.getLoanPaymentFlateMode(loanPayment).getJadwalPembayarans().get(0).getBunga());
////        loanPayment = HRMFinanceLib.getLoanPaymentFlateMode(loanPayment);
//////        System.out.println(" hahahah");
////        List<JadwalPembayaran> jadwalPembayarans = loanPayment.getJadwalPembayarans();
////        for (JadwalPembayaran jadwalPembayaran : jadwalPembayarans) {
////            System.out.println(jadwalPembayaran.getSisaUtang());
////            System.out.println(jadwalPembayaran);
////        }
//
//        loanPayment = HRMFinanceLib.getLoanPaymentAnuitas(loanPayment);
//        System.out.println(" hahahah");
//        List<JadwalPembayaran> jadwalPembayaransdd = loanPayment.getJadwalPembayarans();
//        for (JadwalPembayaran jadwalPembayaran : jadwalPembayaransdd) {
//            System.out.println(jadwalPembayaran.getAngsuran());
//            System.out.println(jadwalPembayaran);
//        }

        System.out.println(RomanovUtil.convertToRoman(12));
    }

}
