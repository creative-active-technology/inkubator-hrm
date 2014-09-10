/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public final class HRMFinanceLib {

    /**
     * Menghitung Pokok yang harus dibayar perbulan pada methode efektif
     * <pre>
     *  Sampel Pinjamam total 24.000.000 dengan lama peminjama  2 tahun (24 bulan) dan bunga effectif 10%
     *  Hasil Perhitungan total pokok yang harus di bayar = 24.000.000/24 = 1 juta.
     * </pre>
     */
    public static double mainPaymmentEffectiveMode(double totalPinjamman, double lamaPinjaman) {
        return totalPinjamman / lamaPinjaman;
    }

    /**
     * Menghitung bunga yang harus dibayar perbulan pada methode efektif
     * <pre>
     *  Sampel Pinjamam total 24.000.000 dengan lama peminjama  2 tahun (24 bulan) dan bunga effectif 10%
     *  Hasil Perhitungan bunga pada bulan pertama adalah 24.000.000 x 10/100/12 = 200.000;
     *   Hasil Perhitungan bunga pada bulan kedua adalah 23.000.000 x 10/100/12 = 191.666,67;
     * </pre>
     */
    public static double interestRatePaymentEffectiveMode(double totalSisaPinjaman, double interestRate) {
        return (totalSisaPinjaman * interestRate) / 1200;
    }

    /**
     * Menghitung bunga yang harus dibayar perbulan pada methode flat
     * <pre>
     * Sampel Pinjamam total 24.000.000 dengan lama peminjama  2 tahun (24 bulan) dan bunga effectif 5%
     * Hasil Perhitungan total pokok yang harus di bayar = 24.000.000/24 = 1 juta.
     * </pre>
     */
    public static double mainPaymmentFlatMode(double totalPinjamman, double lamaPinjaman) {
        return totalPinjamman / lamaPinjaman;
    }

    /**
     * Menghitung bunga yang harus dibayar perbulan pada methode flat
     * <pre>
     * Sampel Pinjamam total 24.000.000 dengan lama peminjama  2 tahun (24 bulan) dan bunga effectif 5%
     *  Hasil Perhitungan bunga pada bulan pertama dan seterusnya adalah adalah 24.000.000 x 10/100/12 = 100.000;
     * </pre>
     */
    public static double interestRatePaymentFlateMode(double totalPinjaman, double interestRatePerYear) {
        return (totalPinjaman * interestRatePerYear) / 1200;
    }

    public static double anuitas(double totalPinjaman, double interestRatePerYear, double lamaPinjaman, double paymentPeriod) {
        double interest = interestRatePerYear / 100 / (12 / paymentPeriod);
        double iM = totalPinjaman * interest;
        double totalAnuitas = lamaPinjaman / paymentPeriod;
        double totalInterestRateAll = Math.pow((1 + interest), totalAnuitas);
        double totalInterestRateAllMin1 = totalInterestRateAll - 1;
        return iM * (totalInterestRateAll / totalInterestRateAllMin1);
    }

   
    public static LoanPayment getLoanPaymentFlateMode(LoanPayment loanPayment) {
        LoanPayment lp = new LoanPayment();
        lp.setBungaPertahun(loanPayment.getBungaPertahun());
        lp.setLamaPinjaman(loanPayment.getLamaPinjaman());
        lp.setName(lp.getName());
        lp.setPaymentPeriod(loanPayment.getPaymentPeriod());
        lp.setTanggalBayar(loanPayment.getTanggalBayar());
        lp.setTotalPinjaman(loanPayment.getTotalPinjaman());
        double totalAnuistas = lp.getLamaPinjaman() / lp.getPaymentPeriod();
        List<JadwalPembayaran> data = new ArrayList<>();
        for (int i = 1; i <= totalAnuistas; i++) {

            JadwalPembayaran jp = new JadwalPembayaran();
            if (i == 1) {
                jp.setUtangAwal(lp.getTotalPinjaman());
            }
            if (i > 1) {
                jp.setUtangAwal(data.get(i - 2).getSisaUtang());
            }

            double pokokPermonth = mainPaymmentFlatMode(lp.getTotalPinjaman(), lp.getLamaPinjaman());
            double bunga = interestRatePaymentFlateMode(lp.getTotalPinjaman(), lp.getBungaPertahun());
            jp.setPokok(pokokPermonth * lp.getPaymentPeriod());
            jp.setBunga(bunga * lp.getPaymentPeriod());
            jp.setAngsuran(jp.getPokok() + jp.getBunga());
            jp.setSisaUtang(jp.getUtangAwal() - jp.getPokok());
            jp.setUrutan(i);
            jp.setTanggalPembayaran(DateTimeUtil.getDateFrom(lp.getTanggalBayar(), i - 1, CommonUtilConstant.DATE_FORMAT_MONTH));
            data.add(jp);
        }
        lp.setJadwalPembayarans(data);
        return lp;
    }

    public static LoanPayment getLoanPaymentPaymentEffectiveMode(LoanPayment loanPayment) {
        LoanPayment lp = new LoanPayment();
        lp.setBungaPertahun(loanPayment.getBungaPertahun());
        lp.setLamaPinjaman(loanPayment.getLamaPinjaman());
        lp.setName(lp.getName());
        lp.setPaymentPeriod(loanPayment.getPaymentPeriod());
        lp.setTanggalBayar(loanPayment.getTanggalBayar());
        lp.setTotalPinjaman(loanPayment.getTotalPinjaman());
        double totalAnuistas = lp.getLamaPinjaman() / lp.getPaymentPeriod();
        List<JadwalPembayaran> data = new ArrayList<>();
        for (int i = 1; i <= totalAnuistas; i++) {

            JadwalPembayaran jp = new JadwalPembayaran();
            if (i == 1) {
                jp.setUtangAwal(lp.getTotalPinjaman());
            }
            if (i > 1) {
                jp.setUtangAwal(data.get(i - 2).getSisaUtang());
            }

            double pokokPermonth = mainPaymmentFlatMode(lp.getTotalPinjaman(), lp.getLamaPinjaman());
            double bunga = interestRatePaymentFlateMode(jp.getUtangAwal(), lp.getBungaPertahun());
            jp.setPokok(pokokPermonth * lp.getPaymentPeriod());
            jp.setBunga(bunga * lp.getPaymentPeriod());
            jp.setAngsuran(jp.getPokok() + jp.getBunga());
            jp.setSisaUtang(jp.getUtangAwal() - jp.getPokok());
            jp.setUrutan(i);
            jp.setTanggalPembayaran(DateTimeUtil.getDateFrom(lp.getTanggalBayar(), i - 1, CommonUtilConstant.DATE_FORMAT_MONTH));
            data.add(jp);
        }
        lp.setJadwalPembayarans(data);
        return lp;

    }

    public static LoanPayment getLoanPaymentAnuitas(LoanPayment loanPayment) {
        LoanPayment lp = new LoanPayment();
        lp.setBungaPertahun(loanPayment.getBungaPertahun());
        lp.setLamaPinjaman(loanPayment.getLamaPinjaman());
        lp.setName(lp.getName());
        lp.setPaymentPeriod(loanPayment.getPaymentPeriod());
        lp.setTanggalBayar(loanPayment.getTanggalBayar());
        lp.setTotalPinjaman(loanPayment.getTotalPinjaman());
        double anuitas = anuitas(lp.getTotalPinjaman(), lp.getBungaPertahun(), lp.getLamaPinjaman(), lp.getPaymentPeriod());
        double totalAnuitas = lp.getLamaPinjaman() / lp.getPaymentPeriod();

        List<JadwalPembayaran> data = new ArrayList<>();
        for (int i = 1; i <= totalAnuitas; i++) {
            JadwalPembayaran jp = new JadwalPembayaran();
            jp.setAngsuran(anuitas);
            if (i == 1) {
                jp.setUtangAwal(lp.getTotalPinjaman());

            }

            if (i > 1) {
                jp.setUtangAwal(data.get(i - 2).getSisaUtang());
            }
            double bungaPerPay = (lp.getPaymentPeriod() * (lp.getBungaPertahun() / 1200)) * jp.getUtangAwal();

            jp.setBunga(bungaPerPay);
            jp.setPokok(anuitas - bungaPerPay);
            jp.setSisaUtang(jp.getUtangAwal() - jp.getPokok());
            jp.setUrutan(i);
            jp.setTanggalPembayaran(DateTimeUtil.getDateFrom(lp.getTanggalBayar(), i - 1, CommonUtilConstant.DATE_FORMAT_MONTH));
            data.add(jp);
        }
        lp.setJadwalPembayarans(data);
        return lp;

    }

}
