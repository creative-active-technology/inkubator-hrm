/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.text.StrTokenizer;

/**
 *
 * @author Deni Husni FR
 */
public class IpUtil {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(IpUtil.class);

    public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public static final Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");

    public static String longToIpV4(long longIp) {

        int octet3 = (int) ((longIp >> 24) % 256);

        int octet2 = (int) ((longIp >> 16) % 256);

        int octet1 = (int) ((longIp >> 8) % 256);

        int octet0 = (int) ((longIp) % 256);

        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;

    }

    public static long ipV4ToLong(String ip) {

        String[] octets = ip.split("\\.");

        return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
                + (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);

    }

    public static boolean isIPv4Private(String ip) {

        long longIp = ipV4ToLong(ip);
        LOGGER.info("IP Number In Long " + longIp);
        boolean condition = false;
        if (longIp == 2130706433) {
            condition = true;
        }
        if ((longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255")) || (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
                || (longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255"))) {
            condition = true;
        }
        return condition;

    }

    public static boolean isIPv4Valid(String ip) {

        return pattern.matcher(ip).matches();

    }

    public static String getIpFromRequest(HttpServletRequest request) {

        String ip;

        boolean found = false;

        if ((ip = request.getHeader("x-forwarded-for")) != null) {
            LOGGER.info("IP Number " + ip);
            StrTokenizer tokenizer = new StrTokenizer(ip, ",");

            while (tokenizer.hasNext()) {

                ip = tokenizer.nextToken().trim();

                if (isIPv4Valid(ip) && !isIPv4Private(ip)) {

                    found = true;

                    break;

                }

            }

        }

        if (!found) {
            LOGGER.info("IP Number " + ip);
            ip = request.getRemoteAddr();

        }

        return ip;

    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        LOGGER.info(request.getHeader("X-Forwarded-For"));
        LOGGER.info(request.getHeader("Proxy-Client-IP"));
        LOGGER.info(request.getHeader("WL-Proxy-Client-IP"));
        LOGGER.info(request.getHeader("HTTP_CLIENT_IP"));
        LOGGER.info(request.getHeader("HTTP_X_FORWARDED_FOR"));
        return ip;
    }
}
