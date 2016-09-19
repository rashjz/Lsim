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
public class DiameterCharge extends BaseModel {

    private String msisdn;
    private Date dateAdded;
    private Date dateProcessed;
    private Date dateSend;
    private String operatorName;
    private String tarifName;
    private BigDecimal tarifPriceNovat;
    private String chargeReason;
    private String clientLogin;
    private int isAsync;
    private String chargeResult;
    private String serviceName;
    private String clientDesc;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTarifName() {
        return tarifName;
    }

    public void setTarifName(String tarifName) {
        this.tarifName = tarifName;
    }

    public BigDecimal getTarifPriceNovat() {
        return tarifPriceNovat;
    }

    public void setTarifPriceNovat(BigDecimal tarifPriceNovat) {
        this.tarifPriceNovat = tarifPriceNovat;
    }

    public String getChargeReason() {
        return chargeReason;
    }

    public void setChargeReason(String chargeReason) {
        this.chargeReason = chargeReason;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public int getIsAsync() {
        return isAsync;
    }

    public void setIsAsync(int isAsync) {
        this.isAsync = isAsync;
    }

    public String getChargeResult() {
        return chargeResult;
    }

    public void setChargeResult(String chargeResult) {
        this.chargeResult = chargeResult;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientDesc() {
        return clientDesc;
    }

    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }

    @Override
    public String toString() {
        return "DiameterCharge{" + "msisdn=" + msisdn + ", dateAdded=" + dateAdded + ", dateProcessed=" + dateProcessed + ", dateSend=" + dateSend + ", operatorName=" + operatorName + ", tarifName=" + tarifName + ", tarifPriceNovat=" + tarifPriceNovat + ", chargeReason=" + chargeReason + ", clientLogin=" + clientLogin + ", isAsync=" + isAsync + ", chargeResult=" + chargeResult + ", serviceName=" + serviceName + ", clientDesc=" + clientDesc + '}';
    }
    

}
