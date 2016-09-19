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
public class ShortNum extends BaseModel {

    private String host_name;
    private String short_num;
    private int isActive;
    private int incomingPrice;
    private int outgoingPrice;
    private String shortNumAlias;
    private String host_desc;
    private BigDecimal hostID;

    public ShortNum() {
        hostID = BigDecimal.ZERO;
    }
    
    

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getShort_num() {
        return short_num;
    }

    public void setShort_num(String short_num) {
        this.short_num = short_num;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIncomingPrice() {
        return incomingPrice;
    }

    public void setIncomingPrice(int incomingPrice) {
        this.incomingPrice = incomingPrice;
    }

    public int getOutgoingPrice() {
        return outgoingPrice;
    }

    public void setOutgoingPrice(int outgoingPrice) {
        this.outgoingPrice = outgoingPrice;
    }

    public String getShortNumAlias() {
        return shortNumAlias;
    }

    public void setShortNumAlias(String shortNumAlias) {
        this.shortNumAlias = shortNumAlias;
    }

    public String getHost_desc() {
        return host_desc;
    }

    public void setHost_desc(String host_desc) {
        this.host_desc = host_desc;
    }

    public BigDecimal getHostID() {
        return hostID;
    }

    public void setHostID(BigDecimal hostID) {
        this.hostID = hostID;
    }

    @Override
    public String toString() {
        return "ShortNum{" + "host_name=" + host_name + ", short_num=" + short_num + ", isActive=" + isActive + ", incomingPrice=" + incomingPrice + ", outgoingPrice=" + outgoingPrice + ", shortNumAlias=" + shortNumAlias + ", host_desc=" + host_desc + ", hostID=" + hostID + '}';
    }

}
