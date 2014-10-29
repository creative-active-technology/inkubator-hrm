/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.service.IpPermitService;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Deni
 */
public class IpPermitLazyDataModel extends LazyDataModel<IpPermit> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(IpPermitLazyDataModel.class);
    private final IpPermitSearchParameter searchParameter;
    private final IpPermitService service;
    private List<IpPermit> ipPermitList = new ArrayList<>();
    private Integer jumlahData;

    public IpPermitLazyDataModel(IpPermitSearchParameter searchParameter, IpPermitService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<IpPermit> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    ipPermitList = service.getByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalIpPermitByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    ipPermitList = service.getByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalIpPermitByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                ipPermitList = service.getByParam(searchParameter, first, pageSize, Order.desc("lokasi"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalIpPermitByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return ipPermitList;
    }
    
    @Override
    public Object getRowKey(IpPermit ipPermit) {
        return ipPermit.getId();
    }

    @Override
    public IpPermit getRowData(String id) {
        for (IpPermit ipPermit : ipPermitList) {
            if (id.equals(String.valueOf(ipPermit.getId()))) {
                return ipPermit;
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
