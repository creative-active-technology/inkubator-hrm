/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm;

/**
 *
 * @author Deni Husni FR
 */
public class HRMConstant {

    public static final String ADMINISTRATOR_ROLE = "ADMINISTRATOR_ROLE";
    public static final String SYSTEM_ADMIN = "SYSTEM_ADMIN";
    public static final String SPRING_SECURITY_CHECK = "j_spring_security_check";
    public static final String BAHASA_ACTIVE = "bahasa_active";
    public static final String LOGIN_DATE = "login_date";
    public static final String USER_LOGIN_ID = "user_login_id";
    public static final String NOTIFICATION_LOGIN_CHANEL_SOCKET = "/notificationsLogin";
    public static final String NOTIFICATION_APPROVAL_CHANEL_SOCKET = "/notificationsApproval";
    public static final String CHECK_IN_OUT_CHANEL_SOCKET = "/checkInChekOut";
    public static final String PASSWORD_CONFIG_CODE = "passconfigcode";
    public static final String APPROVAL_PROCESS_CREATE_USER_ID = "PEMBUATAN USER";
    public static final String APPROVAL_PROCESS_UPDATE_USER_ID = "UPDATE USER";
    public static final String APPROVAL_PROCESS_DELETE_USER_ID = "HAPUS USER";
    public static final String APPROVAL_PROCESS_REQUEST_LEAP_ID = "PERMOHONAN CUTI";
    public static final String APPROVAL_PROCESS_REQUEST_SICK_ID = "PERMOHONAN SAKIT";
    public static final String APPROVAL_PROCESS_REQUEST_PERMIT_ID = "PERMOHONAN IZIN";
    public static final String APPROVAL_PROCESS_CREATE_USER_EN = "CREATE USER";
    public static final String APPROVAL_PROCESS_UPDATE_USER_EN = "UPDATE USER";
    public static final String APPROVAL_PROCESS_DELETE_USER_EN = "DELETE USER";
    public static final String APPROVAL_PROCESS_REQUEST_LEAP_EN = "LEAP REQUEST";
    public static final String APPROVAL_PROCESS_REQUEST_SICK_EN = "SICK REQUEST";
    public static final String APPROVAL_PROCESS_REQUEST_PERMIT_EN = "PERMIT REQUEST";
    public static final String SAVE_CONDITION = "SAVE_CONDITION";
    public static final String UPDATE_CONDITION = "UPDATE_CONDITION";
    public static final Integer ACTIVE = 1;
    public static final Integer NOTACTIVE = 0;
    public static final Integer LOCK = 1;
    public static final Integer NOTLOCK = 0;
    public static final Integer EXPIRED = 1;
    public static final Integer NOTEXPIRED = 0;
    public static final String DELIMITER = "|";
    public static final byte[] KEYVALUE = new byte[]{'z', 'i', 'm', 'A', 'm', 'a', 'B', 'y', 'a', 'L', 'N', 'L', 'd', 'h', 'f', 'r'};
    public static final String AES_ALGO = "AES";
    public static final String USER_NEW = "user_create_new";
    public static final String USER_UPDATE = "user_update";
    public static final String USER_RESET = "user_reset";
    public static final String INKUBA_SYSTEM = "INKUBA_SYSTEM";
    public static final Integer EMAIL_NOTIFICATION_NOT_SEND = -1;
    public static final Integer EMAIL_NOTIFICATION_NOT_YET_SEND = 0;
    public static final Integer EMAIL_NOTIFICATION_SEND = 1;
    public static final Integer SMA_NOTIFICATION_NOT_SEND = -1;
    public static final Integer SMS_NOTIFICATION_NOT_YET_SEND = 0;
    public static final Integer SMS_NOTIFICATION_SEND = 1;
    public static final Integer OT_SUMMARY = 1;
    public static final Integer OT_SEPARATED = 0;
    public static final Double DEFAULT_LATITUDE = -6.211551441520004D;
    public static final Double DEFAULT_LONGITUDE = 106.84444427490234D;


