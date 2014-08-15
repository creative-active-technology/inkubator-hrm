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

    public static final String SPRING_SECURITY_CHECK = "j_spring_security_check";
    public static final String BAHASA_ACTIVE = "bahasa_active";
    public static final String LOGIN_DATE = "login_date";
    public static final String USER_LOGIN_ID = "user_login_id";
    public static final String NOTIFICATION_CHANEL_SOCKET = "/notificationsLogin";
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
    public static final Integer BIOFAMILY_DEPENDENTS_YES = 0;
    public static final Integer BIOFAMILY_DEPENDENTS_NO = 1;
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
}
