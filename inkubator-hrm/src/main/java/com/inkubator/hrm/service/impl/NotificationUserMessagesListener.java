/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Deni Husni FR
 */
public class NotificationUserMessagesListener extends IServiceImpl implements MessageListener {

    @Override
    public void onMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Autowired
//    private JsonConverter jsonConverter;
//    @Autowired
//    private VelocityTemplateSender velocityTemplateSender;
//    @Autowired
//    private PasswordHistoryDao passwordHistoryDao;
//
//    @Override
//    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
//            isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
//    public void onMessage(Message message) {
//        try {
//            TextMessage textMessage = (TextMessage) message;
//            String json = textMessage.getText();
//            PasswordHistory passwordHistory = (PasswordHistory) jsonConverter.getClassFromJson(json, PasswordHistory.class);
//            passwordHistory.setPassword(AESUtil.getAESDescription(passwordHistory.getPassword(), PriceGeneratorConstant.keyValue, PriceGeneratorConstant.AES_ALGO));
//            if (passwordHistory.getEmailNotification() == 0) {
//                LOGGER.info("Send Mail Process " + passwordHistory.getRequestType());
//                VelocityTempalteModel vtm = new VelocityTempalteModel();
//                List<String> toSend = new ArrayList<>();
//                List<String> toSentCC = new ArrayList<>();
//                vtm.setFrom("noreplaypriceoffer@gmail.com");
//                toSend.add(passwordHistory.getEmailAddress());
//                toSentCC.add("rizal2_dhfr@yahoo.com");
//                vtm.setTo(toSend.toArray(new String[toSend.size()]));
//                vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
//                vtm.setSubject("User Notifications");
////                LOGGER.info("Bahasa y active " + FacesUtil.getSessionAttribute(PriceGeneratorConstant.BAHASA_ACTIVE).toString());
//                Map maptoSend = new HashMap();
//                if (passwordHistory.getLocalId().equals("en")) {
//                    vtm.setTemplatePath("email_user_confirmation_en.vm");
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_UPDATE)) {
//                        maptoSend.put("headerInfo", "Your password in PRICE GENERATOR has been updated. <br/>");
//                    }
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_NEW)) {
//                        maptoSend.put("headerInfo", "Your account in PRICE GENERATOR already created.<br/>");
//                    }
//
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_RESET)) {
//                        maptoSend.put("headerInfo", "Your Password in PRICE GENERATOR has been reset. <br/>");
//                    }
//                }
//                if (passwordHistory.getLocalId().equals("in")) {
//                    vtm.setTemplatePath("email_user_confirmation.vm");
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_UPDATE)) {
//                        maptoSend.put("headerInfo", "Password Anda pada Aplikasi PRICE GENERATOR berhasil diubah. <br/>");
//                    }
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_NEW)) {
//                        maptoSend.put("headerInfo", "Anda telah terdaftar di Aplikasi PRICE GENERATOR <br/>");
//                    }
//
//                    if (passwordHistory.getRequestType().equalsIgnoreCase(PriceGeneratorConstant.USER_RESET)) {
//                        maptoSend.put("headerInfo", "Password Anda pada Aplikasi PRICE GENERATOR berhasil direset. <br/>");
//                    }
//                }
//
//                Gson gson = new GsonBuilder().create();
//                TypeToken<List<String>> token = new TypeToken<List<String>>() {
//                };
//                List<String> dataRole = gson.fromJson(passwordHistory.getListRole(), token.getType());
//                maptoSend.put("role", dataRole);
//                maptoSend.put("user", passwordHistory);
//                velocityTemplateSender.sendMail(vtm, maptoSend);
//                passwordHistory.setEmailNotification(1);
//                passwordHistory.setPassword(HashingUtils.getHashSHA256(passwordHistory.getPassword()));
//                this.passwordHistoryDao.update(passwordHistory);
//
//            }
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//
//    }

}
