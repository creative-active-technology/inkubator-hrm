/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.mobile;

import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveImplementationDateService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.joda.time.LocalDate;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "homeMobileController")
@ViewScoped
public class HomeMobileController extends BaseController {

    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{leaveImplementationDateService}")
    private LeaveImplementationDateService leaveImplementationDateService;
    private Long totalRequestHistory;
    private Long totalPendingTask;
    private Long totalPendingRequest;
    private Integer totalLeaveType;
    private PieChartModel pieModel;
    private Date lastUpdateEmpDistByAge;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Long totalMale;
    private Long totalFemale;
    private Date lastUpdateEmpDistByGender;
    private String presentationAttendancePerDayLabel;
    private HorizontalBarChartModel presentationAttendancePerDayBarChartModel;
    private BarChartModel barChartDistribusiByDept;
    private Date lastUpdateEmpDistByDepartment;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            barChartDistribusiByDept = new BarChartModel();
            totalRequestHistory = approvalActivityService.getTotalRequestHistory(UserInfoUtil.getUserName());
            totalPendingTask = approvalActivityService.getTotalPendingTask(UserInfoUtil.getUserName());
            totalPendingRequest = approvalActivityService.getTotalPendingRequest(UserInfoUtil.getUserName());
            Long empDataId = HrmUserInfoUtil.getEmpId();
            List<LeaveImplementationDateModel> listLeaveImplementationDateModel = leaveImplementationDateService.getAllDataWithTotalTakenLeaveByEmpDataId(empDataId);
            totalLeaveType = listLeaveImplementationDateModel.size();
            pieModel = new PieChartModel();
            Map<String, Long> employeesByAge = empDataService.getTotalByAge(HrmUserInfoUtil.getCompanyId());
            pieModel.set("< 26", employeesByAge.get("lessThan26"));
            pieModel.set("25-30", employeesByAge.get("between26And30"));
            pieModel.set("31-35", employeesByAge.get("between31And35"));
            pieModel.set("36-40", employeesByAge.get("between36And40"));
            pieModel.set("> 40", employeesByAge.get("moreThan40"));
            pieModel.setLegendPosition("e");
            pieModel.setFill(true);
            pieModel.setShowDataLabels(true);
            pieModel.setSliceMargin(4);
            pieModel.setDiameter(150);
            pieModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            lastUpdateEmpDistByAge = new Date(employeesByAge.get("lastUpdate"));
            Map<String, Long> employeesByGender = empDataService.getTotalByGender(HrmUserInfoUtil.getCompanyId());
            totalFemale = employeesByGender.get("male");
            totalMale = employeesByGender.get("female");
            lastUpdateEmpDistByGender = new Date(employeesByGender.get("lastUpdate"));
            LocalDate now = new LocalDate();
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", LocaleContextHolder.getLocale());
            int week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
            StringBuffer buff = new StringBuffer();
            buff.append(week);
            if (LocaleContextHolder.getLocale().getLanguage().equals("en")) {
                buff.append(StringUtils.suffixesDayOfMonth[week]);
            }
            Object[] parameters = {buff.toString(), formatter.format(now.toDate())};
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", LocaleContextHolder.getLocale());
            presentationAttendancePerDayLabel = MessageFormat.format(bundle.getString("home.week_update_data"), parameters);
            List<Date> listTanggalWaktuKerja = new ArrayList<>();
            IntStream.range(1, 6).forEach(num -> listTanggalWaktuKerja.add(now.minusDays(num).toDate()));
            List<ChartSeries> listPresentasiAttendance = empDataService.getEmployeePresentationAttendanceOnDashboard(HrmUserInfoUtil.getCompanyId(), listTanggalWaktuKerja, "dd");