    /* 
     * Constant for Leave Module */
    public static final String LEAVE_DAY_TYPE_WORKING = "0";
    public static final String LEAVE_DAY_TYPE_CALENDAR = "1";
    public static final String LEAVE_CALCULATION_FULL_DAY = "0";
    public static final String LEAVE_CALCULATION_PART_DAY = "1";
    public static final String LEAVE_PERIOD_BASE_TMB = "0";
    public static final String LEAVE_PERIOD_BASE_0101 = "1";
    public static final String LEAVE_PERIOD_BASE_TMB_TO_0101 = "2";
    public static final String LEAVE_AVAILABILITY_FULL = "0";
    public static final String LEAVE_AVAILABILITY_INCREASES_MONTH = "1";
    public static final String LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE = "2";
    public static final String LEAVE_END_OF_PERIOD_MONTH = "0";
    public static final String LEAVE_END_OF_PERIOD_REST_OF_LEAVE = "1";
    /* END */

    /* 
     * Constant for JOB Deskripsi Module */
    public static final Integer JOB_DESCRIPTION_PRIMER = 1;
    public static final Integer JOB_DESCRIPTION_SEKUNDER = 2;
    public static final Integer JOB_DESCRIPTION_DAY = 1;
    public static final Integer JOB_DESCRIPTION_MONTH = 2;
    /* END */

    /* 
     * Constant for JOB Deskripsi Module */
    public static final Integer GLOBAL_MALE = 1;
    public static final Integer GLOBAL_FEMALE = 0;
    public static final Integer BLOOD_A_TYPE = 1;
    public static final Integer BLOOD_B_TYPE = 2;
    public static final Integer BLOOD_AB_TYPE = 3;
    public static final Integer BLOOD_O_TYPE = 4;
    /* END */

    /* 
     * Constant for BIO address Module */
    public static final Integer BIOADDRESS_TYPE_IDENTITY_CARD = 0;
    public static final Integer BIOADDRESS_TYPE_RESIDENCE = 1;
    public static final Integer BIOADDRESS_TYPE_EMERGENCY = 2;
    public static final Integer BIOADDRESS_STATUS_FAMILY = 0;
    public static final Integer BIOADDRESS_STATUS_RELATIVES = 1;
    public static final Integer BIOADDRESS_STATUS_OWNER = 2;
    /* END */

    /* Constant for Travel Components */
    public static final Integer MEASUREMENT_PACK = 0;
    public static final Integer MEASUREMENT_UNIT = 1;
    public static final Integer MEASUREMENT_DAY = 2;
    /* END */

    /* Constant for Travel Zone */
    public static final Integer ZONE_DOMESTICS = 0;
    public static final Integer ZONE_OVERSEAS = 1;
    /* END */

    /* Constant for BIO Medical Module */
    public static final Integer BIOMEDICAL_HEALED = 0;
    public static final Integer BIOMEDICAL_NOT_HEALED = 1;
    /* END */

    /* Constant for BIO Family Relation Module */
    public static final Integer BIOFAMILY_DEPENDENTS_YES = 1;
    public static final Integer BIOFAMILY_DEPENDENTS_NO = 0;
    /* END */

    /* Constant for BIO Bank Account Module */
    public static final Integer BANK_SAVING_TYPE_DEPOSITO = 0;
    public static final Integer BANK_SAVING_TYPE_SAVING = 1;
    public static final Integer BANK_SAVING_TYPE_GIRO = 2;
    public static final Integer BANK_SAVING_TYPE_CHECKING = 3;
    public static final Integer BANK_DEFAULT_ACCOUNT_YES = 0;
    public static final Integer BANK_DEFAULT_ACCOUNT_NO = 1;
    /* END */

    /* Constant for Placement */
    public static final Integer PLACEMENT_PREMI_HEATLY_YES = 1;
    public static final Integer PLACEMENT_PREMI_HEATLY_NO = 0;
    public static final Integer PLACEMENT_IS_FINGER_YES = 1;
    public static final Integer PLACEMENT_IS_FINGER_NO = 0;
    public static final Integer PLACEMENT_ISENTIF_YES = 1;
    public static final Integer PLACEMENT_ISENTIF_NO = 0;
    public static final Integer PLACEMENT_MARRIED = 1;
    public static final Integer PLACEMENT_NO_MARRIED = 0;
    /* END */

    /* Constant for Company Policy */
    public static final String COMP_POLICY_REPEAT_ON_WEEKLY = "WEEKLY";
    public static final String COMP_POLICY_REPEAT_ON_MONTHLY = "MONTHLY";
    public static final String COMP_POLICY_REPEAT_ON_QUARTERLY = "QUARTERLY";
    /* END */

