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
 * @author Mobby
 */
public class BulkSMSSend extends BaseModel {

    private String comments;
    private String send_from;
    private Date date_added;
    private Date date_started;
    private String added_by;
    private BigDecimal number_count;
    private BigDecimal wait4status;
    private BigDecimal delivCount;
    private BigDecimal unDelivCount;
    private BigDecimal queueCount;
    private BigDecimal errorCount;
    private BigDecimal blaclListCount;
    private String started;

    public BulkSMSSend() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSend_from() {
        return send_from;
    }

    public void setSend_from(String send_from) {
        this.send_from = send_from;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Date getDate_started() {
        return date_started;
    }

    public void setDate_started(Date date_started) {
        this.date_started = date_started;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public BigDecimal getNumber_count() {
        return number_count;
    }

    public void setNumber_count(BigDecimal number_count) {
        this.number_count = number_count;
    }

    public BigDecimal getWait4status() {
        return wait4status;
    }

    public void setWait4status(BigDecimal wait4status) {
        this.wait4status = wait4status;
    }

    public BigDecimal getDelivCount() {
        return delivCount;
    }

    public void setDelivCount(BigDecimal delivCount) {
        this.delivCount = delivCount;
    }

    public BigDecimal getUnDelivCount() {
        return unDelivCount;
    }

    public void setUnDelivCount(BigDecimal unDelivCount) {
        this.unDelivCount = unDelivCount;
    }

    public BigDecimal getQueueCount() {
        return queueCount;
    }

    public void setQueueCount(BigDecimal queueCount) {
        this.queueCount = queueCount;
    }

    public BigDecimal getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(BigDecimal errorCount) {
        this.errorCount = errorCount;
    }

    public BigDecimal getBlaclListCount() {
        return blaclListCount;
    }

    public void setBlaclListCount(BigDecimal blaclListCount) {
        this.blaclListCount = blaclListCount;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    @Override
    public String toString() {
        return "BulkSMSSend{" + "id=" + getId() + ", comments=" + comments + ", send_from=" + send_from + ", date_added=" + date_added + ", date_started=" + date_started + ", added_by=" + added_by + ", number_count=" + number_count + ", wait4status=" + wait4status + ", delivCount=" + delivCount + ", unDelivCount=" + unDelivCount + ", queueCount=" + queueCount + ", errorCount=" + errorCount + ", blaclListCount=" + blaclListCount + ", started=" + started + '}';
    }

}
