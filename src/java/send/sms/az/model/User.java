/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rasha_000
 */
public class User extends BaseModel {

    private BigDecimal user_id;
    private String login;
    private String passw;
    private String fullname;
    private String email;
    private String company_name;
    private BigDecimal company_id;
    private String group_name;
    private String u_status;
    private int lang_id;
    private String comments;
    private int group_id;
    private BigDecimal addedUser;
    private String addedby;
    private String mob;
    private BigDecimal balance;
    private String[] rolesId;
    private List<Right> rights;
    private Map<String, Right> rightsMap;

    public User() {
        user_id = BigDecimal.ZERO;
        balance = BigDecimal.ZERO;
        rights = new ArrayList();
        rightsMap = new HashMap<>();
    }

    public BigDecimal getUser_id() {
        return user_id;
    }

    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getU_status() {
        return u_status;
    }

    public void setU_status(String u_status) {
        this.u_status = u_status;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public BigDecimal getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(BigDecimal addedUser) {
        this.addedUser = addedUser;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String[] getRolesId() {
        return rolesId;
    }

    public void setRolesId(String[] rolesId) {
        this.rolesId = rolesId;
    }

    public BigDecimal getCompany_id() {
        return company_id;
    }

    public void setCompany_id(BigDecimal company_id) {
        this.company_id = company_id;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public Map<String, Right> getRightsMap() {
        return rightsMap;
    }

    public void setRightsMap(Map<String, Right> rightsMap) {
        this.rightsMap = rightsMap;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", login=" + login + ", passw=" + passw + ", fullname=" + fullname + ", email=" + email + ", company_name=" + company_name + ", company_id=" + company_id + ", group_name=" + group_name + ", u_status=" + u_status + ", lang_id=" + lang_id + ", comments=" + comments + ", group_id=" + group_id + ", addedUser=" + addedUser + ", addedby=" + addedby + ", mob=" + mob + ", balance=" + balance + ", rolesId=" + rolesId + '}';
    }

}