    /* Approval Definition */
    public static final String APPROVAL_TYPE_INDIVIDUAL = "APPROVAL_TYPE_INDIVIDUAL";
    public static final String APPROVAL_TYPE_POSITION = "APPROVAL_TYPE_POSITION";
    public static final String APPROVAL_TYPE_DEPARTMENT = "APPROVAL_TYPE_DEPARTMENT";
    public static final String BIO_DATA_EDIT = "BIO_DATA_EDIT";
    public static final String BIO_HEATLY_EDIT = "BIO_HEATLY_EDIT";
    public static final String BIO_OCCUPATIONAL_EDIT = "BIO_OCCUPATIONAL_EDIT";
    public static final String BIO_EDUCATIONAL_EDIT = "BIO_EDUCATIONAL_EDIT";
    public static final String BIO_HOBY_EDIT = "BIO_HOBY_EDIT";
    public static final String BIO_FAMILY_EDIT = "BIO_FAMILY_EDIT";
    public static final String BIO_DOCUMENT_EDIT = "BIO_DOCUMENT_EDIT";
    public static final String BIO_BANK_EDIT = "BIO_BANK_EDIT";
    public static final String BIO_ID_CARD_EDIT = "BIO_ID_CARD_EDIT";
    public static final String BIO_INSURANCE_EDIT = "BIO_INSURANCE_EDIT";
    public static final String BIO_CONTACT_EDIT = "BIO_CONTACT_EDIT";
    public static final String BIO_SKILL_EDIT = "BIO_SKILL_EDIT";
    public static final String BUSINESS_TRAVEL = "BUSINESS_TRAVEL";
    public static final String REIMBURSEMENT = "REIMBURSEMENT";
    public static final String REIMBURSEMENT_DISBURSEMENT = "REIMBURSEMENT_DISBURSEMENT";
    public static final String ANNOUNCEMENT = "ANNOUNCEMENT";
    public static final String RECRUIT_MPP_APPLY = "RECRUIT_MPP_APPLY";
    public static final String RECRUITMENT_REQUEST = "RECRUITMENT_REQUEST";
    public static final String PERMIT = "PERMIT";
    public static final String LOAN = "LOAN";
    public static final String LEAVE = "LEAVE";
    public static final String LEAVE_CANCELLATION = "LEAVE_CANCELLATION";
    public static final String OVERTIME = "OVERTIME";
    public static final String APPROVAL_PROCESS = "APPROVAL_PROCESS";
    public static final String ON_APPROVE_INFO = "ON_APPROVE_INFO";
    public static final String ON_REJECT_INFO = "ON_REJECT_INFO";
    public static final Integer APPROVAL_STATUS_WAITING_APPROVAL = 0;
    public static final Integer APPROVAL_STATUS_APPROVED = 1;
    public static final Integer APPROVAL_STATUS_REJECTED = 2;
    public static final Integer APPROVAL_STATUS_DIVERTED = 3;
    public static final Integer APPROVAL_STATUS_CANCELLED = 4;
    public static final Integer APPROVAL_STATUS_ASKING_REVISED = 5;
    public static final Integer APPROVAL_STATUS_WAITING_REVISED = 6;
    public static final Integer APPROVAL_STATUS_REVISED = 7;
    public static final Integer APPROVAL_STATUS_DRAFT = 8;
    public static final String SHIFT_SCHEDULE = "SHIFT_SCHEDULE";
    public static final String EMP_CORRECTION_ATTENDANCE = "EMP_CORRECTION_ATTENDANCE";
    /* END */


    /*Placement */
    public static final String EMP_PLACEMENT = "EMP_PLACEMENT";
    public static final String EMP_ROTATION = "EMP_ROTATION";
    public static final String EMP_TERMINATION = "EMP_TERMINATION";
    /* END */

    /* Constant for  Model Component */
    public static final Integer MODEL_COMP_BASIC_SALARY = 0;
    public static final Integer MODEL_COMP_CEIL = 1;
    public static final Integer MODEL_COMP_TAX = 2;
    public static final Integer MODEL_COMP_UPLOAD = 3;
    public static final Integer MODEL_COMP_FORMULA = 4;
    public static final Integer MODEL_COMP_LOAN = 5;
    public static final Integer MODEL_COMP_REIMBURSEMENT = 6;
    public static final Integer MODEL_COMP_BENEFIT_TABLE = 7;
    public static final Integer MODEL_COMP_TAKE_HOME_PAY = 100;
    /* END */

