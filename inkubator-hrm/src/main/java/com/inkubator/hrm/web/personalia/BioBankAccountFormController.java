package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.BioBankAccount;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.service.BioBankAccountService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.CurrencyService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioBankAccountModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "bioBankAccountFormController")
@ViewScoped
public class BioBankAccountFormController extends BaseController {

    private BioBankAccountModel bioBankAccountModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{bioBankAccountService}")
    private BioBankAccountService bioBankAccountService;
    @ManagedProperty(value = "#{bankService}")
    private BankService bankService;
    @ManagedProperty(value = "#{currencyService}")
    private CurrencyService currencyService;
    private Map<String, Long> banks = new TreeMap<>();
    private Map<String, Long> currencys = new TreeMap<>();
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    @ManagedProperty(value = "#{provinceService}")
    private ProvinceService provinceService;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    private Map<String, Long> countrys = new TreeMap<>();
    private Map<String, Long> provinces = new TreeMap<>();
    private Map<String, Long> citys = new TreeMap<>();
    private Boolean disabledProvince;
    private Boolean disabledCity;
    private String locale;
    private ResourceBundle messages;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            bioBankAccountModel = new BioBankAccountModel();
            isUpdate = Boolean.FALSE;
            String bioDataId = FacesUtil.getRequestParameter("bioDataId");
            bioBankAccountModel.setBioDataId(Long.parseLong(bioDataId));

            List<Bank> listBank = bankService.getAllData();

            for (Bank bank : listBank) {

                if (bank.getBankName() != null) {
                    banks.put(bank.getBankName(), bank.getId());
                } else {
                    banks.put(bank.getBank().getBankName() + " - " + bank.getBranchName(), bank.getId());
                }

//                banks.put(bank.getBankName(), bank.getId());
            }

            MapUtil.sortByValue(banks);

            List<Currency> listCurrency = currencyService.getAllData();

            for (Currency currency : listCurrency) {
                currencys.put(currency.getName(), currency.getId());
            }

            MapUtil.sortByValue(currencys);

            disabledCity = Boolean.TRUE;
            disabledProvince = Boolean.TRUE;
            locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
            messages = ResourceBundle.getBundle("messages", new Locale(locale));
            List<Country> listCountrys = countryService.getAllData();

            for (Country country : listCountrys) {
                countrys.put(country.getCountryName(), country.getId());
            }

            MapUtil.sortByValue(countrys);

