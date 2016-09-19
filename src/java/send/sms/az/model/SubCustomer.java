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
public class SubCustomer extends BaseModel {

    private BigDecimal rec_id;
    private String subs_name;
    private String msisdn;
    private Date sub_date;
    private Date unSubs_date;
    private Date lastCharge_date;
    private Date nextCharge_date;
    private String subs_status;
    private String agent_name;

    public SubCustomer() {
    }

    public BigDecimal getRec_id() {
        return rec_id;
    }

    public void setRec_id(BigDecimal rec_id) {
        this.rec_id = rec_id;
    }

    public String getSubs_name() {
        return subs_name;
    }

    public void setSubs_name(String subs_name) {
        this.subs_name = subs_name;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Date getSub_date() {
        return sub_date;
    }

    public void setSub_date(Date sub_date) {
        this.sub_date = sub_date;
    }

    public Date getUnSubs_date() {
        return unSubs_date;
    }

    public void setUnSubs_date(Date unSubs_date) {
        this.unSubs_date = unSubs_date;
    }

    public Date getLastCharge_date() {
        return lastCharge_date;
    }

    public void setLastCharge_date(Date lastCharge_date) {
        this.lastCharge_date = lastCharge_date;
    }

    public Date getNextCharge_date() {
        return nextCharge_date;
    }

    public void setNextCharge_date(Date nextCharge_date) {
        this.nextCharge_date = nextCharge_date;
    }

    public String getSubs_status() {
        return subs_status;
    }

    public void setSubs_status(String subs_status) {
        this.subs_status = subs_status;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    @Override
    public String toString() {
        return "CustomerSubs{" + "rec_id=" + rec_id + ", subs_name=" + subs_name + ", msisdn=" + msisdn + ", sub_date=" + sub_date + ", unSubs_date=" + unSubs_date + ", lastCharge_date=" + lastCharge_date + ", nextCharge_date=" + nextCharge_date + ", subs_status=" + subs_status + ", agent_name=" + agent_name + '}';
    }

}
