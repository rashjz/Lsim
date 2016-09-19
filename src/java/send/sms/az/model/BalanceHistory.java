/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;
import java.util.Date;
import send.sms.az.model.BaseModel;

/**
 *
 * @author Mobby
 */
public class BalanceHistory extends  BaseModel{
 
    private String login;
    private BigDecimal old_value;
    private BigDecimal new_value;
    private String modified_by;
    private Date modified_date;
    private String comments;

 

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getOld_value() {
        return old_value;
    }

    public void setOld_value(BigDecimal old_value) {
        this.old_value = old_value;
    }

    public BigDecimal getNew_value() {
        return new_value;
    }

    public void setNew_value(BigDecimal new_value) {
        this.new_value = new_value;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BalanceHistory{" + "id=" + getId() + ", login=" + login + ", old_value=" + old_value + ", new_value=" + new_value + ", modified_by=" + modified_by + ", modified_date=" + modified_date + ", comments=" + comments + '}';
    }

}
