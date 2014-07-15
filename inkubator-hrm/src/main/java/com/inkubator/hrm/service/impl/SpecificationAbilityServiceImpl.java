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
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.service.SpecificationAbilityService;
import com.inkubator.hrm.web.model.SpecificationAbilityModelView;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;

/**
*
* @author rizkykojek
*/
@Service(value = "specificationAbilityService")
@Lazy
public class SpecificationAbilityServiceImpl extends IServiceImpl implements SpecificationAbilityService {

	@Autowired
	private SpecificationAbilityDao specificationAbilityDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(SpecificationAbility specificationAbility) throws Exception {
		specificationAbilityDao.delete(specificationAbility);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
        public List<SpecificationAbility> getAllData() throws Exception {
            return specificationAbilityDao.getAllData();
        }

	@Override
	public List<SpecificationAbility> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<SpecificationAbility> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public SpecificationAbility getEntiyByPK(Long id) throws Exception {
		return specificationAbilityDao.getEntiyByPK(id);
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(SpecificationAbility specificationAbility) throws Exception {
		// check duplicate name
		long totalDuplicates = specificationAbilityDao.getTotalByName(specificationAbility.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("specificationability.error_duplicate_name");
		}
				
		specificationAbility.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		specificationAbility.setCreatedBy(UserInfoUtil.getUserName());
		specificationAbility.setCreatedOn(new Date());
		specificationAbilityDao.save(specificationAbility);
	}

	@Override
	public SpecificationAbility saveData(SpecificationAbility arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(SpecificationAbility arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public SpecificationAbility saveOrUpdateData(SpecificationAbility arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(SpecificationAbility arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(SpecificationAbility sa) throws Exception {
		// check duplicate name
		long totalDuplicates = specificationAbilityDao.getTotalByNameAndNotId(sa.getName(), sa.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("specificationability.error_duplicate_name");
		}
		
		SpecificationAbility specificationAbility = specificationAbilityDao.getEntiyByPK(sa.getId());
		specificationAbility.setName(sa.getName());
		specificationAbility.setOptionAbility(sa.getOptionAbility());
		specificationAbility.setScaleValue(sa.getScaleValue());
		specificationAbility.setUpdatedBy(UserInfoUtil.getUserName());
		specificationAbility.setUpdatedOn(new Date());
		specificationAbilityDao.update(specificationAbility);
	}

	@Override
	public SpecificationAbility updateData(SpecificationAbility arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<SpecificationAbility> getByParam(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return this.specificationAbilityDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(SpecificationAbilitySearchParameter parameter) throws Exception {
		return this.specificationAbilityDao.getTotalByParam(parameter);
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public SpecificationAbility getByName(String name) throws Exception {
        return specificationAbilityDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SpecificationAbilityModelView> getDataBySpecAbilityNotExistInJabatanSpec(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResult, Order order) throws Exception {
        List<SpecificationAbilityModelView> modelViews = new ArrayList<SpecificationAbilityModelView>();
        List<SpecificationAbility> specificationAbilitys = specificationAbilityDao.getDataBySpecAbilityNotExistInJabatanSpec(parameter, firstResult, maxResult, order);
        for (SpecificationAbility specAbi : specificationAbilitys) {
            SpecificationAbilityModelView modelView = new SpecificationAbilityModelView();
            modelView.setId(specAbi.getId());
            modelView.setName(specAbi.getName());
            modelView.setOptionAbility(specAbi.getOptionAbility());
            modelView.setScaleValue(specAbi.getScaleValue());
            if(!specAbi.getJabatanSpesifikasis().isEmpty()){
                modelView.setIsEdit(Boolean.FALSE);
            }else{
                modelView.setIsEdit(Boolean.TRUE);
            }
            modelViews.add(modelView);
        }
        return modelViews;
    }

}
