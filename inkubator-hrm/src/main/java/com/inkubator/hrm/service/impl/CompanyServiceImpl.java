package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BusinessTypeDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.CountryDao;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.web.search.CompanySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "companyService")
@Lazy
public class CompanyServiceImpl extends IServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private BusinessTypeDao businessTypeDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private CityDao cityDao;

    @Override
    public Company getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Company getEntiyByPK(Long id) throws Exception {
        return companyDao.getEntiyByPK(id);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Company entity) throws Exception {
        // check duplicate code, legalNo, phone and taxAccountNo
        long totalDuplicates = companyDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_code");
        }
        totalDuplicates = companyDao.getTotalByLegalNo(entity.getLegalNo());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_legal_no");
        }
        totalDuplicates = companyDao.getTotalByPhone(entity.getPhone());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_phone");
        }
        totalDuplicates = companyDao.getTotalByTaxAccountNumber(entity.getTaxAccountNumber());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_tax_account_number");
        }

        //saving process
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        Country taxCountry = countryDao.getEntiyByPK(entity.getTaxCountry().getId());
        City city = cityDao.getEntiyByPK(entity.getCity().getId());
        BusinessType businessType = businessTypeDao.getEntiyByPK(entity.getBusinessType().getId());

        entity.setTaxCountry(taxCountry);
        entity.setCity(city);
        entity.setBusinessType(businessType);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        companyDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Company c) throws Exception {
        // check duplicate code, legalNo, phone and taxAccountNo
        long totalDuplicates = companyDao.getTotalByCodeAndNotId(c.getCode(), c.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_code");
        }
        totalDuplicates = companyDao.getTotalByLegalNoAndNotId(c.getLegalNo(), c.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_legal_no");
        }
        totalDuplicates = companyDao.getTotalByPhoneAndNotId(c.getPhone(), c.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_phone");
        }
        totalDuplicates = companyDao.getTotalByTaxAccountNumberAndNotId(c.getTaxAccountNumber(), c.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("organization.error_duplicate_tax_account_number");
        }

        //updating process
        Company company = companyDao.getEntiyByPK(c.getId());
        Country taxCountry = countryDao.getEntiyByPK(c.getTaxCountry().getId());
        City city = cityDao.getEntiyByPK(c.getCity().getId());
        BusinessType businessType = businessTypeDao.getEntiyByPK(c.getBusinessType().getId());

        if (c.getCompanyLogo() != null) {
            company.setCompanyLogo(c.getCompanyLogo());
            company.setCompanyLogoName(c.getCompanyLogoName());
        }
        company.setCode(c.getCode());
        company.setName(c.getName());
        company.setOfficialName(c.getOfficialName());
        company.setLegalNo(c.getLegalNo());
        company.setLevel(c.getLevel());
        company.setTaxCountry(taxCountry);
        company.setTaxAccountNumber(c.getTaxAccountNumber());
        company.setAddress(c.getAddress());
        company.setCity(city);
        company.setBusinessType(businessType);
        company.setPostalCode(c.getPostalCode());
        company.setPhone(c.getPhone());
        company.setFax(c.getFax());
        company.setVision(c.getVision());
        company.setMision(c.getMision());
        company.setUpdatedBy(UserInfoUtil.getUserName());
        company.setUpdatedOn(new Date());
        companyDao.update(company);
    }

    @Override
    public void saveOrUpdate(Company enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company saveData(Company entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company updateData(Company entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company saveOrUpdateData(Company entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Integer id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Integer id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Long id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Company getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Company entity) throws Exception {
        companyDao.delete(entity);

    }

    @Override
    public void softDelete(Company entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Company> getAllData() throws Exception {
        return this.companyDao.getAllData();
    }

    @Override
    public List<Company> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllDataPageAble(int firstResult, int maxResults,
            Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Company> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Company> getByParam(CompanySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return companyDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(CompanySearchParameter parameter) throws Exception {
        return companyDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Company getEntityByPKWithDetail(Long id) throws Exception {
        return companyDao.getEntityByPKWithDetail(id);

    }

}
