package com.inkubator.hrm.web.lazymodel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.PaCompetencyPoint;
import com.inkubator.hrm.service.PaCompetencyPointService;
import com.inkubator.hrm.web.search.PaCompetencyPointSearchParameter;
/**
 *
 * @author WebGenX
 */
public class PaCompetencyPointLazyDataModel extends LazyDataModel<PaCompetencyPoint> implements Serializable {
private static final Logger LOGGER = Logger.getLogger(PaCompetencyPointLazyDataModel.class);
private final PaCompetencyPointSearchParameter paCompetencyPointSearchParameter;
private final PaCompetencyPointService paCompetencyPointService;
private List<PaCompetencyPoint>paCompetencyPoints = new ArrayList<>();
private Integer totalData;
public PaCompetencyPointLazyDataModel(PaCompetencyPointSearchParameter searchParameter, PaCompetencyPointService paCompetencyPointService){
this.paCompetencyPointSearchParameter = searchParameter;
this.paCompetencyPointService = paCompetencyPointService;
}
@Override
public List<PaCompetencyPoint> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
LOGGER.info("Step Load Lazy data Model");
if(sortField !=null){
if (sortOrder == SortOrder.ASCENDING) {
try {
paCompetencyPoints = paCompetencyPointService.getByParam(paCompetencyPointSearchParameter, first, pageSize, Order.asc(sortField));
totalData = Integer.parseInt(String.valueOf(paCompetencyPointService.getTotalPaCompetencyPointByParam(paCompetencyPointSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
} else {
try {
paCompetencyPoints = paCompetencyPointService.getByParam(paCompetencyPointSearchParameter, first, pageSize, Order.desc(sortField));
totalData = Integer.parseInt(String.valueOf(paCompetencyPointService.getTotalPaCompetencyPointByParam(paCompetencyPointSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
}
else {
try {
// Change default type order if u want change from id to other entity variable
paCompetencyPoints = paCompetencyPointService.getByParam(paCompetencyPointSearchParameter, first, pageSize, Order.desc("id"));
totalData = Integer.parseInt(String.valueOf(paCompetencyPointService.getTotalPaCompetencyPointByParam(paCompetencyPointSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
LOGGER.info("Success Load Lazy data Model");
setPageSize(pageSize);
setRowCount(totalData);
return paCompetencyPoints;
}
@Override
public Object getRowKey(PaCompetencyPoint paCompetencyPoint){
return paCompetencyPoint.getId();
}
@Override
public PaCompetencyPoint getRowData(String id){
for(PaCompetencyPoint paCompetencyPoint : paCompetencyPoints){
if(id.equals(String.valueOf(paCompetencyPoint.getId()))){
return paCompetencyPoint;
}
}
return null;
}
@Override
public void setRowIndex(int rowIndex){
if (rowIndex == -1 || getPageSize() == 0) {
super.setRowIndex(-1);
} else {
super.setRowIndex(rowIndex % getPageSize());
}
}
}
