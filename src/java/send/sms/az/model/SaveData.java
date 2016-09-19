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
public class SaveData {

    private String msisdn;
    private String name;
    private String surname;
    private String note;
    private Date bdate;
    private Date addDate1;
    private Date addDate2;
    private boolean update;
    private BigDecimal rowix;

    public SaveData() {
        update = false;
        rowix=BigDecimal.ZERO;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getAddDate1() {
        return addDate1;
    }

    public void setAddDate1(Date addDate1) {
        this.addDate1 = addDate1;
    }

    public Date getAddDate2() {
        return addDate2;
    }

    public void setAddDate2(Date addDate2) {
        this.addDate2 = addDate2;
    }

    public BigDecimal getRowix() {
        return rowix;
    }

    public void setRowix(BigDecimal rowix) {
        this.rowix = rowix;
    }

    @Override
    public String toString() {
        return "SaveData{" + "msisdn=" + msisdn + ", name=" + name + ", surname=" + surname + ", note=" + note + ", bdate=" + bdate + ", addDate1=" + addDate1 + ", addDate2=" + addDate2 + '}';
    }

}
