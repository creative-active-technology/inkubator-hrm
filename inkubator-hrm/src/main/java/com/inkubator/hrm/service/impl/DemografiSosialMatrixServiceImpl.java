/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.DemografiSosialMatrixService;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        } else if (param.equals("umur")) {
            List<EmpDataMatrixModel> listAge = bioDataDao.getAllAgeFromBirthDate();
            for (EmpDataMatrixModel empDataMatrixModel : listAge) {
                if (empDataMatrixModel.getAges2() != null) {
                    listAbsisToShow.add(String.valueOf(empDataMatrixModel.getAges2()));
                }
            }
        }
        listAbsisToShow.add("Total");
        return listAbsisToShow;
    }

    @Override
    public List<EmpDataMatrixModel> getAllDataPendidikanVsGender() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow;
        EmpDataMatrixModel empDataMatrixModel;
        Long totalMale;
        Long totalFemale;
        Long totalDataBerdasarkanEdukasi;
        long tempTotalAllMale = 0;
        long tempTotalAllFemale = 0;
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        for (EducationLevel educationLevel : listEducationLevel) {
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            totalMale = bioEducationHistoryDao.getTotalByGenderMaleAndEducationLevel(educationLevel.getId());
            totalFemale = bioEducationHistoryDao.getTotalByGenderFemaleAndEducationLevel(educationLevel.getId());
            tempTotalAllFemale = tempTotalAllFemale + totalFemale;
            tempTotalAllMale = tempTotalAllMale + totalMale;
       
            listEducationShow.add(educationLevel.getName());
            listEducationShow.add(String.valueOf(totalFemale));
            listEducationShow.add(String.valueOf(totalMale));
            listEducationShow.add(String.valueOf(totalMale + totalFemale));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }

        EmpDataMatrixModel totalBawah = new EmpDataMatrixModel();
        List<String> listTotalBawah = new ArrayList<String>();

        listTotalBawah.add("TotalBawah");
        listTotalBawah.add(String.valueOf(tempTotalAllFemale));
        listTotalBawah.add(String.valueOf(tempTotalAllMale));
        listTotalBawah.add(String.valueOf(tempTotalAllFemale + tempTotalAllMale));
        totalBawah.setListGender(listTotalBawah);
        listDataModel.add(totalBawah);
        return listDataModel;
    }

    @Override
    public List<EmpDataMatrixModel> getAllDataGenderVsPendidikan() throws Exception {
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
    public List<EmpDataMatrixModel> getAllDataUmurVsGender() throws Exception {
        List<String> gender = new ArrayList<String>();
        List<Double> listTotalFemale = new ArrayList<Double>();
        List<Double> listTotalMale = new ArrayList<Double>();
        List<String> listEducationShow = new ArrayList<String>();
        Map<Double, Double> ageMapMale = new HashMap<Double, Double>();
        Map<Double, Double> ageMapFemale = new HashMap<Double, Double>();
        EmpDataMatrixModel empDataMatrixModel = null;
        gender.add("F");
        gender.add("M");
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        int j;
        List<EmpDataMatrixModel> listAge = bioDataDao.getAllAgeFromBirthDate();

        int pembandingTerbanyak = listAge.size();
        List<EmpDataMatrixModel> listAgeFemale = bioDataDao.getTotalByAgeAndGenderFemaleFromBirthDate();
        List<EmpDataMatrixModel> listAgeMale = bioDataDao.getTotalByAgeAndGenderMaleFromBirthDate();
        for (EmpDataMatrixModel ages : listAgeMale) {
            ageMapMale.put(ages.getAges2(), ages.getBanyakDatas().doubleValue());
        }
        for (EmpDataMatrixModel ages : listAgeFemale) {
            ageMapFemale.put(ages.getAges2(), ages.getBanyakDatas().doubleValue());
        }
  
        Double totalMale = null;
        Double totalFemale = null;
        Double totalSamping;
        int compareDouble = 0;
        j = 0;
        while (j < pembandingTerbanyak) {
            if (j < listAgeFemale.size()) {
                if (listAgeFemale.get(j).getAges2() != null) {
                    if (ageMapFemale.get(listAge.get(j).getAges2()) == null) {
                        ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                    }
                }
            }
            if (j < listAgeMale.size()) {
                if (listAgeMale.get(j).getAges2() != null) {
                    if (ageMapMale.get(listAge.get(j).getAges2()) == null) {
                        ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                    }
                }
            } else {
                if (ageMapMale.get(listAge.get(j).getAges2()) == null) {
                    ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                }
            }
            j++;
        }

        for (int i = 0; i < 2; i++) {
            j = 0;
            totalSamping = 0.0;
            empDataMatrixModel = new EmpDataMatrixModel();
            listEducationShow = new ArrayList<String>();
            listEducationShow.add(gender.get(i));

            while (j < pembandingTerbanyak) {
                if (i == 0) {
                    if (j < ageMapFemale.size()) {
                        if (listAge.get(j).getAges2() != null) {
                            if (ageMapFemale.get(listAge.get(j).getAges2()) != null) {
                                totalFemale = ageMapFemale.get(listAge.get(j).getAges2());
                                totalSamping = totalSamping + totalFemale;
                                listTotalFemale.add(totalFemale);
                            }

                            listEducationShow.add(String.valueOf(totalFemale));
                        }
                    }
                } else if (i == 1) {
                    if (j < ageMapMale.size()) {
                        if (listAge.get(j).getAges2() != null) {
                            if (ageMapMale.get(listAge.get(j).getAges2()) != null) {
                                totalMale = ageMapMale.get(listAge.get(j).getAges2());
                                totalSamping = totalSamping + totalMale;
                                listTotalMale.add(totalMale);
                            }

                            listEducationShow.add(String.valueOf(totalMale));
                        }

                    } else {

                    }
                }

                j++;
            }
            listEducationShow.add(String.valueOf(totalSamping));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }

        // Total Data Paling Bawah
        List<String> listTotalBawah = new ArrayList<String>();
        EmpDataMatrixModel totalBawah = new EmpDataMatrixModel();
        Double tempTotalMaleAndFemale;
        Double totalFemaleAndMale = 0.0;
        totalFemaleAndMale = 0.0;
        tempTotalMaleAndFemale = 0.0;
        j = 0;
        listEducationShow = new ArrayList<String>();
        empDataMatrixModel = new EmpDataMatrixModel();
        listTotalBawah.add("TotalBawah");
        while (j < pembandingTerbanyak) {
            if (j < listTotalFemale.size()) {
                totalFemaleAndMale = listTotalFemale.get(j) + listTotalMale.get(j);
                tempTotalMaleAndFemale = tempTotalMaleAndFemale + totalFemaleAndMale;
                listTotalBawah.add(String.valueOf(totalFemaleAndMale));
            }
            j = j + 1;
        }
        listTotalBawah.add(String.valueOf(tempTotalMaleAndFemale));
        totalBawah.setListGender(listTotalBawah);
        listDataModel.add(totalBawah);

        return listDataModel;
    }

    @Override
    public List<EmpDataMatrixModel> getAllDataGenderVsUmur() throws Exception {
        List<EmpDataMatrixModel> listAge = bioDataDao.getAllAgeFromBirthDate();
        List<String> listEducationShow;
        EmpDataMatrixModel empDataMatrixModel;
        Double totalMale;
        Double totalFemale;
        Double tempTotalAllMale = 0.0;
        Double tempTotalAllFemale = 0.0;
        int pembandingTerbanyak = listAge.size();
        int j;
        Map<Double, Double> ageMapMale = new HashMap<Double, Double>();
        Map<Double, Double> ageMapFemale = new HashMap<Double, Double>();
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        List<EmpDataMatrixModel> listAgeFemale = bioDataDao.getTotalByAgeAndGenderFemaleFromBirthDate();
        List<EmpDataMatrixModel> listAgeMale = bioDataDao.getTotalByAgeAndGenderMaleFromBirthDate();
        for (EmpDataMatrixModel ages : listAgeMale) {
            ageMapMale.put(ages.getAges2(), ages.getBanyakDatas().doubleValue());
        }
        for (EmpDataMatrixModel ages : listAgeFemale) {
            ageMapFemale.put(ages.getAges2(), ages.getBanyakDatas().doubleValue());
        }
        j = 0;
        while (j < pembandingTerbanyak) {
            if (j < listAgeFemale.size()) {
                if (listAgeFemale.get(j).getAges2() != null) {
                    if (ageMapFemale.get(listAge.get(j).getAges2()) == null) {
                        ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                    }
                }
            }
            if (j < listAgeMale.size()) {
                if (listAgeMale.get(j).getAges2() != null) {
                    if (ageMapMale.get(listAge.get(j).getAges2()) == null) {
                        ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                    }
                }
            } else {
                if (ageMapMale.get(listAge.get(j).getAges2()) == null) {
                    ageMapMale.put(listAge.get(j).getAges2(), 0.0);
                }
            }
            j++;
        }
        j = 0;
        for (EmpDataMatrixModel age : listAge) {
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            if (listAge.get(j).getAges2() != null) {
                totalFemale = ageMapFemale.get(listAge.get(j).getAges2());
                totalMale = ageMapMale.get(listAge.get(j).getAges2());
                tempTotalAllFemale = tempTotalAllFemale + totalFemale;
                tempTotalAllMale = tempTotalAllMale + totalMale;
                listEducationShow.add(String.valueOf(age.getAges2()));
                listEducationShow.add(String.valueOf(totalFemale));
                listEducationShow.add(String.valueOf(totalMale));
                listEducationShow.add(String.valueOf(totalMale + totalFemale));
                empDataMatrixModel.setListGender(listEducationShow);
                listDataModel.add(empDataMatrixModel);
            }
            j++;
        }

        EmpDataMatrixModel totalBawah = new EmpDataMatrixModel();
        List<String> listTotalBawah = new ArrayList<String>();

        listTotalBawah.add("TotalBawah");
        listTotalBawah.add(String.valueOf(tempTotalAllFemale));
        listTotalBawah.add(String.valueOf(tempTotalAllMale));
        listTotalBawah.add(String.valueOf(tempTotalAllFemale + tempTotalAllMale));
        totalBawah.setListGender(listTotalBawah);
        listDataModel.add(totalBawah);
        return listDataModel;
    }

}
