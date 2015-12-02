/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.lazymodel.SearchEmployeeLazyDataModel;
import com.inkubator.hrm.web.model.SearchEmployeeModel;
import com.inkubator.webcore.util.FacesUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author Deni
 */
@Component(value = "searchEmployeeViewController")
@Lazy
public class SearchEmployeeViewController implements Serializable {

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private GolonganJabatanService golonganJabatanService;
    @Autowired
    private EmployeeTypeService employeeTypeService;
    @Autowired
    private EmpDataService empDataService;
    private String urlBackToSearchEmployee = StringUtils.EMPTY;

    public SearchEmployeeModel initSearchEmployeeFormFlow(RequestContext context) throws Exception {
        //deklarasi variable
        SearchEmployeeModel searchEmployeeModel = new SearchEmployeeModel();
        DualListModel<Department> dualListModelDepartment = new DualListModel<>();
        DualListModel<GolonganJabatan> dualListModelGolJab = new DualListModel<>();
        
        //get data
        List<Department> listDepartment = departmentService.getAllData();
        List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
        List<String> listEmployeeType = employeeTypeService.getEmployeeTypeNameByPk();
        //assign value
        dualListModelGolJab.setSource(listGolonganJabatan);
        dualListModelDepartment.setSource(listDepartment);
        searchEmployeeModel.setDualListModelDepartment(dualListModelDepartment);
        searchEmployeeModel.setDualListModelGoljab(dualListModelGolJab);
        searchEmployeeModel.setListEmployeeType(listEmployeeType);
        return searchEmployeeModel;
    }
    
    public void doGetParamSearchEmployee(RequestContext context) throws Exception{
    	
    	//Set urlBackToSearchEmployee ketika onRender halaman Result
    	urlBackToSearchEmployee = context.getFlowExecutionUrl();
    	
        String departments = "";
        String golonganJabatan = "";
        String tipeKaryawan = "";
        SearchEmployeeModel searchEmployeeModel = (SearchEmployeeModel) context.getFlowScope().get("searchEmployeeModel");
        List<Department> listDepartment = searchEmployeeModel.getDualListModelDepartment().getTarget();
        List<GolonganJabatan> listGolonganJabatan = searchEmployeeModel.getDualListModelGoljab().getTarget();
        String[] listEmployeeType = searchEmployeeModel.getEmployeeTypeName();
        int sizeDepartment = searchEmployeeModel.getDualListModelDepartment().getTarget().size();
        int sizeGolonganJabatan = searchEmployeeModel.getDualListModelGoljab().getTarget().size();
        int sizeTipeKaryawan = searchEmployeeModel.getEmployeeTypeName().length;
        int fromBirth = searchEmployeeModel.getFrom();
        int untilBirth = searchEmployeeModel.getUntil();
        int joinDate = searchEmployeeModel.getFromJoin();
        int untilDate = searchEmployeeModel.getUntilJoin();
        String nikFrom = searchEmployeeModel.getNikFrom();
        String nikUntil = searchEmployeeModel.getNikUntil();
        for(int j = 0; j < sizeDepartment; j++){
            if(j == (sizeDepartment - 1)){
                departments += listDepartment.get(j).getDepartmentName();
            }else{
                departments += listDepartment.get(j).getDepartmentName()+", ";
            }
        }
        
        for(int j = 0; j < sizeGolonganJabatan; j++){
            if(j == (sizeGolonganJabatan - 1)){
                golonganJabatan += listGolonganJabatan.get(j).getCode();
            }else{
                golonganJabatan += listGolonganJabatan.get(j).getCode()+", ";
            }
        }
        
        for(int j = 0; j < sizeTipeKaryawan; j++){
            if(j == (sizeTipeKaryawan - 1)){
                tipeKaryawan += listEmployeeType[j];
            }else{
                tipeKaryawan += listEmployeeType[j]+", ";
            }
        }
        searchEmployeeModel.setDepartments(departments);
        searchEmployeeModel.setGolonganJabatans(golonganJabatan);
        searchEmployeeModel.setTipeKaryawan(tipeKaryawan);
        List<String> listNik = empDataService.getAllNikBetween(nikFrom, nikUntil);
        List<Integer> listAge = getNumberBetweenFromAndUntil(fromBirth, untilBirth);
        List<Integer> listJoinDate = getNumberBetweenFromAndUntil(joinDate, untilDate);
//        List<EmpData> listEmpData = empDataService.getAllDataByParamWithDetail(listDepartment, listGolonganJabatan, listEmployeeType, listAge, listJoinDate, listNik);
//        searchEmployeeModel.setListEmpData(listEmpData);
        LazyDataModel<EmpData> lazyDataModel = new SearchEmployeeLazyDataModel(empDataService, listDepartment, listGolonganJabatan, listEmployeeType, listAge, listJoinDate, listNik);
        searchEmployeeModel.setLazyDataModel(lazyDataModel);
    }
    
