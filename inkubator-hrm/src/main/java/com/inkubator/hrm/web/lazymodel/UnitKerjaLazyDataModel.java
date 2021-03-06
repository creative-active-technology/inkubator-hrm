/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;
import org.jboss.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author deniarianto
 */
public class UnitKerjaLazyDataModel extends LazyDataModel<UnitKerja> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(UnitKerjaLazyDataModel.class);
    private final UnitKerjaSearchParameter unitKerjaSearchParameter;
    private final UnitKerjaService unitKerjaService;
    private List<UnitKerja> unitKerjaList = new ArrayList<>();
    private Integer jumlahData;

    public UnitKerjaLazyDataModel(UnitKerjaSearchParameter unitKerjaSearchParameter, UnitKerjaService unitKerjaService) {
        this.unitKerjaSearchParameter = unitKerjaSearchParameter;
        this.unitKerjaService = unitKerjaService;
    }
    
    @Override
    public List<UnitKerja> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                unitKerjaList = unitKerjaService.getAllDataWithCity(unitKerjaSearchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(unitKerjaService.getTotalUnitKerjaByParam(unitKerjaSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return unitKerjaList;
    }
    
    @Override
    public Object getRowKey(UnitKerja unitKerja) {
        return unitKerja.getId();
    }

    @Override
    public UnitKerja getRowData(String id) {
        for (UnitKerja unitKerja : unitKerjaList) {
            if (id.equals(String.valueOf(unitKerja.getId()))) {
                return unitKerja;
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
