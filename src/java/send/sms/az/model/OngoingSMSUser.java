/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author @rashjz
 */
public class OngoingSMSUser extends BaseModel {

    private String username;
    private String source_addr;
    private BigDecimal delivrd;
    private BigDecimal undeliv;
    private BigDecimal expired;
    private BigDecimal queued;
    private BigDecimal notSent;
    private BigDecimal wait_status;
    private BigDecimal total;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource_addr() {
        return source_addr;
    }

    public void setSource_addr(String source_addr) {
        this.source_addr = source_addr;
    }

    public BigDecimal getDelivrd() {
        return delivrd;
    }

    public void setDelivrd(BigDecimal delivrd) {
        this.delivrd = delivrd;
    }

    public BigDecimal getUndeliv() {
        return undeliv;
    }

    public void setUndeliv(BigDecimal undeliv) {
        this.undeliv = undeliv;
    }

    public BigDecimal getExpired() {
        return expired;
    }

    public void setExpired(BigDecimal expired) {
        this.expired = expired;
    }

    public BigDecimal getQueued() {
        return queued;
    }

    public void setQueued(BigDecimal queued) {
        this.queued = queued;
    }

    public BigDecimal getNotSent() {
        return notSent;
    }

    public void setNotSent(BigDecimal notSent) {
        this.notSent = notSent;
    }

    public BigDecimal getWait_status() {
        return wait_status;
    }

    public void setWait_status(BigDecimal wait_status) {
        this.wait_status = wait_status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OngoingSMSUser{" + "username=" + username + ", source_addr=" + source_addr + ", delivrd=" + delivrd + ", undeliv=" + undeliv + ", expired=" + expired + ", queued=" + queued + ", notSent=" + notSent + ", wait_status=" + wait_status + ", total=" + total + '}';
    }

}