    /* Constant for  Appraisal Element */
    public static final Integer LEADER_YES = 1;
    public static final Integer LEADER_NO = 0;
    public static final Integer OPERATOR_YES = 1;
    public static final Integer OPERATOR_NO = 0;
    /* END */

    /* Constant for LoanSchema */
    public static final Integer ANNUITY = 0;
    public static final Integer FLAT = 1;
    public static final Integer FLOATING = 2;

    public static final Integer NOMINAL = 0;
    public static final Integer SALARY = 1;
    /* END */

    /* Constant for Organization */
    public static final String ORGANIZATION_LEVEL_HOLDING = "0";
    public static final String ORGANIZATION_LEVEL_COMPANY = "1";
    /* END */

    /* Constant for FinancialNonBanking */
    public static final String FINANCIAL_SERVICE_INSURANCE = "0";
    public static final String FINANCIAL_SERVICE_FINANCING = "1";
    public static final String FINANCIAL_SERVICE_PENSION = "2";
    /* END */

    /* Constant for ReimbursmentSchema */
    public static final Integer REIMBURSMENT_UNIT = 0;
    public static final Integer REIMBURSMENT_NOMINAL = 1;

    public static final Integer PAYROLL_COMPONENT_NO = 0;
    public static final Integer PAYROLL_COMPONENT_YES = 1;

    public static final Integer TIME_RANGE_MONTHLY = 0;
    public static final Integer TIME_RANGE_YEARLY = 1;
    public static final Integer TIME_RANGE_PER_EMPLOYEE = 2;
    public static final Integer BASIC_VALUE_NOMINAL = 0;
    public static final Integer BASIC_VALUE_RATIO_SALARY = 1;

    /* Constant for Working Group */
    public static final Integer NORMAL_SCHEDULE = 0;
    public static final Integer NON_NORMAL_SCHEDULE = 1;
    public static final String WORKING_GROUP_CODE_DEFAULT = "DEFAULT";

    /* Placement of Employee Work Schedule */
    public static final Integer DEPARTMENT_EQUAL = 0;
    public static final Integer DEPARTMENT_LIKE = 0;

    public static final Integer EMPLOYEE_TYPE_EQUAL = 0;
    public static final Integer EMPLOYEE_TYPE_LIKE = 0;

    public static final Integer SORT_BY_NIK = 0;
    public static final Integer SORT_BY_NAME = 1;

    public static final Integer ORDER_BY_ASC = 0;
    public static final Integer ORDER_BY_DSC = 1;

    /* Permit Classification */
    public static final Integer PERMIT_DAY_FULL = 1;
    public static final Integer PERMIT_DAY_PART = 0;
    public static final Integer PERMIT_BASE_FROM_JANUARY_NEXT_PERIOD = 1;
    public static final Integer PERMIT_BASE_FROM_JOIN_DATE_NEXT_PERIOD = 0;
    public static final Integer PERMIT_BASE_FROM_JOIN_DATE_THIS_PERIOD = 2;
    public static final Integer PERMIT_AVAILIBILITY_FULL = 0;
    public static final Integer PERMIT_AVALILIBILITY_PER_MONTH = 1;
    public static final Integer PERMIT_AVALILIBILITY_PER_DATE = 2;

    /*Mecine Finger */
    public static final Integer METHOD_UPLOAD_MACINE = 0;
    public static final Integer METHOD_SERVICE_MACINE = 1;
    public static final Integer METHOD_QUERY_MACINE = 2;

    public static final Integer UPLOAD_EXCEL_TYPE = 0;
    public static final Integer UPLOAD_CSV_TYPE = 1;

    public static final Integer FILE_EXTENSION_XLS = 0;
    public static final Integer FILE_EXTENSION_XLSX = 1;
    public static final Integer FILE_EXTENSION_CSV = 2;

    public static final Integer SERVICE_DATA_JSON = 0;
    public static final Integer SERVICE_DATA_XML = 1;
    public static final Integer FILE_EXTENSION_OTHER = 2;

    public static final Integer MATCH_BASE_NIK = 0;
    public static final Integer MATCH_BASE_NAME = 1;

    public static final Integer PROTOCOL_FTP = 0;
    public static final Integer PROTOCOL_HTTP = 1;
    public static final Integer PROTOCOL_XMPP = 2;
    public static final Integer PROTOCOL_OTHER = 3;

