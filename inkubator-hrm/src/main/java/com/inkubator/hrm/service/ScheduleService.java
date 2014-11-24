/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

/**
 *
 * @author Deni Husni FR
 */
public interface ScheduleService {

    public void deleteRiwayatAkses() throws Exception;

    public void calculateScheduleWorking() throws Exception;

    public void deleteLoginHistory() throws Exception;
    
//    public void checkPasswordHistoryEmailNotSend() throws Exception;
}
