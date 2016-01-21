package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DualListModel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtOverTime;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class OvertimeImplSearchingModel implements Serializable {
	private Date startDatePeriod;
	private Date endDatePeriod;
	private DualListModel<WtOverTime> dualListWtOverTime = new DualListModel<>();
	private DualListModel<GolonganJabatan> dualListGolonganJabatan = new DualListModel<GolonganJabatan>();
	private DualListModel<Department> dualListDepartemen = new DualListModel<>();
	private List<WtOverTime> listSelectedWtOvertime = new ArrayList<WtOverTime>();
	private List<GolonganJabatan> listSelectedGolJabatan = new ArrayList<GolonganJabatan>();
	private List<Department> listSelectedDepartment = new ArrayList<>();
	
	public Date getStartDatePeriod() {
		return startDatePeriod;
	}
	public void setStartDatePeriod(Date startDatePeriod) {
		this.startDatePeriod = startDatePeriod;
	}
	public Date getEndDatePeriod() {
		return endDatePeriod;
	}
	public void setEndDatePeriod(Date endDatePeriod) {
		this.endDatePeriod = endDatePeriod;
	}
	public DualListModel<WtOverTime> getDualListWtOverTime() {
		return dualListWtOverTime;
	}
	public void setDualListWtOverTime(DualListModel<WtOverTime> dualListWtOverTime) {
		this.dualListWtOverTime = dualListWtOverTime;
	}
	public DualListModel<GolonganJabatan> getDualListGolonganJabatan() {
		return dualListGolonganJabatan;
	}
	public void setDualListGolonganJabatan(DualListModel<GolonganJabatan> dualListGolonganJabatan) {
		this.dualListGolonganJabatan = dualListGolonganJabatan;
	}
	public DualListModel<Department> getDualListDepartemen() {
		return dualListDepartemen;
	}
	public void setDualListDepartemen(DualListModel<Department> dualListDepartemen) {
		this.dualListDepartemen = dualListDepartemen;
	}
	public List<WtOverTime> getListSelectedWtOvertime() {
		return listSelectedWtOvertime;
	}
	public void setListSelectedWtOvertime(List<WtOverTime> listSelectedWtOvertime) {
		this.listSelectedWtOvertime = listSelectedWtOvertime;
	}
	public List<GolonganJabatan> getListSelectedGolJabatan() {
		return listSelectedGolJabatan;
	}
	public void setListSelectedGolJabatan(List<GolonganJabatan> listSelectedGolJabatan) {
		this.listSelectedGolJabatan = listSelectedGolJabatan;
	}
	public List<Department> getListSelectedDepartment() {
		return listSelectedDepartment;
	}
	public void setListSelectedDepartment(List<Department> listSelectedDepartment) {
		this.listSelectedDepartment = listSelectedDepartment;
	}
	
	
}
