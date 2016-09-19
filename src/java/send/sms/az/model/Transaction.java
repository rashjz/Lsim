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
public class Transaction extends BaseModel {

    private String trans_type;
    private Date trans_date;
    private String done_date;
    private String msisdn;
    private String short_num;
    private String trans_desc;
    private String smssize;
    private String trans_result;
    private String operator_name;
    private String host_name;
    private String app_name;
    private String client;

    public Transaction() {
        msisdn = "";
        operator_name = "0";
        short_num = "0";
        host_name = "0";
        client = "0";
        trans_result = "0";
        trans_type = "0";
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public Date getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(Date trans_date) {
        this.trans_date = trans_date;
    }

    public String getDone_date() {
        return done_date;
    }

    public void setDone_date(String done_date) {
        this.done_date = done_date;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getShort_num() {
        return short_num;
    }

    public void setShort_num(String short_num) {
        this.short_num = short_num;
    }

    public String getTrans_desc() {
        return trans_desc;
    }

    public void setTrans_desc(String trans_desc) {
        this.trans_desc = trans_desc;
    }

    public String getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(String trans_result) {
        this.trans_result = trans_result;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSmssize() {
        return smssize;
    }

    public void setSmssize(String smssize) {
        this.smssize = smssize;
    }

    @Override
    public String toString() {
        return "Transaction{" + "trans_type=" + trans_type + ", trans_date=" + trans_date + ", done_date=" + done_date + ", msisdn=" + msisdn + ", short_num=" + short_num + ", trans_desc=" + trans_desc + ", trans_result=" + trans_result + ", operator_name=" + operator_name + ", host_name=" + host_name + ", app_name=" + app_name + ", client=" + client + '}';
    }

}
