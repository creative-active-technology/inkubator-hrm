package com.inkubator.hrm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class MachineFingerUtil {

	public static String getAllDataAttendanceLog(String host, int port) throws UnsupportedEncodingException, IOException{
		
		//set initial connection
		String xmlGetAllDataAttendance = "<GetAttLog><ArgComKey xsi:type=\"xsd:integer\">0</ArgComKey><Arg><PIN xsi:type=\"xsd:integer\">ALL</PIN></Arg></GetAttLog>";
		InetAddress addr = InetAddress.getByName(host);
        Socket sock = new Socket(addr, port);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
        wr.write("POST /iWsService HTTP/1.0\r\n");
        wr.write("Content-Length: " + xmlGetAllDataAttendance.length() + "\r\n");
        wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
        wr.write("\r\n");
        
        //Send data
        wr.write(xmlGetAllDataAttendance);
        wr.flush();
		
        //Response	    	
    	BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	String line;
    	StringBuffer xmlBuffer = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            if(StringUtils.startsWith(line, "<")){
            	xmlBuffer.append(line);
            }
        }
        
        return xmlBuffer.toString();
	}
	
	public static String deleteAllDataAttendanceLog(String host, int port) throws IOException{
		
		//set initial connection
		/*String xmlDeleteAllDataAttendance = "<ClearData><ArgComKey xsi:type=\"xsd:integer\">0</ArgComKey><Arg><Value xsi:type=\"xsd:integer\">3</Value></Arg></ClearData>";
		InetAddress addr = InetAddress.getByName(host);
        Socket sock = new Socket(addr, port);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
        wr.write("POST /iWsService HTTP/1.0\r\n");
        wr.write("Content-Length: " + xmlDeleteAllDataAttendance.length() + "\r\n");
        wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
        wr.write("\r\n");*/
        
        //Send data
        /*wr.write(xmlDeleteAllDataAttendance);
        wr.flush();*/
		
        //Response	    	
    	/*BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	String line;*/
    	StringBuffer xmlBuffer = new StringBuffer();
        /*while ((line = rd.readLine()) != null) {
            if(StringUtils.startsWith(line, "<")){
            	xmlBuffer.append(line);
            }
        }*/
        
        return xmlBuffer.toString();		

	}
}
