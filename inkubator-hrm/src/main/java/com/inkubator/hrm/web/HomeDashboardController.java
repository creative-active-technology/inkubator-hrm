/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.hamcrest.Matchers;
import org.jfree.data.time.Week;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.i18n.LocaleContextHolder;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.EmployeeResumeDashboardModel;
import com.inkubator.hrm.web.model.LoginHistoryModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "homeDashboardController")
@RequestScoped
public class HomeDashboardController extends BaseController {

    private Date startCalendarDate;
    private Date endCalendarDate;
    private List<Integer> listHolidayDate = new ArrayList<Integer>();
    private Date lastUpdateEmpDistByGender;
    private Date lastUpdateEmpDistByDepartment;
    private Date lastUpdateEmpDistByAge;
    private Long totalMale;
    private Long totalFemale;
    private EmployeeResumeDashboardModel employeeResumeModel;
    private PieChartModel pieModel;
    private CartesianChartModel distribusiKaryawanPerDepartment;
    private CartesianChartModel presensiModel;
    private CartesianChartModel persentasiKehadiranPerWeek;
    private BarChartModel barChartModel;
    private HorizontalBarChartModel presentationAttendancePerDayBarChartModel;
    private String presentationAttendancePerDayLabel;
    private BarChartModel barChartDistribusiByDept;
    private List<LoginHistoryModel> logHistorys = new ArrayList<>();
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    private RealizationAttendanceModel attendanceModel;
    private Double totalPersent;

    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{wtScheduleShiftService}")
    private WtScheduleShiftService wtScheduleShiftService;
    @ManagedProperty(value = "#{wtHolidayService}")
    private WtHolidayService wtHolidayService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        distribusiKaryawanPerDepartment = new CartesianChartModel();
        barChartDistribusiByDept = new BarChartModel();
        pieModel = new PieChartModel();
        totalMale = new Long(0);
        totalFemale = new Long(0);
        try {
            /**
             * find All holiday date based on JadwalKaryawan "DEFAULT" which is
             * OFF and public holiday
             */
            LocalDate now = new LocalDate();
            startCalendarDate = now.dayOfMonth().withMinimumValue().toDate();
            endCalendarDate = now.dayOfMonth().withMaximumValue().toDate();
            Set<Date> holidays = wtScheduleShiftService.getAllRegulerOffDaysBetween(startCalendarDate, endCalendarDate);
            for (Date holiday : holidays) {
                DateTime dtHoliday = new DateTime(holiday);
                listHolidayDate.add(dtHoliday.getDayOfMonth());
            }

            /**
             * calculate employee distribution based on GENDER
             */
            Map<String, Long> employeesByGender = empDataService.getTotalByGender(HrmUserInfoUtil.getCompanyId());
            totalFemale = employeesByGender.get("male");
            totalMale = employeesByGender.get("female");
            lastUpdateEmpDistByGender = new Date(employeesByGender.get("lastUpdate"));

            /**
             * calculate employee distribution based on DEPARTMENT
             */
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
            barChartDistribusiByDept.setLegendCols(8);
            barChartDistribusiByDept.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,006666");
            lastUpdateEmpDistByDepartment = new Date(employeesByDepartment.get("lastUpdate"));

            /**
             * calculate employee distribution based on AGE
             */
            Map<String, Long> employeesByAge = empDataService.getTotalByAge(HrmUserInfoUtil.getCompanyId());
            pieModel.set("< 26", employeesByAge.get("lessThan26"));
            pieModel.set("25-30", employeesByAge.get("between26And30"));
            pieModel.set("31-35", employeesByAge.get("between31And35"));
            pieModel.set("36-40", employeesByAge.get("between36And40"));
            pieModel.set("> 40", employeesByAge.get("moreThan40"));
            pieModel.setLegendPosition("e");
            pieModel.setFill(false);
            pieModel.setShowDataLabels(true);
            pieModel.setSliceMargin(4);
            pieModel.setDiameter(120);
            pieModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            lastUpdateEmpDistByAge = new Date(employeesByAge.get("lastUpdate"));

            /**
             * calculate employee resume
             */
            employeeResumeModel = empDataService.getEmployeeResumeOnDashboard(HrmUserInfoUtil.getCompanyId());

            /**
             * calculate attendance statistic
             */
            attendanceModel = tempAttendanceRealizationService.getStatisticEmpAttendaceRealization();
            double totalPresent = Double.parseDouble(String.valueOf(attendanceModel.getTotaldayPresent()));
            double totalSchedule = Double.parseDouble(String.valueOf(attendanceModel.getTotaldaySchedule()));
            totalPersent = (totalPresent / totalSchedule);

            persentasiKehadiranPerWeek = new CartesianChartModel();
            barChartModel = new BarChartModel();
            barChartModel.setStacked(false);
            barChartModel.setLegendPosition("ne");
            barChartModel.setLegendCols(6);
            barChartModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc");
            barChartModel.setShowDatatip(true);
            barChartModel.setShadow(true);
            barChartModel.setShowPointLabels(true);
            Axis yAxis = barChartModel.getAxis(AxisType.Y);
            yAxis.setMax(150);
            yAxis.setMin(0);

            //Get Attendance Percentation per Department on Active Period
            Map<String, List<DepAttendanceRealizationViewModel>> mapResult = empDataService.getListDepAttendanceByCompanyId(HrmUserInfoUtil.getCompanyId());

            //Looping and render it
            for (Map.Entry<String, List<DepAttendanceRealizationViewModel>> entry : mapResult.entrySet()) {

                ChartSeries charDepartmentSeries = new ChartSeries();
                charDepartmentSeries.setLabel(entry.getKey());
                List<DepAttendanceRealizationViewModel> listDepartmentModel = Lambda.sort(entry.getValue(), Lambda.on(DepAttendanceRealizationViewModel.class).getWeekNumber());
                for (DepAttendanceRealizationViewModel depAttendanceModel : listDepartmentModel) {
                    charDepartmentSeries.set(ResourceBundleUtil.getAsString("global.week") + "  " + depAttendanceModel.getWeekNumber(), depAttendanceModel.getAttendancePercentage().doubleValue() * 100);
                }

                barChartModel.addSeries(charDepartmentSeries);
            }
            
            
            /**
             * calculate attendance statistic from 6 days ago until yesterday
             */
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            int week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
            StringBuffer buff = new StringBuffer();
            buff.append(week);
            if(LocaleContextHolder.getLocale().getLanguage().equals("en")){
            	buff.append(StringUtils.suffixesDayOfMonth[week]);
            }
            Object[] parameters = {buff.toString(),formatter.format(now.toDate())};
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", LocaleContextHolder.getLocale());
            presentationAttendancePerDayLabel = MessageFormat.format(bundle.getString("home.week_update_data"), parameters);
            List<Date> listTanggalWaktuKerja = new ArrayList<>();
            IntStream.range(1, 6).forEach(num -> listTanggalWaktuKerja.add(now.minusDays(num).toDate()));
            List<ChartSeries> listPresentasiAttendance = empDataService.getEmployeePresentationAttendanceOnDashboard(HrmUserInfoUtil.getCompanyId() ,listTanggalWaktuKerja);
            
            presentationAttendancePerDayBarChartModel = new HorizontalBarChartModel();
            listPresentasiAttendance.forEach(series -> presentationAttendancePerDayBarChartModel.addSeries(series));
            presentationAttendancePerDayBarChartModel.setStacked(true);
            presentationAttendancePerDayBarChartModel.setShowDatatip(true);
            presentationAttendancePerDayBarChartModel.setLegendPosition("se");
            presentationAttendancePerDayBarChartModel.setSeriesColors("66cc00,629de1,003366,990000,cccc00,6600cc,d500d5,ff2a55");
            Axis xAxis = presentationAttendancePerDayBarChartModel.getAxis(AxisType.X);
            xAxis.setMax(300);
            xAxis.setTickInterval("20");
            xAxis.setMin(0);
            
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }

    }

    public void onSelectDate(SelectEvent event) {
        try {
            Date date = (Date) event.getObject();
            WtHoliday holiday = wtHolidayService.getEntityByDate(date);
            if (holiday != null) {
                //libur holiday
                ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, messages.getString("global.information"), holiday.getHolidayName());
                FacesUtil.getFacesContext().addMessage(null, msg);
            } else if (Lambda.exists(listHolidayDate, Matchers.equalTo(new DateTime(date).getDayOfMonth()))) {
                //libur jadwal kerja reguler(default)
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.information", "global.regular_holiday", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
    }

    public Date getLastUpdateEmpDistByGender() {
        return lastUpdateEmpDistByGender;
    }

    public void setLastUpdateEmpDistByGender(Date lastUpdateEmpDistByGender) {
        this.lastUpdateEmpDistByGender = lastUpdateEmpDistByGender;
    }

    public Date getLastUpdateEmpDistByDepartment() {
        return lastUpdateEmpDistByDepartment;
    }

    public void setLastUpdateEmpDistByDepartment(Date lastUpdateEmpDistByDepartment) {
        this.lastUpdateEmpDistByDepartment = lastUpdateEmpDistByDepartment;
    }

    public Date getLastUpdateEmpDistByAge() {
        return lastUpdateEmpDistByAge;
    }

    public void setLastUpdateEmpDistByAge(Date lastUpdateEmpDistByAge) {
        this.lastUpdateEmpDistByAge = lastUpdateEmpDistByAge;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public CartesianChartModel getPresensiModel() {
        return presensiModel;
    }

    public void setPresensiModel(CartesianChartModel presensiModel) {
        this.presensiModel = presensiModel;
    }

    public List<LoginHistoryModel> getLogHistorys() {
        return logHistorys;
    }

    public void setLogHistorys(List<LoginHistoryModel> logHistorys) {
        this.logHistorys = logHistorys;
    }

    public CartesianChartModel getPersentasiKehadiranPerWeek() {
        return persentasiKehadiranPerWeek;
    }

    public void setPersentasiKehadiranPerWeek(CartesianChartModel persentasiKehadiranPerWeek) {
        this.persentasiKehadiranPerWeek = persentasiKehadiranPerWeek;
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

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public CartesianChartModel getDistribusiKaryawanPerDepartment() {
        return distribusiKaryawanPerDepartment;
    }

    public void setDistribusiKaryawanPerDepartment(CartesianChartModel distribusiKaryawanPerDepartment) {
        this.distribusiKaryawanPerDepartment = distribusiKaryawanPerDepartment;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public HorizontalBarChartModel getPresentationAttendancePerDayBarChartModel() {
		return presentationAttendancePerDayBarChartModel;
	}

	public void setPresentationAttendancePerDayBarChartModel(
			HorizontalBarChartModel presentationAttendancePerDayBarChartModel) {
		this.presentationAttendancePerDayBarChartModel = presentationAttendancePerDayBarChartModel;
	}

	public BarChartModel getBarChartDistribusiByDept() {
        return barChartDistribusiByDept;
    }

    public void setBarChartDistribusiByDept(BarChartModel barChartDistribusiByDept) {
        this.barChartDistribusiByDept = barChartDistribusiByDept;
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    public RealizationAttendanceModel getAttendanceModel() {
        return attendanceModel;
    }

    public void setAttendanceModel(RealizationAttendanceModel attendanceModel) {
        this.attendanceModel = attendanceModel;
    }

    public Double getTotalPersent() {
        return totalPersent;
    }

    public void setTotalPersent(Double totalPersent) {
        this.totalPersent = totalPersent;
    }

    public EmployeeResumeDashboardModel getEmployeeResumeModel() {
        return employeeResumeModel;
    }

    public void setEmployeeResumeModel(EmployeeResumeDashboardModel employeeResumeModel) {
        this.employeeResumeModel = employeeResumeModel;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public List<Integer> getListHolidayDate() {
        return listHolidayDate;
    }

    public void setListHolidayDate(List<Integer> listHolidayDate) {
        this.listHolidayDate = listHolidayDate;
    }

    public Date getStartCalendarDate() {
        return startCalendarDate;
    }

    public void setStartCalendarDate(Date startCalendarDate) {
        this.startCalendarDate = startCalendarDate;
    }

    public Date getEndCalendarDate() {
        return endCalendarDate;
    }

    public void setEndCalendarDate(Date endCalendarDate) {
        this.endCalendarDate = endCalendarDate;
    }

    public void setWtScheduleShiftService(WtScheduleShiftService wtScheduleShiftService) {
        this.wtScheduleShiftService = wtScheduleShiftService;
    }

    public void setWtHolidayService(WtHolidayService wtHolidayService) {
        this.wtHolidayService = wtHolidayService;
    }

	public String getPresentationAttendancePerDayLabel() {
		return presentationAttendancePerDayLabel;
	}

	public void setPresentationAttendancePerDayLabel(String presentationAttendancePerDayLabel) {
		this.presentationAttendancePerDayLabel = presentationAttendancePerDayLabel;
	}
    
}
