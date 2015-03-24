package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsSchemaListOfEmpDao;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsSchemaListOfEmpDao")
@Lazy
public class RmbsSchemaListOfEmpDaoImpl extends IDAOImpl<RmbsSchemaListOfEmp> implements RmbsSchemaListOfEmpDao {

	@Override
	public Class<RmbsSchemaListOfEmp> getEntityClass() {
		return RmbsSchemaListOfEmp.class;
		
	}

	@Override
	public List<RmbsSchemaEmpViewModel> getByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT empData.id as empDataId, " +
    			"empData.nik as empNik, " +
    			"concat(bioData.firstName, ' ', bioData.lastName) as empName, " +
    			"golonganJabatan.code as empGolJabatan, " +
    			"rmbsSchema.id as rmbsSchemaId, " +
    			"rmbsSchema.code as rmbsSchemaCode, " +
    			"rmbsSchemaListOfEmp.nomorSk as nomorSK " +
    			"FROM EmpData as empData " + 
    			"INNER JOIN empData.bioData as bioData " +
    			"INNER JOIN empData.golonganJabatan as golonganJabatan " +
    			"LEFT JOIN empData.rmbsSchemaListOfEmps as rmbsSchemaListOfEmp " +
    			"LEFT JOIN rmbsSchemaListOfEmp.rmbsSchema as rmbsSchema ");    	
    	selectQuery.append(this.getWhereQueryByParamEmployeeSchema(parameter));
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(RmbsSchemaEmpViewModel.class));
    	hbm = this.setValueQueryByParamEmployeeSchema(hbm, parameter);
    	
    	return hbm.list();    
	}

	@Override
	public Long getTotalByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " + 
    			"FROM EmpData as empData " + 
    	    	"INNER JOIN empData.bioData as bioData " +
    	    	"INNER JOIN empData.golonganJabatan as golonganJabatan " +
    	    	"LEFT JOIN empData.rmbsSchemaListOfEmps as rmbsSchemaListOfEmp " +
    	    	"LEFT JOIN rmbsSchemaListOfEmp.rmbsSchema as rmbsSchema ");
    							
    	selectQuery.append(this.getWhereQueryByParamEmployeeSchema(parameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParamEmployeeSchema(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String getWhereQueryByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(parameter.getEmpName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("(");
    		whereQuery.append("bioData.firstName LIKE :firstName ");
    		whereQuery.append("OR ");
    		whereQuery.append("bioData.lastName LIKE :lastName ");
    		whereQuery.append(")");
        }
    	
        if (StringUtils.isNotEmpty(parameter.getNik())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("empData.nik LIKE :empNik ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getGolJabatan())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("golonganJabatan.code LIKE :empGolJabatan ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getSchemaCode())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("rmbsSchema.code LIKE :rmbsSchemaCode ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getNomorSK())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("rmbsSchemaListOfEmp.nomorSk LIKE :nomorSK ");
        }
        
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParamEmployeeSchema(Query hbm, RmbsSchemaEmpSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "firstName")){
    			hbm.setParameter("firstName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "lastName")){
    			hbm.setParameter("lastName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "empGolJabatan")){
    			hbm.setParameter("empGolJabatan", "%" + parameter.getGolJabatan() + "%");
    		} else if(StringUtils.equals(param, "rmbsSchemaCode")){
    			hbm.setParameter("rmbsSchemaCode", "%" + parameter.getSchemaCode() + "%");
    		} else if(StringUtils.equals(param, "nomorSK")){
    			hbm.setParameter("nomorSK", "%" + parameter.getNomorSK() + "%");
    		}
    	}
    	
    	return hbm;
    }

	
}
