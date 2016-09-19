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
public class Handler {

    private BigDecimal handler_id;
    private String handler_name;
    private String hostname;
    private String a_num;
    private String b_num;
    private String content;
    private BigDecimal hand_type_id;
    private String proc_name;
    private BigDecimal sort_order;
    private String hand_status;
    private Date valid_from;
    private Date valid_to;
    private BigDecimal pack_tarif_id;
    private String charge_reason;
    private String app_name;
    private BigDecimal retry_on_netErr;
    private BigDecimal rep_rule_id;
    private BigDecimal numparam1;
    private BigDecimal numparam2;
    private String strparam1;
    private String strparam2;
    private BigDecimal send_Charge_Fail;
    private BigDecimal seg_id;
    private BigDecimal user_id;

    public Handler() {
        handler_id = BigDecimal.ZERO;
        user_id = BigDecimal.ZERO;
//        seg_id = BigDecimal.ZERO;
        retry_on_netErr = BigDecimal.ZERO;
        pack_tarif_id = BigDecimal.ZERO;
        hand_status = "a";
    }

    public BigDecimal getHandler_id() {
        return handler_id;
    }

    public void setHandler_id(BigDecimal handler_id) {
        this.handler_id = handler_id;
    }

    public String getHandler_name() {
        return handler_name;
    }

    public void setHandler_name(String handler_name) {
        this.handler_name = handler_name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getA_num() {
        return a_num;
    }

    public void setA_num(String a_num) {
        this.a_num = a_num;
    }

    public String getB_num() {
        return b_num;
    }

    public void setB_num(String b_num) {
        this.b_num = b_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getHand_type_id() {
        return hand_type_id;
    }

    public void setHand_type_id(BigDecimal hand_type_id) {
        this.hand_type_id = hand_type_id;
    }

    public String getProc_name() {
        return proc_name;
    }

    public void setProc_name(String proc_name) {
        this.proc_name = proc_name;
    }

    public BigDecimal getSort_order() {
        return sort_order;
    }

    public void setSort_order(BigDecimal sort_order) {
        this.sort_order = sort_order;
    }

    public String getHand_status() {
        return hand_status;
    }

    public void setHand_status(String hand_status) {
        this.hand_status = hand_status;
    }

    public Date getValid_from() {
        return valid_from;
    }

    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    public Date getValid_to() {
        return valid_to;
    }

    public void setValid_to(Date valid_to) {
        this.valid_to = valid_to;
    }

    public BigDecimal getPack_tarif_id() {
        return pack_tarif_id;
    }

    public void setPack_tarif_id(BigDecimal pack_tarif_id) {
        this.pack_tarif_id = pack_tarif_id;
    }

    public BigDecimal getRep_rule_id() {
        return rep_rule_id;
    }

    public void setRep_rule_id(BigDecimal rep_rule_id) {
        this.rep_rule_id = rep_rule_id;
    }

    public String getCharge_reason() {
        return charge_reason;
    }

    public void setCharge_reason(String charge_reason) {
        this.charge_reason = charge_reason;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public BigDecimal getRetry_on_netErr() {
        return retry_on_netErr;
    }

    public void setRetry_on_netErr(BigDecimal retry_on_netErr) {
        this.retry_on_netErr = retry_on_netErr;
    }

    public BigDecimal getNumparam1() {
        return numparam1;
    }

    public void setNumparam1(BigDecimal numparam1) {
        this.numparam1 = numparam1;
    }

    public BigDecimal getNumparam2() {
        return numparam2;
    }

    public void setNumparam2(BigDecimal numparam2) {
        this.numparam2 = numparam2;
    }

    public String getStrparam1() {
        return strparam1;
    }

    public void setStrparam1(String strparam1) {
        this.strparam1 = strparam1;
    }

    public String getStrparam2() {
        return strparam2;
    }

    public void setStrparam2(String strparam2) {
        this.strparam2 = strparam2;
    }

    public BigDecimal getSend_Charge_Fail() {
        return send_Charge_Fail;
    }

    public void setSend_Charge_Fail(BigDecimal send_Charge_Fail) {
        this.send_Charge_Fail = send_Charge_Fail;
    }

    public BigDecimal getSeg_id() {
        return seg_id;
    }

    public void setSeg_id(BigDecimal seg_id) {
        this.seg_id = seg_id;
    }

    public BigDecimal getUser_id() {
        return user_id;
    }

    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Handler{" + "handler_id=" + handler_id + ", handler_name=" + handler_name + ", hostname=" + hostname + ", a_num=" + a_num + ", b_num=" + b_num + ", content=" + content + ", hand_type_id=" + hand_type_id + ", proc_name=" + proc_name + ", sort_order=" + sort_order + ", hand_status=" + hand_status + ", valid_from=" + valid_from + ", valid_to=" + valid_to + ", pack_tarif_id=" + pack_tarif_id + ", charge_reason=" + charge_reason + ", app_name=" + app_name + ", retry_on_netErr=" + retry_on_netErr + ", rep_rule_id=" + rep_rule_id + ", numparam1=" + numparam1 + ", numparam2=" + numparam2 + ", strparam1=" + strparam1 + ", strparam2=" + strparam2 + ", send_Charge_Fail=" + send_Charge_Fail + ", seg_id=" + seg_id + ", user_id=" + user_id + '}';
    }

}
