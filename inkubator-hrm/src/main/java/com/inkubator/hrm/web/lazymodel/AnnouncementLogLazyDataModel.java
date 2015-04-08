package com.inkubator.hrm.web.lazymodel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.hrm.service.AnnouncementLogService;
import com.inkubator.hrm.web.search.AnnouncementLogSearchParameter;
/**
 *
 * @author WebGenX
 */
public class AnnouncementLogLazyDataModel extends LazyDataModel<AnnouncementLog> implements Serializable {
private static final Logger LOGGER = Logger.getLogger(AnnouncementLogLazyDataModel.class);
private final AnnouncementLogSearchParameter announcementLogSearchParameter;
private final AnnouncementLogService announcementLogService;
private List<AnnouncementLog>announcementLogs = new ArrayList<>();
private Integer totalData;
public AnnouncementLogLazyDataModel(AnnouncementLogSearchParameter searchParameter, AnnouncementLogService announcementLogService){
this.announcementLogSearchParameter = searchParameter;
this.announcementLogService = announcementLogService;
}
@Override
public List<AnnouncementLog> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
LOGGER.info("Step Load Lazy data Model");
if(sortField !=null){
if (sortOrder == SortOrder.ASCENDING) {
try {
announcementLogs = announcementLogService.getByParam(announcementLogSearchParameter, first, pageSize, Order.asc(sortField));
totalData = Integer.parseInt(String.valueOf(announcementLogService.getTotalAnnouncementLogByParam(announcementLogSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
} else {
try {
announcementLogs = announcementLogService.getByParam(announcementLogSearchParameter, first, pageSize, Order.desc(sortField));
totalData = Integer.parseInt(String.valueOf(announcementLogService.getTotalAnnouncementLogByParam(announcementLogSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
}
else {
try {
// Change default type order if u want change from id to other entity variable
announcementLogs = announcementLogService.getByParam(announcementLogSearchParameter, first, pageSize, Order.desc("id"));
totalData = Integer.parseInt(String.valueOf(announcementLogService.getTotalAnnouncementLogByParam(announcementLogSearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
LOGGER.info("Success Load Lazy data Model");
setPageSize(pageSize);
setRowCount(totalData);
return announcementLogs;
}
@Override
public Object getRowKey(AnnouncementLog announcementLog){
return announcementLog.getId();
}
@Override
public AnnouncementLog getRowData(String id){
for(AnnouncementLog announcementLog : announcementLogs){
if(id.equals(String.valueOf(announcementLog.getId()))){
return announcementLog;
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
