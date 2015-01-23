package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanPaymentDetail;

/**
 *
 * @author rizkykojek
 */
public class LoanModel implements Serializable {

	private Long id;
	private EmpData empData;
	private Long loanSchemaId;
	private Double nominalPrincipal;
	private Double maxNominalPrincipal;
	private Integer termin;
        private String termins;
	private Integer maxTermin;
	private Double interestRate;
	private Date loanPaymentDate;
	private Date maxLoanPaymentDate;
	private Date loanDate;
	private Integer typeOfInterest;
	private List<LoanPaymentDetail> loanPaymentDetails;
        private String pathUpload;
        private String createdBy;
        private String nik;
	
	public LoanModel(){
		loanPaymentDetails = new ArrayList<LoanPaymentDetail>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Long getLoanSchemaId() {
		return loanSchemaId;
	}
	public void setLoanSchemaId(Long loanSchemaId) {
		this.loanSchemaId = loanSchemaId;
	}
	public Double getNominalPrincipal() {
		return nominalPrincipal;
	}
	public void setNominalPrincipal(Double nominalPrincipal) {
		this.nominalPrincipal = nominalPrincipal;
	}
	public Integer getTermin() {
		return termin;
	}
	public void setTermin(Integer termin) {
		this.termin = termin;
	}
	public Date getLoanPaymentDate() {
		return loanPaymentDate;
	}
	public void setLoanPaymentDate(Date loanPaymentDate) {
		this.loanPaymentDate = loanPaymentDate;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public List<LoanPaymentDetail> getLoanPaymentDetails() {
		return loanPaymentDetails;
	}
	public void setLoanPaymentDetails(List<LoanPaymentDetail> loanPaymentDetails) {
		this.loanPaymentDetails = loanPaymentDetails;
	}
	public Double getMaxNominalPrincipal() {
		return maxNominalPrincipal;
	}
	public void setMaxNominalPrincipal(Double maxNominalPrincipal) {
		this.maxNominalPrincipal = maxNominalPrincipal;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public Integer getMaxTermin() {
		return maxTermin;
	}
	public void setMaxTermin(Integer maxTermin) {
		this.maxTermin = maxTermin;
	}
	public Integer getTypeOfInterest() {
		return typeOfInterest;
	}
	public void setTypeOfInterest(Integer typeOfInterest) {
		this.typeOfInterest = typeOfInterest;
	}
	public Date getMaxLoanPaymentDate() {
		return DateTimeUtil.getDateFrom(loanPaymentDate, termin-1, CommonUtilConstant.DATE_FORMAT_MONTH);
	}
	public void setMaxLoanPaymentDate(Date maxLoanPaymentDate) {
		this.maxLoanPaymentDate = maxLoanPaymentDate;
	}	
	public Boolean getIsPaginator(){
		return loanPaymentDetails.size() > 11;
	}

        public String getPathUpload() {
            return pathUpload;
        }

        public void setPathUpload(String pathUpload) {
            this.pathUpload = pathUpload;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTermins() {
        return termins;
    }

    public void setTermins(String termins) {
        this.termins = termins;
    }
        
        
        
        
}
