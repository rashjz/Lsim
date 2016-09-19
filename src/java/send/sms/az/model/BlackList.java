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
public class BlackList extends BaseModel {

    private String msisdn;
    private Date fdate;
    private String for_vas;
    private String for_bulk;

    public BlackList() {
    }

    public BlackList(String msisdn, String for_vas, String for_bulk) {
        this.msisdn = msisdn;
        this.for_vas = for_vas;
        this.for_bulk = for_bulk;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFor_vas() {
        return for_vas;
    }

    public void setFor_vas(String for_vas) {
        this.for_vas = for_vas;
    }

    public String getFor_bulk() {
        return for_bulk;
    }

    public void setFor_bulk(String for_bulk) {
        this.for_bulk = for_bulk;
    }

    @Override
    public String toString() {
        return "BlackList{" + "id=" + getId() + ", msisdn=" + msisdn + ", for_vas=" + for_vas + ", for_bulk=" + for_bulk + '}';
    }

}
