package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.OhsaEmpInvolveService;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.hrm.web.search.CompanySearchParameter;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class OhsaIncidentLazyDataModel extends LazyDataModel<OhsaIncident> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(OhsaIncidentLazyDataModel.class);
    private final OhsaIncidentSearchParameter parameter;
    private final OhsaIncidentService ohsaIncidentService;
    private final OhsaEmpInvolveService ohsaEmpInvolveService;
    private List<OhsaIncident> ohsaIncidentsList = new ArrayList<>();
    private Integer total;

    public OhsaIncidentLazyDataModel(OhsaIncidentSearchParameter parameter, OhsaIncidentService ohsaIncidentService, OhsaEmpInvolveService ohsaEmpInvolveService ) {
        this.parameter = parameter;
        this.ohsaIncidentService = ohsaIncidentService;
        this.ohsaEmpInvolveService = ohsaEmpInvolveService;
    }

    @Override
    public List<OhsaIncident> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("subject");
	        }
	        
	        ohsaIncidentsList = ohsaIncidentService.getByParam(parameter, first, pageSize, orderable);
                
                //Set Total Employee yang terlibat dari masing - masih peristiwa K3
                for(OhsaIncident ohsaIncident : ohsaIncidentsList){
                    Long totalEmpInvolves = ohsaEmpInvolveService.getTotalEmpInvolveByIdOhsaIncident(ohsaIncident.getId());
                    ohsaIncident.setTotalEmpInvolves(totalEmpInvolves);
                }
                
                total = Integer.parseInt(String.valueOf(ohsaIncidentService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return ohsaIncidentsList;
    }

    @Override
    public Object getRowKey(OhsaIncident ohsaIncident) {
        return ohsaIncident.getId();
    }

    @Override
    public OhsaIncident getRowData(String id) {
        for (OhsaIncident ohsaIncident : ohsaIncidentsList) {
            if (id.equals(String.valueOf(ohsaIncident.getId()))) {
                return ohsaIncident;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
