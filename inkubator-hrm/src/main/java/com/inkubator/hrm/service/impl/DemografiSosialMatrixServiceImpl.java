/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.DemografiSosialMatrixService;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author deni
 */
@Service(value = "demografiSosialMatrixService")
@Lazy
public class DemografiSosialMatrixServiceImpl implements DemografiSosialMatrixService {

    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private BioEducationHistoryDao bioEducationHistoryDao;
    @Autowired
    private BioDataDao bioDataDao;

    @Override
    public List<EducationLevel> getAllDataOrderByLevel() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllNameByParamOrderByLevelForAbsis(String param) throws Exception {
        List<String> listAbsisToShow = new ArrayList<String>();
        List<EmpDataMatrixModel> listDataAbsis = new ArrayList<EmpDataMatrixModel>();
        listAbsisToShow.add("Header");
        if (param.equals("pendidikan")) {
            List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
            for (EducationLevel educationLevel : listEducationLevel) {
                listAbsisToShow.add(educationLevel.getName());
            }
        } else if (param.equals("gender")) {
            listAbsisToShow.add("F");
            listAbsisToShow.add("M");
        } else if (param.equals("umur")){
            List<EmpDataMatrixModel> listAge = bioDataDao.getAllAgeFromBirthDate();
            for (EmpDataMatrixModel empDataMatrixModel : listAge) {
                listAbsisToShow.add(String.valueOf(empDataMatrixModel.getAges()));
            }
        }
        listAbsisToShow.add("Total");
        return listAbsisToShow;
    }

    @Override
    public List<EmpDataMatrixModel> getAllNameOrderByLevelWithModel() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow;
        EmpDataMatrixModel empDataMatrixModel;
        Long totalMale;
        Long totalFemale;
        Long totalDataBerdasarkanEdukasi;
        long tempTotalAllMale = 0 ;
        long tempTotalAllFemale = 0;
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        for (EducationLevel educationLevel : listEducationLevel) {
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            totalMale = bioEducationHistoryDao.getTotalByGenderMaleAndEducationLevel(educationLevel.getId());
            totalFemale = bioEducationHistoryDao.getTotalByGenderFemaleAndEducationLevel(educationLevel.getId());
            tempTotalAllFemale = tempTotalAllFemale + totalFemale;
            tempTotalAllMale = tempTotalAllMale + totalMale;
            System.out.println(totalMale + " - total");
            listEducationShow.add(educationLevel.getName());
            listEducationShow.add(String.valueOf(totalFemale));
            listEducationShow.add(String.valueOf(totalMale));
            listEducationShow.add(String.valueOf(totalMale + totalFemale));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }
        
        EmpDataMatrixModel totalBawah = new EmpDataMatrixModel();
        List<String> listTotalBawah = new ArrayList<String>();
        
        for (int i = 0; i < 1; i++) {
            listTotalBawah.add("TotalBawah");
            listTotalBawah.add(String.valueOf(tempTotalAllFemale));
            listTotalBawah.add(String.valueOf(tempTotalAllMale));
            listTotalBawah.add(String.valueOf(tempTotalAllFemale + tempTotalAllMale));
            totalBawah.setListGender(listTotalBawah);
            listDataModel.add(totalBawah);
        }
        return listDataModel;
    }

    @Override
    public List<EmpDataMatrixModel> getAllNameByGenderOrderByLevelWithModel() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow = new ArrayList<String>();
        EmpDataMatrixModel empDataMatrixModel = null;
        List<String> gender = new ArrayList<String>();
        List<Long> listTotalFemale = new ArrayList<Long>();
        List<Long> listTotalMale = new ArrayList<Long>();
        gender.add("F");
        gender.add("M");
        Long totalMale = null;
        Long totalFemale = null;
        long temp;
        long tempTotalMaleAndFemale;
        Long totalDataBerdasarkanEdukasi;
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        int j;
        for (int i = 0; i < 2; i++) {
            temp = 0;
            j = 0;
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            listEducationShow.add(gender.get(i));
            while (j < listEducationLevel.size()) {
                if (i == 0) {
                    totalFemale = bioEducationHistoryDao.getTotalByGenderFemaleAndEducationLevel(listEducationLevel.get(j).getId());
                    temp = temp + totalFemale;
                    listEducationShow.add(String.valueOf(totalFemale));
                    listTotalFemale.add(totalFemale);
                } else if (i == 1) {

                    totalMale = bioEducationHistoryDao.getTotalByGenderMaleAndEducationLevel(listEducationLevel.get(j).getId());
                    temp = temp + totalMale;
                    listEducationShow.add(String.valueOf(totalMale));
                    listTotalMale.add(totalMale);
                }

                j = j + 1;
            }
            listEducationShow.add(String.valueOf(temp));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }
        
        // Total Data Paling Bawah
        List<String> listTotalBawah = new ArrayList<String>();
        EmpDataMatrixModel totalBawah = new EmpDataMatrixModel();
        long totalFemaleAndMale;
        for (int i = 0; i < 1; i++) {
            totalFemaleAndMale = 0;
            tempTotalMaleAndFemale = 0;
            j = 0;
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            listTotalBawah.add("TotalBawah");
            while (j < listEducationLevel.size()) {
                if (i == 0) {
                    totalFemaleAndMale = listTotalFemale.get(j) + listTotalMale.get(j);
                    tempTotalMaleAndFemale = tempTotalMaleAndFemale + totalFemaleAndMale;
                    listTotalBawah.add(String.valueOf(totalFemaleAndMale));
                }
                j = j + 1;
            }
            listTotalBawah.add(String.valueOf(tempTotalMaleAndFemale));
            totalBawah.setListGender(listTotalBawah);
            listDataModel.add(totalBawah);
        }

        return listDataModel;
    }

    @Override
    public List<String> getAllAgeForAbsis() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
