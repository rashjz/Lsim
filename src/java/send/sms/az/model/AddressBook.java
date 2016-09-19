/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author rasha_000
 */
public class AddressBook extends BaseModel {

    private BigDecimal ab_id;
    private String name;
    private String surname;
    private String patrName;
    private String addName;
    private String mobNum;
    private String debt;
    private String payment;
    private String bdate;
    private String company;
    private String type;
    private String status;
    private String category;
    private String idnumber;
    private String email;
    private String voen;
    private String addDate1;
    private String addDate2;
    private String specCol1;
    private String specCol2;
    private String additional;
    private Date date1;
    private Date date2;
    private Date dateBD;

    public AddressBook() {
        name = "";
        surname = "";
        patrName = "";
        addName = "";
        mobNum = "";
        debt = "";
        payment = "";
        bdate = "";
        company = "";
        type = "";
        status = "";
        category = "";
        idnumber = "";
        email = "";
        voen = "";
        addDate1 = "";
        addDate2 = "";
        specCol1 = "";
        specCol2 = "";
        additional = "";
        ab_id = BigDecimal.ZERO;
    }

    public BigDecimal getAb_id() {
        return ab_id;
    }

    public void setAb_id(BigDecimal ab_id) {
        this.ab_id = ab_id;
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

    public String getPatrName() {
        return patrName;
    }

    public void setPatrName(String patrName) {
        this.patrName = patrName;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVoen() {
        return voen;
    }

    public void setVoen(String voen) {
        this.voen = voen;
    }

    public String getAddDate1() {
        return addDate1;
    }

    public void setAddDate1(String addDate1) {
        this.addDate1 = addDate1;
    }

    public String getAddDate2() {
        return addDate2;
    }

    public void setAddDate2(String addDate2) {
        this.addDate2 = addDate2;
    }

    public String getSpecCol1() {
        return specCol1;
    }

    public void setSpecCol1(String specCol1) {
        this.specCol1 = specCol1;
    }

    public String getSpecCol2() {
        return specCol2;
    }

    public void setSpecCol2(String specCol2) {
        this.specCol2 = specCol2;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDateBD() {
        return dateBD;
    }

    public void setDateBD(Date dateBD) {
        this.dateBD = dateBD;
    }

    @Override
    public String toString() {
        return "AddressBook{" + "name=" + name + ", surname=" + surname + ", patrName=" + patrName + ", addName=" + addName + ", mobNum=" + mobNum + ", debt=" + debt + ", payment=" + payment + ", bdate=" + bdate + ", company=" + company + ", type=" + type + ", status=" + status + ", category=" + category + ", idnumber=" + idnumber + ", email=" + email + ", voen=" + voen + ", addDate1=" + addDate1 + ", addDate2=" + addDate2 + ", specCol1=" + specCol1 + ", specCol2=" + specCol2 + ", additional=" + additional + '}';
    }

}
