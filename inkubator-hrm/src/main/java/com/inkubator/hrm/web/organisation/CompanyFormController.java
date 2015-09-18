package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.BusinessTypeService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.CompanyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyFormController")
@ViewScoped
public class CompanyFormController extends BaseController {

    private CompanyModel model;
    private Boolean isUpdate;
    private List<Country> countries;
    private List<Province> provinces;
    private List<City> cities;
    private List<BusinessType> businessTypes;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{businessTypeService}")
    private BusinessTypeService businessTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            model = new CompanyModel();
            businessTypes = businessTypeService.getAllData();
            countries = countryService.getAllData();
            provinces = new ArrayList<Province>();
            cities = new ArrayList<City>();

            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                Company company = companyService.getEntityByPKWithDetail(Long.parseLong(param.substring(1)));
                if (company != null) {
                    getModelFromEntity(company);
                    provinces = provinceService.getByCountryId(model.getCountryId());
                    cities = cityService.getByProvinceId(model.getProvinceId());
                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        model = null;
        isUpdate = null;
        countries = null;
        provinces = null;
        cities = null;
        companyService = null;
        countryService = null;
        provinceService = null;
        cityService = null;
        businessTypeService = null;
        businessTypes = null;
    }

    public CompanyModel getModel() {
        return model;
    }

    public void setModel(CompanyModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
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

    public List<BusinessType> getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(List<BusinessType> businessTypes) {
        this.businessTypes = businessTypes;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
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

    public void setBusinessTypeService(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    public void doReset() {
        if (isUpdate) {
            try {
                Company company = companyService.getEntityByPKWithDetail(model.getId());
                if (company != null) {
                    getModelFromEntity(company);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new CompanyModel();
        }
    }

    public String doSave() {
        Company company = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                companyService.update(company);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                companyService.save(company);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/organisation/company_detail.htm?faces-redirect=true&execution=e" + company.getId();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private Company getEntityFromViewModel(CompanyModel model) {
        Company company = new Company();
        if (model.getId() != null) {
            company.setId(model.getId());
        }
        company.setCode(model.getCode());
        company.setCompanyLogo(model.getCompanyLogo());
        company.setCompanyLogoName(model.getCompanyLogoName());
        company.setName(model.getName());
        company.setOfficialName(model.getOfficialName());
        company.setLegalNo(model.getLegalNo());
        company.setLevel(model.getLevel());
        company.setTaxCountry(new Country(model.getTaxCountryId()));
        company.setTaxAccountNumber(model.getTaxAccountNumber());
        company.setAddress(model.getAddress());
        company.setCity(new City(model.getCityId()));
        company.setBusinessType(new BusinessType(model.getBusinessTypeId()));
        company.setPostalCode(model.getPostalCode());
        company.setPhone(model.getPhone());
        company.setFax(model.getFax());
        company.setVision(model.getVision());
        company.setMision(model.getMision());
        return company;
    }

    private void getModelFromEntity(Company company) {
        model.setId(company.getId());
        model.setCode(company.getCode());
        model.setCompanyLogoName(company.getCompanyLogoName());
        model.setName(company.getName());
        model.setOfficialName(company.getOfficialName());
        model.setLegalNo(company.getLegalNo());
        model.setLevel(company.getLevel());
        model.setTaxCountryId(company.getTaxCountry().getId());
        model.setTaxAccountNumber(company.getTaxAccountNumber());
        model.setAddress(company.getAddress());
        model.setCountryId(company.getCity().getProvince().getCountry().getId());
        model.setProvinceId(company.getCity().getProvince().getId());
        model.setCityId(company.getCity().getId());
        model.setBusinessTypeId(company.getBusinessType().getId());
        model.setPostalCode(company.getPostalCode());
        model.setPhone(company.getPhone());
        model.setFax(company.getFax());
        model.setVision(company.getVision());
        model.setMision(company.getMision());
    }

    public String doBack() {
        return "/protected/organisation/company_view.htm?faces-redirect=true";
    }

    public void onChangeCountries() {
        try {
            provinces.clear();
            cities.clear();
            provinces = provinceService.getByCountryId(model.getCountryId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void onChangeProvinces() {
        try {
            cities.clear();
            cities = cityService.getByProvinceId(model.getProvinceId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void handingFotoUpload(FileUploadEvent fileUploadEvent) {
        UploadedFile logoFile = fileUploadEvent.getFile();
        model.setCompanyLogo(logoFile.getContents());
        model.setCompanyLogoName(logoFile.getFileName());
    }

}
