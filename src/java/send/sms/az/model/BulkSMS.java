/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author rasha_000
 */
public class BulkSMS extends BaseModel implements Serializable {

    private BigDecimal sendFrom;
    private String comment;
    private Books addressBooks;
    private String message;
    private BigDecimal validPeriod;
    private BigDecimal offset;
    private BigDecimal maxCount;
    private BigDecimal batchSize;
    private BigDecimal batchInterval;
    private BigDecimal sendAt;
    private Date sendDate;
    private BigDecimal userID;
    private int smsCharSize = 0;
    private String numberCount;

    public BulkSMS() {
        addressBooks=new Books();
        offset = BigDecimal.ZERO;
        maxCount = BigDecimal.ZERO;
        validPeriod = BigDecimal.ZERO;
        batchSize = BigDecimal.ZERO;
        batchInterval = BigDecimal.ZERO;
        sendAt = BigDecimal.ZERO;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public BigDecimal getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(BigDecimal sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Books getAddressBooks() {
        return addressBooks;
    }

    public void setAddressBooks(Books addressBooks) {
        this.addressBooks = addressBooks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(BigDecimal validPeriod) {
        this.validPeriod = validPeriod;
    }

    public BigDecimal getOffset() {
        return offset;
    }

    public void setOffset(BigDecimal offset) {
        this.offset = offset;
    }

    public BigDecimal getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(BigDecimal maxCount) {
        this.maxCount = maxCount;
    }

    public BigDecimal getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(BigDecimal batchSize) {
        this.batchSize = batchSize;
    }

    public BigDecimal getBatchInterval() {
        return batchInterval;
    }

    public void setBatchInterval(BigDecimal batchInterval) {
        this.batchInterval = batchInterval;
    }

    public BigDecimal getSendAt() {
        return sendAt;
    }

    public void setSendAt(BigDecimal sendAt) {
        this.sendAt = sendAt;
    }

    public BigDecimal getUserID() {
        return userID;
    }

    public void setUserID(BigDecimal userID) {
        this.userID = userID;
    }

    public int getSmsCharSize() {
        return smsCharSize;
    }

    public void setSmsCharSize(int smsCharSize) {
        this.smsCharSize = smsCharSize;
    }

    public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    @Override
    public String toString() {
        return "BulkSMS{" + "sendFrom=" + sendFrom + ", comment=" + comment + ", addressBooks=" + addressBooks.getAbid() + ", message=" + message + ", validPeriod=" + validPeriod + ", offset=" + offset + ", maxCount=" + maxCount + ", batchSize=" + batchSize + ", batchInterval=" + batchInterval + ", sendAt=" + sendAt + ", userID=" + userID + '}';
    }

}
