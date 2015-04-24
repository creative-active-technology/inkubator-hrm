package com.inkubator.hrm.web.lazymodel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
/**
 *
 * @author WebGenX
 */
public class RecruitHireApplyLazyDataModel extends LazyDataModel<RecruitHireApply> implements Serializable {
private static final Logger LOGGER = Logger.getLogger(RecruitHireApplyLazyDataModel.class);
private final RecruitHireApplySearchParameter recruitHireApplySearchParameter;
private final RecruitHireApplyService recruitHireApplyService;
private List<RecruitHireApply>recruitHireApplys = new ArrayList<>();
private Integer totalData;
public RecruitHireApplyLazyDataModel(RecruitHireApplySearchParameter searchParameter, RecruitHireApplyService recruitHireApplyService){
this.recruitHireApplySearchParameter = searchParameter;
this.recruitHireApplyService = recruitHireApplyService;
}
@Override
public List<RecruitHireApply> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
LOGGER.info("Step Load Lazy data Model");
if(sortField !=null){
if (sortOrder == SortOrder.ASCENDING) {
try {
recruitHireApplys = recruitHireApplyService.getByParam(recruitHireApplySearchParameter, first, pageSize, Order.asc(sortField));
totalData = Integer.parseInt(String.valueOf(recruitHireApplyService.getTotalRecruitHireApplyByParam(recruitHireApplySearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
} else {
try {
recruitHireApplys = recruitHireApplyService.getByParam(recruitHireApplySearchParameter, first, pageSize, Order.desc(sortField));
totalData = Integer.parseInt(String.valueOf(recruitHireApplyService.getTotalRecruitHireApplyByParam(recruitHireApplySearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
}
else {
try {
// Change default type order if u want change from id to other entity variable
recruitHireApplys = recruitHireApplyService.getByParam(recruitHireApplySearchParameter, first, pageSize, Order.desc("id"));
totalData = Integer.parseInt(String.valueOf(recruitHireApplyService.getTotalRecruitHireApplyByParam(recruitHireApplySearchParameter)));
} catch (Exception ex) {
LOGGER.error("Error", ex);
}
}
LOGGER.info("Success Load Lazy data Model");
setPageSize(pageSize);
setRowCount(totalData);
return recruitHireApplys;
}
@Override
public Object getRowKey(RecruitHireApply recruitHireApply){
return recruitHireApply.getId();
}
@Override
public RecruitHireApply getRowData(String id){
for(RecruitHireApply recruitHireApply : recruitHireApplys){
if(id.equals(String.valueOf(recruitHireApply.getId()))){
return recruitHireApply;
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