            presentationAttendancePerDayBarChartModel = new HorizontalBarChartModel();
            listPresentasiAttendance.forEach(series -> presentationAttendancePerDayBarChartModel.addSeries(series));
            presentationAttendancePerDayBarChartModel.setStacked(true);
            presentationAttendancePerDayBarChartModel.setShowDatatip(true);
            presentationAttendancePerDayBarChartModel.setLegendPosition("se");
            presentationAttendancePerDayBarChartModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,d500d5,ff2a55");
            Axis xAxis = presentationAttendancePerDayBarChartModel.getAxis(AxisType.X);
            xAxis.setMax(100);
            xAxis.setTickInterval("20");
            xAxis.setMin(0);
            Map<String, Long> employeesByDepartment = empDataService.getTotalByDepartment(HrmUserInfoUtil.getCompanyId());
            int i = 0;
            for (Map.Entry<String, Long> entry : employeesByDepartment.entrySet()) {

                /**
                 * only 8 department is allowed to show atau jika entry key nya
                 * "lastUpdate" berarti itu akhir dari list
                 */
                if (i == 8 || StringUtils.equals(entry.getKey(), "lastUpdate")) {
                    break;
                }
                i++;

                ChartSeries deptSeries = new ChartSeries();
                deptSeries.setLabel(entry.getKey());
                deptSeries.set("Department", entry.getValue());
                barChartDistribusiByDept.addSeries(deptSeries);

            }
            barChartDistribusiByDept.setLegendPosition("ne");
            barChartDistribusiByDept.setStacked(false);
            barChartDistribusiByDept.setShowDatatip(true);
            barChartDistribusiByDept.setLegendCols(4);
            barChartDistribusiByDept.setLegendRows(2);
            barChartDistribusiByDept.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,006666,660066");
            lastUpdateEmpDistByDepartment = new Date(employeesByDepartment.get("lastUpdate"));
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public Long getTotalRequestHistory() {
        return totalRequestHistory;
    }

    public void setTotalRequestHistory(Long totalRequestHistory) {
        this.totalRequestHistory = totalRequestHistory;
    }

    public Long getTotalPendingTask() {
        return totalPendingTask;
    }

    public void setTotalPendingTask(Long totalPendingTask) {
        this.totalPendingTask = totalPendingTask;
    }

    public Long getTotalPendingRequest() {
        return totalPendingRequest;
    }

    public void setTotalPendingRequest(Long totalPendingRequest) {
        this.totalPendingRequest = totalPendingRequest;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public void setLeaveImplementationDateService(LeaveImplementationDateService leaveImplementationDateService) {
        this.leaveImplementationDateService = leaveImplementationDateService;
    }

    public Integer getTotalLeaveType() {
        return totalLeaveType;
    }

    public void setTotalLeaveType(Integer totalLeaveType) {
        this.totalLeaveType = totalLeaveType;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public Date getLastUpdateEmpDistByAge() {
        return lastUpdateEmpDistByAge;
    }

    public void setLastUpdateEmpDistByAge(Date lastUpdateEmpDistByAge) {
        this.lastUpdateEmpDistByAge = lastUpdateEmpDistByAge;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Long getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(Long totalMale) {
        this.totalMale = totalMale;
    }

    public Long getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(Long totalFemale) {
        this.totalFemale = totalFemale;
    }

    public Date getLastUpdateEmpDistByGender() {
        return lastUpdateEmpDistByGender;
    }

    public void setLastUpdateEmpDistByGender(Date lastUpdateEmpDistByGender) {
        this.lastUpdateEmpDistByGender = lastUpdateEmpDistByGender;
    }

    public String getPresentationAttendancePerDayLabel() {
        return presentationAttendancePerDayLabel;
    }

    public void setPresentationAttendancePerDayLabel(String presentationAttendancePerDayLabel) {
        this.presentationAttendancePerDayLabel = presentationAttendancePerDayLabel;
    }

    public HorizontalBarChartModel getPresentationAttendancePerDayBarChartModel() {
        return presentationAttendancePerDayBarChartModel;
    }

    public void setPresentationAttendancePerDayBarChartModel(HorizontalBarChartModel presentationAttendancePerDayBarChartModel) {
        this.presentationAttendancePerDayBarChartModel = presentationAttendancePerDayBarChartModel;
    }

    public BarChartModel getBarChartDistribusiByDept() {
        return barChartDistribusiByDept;
    }

    public void setBarChartDistribusiByDept(BarChartModel barChartDistribusiByDept) {
        this.barChartDistribusiByDept = barChartDistribusiByDept;
    }

    public Date getLastUpdateEmpDistByDepartment() {
        return lastUpdateEmpDistByDepartment;
    }

    public void setLastUpdateEmpDistByDepartment(Date lastUpdateEmpDistByDepartment) {
        this.lastUpdateEmpDistByDepartment = lastUpdateEmpDistByDepartment;
    }

}
