/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service.impl;

import com.inkubator.common.notification.model.SerialGateWay;
import com.inkubator.sms.gateway.web.model.ApprovalModel;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Deni Husni FR
 */
public class ModemManageService {
    
    private static final Logger LOGGER = Logger.getLogger(ModemManageService.class);
    
    public void startServiceAndAddGateway(SerialGateWay gateway) {
        
        try {
            SerialModemGateway modemGateway = new SerialModemGateway(gateway.getModemId(), gateway.getComPort(), gateway.getBaudRate(), gateway.getManaufactur(), gateway.getModelName());
            modemGateway.setInbound(gateway.getInBound());
            modemGateway.setOutbound(gateway.getOutBound());
            modemGateway.setSimPin(gateway.getPinNumber());
            modemGateway.setSmscNumber(gateway.getSmscNumber());
            InboundNotification inboundNotification = new InboundNotification();
// Create the notification callback method for inbound voice calls.
            CallNotification callNotification = new CallNotification();
//Create the notification callback method for gateway statuses.
            GatewayStatusNotification statusNotification = new GatewayStatusNotification();
            OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
            Service.getInstance().setInboundMessageNotification(inboundNotification);
            Service.getInstance().setCallNotification(callNotification);
            Service.getInstance().setGatewayStatusNotification(statusNotification);
            Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
            Service.getInstance().addGateway(modemGateway);
        } catch (GatewayException ex) {
            LOGGER.error(ex, ex);
        }
        
    }
    
    public static class OutboundNotification implements IOutboundMessageNotification {
        
        @Override
        public void process(org.smslib.AGateway gateway, OutboundMessage msg) {
            LOGGER.info("Outbound handler called from Gateway " + gateway.getGatewayId());
            LOGGER.info(msg);
        }
        
    }
    
    public class InboundNotification implements IInboundMessageNotification {
        
        @Override
        public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg) {
            try {
                if (msgType == Message.MessageTypes.INBOUND) {
                    LOGGER.info(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
                } else if (msgType == Message.MessageTypes.STATUSREPORT) {
                    LOGGER.info(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
                }
                String message = msg.getText();
                
                StringTokenizer st = new StringTokenizer(message, "#");
                System.out.println(message);
                ApprovalModel approvalModel = new ApprovalModel();
                approvalModel.setApproverNumberHp(msg.getOriginator());
                Service.getInstance().deleteMessage(msg);//harus disini jika terjadi error parsing message tetap terdelere dan tidak menumpuk di kartu
                approvalModel.setApproveCondition(st.nextToken());
                approvalModel.setApprovalActivityNumber(st.nextToken());
                approvalModel.setComentar(st.nextToken());
                System.out.println(approvalModel);
                
            } catch (Exception ex) {
                LOGGER.error("ini errornya "+ex.getClass());
                if (ex.getClass().isInstance(NoSuchElementException.class)) {
                       LOGGER.info("sdjfhdsjhfdsjhfdsjhf");
                }
                LOGGER.error(ex, ex);
            }
            
        }
    }
    
    public class CallNotification implements ICallNotification {
        
        @Override
        public void process(AGateway gateway, String callerId) {
            System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
        }
    }
    
    public class GatewayStatusNotification implements IGatewayStatusNotification {
        
        @Override
        public void process(AGateway gateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus) {
            System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
        }
    }
    
    public class OrphanedMessageNotification implements IOrphanedMessageNotification {
        
        @Override
        public boolean process(AGateway gateway, InboundMessage msg) {
            System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
            System.out.println(msg);
// Since we are just testing, return FALSE and keep the orphaned message part.
            return false;
        }
    }
}
