/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public class LoanPayment {

    private Date tanggalBayar;
    private String name;
    private double totalPinjaman;
    private double bungaPertahun;
    private double lamaPinjaman;
    private double paymentPeriod;
    private List<JadwalPembayaran> jadwalPembayarans = new ArrayList<JadwalPembayaran>();

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(double totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

    public double getBungaPertahun() {
        return bungaPertahun;
    }

    public void setBungaPertahun(double bungaPertahun) {
        this.bungaPertahun = bungaPertahun;
    }

    public double getLamaPinjaman() {
        return lamaPinjaman;
    }

    public void setLamaPinjaman(double lamaPinjaman) {
        this.lamaPinjaman = lamaPinjaman;
    }

    public double getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(double paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public List<JadwalPembayaran> getJadwalPembayarans() {
        return jadwalPembayarans;
    }

    public void setJadwalPembayarans(List<JadwalPembayaran> jadwalPembayarans) {
        this.jadwalPembayarans = jadwalPembayarans;
    }
    
    

}