    public static final String SERVICE_NIK = "NIK";
    public static final String INDEX_MECINE = "IDX";

    public static final Integer CHECK_IN = 0;
    public static final Integer CHECK_OUT = 1;
    public static final String CHECK_IN_EARLY = "EARLY_COME";
    public static final String CHECK_IN_ON_TIME = "ON TIME";
    public static final String CHECK_IN_LATE = "LATE";

    public static final Integer REPORT_FAMILY_DEPENDENTS_NO = 0;
    public static final Integer REPORT_FAMILY_DEPENDENTS_YES = 1;

    public static final Integer PAY_SALARY_JURNAL_TYPE_DEBET = 0;
    public static final Integer PAY_SALARY_JURNAL_TYPE_KREDIT = 1;

    public static final Integer PAY_SALARY_JURNAL_MODEL_CASH = 0;
    public static final Integer PAY_SALARY_JURNAL_MODEL_TRANSFER = 1;

    public static final Boolean REIMBURSMENT_PAYROLL_COMPONENT = Boolean.TRUE;

    /* Pay Salary Component */
    public static final Integer PAY_SALARY_COMPONENT_TUNJANGAN = 0;
    public static final Integer PAY_SALARY_COMPONENT_POTONGAN = 1;
    public static final Integer PAY_SALARY_COMPONENT_SUBSIDI = 2;

    /* Benefit Group */
    public static final Integer BENEFIT_GROUP_MEASUREMENT_HOUR = 0;
    public static final Integer BENEFIT_GROUP_MEASUREMENT_DAY = 1;
    public static final Integer BENEFIT_GROUP_MEASUREMENT_PERIOD = 2;
    public static final Integer BENEFIT_GROUP_MEASUREMENT_HOUR_MULTIPLIER = 173;
    public static final Integer BENEFIT_GROUP_MEASUREMENT_DAY_MULTIPLIER = 22;

    /* Tax Free */
    public static final String TF_STATUS_TIDAK_KAWIN = "TK";
    public static final String TF_STATUS_KAWIN = "K";
    public static final Integer TF_INC_PERSON_ZERO = 0;
    public static final Integer TF_INC_PERSON_ONE = 1;
    public static final Integer TF_INC_PERSON_TWO = 2;
    public static final Integer TF_INC_PERSON_THREE = 0;

    public static final Integer GLOBAL_MARRIED = 1;
    public static final Integer GLOBAL_NO_MARRIED = 0;

    /* Periode */
    public static final String PERIODE_ABSEN_VOID = "Void";
    public static final String PERIODE_ABSEN_ACTIVE = "Active";
    public static final String PERIODE_ABSEN_NOT_ACTIVE = "Not Active";
    public static final String PERIODE_PAYROLL_VOID = "Void";
    public static final String PERIODE_PAYROLL_ACTIVE = "Active";
    public static final String PERIODE_PAYROLL_NOT_ACTIVE = "Not Active";

    /* Loan */
    public static final Integer LOAN_UNDISBURSED = 0;
    public static final Integer LOAN_DISBURSED = 1;
    public static final Integer LOAN_CANCELED = 2;
    public static final Integer LOAN_REJECTED = 3;
    public static final Integer LOAN_PAID = 4;

//    KODEFIKAISIPROSES
    public static final String LOAN_KODE = "LOAN";
    public static final String REIMBURSEMENT_KODE = "REIM";
    public static final String REIMBURSEMENT_CANCEL_KODE = "REIM-CL";
    public static final String REIMBURSEMENT_DISBURSED_KODE = "REIM-DI";
    public static final String ACHIEFMENT_KODE = "ACHI";
    public static final String TATIB_KODE = "DEML";
    public static final String TRAVEL_KODE = "TRAV";
    public static final String TERMINASI_KODE = "TERM";
    public static final String OVERTIME_KODE = "OTIM";
    public static final String ANNOUNCEMENT_KODE = "ANNO";
    public static final String LOAN_CANCELLATION_KODE = "LCAN";
    public static final String LOAN_DISBURSEMENT_KODE = "LDIS";
    public static final String RECRUITMENT_REQUEST_KODE = "RECR-REQ";
    public static final String EMP_CORRECTION_ATTENDANCE_KODE = "CORR-ATT";

