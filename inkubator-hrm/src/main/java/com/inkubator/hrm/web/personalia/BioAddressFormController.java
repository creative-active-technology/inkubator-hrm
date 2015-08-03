package com.inkubator.hrm.web.personalia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.BioAddressService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.BioAddressModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "bioAddressFormController")
@ViewScoped
public class BioAddressFormController extends BaseController {
	
	private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
	private BioAddressModel model;
	private Boolean isUpdate;
	@ManagedProperty(value = "#{bioAddressService}")
	private BioAddressService bioAddressService;
	@ManagedProperty(value = "#{countryService}")
	private CountryService countryService;
	@ManagedProperty(value = "#{provinceService}")
	private ProvinceService provinceService;
	@ManagedProperty(value = "#{cityService}")
	private CityService cityService;
	private String isRevision;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	isRevision = StringUtils.EMPTY;
            isUpdate = Boolean.FALSE;
            model = new BioAddressModel();
            countries = countryService.getAllData();
            provinces = new ArrayList<Province>();
            cities = new ArrayList<City>();
            
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            model.setBioDataId(Long.parseLong(bioDataId));
            
            //parameter is Revision untuk flag jika ini datangnya dari request perubahan biodata
            isRevision = FacesUtil.getRequestParameter("isRevision");
            if(StringUtils.isNotBlank(isRevision)){
            	
            	String isEditOnRevision = FacesUtil.getRequestParameter("isEditOnRevision");
            	if(StringUtils.equals(isEditOnRevision, "Yes")){
            		Map<String, Object> sessionMap = FacesUtil.getExternalContext().getSessionMap();
                	BioAddress bioAddress = (BioAddress) sessionMap.get("selectedBioAddress");
                	if(ObjectUtils.notEqual(bioAddress, null)){
                		model = getModelFromEntity(bioAddress);
                		provinces = provinceService.getByCountryId(model.getCountryId());
                		cities = cityService.getByProvinceId(model.getProvinceId());
                		isUpdate = Boolean.TRUE;
                	}
            	}
            	
            }else{
            	
            	String bioAddressId = FacesUtil.getRequestParameter("bioAddressId");
                if (StringUtils.isNotEmpty(bioAddressId)) {
                	BioAddress bioAddress = bioAddressService.getEntityByPKWithDetail(Long.parseLong(bioAddressId));
                	if(bioAddress != null){
                		model = getModelFromEntity(bioAddress);
                		provinces = provinceService.getByCountryId(model.getCountryId());
                		cities = cityService.getByProvinceId(model.getProvinceId());
                		isUpdate = Boolean.TRUE;
                	}
                }   
            }
            
           
            
                     	
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
	}

	@PreDestroy
    public void cleanAndExit() {
		countries = null;
		provinces = null;
		cities = null;
		model = null;
		isUpdate = null;
		bioAddressService = null;
		countryService = null;
		provinceService = null; 
		cityService = null;
		isRevision = null;
	}
	
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public BioAddressModel getModel() {
		return model;
	}

	public void setModel(BioAddressModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setBioAddressService(BioAddressService bioAddressService) {
		this.bioAddressService = bioAddressService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public void doSave() {
        BioAddress bioAddress = getEntityFromViewModel(model);
        try {
        	
        	/** jika tidak blank, berarti datangnya dari proses revisi biodata, jangan langsung di save / update,
        	 	cukup di return kembali Object BioAddress yang telah di add / edit untuk kemudian di proses kembali di form revisi, 
        	 	ini dikarenakan proses revisi menggunakan approval sehingga data yang telah di ubah
        	 	tidak langsung di persist ke table yang bersangkutan, melainkan di tampung dahulu di json pendingData (Approval Activity)*/
        	if(StringUtils.isNotBlank(isRevision)){
        		RequestContext.getCurrentInstance().closeDialog(bioAddress);
        	}else{
        		if (isUpdate) {
                    bioAddressService.update(bioAddress);
                    RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
                } else {
                	bioAddressService.save(bioAddress);
                    RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
                }
        	}
            
            cleanAndExit();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public void doReset() {
        if (isUpdate) {
            try {
                BioAddress bioAddress = bioAddressService.getEntiyByPK(model.getId());
                if (bioAddress != null) {
                    model = getModelFromEntity(bioAddress);
                    provinces = provinceService.getByCountryId(model.getCountryId());
            		cities = cityService.getByProvinceId(model.getProvinceId());
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new BioAddressModel();
            provinces.clear();
            cities.clear();            
        }
    }
	
	private BioAddressModel getModelFromEntity(BioAddress entity) {
		BioAddressModel bioModel = new BioAddressModel();
		bioModel.setId(entity.getId());
		bioModel.setBioDataId(entity.getBioData().getId());
	    bioModel.setStatusAddress(entity.getStatusAddress());
	    bioModel.setType(entity.getType());
	    bioModel.setPhoneNumber(entity.getPhoneNumber());
	    bioModel.setAddressDetail(entity.getAddressDetail());
	    bioModel.setCountryId(entity.getCity().getProvince().getCountry().getId());
	    bioModel.setProvinceId(entity.getCity().getProvince().getId());
	    bioModel.setCityId(entity.getCity().getId());
	    bioModel.setSubDistrict(entity.getSubDistrict());
	    bioModel.setVillage(entity.getVillage());
	    bioModel.setNotes(entity.getNotes());
		
		return bioModel;
		
	}
	
	private BioAddress getEntityFromViewModel(BioAddressModel model) {
		BioAddress bioAddress = new BioAddress();
		System.out.println("getEntityFromViewModel, model.getId() :  " + model.getId());
		if(model.getId() != null){
			bioAddress.setId(model.getId());
		}
		bioAddress.setBioData(new BioData(model.getBioDataId()));
		bioAddress.setStatusAddress(model.getStatusAddress());
		bioAddress.setType(model.getType());
		bioAddress.setPhoneNumber(model.getPhoneNumber());
		bioAddress.setAddressDetail(model.getAddressDetail());
		if(model.getCityId() != null){
			bioAddress.setCity(new City(model.getCityId()));
		}		
		bioAddress.setSubDistrict(model.getSubDistrict());
		bioAddress.setVillage(model.getVillage());
		bioAddress.setNotes(model.getNotes());
		System.out.println("getEntityFromViewModel, bioAddress.getId() :  " + bioAddress.getId());
	    return bioAddress;
	}
	
	public void onChangeCountries(){
		try {
			provinces.clear();
			cities.clear();
			provinces = provinceService.getByCountryId(model.getCountryId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void onChangeProvinces(){
		try {
			cities.clear();
			cities = cityService.getByProvinceId(model.getProvinceId());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}

}
