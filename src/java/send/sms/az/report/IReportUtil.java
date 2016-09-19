///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package send.sms.az.report;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.logging.Logger;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
//import send.sms.az.util.DatabaseUtil;
//
///**
// *
// * @author Mobby
// */
//public class IReportUtil {
//
//    private static final Logger LOG = Logger.getLogger(IReportUtil.class.getName());
//    
//    public static void main(String[] args) throws JRException, SQLException {
//        LOG.info(DatabaseUtil.connect().toString());
//        JasperPrint jasperPrint = null;
//        JasperReport jasperReport;
//        HashMap jasperParameter = new HashMap();
//        jasperReport = JasperCompileManager.compileReport("C://transaction.jrxml");
//        jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, DatabaseUtil.connect());
//        JRXlsExporter exporter = new JRXlsExporter();
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C://simple_report.xls");
//        exporter.exportReport();
//    }
//    
//}
