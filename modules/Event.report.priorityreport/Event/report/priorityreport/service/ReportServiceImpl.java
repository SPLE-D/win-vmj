package Event.report.priorityreport.service;

import java.util.*;
import java.lang.*;

import Event.report.core.service.ReportServiceDecorator;
import Event.report.core.service.ReportServiceComponent;
import Event.report.core.model.Report;
import Event.report.core.model.ReportDecorator;
import Event.report.ReportFactory;
import Event.report.priorityreport.model.PriorityReport;

public class ReportServiceImpl extends ReportServiceDecorator {
    public ReportServiceImpl(ReportServiceComponent record) {
        super(record);
    }

    public Report createReport(Map<String, Object> requestBody) {
        Report reportpriorityreport = record.createReport(requestBody);

        String priorityReportStr = (String) requestBody.get("PriorityReport");
        PriorityReport priorityReport = PriorityReport.valueOf(priorityReportStr);

        Report reportpriorityreportdeco = ReportFactory.createReport(
            "Event.report.priorityreport.model.ReportImpl",
            reportpriorityreport,
            priorityReport
        );

        Repository.saveObject(reportpriorityreportdeco);
        return reportpriorityreportdeco;
    }

    public Report createReport(Map<String, Object> requestBody, int id) {
        Report savedReport = Repository.getObject(id);
        int recordReportReportId = ((ReportDecorator) savedReport).getReportId();

        Report report = record.createReport(requestBody, recordReportReportId);

        String priorityReportStr = (String) requestBody.get("PriorityReport");
        PriorityReport priorityReport = PriorityReport.valueOf(priorityReportStr);

        Report reportpriorityreport = ReportFactory.createReport(
            "Event.report.priorityreport.model.ReportImpl",
            report,
            priorityReport
        );

        return reportpriorityreport;
    }

    public HashMap<String, Object> updateReport(Map<String, Object> requestBody) {
        String idStr = (String) requestBody.get("reportId");
        int id = Integer.parseInt(idStr);

        Report reportpriorityreport = createReport(requestBody, id);

        Repository.updateObject(reportpriorityreport);
        reportpriorityreport = Repository.getObject(id);

        return reportpriorityreport.toHashMap();
    }

    public HashMap<String, Object> getReport(String idStr) {
        int id = Integer.parseInt(idStr);
        Report reportpriorityreport = Repository.getObject(id);
        return reportpriorityreport.toHashMap();
    }

    public HashMap<String, Object> getReportById(int id) {
        List<HashMap<String, Object>> reportList = getAllReport();
        for (HashMap<String, Object> report : reportList) {
            int report_id = ((Number) report.get("reportId")).intValue();
            if (report_id == id) {
                return report;
            }
        }
        return null;
    }

    public List<HashMap<String,Object>> getAllReport() {
        List<Report> List = Repository.getAllObject("report_priorityreport");
        return transformListToHashMap(List);
    }

    public List<HashMap<String,Object>> transformListToHashMap(List<Report> List) {
        List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for (int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }
        return resultList;
    }

    public List<HashMap<String,Object>> deleteReport(Map<String, Object> requestBody) {
        String idStr = (String) requestBody.get("reportId");
        int id = Integer.parseInt(idStr);
        Repository.deleteObject(id);
        return getAllReport();
    }
}