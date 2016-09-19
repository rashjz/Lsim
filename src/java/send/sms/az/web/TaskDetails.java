/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.chart.PieChartModel;
import send.sms.az.dao.BulkSMSDao;
import send.sms.az.model.BulkSMSSend;
import send.sms.az.model.TaskDetail;
import send.sms.az.web.ext.BulkSMSDetail;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "taskCnt")
@SessionScoped
public class TaskDetails extends BulkSMSDetail implements Serializable {

    private static final Logger logger = Logger.getLogger(TaskDetails.class.getName());

    List<TaskDetail> taskList = new ArrayList<>();
    private BulkSMSSend bsmss;
    private BulkSMSDao bulkSMSDao;
    private PieChartModel pieModel;

    public TaskDetails() {
        bulkSMSDao = new BulkSMSDao();
    }

    public void pauseTask() {
        logger.info("pauseTask ");
        bulkSMSDao.pauseTask(bsmss.getId(), loginCnt.getUser().getUser_id());
    }

    public void startTask() {
        logger.info("startTask ");
        bulkSMSDao.startTask(bsmss.getId(), loginCnt.getUser().getUser_id());
    }

    public void resumeTask() {
        logger.info("resumeTask ");
        bulkSMSDao.resumeTask(bsmss.getId(), loginCnt.getUser().getUser_id());
    }

    public void listTask() {
        logger.info("listTask ");
        bulkCnt.getBsmsm().setShowDetail(false);
    }

    public void recDetails(BulkSMSSend selSMS) {
        logger.info(" - - - recId " + selSMS.getId());
        bsmss = selSMS;
        taskList = bulkSMSDao.taskDetailList(selSMS.getId());
        logger.info("taskList size : " + taskList.size());
        pieModel = new PieChartModel();
        pieModel.set("DelivCount", bsmss.getDelivCount());
        pieModel.set("ErrorCount", bsmss.getErrorCount());
        pieModel.set("UnDelivCount", bsmss.getUnDelivCount());
        pieModel.set("Wait for Status", bsmss.getWait4status());
        pieModel.set("QueueCount", bsmss.getQueueCount());
        pieModel.setLegendPosition("w");
        pieModel.setTitle("Simple Pie");
        bulkCnt.getBsmsm().setShowDetail(true);
    }

    public BulkSMSSend getBsmss() {
        return bsmss;
    }

    public void setBsmss(BulkSMSSend bsmss) {
        this.bsmss = bsmss;
    }

    public List<TaskDetail> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDetail> taskList) {
        this.taskList = taskList;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}
