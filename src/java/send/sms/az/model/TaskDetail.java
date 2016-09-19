/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author rasha_000
 */
public class TaskDetail {

    private String msisdn;
    private String msg_body;
    private Date dateAdded;
    private String status;

    public TaskDetail() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMsg_body() {
        return msg_body;
    }

    public void setMsg_body(String msg_body) {
        this.msg_body = msg_body;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDetail{" + "msisdn=" + msisdn + ", msg_body=" + msg_body + ", dateAdded=" + dateAdded + ", status=" + status + '}';
    }
    
}
