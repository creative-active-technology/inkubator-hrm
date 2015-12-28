package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;

public class KompetensiJabatanLazyDataModel extends LazyDataModel<Jabatan> implements Serializable{

	private static final Logger LOGGER = Logger.getLogger(KompetensiJabatanLazyDataModel.class);
    private final KompetensiJabatanSearchParameter searchParameter;
    private final JabatanService jabatanService;
    private List<Jabatan> list = new ArrayList<>();
    private Integer total;
    
    public KompetensiJabatanLazyDataModel(KompetensiJabatanSearchParameter searchParameter, JabatanService jabatanService){
        this.searchParameter = searchParameter;
        this.jabatanService = jabatanService;
    }
    
    @Override
    public List<Jabatan> load(int firstResult, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    	LOGGER.info("Step load lazy data Model");
    	try{
    			Order orderable = null;
    			if(sortField != null){
    				orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
    			} else {
    				orderable = Order.asc("id");
    			}
    			list = jabatanService.getByParamForKompetensiJabatan(searchParameter, firstResult, pageSize, orderable);
    			total = Integer.parseInt(String.valueOf(jabatanService.getTotalByParamForKompetensiJabatan(searchParameter)));
    			LOGGER.info("Success load lazy data model");
    	} catch (Exception ex){
    		LOGGER.error("Failed to load lazy data model");
    		LOGGER.error("Error : ", ex);
    	}
    	
    	setPageSize(pageSize);
    	setRowCount(total);
    	return list;
    }
    
    @Override
    public Object getRowKey(Jabatan jabatan){
    	return jabatan.getId();
    }
    
    @Override
    public Jabatan getRowData(String id){
    	for (Jabatan jabatan : list){
    		if(id.equals(String.valueOf(jabatan.getId()))){
    			return jabatan;
    		}
    	}
    	return null;
    }
    
    @Override
	public void setRowIndex(int rowIndex) {
		if(rowIndex == -1 || getPageSize() == 0){
    		super.setRowIndex(-1);
    	} else {
    		super.setRowIndex(rowIndex % getPageSize());
    	}
	}
}
