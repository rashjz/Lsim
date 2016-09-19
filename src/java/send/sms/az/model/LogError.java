/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author rasha_000
 */
public class LogError extends BaseModel {

    private BigDecimal logID;
    private String LogType;
    private Date LogDate;
    private String LogText;
    private String AppName;
    private String Msisdn;

    public BigDecimal getLogID() {
        return logID;
    }

    public void setLogID(BigDecimal logID) {
        this.logID = logID;
    }

    public String getLogType() {
        return LogType;
    }

    public void setLogType(String LogType) {
        this.LogType = LogType;
    }

    public Date getLogDate() {
        return LogDate;
    }

    public void setLogDate(Date LogDate) {
        this.LogDate = LogDate;
    }

    public String getLogText() {
        return LogText;
    }

    public void setLogText(String LogText) {
        this.LogText = LogText;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setMsisdn(String Msisdn) {
        this.Msisdn = Msisdn;
    }

    @Override
    public String toString() {
        return "LogError{" + "logID=" + logID + ", LogType=" + LogType + ", LogDate=" + LogDate + ", LogText=" + LogText + ", AppName=" + AppName + ", Msisdn=" + Msisdn + '}';
    }
    
    
}
