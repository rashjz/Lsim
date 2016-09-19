/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.util.Date;

/**
 *
 * @author rasha_000
 */
public class Ongoing_SMS extends BaseModel {

    private String operatorName;
    private String sourceAddr;
    private String destAddr;
    private String msgBody;
    private Date dateAdded;
    private String osStatus;
    private String appName;
    private String errDesc;

    public Ongoing_SMS() {
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getOsStatus() {
        return osStatus;
    }

    public void setOsStatus(String osStatus) {
        this.osStatus = osStatus;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    @Override
    public String toString() {
        return "Ongoing_SMS{" + "operatorName=" + operatorName + ", sourceAddr=" + sourceAddr + ", destAddr=" + destAddr + ", msgBody=" + msgBody + ", dateAdded=" + dateAdded + ", osStatus=" + osStatus + ", appName=" + appName + ", errDesc=" + errDesc + '}';
    }

}
