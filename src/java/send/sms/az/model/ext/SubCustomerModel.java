/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.util.ArrayList;
import java.util.List;
import send.sms.az.model.SubCustomer;

/**
 *
 * @author Mobby
 */
public class SubCustomerModel {

    private String msisdn;
    private String subscription;
    private String status;
    private String agent_name;
    private List<SubCustomer> customers;

    public SubCustomerModel() {
        msisdn="";
        customers = new ArrayList<>();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public List<SubCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<SubCustomer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "SubCustomerModel{" + "msisdn=" + msisdn + ", subscription=" + subscription + ", status=" + status + ", agent_name=" + agent_name + '}';
    }

}
