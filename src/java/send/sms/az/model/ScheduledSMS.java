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
 * @author Rashad Javadov
 */
public class ScheduledSMS extends BaseModel {

    private BigDecimal rec_id;
    private String comments;
    private String sendFrom;
    private Date dateAdded;
    private Date dateStarted;
    private String addedBy;
    private String numberCount;
    private String sendType;
    private String periodType;

    public ScheduledSMS() { 
    }

    public BigDecimal getRec_id() {
        return rec_id;
    }

    public void setRec_id(BigDecimal rec_id) {
        this.rec_id = rec_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    @Override
    public String toString() {
        return "ScheduledSMS{" + "rec_id=" + rec_id + ", comments=" + comments + ", sendFrom=" + sendFrom + ", dateAdded=" + dateAdded + ", dateStarted=" + dateStarted + ", addedBy=" + addedBy + ", numberCount=" + numberCount + ", sendType=" + sendType + ", periodType=" + periodType + '}';
    }

}
