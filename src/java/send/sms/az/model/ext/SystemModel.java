/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import send.sms.az.model.LogError;

/**
 *
 * @author rasha_000
 */
public class SystemModel {

    private BigDecimal logErrorCount;
    private List<LogError> appLogErrors;
    private Date lastBlackListEntryDate;
    private BigDecimal outgoingNotProcessed;
    private BigDecimal outgoingInError;
    private BigDecimal incomingNotProcessed;

    public SystemModel() {
        logErrorCount = BigDecimal.ZERO;
        List<LogError> appLogErrors = new ArrayList<>();
        outgoingNotProcessed = BigDecimal.ZERO;
        outgoingInError = BigDecimal.ZERO;
        incomingNotProcessed = BigDecimal.ZERO;
    }

    public BigDecimal getLogErrorCount() {
        return logErrorCount;
    }

    public void setLogErrorCount(BigDecimal logErrorCount) {
        this.logErrorCount = logErrorCount;
    }

    public List<LogError> getAppLogErrors() {
        return appLogErrors;
    }

    public void setAppLogErrors(List<LogError> appLogErrors) {
        this.appLogErrors = appLogErrors;
    }

    public Date getLastBlackListEntryDate() {
        return lastBlackListEntryDate;
    }

    public void setLastBlackListEntryDate(Date lastBlackListEntryDate) {
        this.lastBlackListEntryDate = lastBlackListEntryDate;
    }

    public BigDecimal getOutgoingNotProcessed() {
        return outgoingNotProcessed;
    }

    public void setOutgoingNotProcessed(BigDecimal outgoingNotProcessed) {
        this.outgoingNotProcessed = outgoingNotProcessed;
    }

    public BigDecimal getOutgoingInError() {
        return outgoingInError;
    }

    public void setOutgoingInError(BigDecimal outgoingInError) {
        this.outgoingInError = outgoingInError;
    }

    public BigDecimal getIncomingNotProcessed() {
        return incomingNotProcessed;
    }

    public void setIncomingNotProcessed(BigDecimal incomingNotProcessed) {
        this.incomingNotProcessed = incomingNotProcessed;
    }

}