    public List<Integer> getNumberBetweenFromAndUntil(int from, int until){
        List<Integer> listNumber = new ArrayList<Integer>();
        for(int i = from; i <= until; i++){
            listNumber.add(i);
        }
        return listNumber;
    }
    
    public void listEmployeeTypeNameView(RequestContext context) {
        String tipeKaryawan = "";
        SearchEmployeeModel searchEmployeeModel = (SearchEmployeeModel) context.getFlowScope().get("searchEmployeeModel");
        String[] listEmployeeType = searchEmployeeModel.getEmployeeTypeName();
        int sizeTipeKaryawan = searchEmployeeModel.getEmployeeTypeName().length;
        for(int j = 0; j < sizeTipeKaryawan; j++){
            if(j == (sizeTipeKaryawan - 1)){
                tipeKaryawan += listEmployeeType[j];
            }else{
                tipeKaryawan += listEmployeeType[j]+", ";
            }
        }
        searchEmployeeModel.setEmployeeTypeView(tipeKaryawan);
    }
    public String doBack() {
        return "/flow-protected/search_employee";
    }
    
    public void doDetailPersonalInfo(Long bioDataId){
    	try {
    		
    		/*Set Flag kalau url tujuan itu di view dari proses pencarian karyawan 
    		 * dan juga url back dari flow pencariannya ke dalam sessionMap
    		 */
			ExternalContext context = FacesUtil.getExternalContext();
			Map<String, Object> sessionMap = context.getSessionMap();
			sessionMap.put("isFromSearchEmployee", "true");
			sessionMap.put("urlBackToSearchEmployee", urlBackToSearchEmployee);
			
			context.redirect(context.getRequestContextPath() + "/protected/personalia/biodata_detail.htm?execution=e" + bioDataId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
    }
    
    public void doDetailEmployeeInfo(Long empDataId){
    	try {
    		
    		/*Set Flag kalau url tujuan itu di view dari proses pencarian karyawan 
    		 * dan juga url back dari flow pencariannya ke dalam sessionMap
    		 */
			ExternalContext context = FacesUtil.getExternalContext();
			Map<String, Object> sessionMap = context.getSessionMap();
			sessionMap.put("isFromSearchEmployee", "true");
			sessionMap.put("urlBackToSearchEmployee", urlBackToSearchEmployee);
			
			context.redirect(context.getRequestContextPath() + "/protected/employee/emp_placement_detail.htm?execution=e" + empDataId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
    }
    
    public void doDetailEmployeeBackgroundInfo(Long empDataId){
    	try {
    		
    		/*Set Flag kalau url tujuan itu di view dari proses pencarian karyawan 
    		 * dan juga url back dari flow pencariannya ke dalam sessionMap
    		 */
			ExternalContext context = FacesUtil.getExternalContext();
			Map<String, Object> sessionMap = context.getSessionMap();
			sessionMap.put("isFromSearchEmployee", "true");
			sessionMap.put("urlBackToSearchEmployee", urlBackToSearchEmployee);
			
			context.redirect(context.getRequestContextPath() + "/protected/personalia/emp_background_detail.htm?execution=e" + empDataId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
    }
}