    public static final String APP_NAME = "Web Destop App";

    public static final Integer KLASIFIKASI_STRENGTHS = 0;
    public static final Integer KLASIFIKASI_WEAKNESSES = 1;
    public static final Integer KLASIFIKASI_OPPORTUNITIES = 2;
    public static final Integer KLASIFIKASI_THREATS = 3;

    /* 
     * Constant for Reimbursement Module */
    public static final Integer RMBS_TYPE_ROUND_DIGIT_0 = 0;
    public static final Integer RMBS_TYPE_ROUND_DIGIT_1 = 1;
    public static final Integer RMBS_TYPE_ROUND_DIGIT_2 = 2;
    public static final Integer RMBS_TYPE_ROUND_DIGIT_3 = 3;
    public static final Integer RMBS_TYPE_PERIOD_RECEIPT_DATE = 0;
    public static final Integer RMBS_TYPE_PERIOD_FILLING_DATE = 1;
    public static final Integer RMBS_APPLICATION_STATUS_UNDISBURSED = 0;
    public static final Integer RMBS_APPLICATION_STATUS_DISBURSED = 1;
    public static final Integer RMBS_APPLICATION_STATUS_CANCELED = 2;
    public static final Integer RMBS_APPLICATION_STATUS_REJECTED = 3;
    public static final Integer RMBS_APPLICATION_STATUS_PAID = 5;
    public static final Integer RMBS_DISBURSEMENT_STATUS_APPROVED = 0;
    public static final Integer RMBS_DISBURSEMENT_STATUS_REJECTED = 1;
    public static final Integer RMBS_DISBURSEMENT_STATUS_CANCELLED = 2;
    /* END */

    /* 
     * Constant for Reimbursement Module */
    public static final Integer ANNOUNCEMENT_STATUS_APPROVED = 0;
    public static final Integer ANNOUNCEMENT_STATUS_REJECTED = 1;
    public static final Integer ANNOUNCEMENT_STATUS_CANCELLED = 2;
    public static final Integer ANNOUNCEMENT_TIME_DAILY = 0;
    public static final Integer ANNOUNCEMENT_TIME_PERIOD = 1;
    public static final Integer ANNOUNCEMENT_VIEW_MAIL = 0;
    public static final Integer ANNOUNCEMENT_VIEW_WEB = 1;
    /* END */

    /* 
     * Constant for ORGANISASI LEVEL */
    public static final String ORGANISASI = "ORG";
    public static final String DEPARTMENT = "DEP";
    public static final String DIVISI = "DEV";
    public static final String DIREKTORAT = "DIR";
    public static final String SEKRETARIAT = "SKR";
    public static final String COMPANY_ACTIVE = "COMPANY_ACTIVE";
    public static final String SUPER_ADMIN = "SUPER ADMIN";
    public static final String BIODATA_ID = "BIODATA_ID";
    public static final String EMP_DATA_ID = "EMP_DATA_ID";
    /* END */
    
    /* 
     * Constant for Emp Correction Attendance Module */
    public static final Integer EMP_CORRECTION_ATTENDANCE_STATUS_APPROVED = 0;
    public static final Integer EMP_CORRECTION_ATTENDANCE_STATUS_REJECTED = 1;
    public static final Integer EMP_CORRECTION_ATTENDANCE_STATUS_CANCELLED = 2;
    /* END */
    
    //Constant For WT Period Status
    public static final String WT_PERIOD_STATUS_ACTIVE = "Active";
    public static final String WT_PERIOD_STATUS_VOID = "Void";
    
    //Constant For Biodata Revision Data Type
    public static final String BIO_REV_DETAIL_BIO_DATA = "DETAIL_BIO_DATA";
    public static final String BIO_REV_ADDRESS = "ADDRESS";
    public static final String BIO_REV_CONTACT = "CONTACT";
    public static final String BIO_REV_ID_CARD = "ID_CARD";
    public static final String BIO_REV_FAMILY = "FAMILY";
    public static final String BIO_REV_COMPANY_RELATION = "COMPANY_RELATION";
    public static final String BIO_REV_EDUCATION = "EDUCATION";
    public static final String BIO_REV_SKILL = "SKILL";
    public static final String BIO_REV_SPESIFICATION_ABILITY = "SPESIFICATION_ABILITY";
    public static final String BIO_REV_INTEREST = "INTEREST";
    
    
}
