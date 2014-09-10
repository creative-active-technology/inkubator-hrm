/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.util.Date;

/**
 *
 * @author Deni Husni FR
 */
public class JadwalPembayaran {

    private int urutan;
    private Date tanggalPembayaran;
    private double angsuran;
    private double utangAwal;
    private double sisaUtang;
    private double bunga;
    private double pokok;

    public int getUrutan() {
        return urutan;
    }

    public void setUrutan(int urutan) {
        this.urutan = urutan;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public double getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(double angsuran) {
        this.angsuran = angsuran;
    }

    public double getUtangAwal() {
        return utangAwal;
    }

    public void setUtangAwal(double utangAwal) {
        this.utangAwal = utangAwal;
    }

    public double getSisaUtang() {
        return sisaUtang;
    }

    public void setSisaUtang(double sisaUtang) {
        this.sisaUtang = sisaUtang;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public double getPokok() {
        return pokok;
    }

    public void setPokok(double pokok) {
        this.pokok = pokok;
    }

    @Override
    public String toString() {
        return "JadwalPembayaran{" + "urutan=" + urutan + ", tanggalPembayaran=" + tanggalPembayaran + ", angsuran=" + angsuran + ", utangAwal=" + utangAwal + ", sisaUtang=" + sisaUtang + ", bunga=" + bunga + ", pokok=" + pokok + '}';
    }
    
    

}
