/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author rasha_000
 */
public class Subscribe   {

    private BigDecimal subs_id;
    private String subs_name;
    private String subs_desc;
    private String status;
    private String algorithm_name;
    private BigDecimal selection_algorithm;
    private BigDecimal selection_max_days_back;
    private String welcome_message;
    private String welcome_message2;
    private String info_message;
    private String goodbye_message;
    private String already_member_message;

    public Subscribe() {
        status = "a";
        selection_algorithm = BigDecimal.ZERO;
        selection_max_days_back = BigDecimal.ZERO;
    }

    public BigDecimal getSubs_id() {
        return subs_id;
    }

    public void setSubs_id(BigDecimal subs_id) {
        this.subs_id = subs_id;
    }

    public String getSubs_name() {
        return subs_name;
    }

    public void setSubs_name(String subs_name) {
        this.subs_name = subs_name;
    }

    public String getSubs_desc() {
        return subs_desc;
    }

    public void setSubs_desc(String subs_desc) {
        this.subs_desc = subs_desc;
    }

    public BigDecimal getSelection_algorithm() {
        return selection_algorithm;
    }

    public void setSelection_algorithm(BigDecimal selection_algorithm) {
        this.selection_algorithm = selection_algorithm;
    }

    public BigDecimal getSelection_max_days_back() {
        return selection_max_days_back;
    }

    public void setSelection_max_days_back(BigDecimal selection_max_days_back) {
        this.selection_max_days_back = selection_max_days_back;
    }

    public String getWelcome_message() {
        return welcome_message;
    }

    public void setWelcome_message(String welcome_message) {
        this.welcome_message = welcome_message;
    }

    public String getWelcome_message2() {
        return welcome_message2;
    }

    public void setWelcome_message2(String welcome_message2) {
        this.welcome_message2 = welcome_message2;
    }

    public String getInfo_message() {
        return info_message;
    }

    public void setInfo_message(String info_message) {
        this.info_message = info_message;
    }

    public String getGoodbye_message() {
        return goodbye_message;
    }

    public void setGoodbye_message(String goodbye_message) {
        this.goodbye_message = goodbye_message;
    }

    public String getAlready_member_message() {
        return already_member_message;
    }

    public void setAlready_member_message(String already_member_message) {
        this.already_member_message = already_member_message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlgorithm_name() {
        return algorithm_name;
    }

    public void setAlgorithm_name(String algorithm_name) {
        this.algorithm_name = algorithm_name;
    }

    @Override
    public String toString() {
        return "Subscribe{" + "subs_id=" + subs_id + ", subs_name=" + subs_name + ", subs_desc=" + subs_desc + ", status=" + status + ", selection_algorithm=" + selection_algorithm + ", selection_max_days_back=" + selection_max_days_back + ", welcome_message=" + welcome_message + ", welcome_message2=" + welcome_message2 + ", info_message=" + info_message + ", goodbye_message=" + goodbye_message + ", already_member_message=" + already_member_message + '}';
    }

}