            String bioBankAccountId = FacesUtil.getRequestParameter("bioBankAccountId");
            if (StringUtils.isNotEmpty(bioBankAccountId)) {
                BioBankAccount bioBankAccount = bioBankAccountService.getEntityByPKWithDetail(Long.parseLong(bioBankAccountId));
                if (bioBankAccountId != null) {
                    disabledCity = Boolean.FALSE;
                    disabledProvince = Boolean.FALSE;
                    bioBankAccountModel = getModelFromEntity(bioBankAccount);
                    List<Province> listProvinces = provinceService.getByCountryIdWithDetail(bioBankAccountModel.getCountryId());
                    for (Province province : listProvinces) {
                        provinces.put(province.getProvinceName(), province.getId());
                    }

                    MapUtil.sortByValue(provinces);

                    List<City> listCities = cityService.getByProvinceIdWithDetail(bioBankAccountModel.getProvinceId());

                    for (City city : listCities) {
                        citys.put(city.getCityName(), city.getId());
                    }

                    MapUtil.sortByValue(citys);

                    isUpdate = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        bioBankAccountService = null;
//        bioBankAccountModel = null;
        isUpdate = null;
        bankService = null;
        currencyService = null;
        banks = null;
        currencys = null;
        cityService = null;
        provinceService = null;
        countryService = null;
        countrys = null;
        provinces = null;
        citys = null;
        disabledProvince = null;
        disabledCity = null;
        locale = null;
        messages = null;
    }

    public BioBankAccountModel getBioBankAccountModel() {
        return bioBankAccountModel;
    }

    public void setBioBankAccountModel(BioBankAccountModel bioBankAccountModel) {
        this.bioBankAccountModel = bioBankAccountModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setBioBankAccountService(BioBankAccountService bioBankAccountService) {
        this.bioBankAccountService = bioBankAccountService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public Map<String, Long> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, Long> citys) {
        this.citys = citys;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public Map<String, Long> getBanks() {
        return banks;
    }

    public void setBanks(Map<String, Long> banks) {
        this.banks = banks;
    }

    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Map<String, Long> getCurrencys() {
        return currencys;
    }

    public void setCurrencys(Map<String, Long> currencys) {
        this.currencys = currencys;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public Map<String, Long> getCountrys() {
        return countrys;
    }

    public void setCountrys(Map<String, Long> countrys) {
        this.countrys = countrys;
    }

    public Map<String, Long> getProvinces() {
        return provinces;
    }

    public void setProvinces(Map<String, Long> provinces) {
        this.provinces = provinces;
    }

    public Boolean getDisabledProvince() {
        return disabledProvince;
    }

    public void setDisabledProvince(Boolean disabledProvince) {
        this.disabledProvince = disabledProvince;
    }

    public Boolean getDisabledCity() {
        return disabledCity;
    }

    public void setDisabledCity(Boolean disabledCity) {
        this.disabledCity = disabledCity;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public ResourceBundle getMessages() {
        return messages;
    }

    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }

    public void doSave() {
        BioBankAccount bioBankAccount = getEntityFromViewModel(bioBankAccountModel);
        try {
            if (isUpdate) {
                bioBankAccountService.update(bioBankAccount);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                bioBankAccountService.save(bioBankAccount);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private BioBankAccount getEntityFromViewModel(BioBankAccountModel bioBankAccountModel) {
        BioBankAccount bioBankAccount = new BioBankAccount();
        if (bioBankAccountModel.getId() != null) {
            bioBankAccount.setId(bioBankAccountModel.getId());
        }
        bioBankAccount.setBioData(new BioData(bioBankAccountModel.getBioDataId()));
        bioBankAccount.setCity(new City(bioBankAccountModel.getCityId()));
        bioBankAccount.setCurrency(new Currency(bioBankAccountModel.getCurrencyId()));
        bioBankAccount.setBank(new Bank(bioBankAccountModel.getBankId()));
        bioBankAccount.setOwnerName(bioBankAccountModel.getOwnerName());
        bioBankAccount.setAccountNumber(bioBankAccountModel.getAccountNumber());
        bioBankAccount.setBranch(bioBankAccountModel.getBranch());
        bioBankAccount.setAddress(bioBankAccountModel.getAddress());
        bioBankAccount.setSavingType(bioBankAccountModel.getSavingType());
        bioBankAccount.setSwiftCode(bioBankAccountModel.getSwiftCode());
        bioBankAccount.setDefaultAccount(bioBankAccountModel.getDefaultAccount());
        return bioBankAccount;
    }

    private BioBankAccountModel getModelFromEntity(BioBankAccount entity) {
        BioBankAccountModel bioBankAccountModel = new BioBankAccountModel();
        bioBankAccountModel.setId(entity.getId());
        bioBankAccountModel.setBioDataId(entity.getBioData().getId());
        bioBankAccountModel.setCountryId(entity.getCity().getProvince().getCountry().getId());
        bioBankAccountModel.setProvinceId(entity.getCity().getProvince().getId());
        bioBankAccountModel.setCityId(entity.getCity().getId());
        bioBankAccountModel.setCurrencyId(entity.getCurrency().getId());
        bioBankAccountModel.setBankId(entity.getBank().getId());
        bioBankAccountModel.setOwnerName(entity.getOwnerName());
        bioBankAccountModel.setAccountNumber(entity.getAccountNumber());
        bioBankAccountModel.setBranch(entity.getBranch());
        bioBankAccountModel.setAddress(entity.getAddress());
        bioBankAccountModel.setSavingType(entity.getSavingType());
        bioBankAccountModel.setSwiftCode(entity.getSwiftCode());
        bioBankAccountModel.setDefaultAccount(entity.getDefaultAccount());
        return bioBankAccountModel;
    }

    public void countryChanged(ValueChangeEvent event) {
        try {

            Country country = countryService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<Province> listProvinces = provinceService.getByCountryIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));

            if (listProvinces.isEmpty() || listProvinces == null) {
                disabledProvince = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formBioBankAccountFormId:provinceId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledProvince = Boolean.FALSE;
                provinces.clear();
                for (Province province : listProvinces) {
                    provinces.put(province.getProvinceName(), province.getId());
                }

                MapUtil.sortByValue(provinces);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void provinceChanged(ValueChangeEvent event) {
        try {

            Province province = provinceService.getEntiyByPK(Long.parseLong(String.valueOf(event.getNewValue())));

            List<City> listCities = cityService.getByProvinceIdWithDetail(Long.parseLong(String.valueOf(event.getNewValue())));

            if (listCities.isEmpty() || listCities == null) {
                disabledCity = Boolean.TRUE;

                FacesContext.getCurrentInstance().addMessage("formBioBankAccountFormId:cityId", new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), messages.getString("city.province_is_empty")));

            } else {
                disabledCity = Boolean.FALSE;
                citys.clear();
                for (City city : listCities) {
                    citys.put(city.getCityName(), city.getId());
                }

                MapUtil.sortByValue(citys);
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

}
