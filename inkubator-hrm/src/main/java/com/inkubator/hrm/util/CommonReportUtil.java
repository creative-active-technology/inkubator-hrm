/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Deni
 */
public class CommonReportUtil {

    public static String generateRandomFileName(String prefix, String extension) {
        Random random = new Random();
        long randomLong = random.nextLong();
        return prefix + randomLong + "." + extension;
    }

    public static DefaultStreamedContent exportReportToPDFStream(
            String jasperName,
            Map<String, Object> params,
            String reportOutputFileName) throws JRException, SQLException {

        //generate report from jrxml
        InputStream input = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/reports/" + jasperName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(input, params, ServiceWebUtilCon.getConnection());

        //stream the output file
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
        InputStream pdfInput = new ByteArrayInputStream(output.toByteArray());
        DefaultStreamedContent result = new DefaultStreamedContent(pdfInput, "application/pdf", reportOutputFileName);

        return result;
    }

    public static DefaultStreamedContent exportReportToPDFStreamWithAttachment(
            String jasperName,
            Map<String, Object> params,
            String reportOutputFileName, List<String> attachments) throws JRException, SQLException {

        //initial merging utility
        PDFMergerUtility mergerPDF = new PDFMergerUtility();

        //generate master report from jrxml
        InputStream masterStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/reports/" + jasperName);
        JasperPrint masterJP = JasperFillManager.fillReport(masterStream, params, ServiceWebUtilCon.getConnection());
        ByteArrayOutputStream masterOutput = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(masterJP, masterOutput);
        InputStream masterPDF = new ByteArrayInputStream(masterOutput.toByteArray());
        mergerPDF.addSource(masterPDF);

        //attachment pdf
        for (String path : attachments) {
            File file = new File(path);
            mergerPDF.addSource(file);
        }

        //merging process
        ByteArrayOutputStream mergerOutput = new ByteArrayOutputStream();
        mergerPDF.setDestinationStream(mergerOutput);
        try {
            mergerPDF.mergeDocuments();
        } catch (COSVisitorException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //stream the output file
        InputStream output = new ByteArrayInputStream(mergerOutput.toByteArray());
        DefaultStreamedContent result = new DefaultStreamedContent(output, "application/pdf", reportOutputFileName);

        return result;
    }

    public static DefaultStreamedContent exportReportToPDFStream(
            String jasperName,
            Map<String, Object> params,
            String reportOutputFileName,
            List listOfMap) throws JRException, SQLException, Exception {

        DefaultStreamedContent result;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        // get jasper name
        InputStream input = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/reports/" + jasperName);
        HashMap hm = new HashMap();
        // get list value
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOfMap);
        // compile jasper design with value
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                input,
                hm,
                beanCollectionDataSource);
        //export to pdf
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
        InputStream pdfInput = new ByteArrayInputStream(output.toByteArray());
        result = new DefaultStreamedContent(pdfInput, "application/pdf", reportOutputFileName);
        return result;
    }

    public static void exportReportToPDFFile(
            String jasperName,
            Map<String, Object> params,
            String reportOutputFileName) throws JRException, SQLException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/");

        InputStream input = FacesContext.getCurrentInstance().getExternalContext()
                .getResourceAsStream("/resources/reports/" + jasperName);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                input,
                params,
                ServiceWebUtilCon.getConnection());
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/" + reportOutputFileName);

    }

    public static DefaultStreamedContent exportReportToXLSX(
            // Karena proses uprgare ke versi 5.6.1 akan di fix .. sementara di biarakan dahulu karena tidak ada yang menggunakannya
            String jasperName,
            Map<String, Object> params,
            String reportOutputFileName) throws JRException, SQLException {
        DefaultStreamedContent result;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/");

        InputStream input = FacesContext.getCurrentInstance().getExternalContext()
                .getResourceAsStream("/resources/reports/" + jasperName);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                input,
                params,
                ServiceWebUtilCon.getConnection());
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
//        exporter.setParameter(JRExporterParameter., input);
//        exporterXLS.setParameter(JRXlsExporterParameter.JA SPER_PRINT, print);
//exporterXLS.setParameter(JRXlsExporterParameter.OU TPUT_STREAM, output);
//exporterXLS.setParameter(JRXlsExporterParameter.IS _ONE_PAGE_PER_SHEET, Boolean.TRUE);
//exporterXLS.setParameter(JRXlsExporterParameter.IS _AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
//exporterXLS.setParameter(JRXlsExporterParameter.IS _WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//exporterXLS.setParameter(JRXlsExporterParameter.IS _REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
        exporter.exportReport();
        InputStream pdfInput = new ByteArrayInputStream(output.toByteArray());
        result = new DefaultStreamedContent(pdfInput, "application/xls", reportOutputFileName);
        return result;
    }
}
