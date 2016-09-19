/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Mobby
 */
public class TarifSearchModel {

    private BigDecimal operatorID;
    private BigDecimal userid;
    private Date startDate;
    private Date endDate;

    public BigDecimal getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(BigDecimal operatorID) {
        this.operatorID = operatorID;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
