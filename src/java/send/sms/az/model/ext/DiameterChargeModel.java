/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import send.sms.az.model.DiameterCharge;
import send.sms.az.model.Transaction;

/**
 *
 * @author Mobby
 */
public class DiameterChargeModel {

    //lazymodel 
    private BigDecimal start;
    private BigDecimal end;
    private BigDecimal rowCount;
    //search clause
    private String msisdn;
    private BigDecimal packID;
    private BigDecimal codeID;
    private Date startDate;
    private Date endDate;
    private List<DiameterCharge> diameterList;

    public DiameterChargeModel() {
        msisdn = "";
        start = BigDecimal.ZERO;
        end = BigDecimal.ZERO;
        rowCount = BigDecimal.ZERO;
        packID = BigDecimal.ZERO;
        codeID = new BigDecimal(-1);
        diameterList = new ArrayList<>();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public BigDecimal getPackID() {
        return packID;
    }

    public void setPackID(BigDecimal packID) {
        this.packID = packID;
    }

    public BigDecimal getCodeID() {
        return codeID;
    }

    public void setCodeID(BigDecimal codeID) {
        this.codeID = codeID;
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

    public List<DiameterCharge> getDiameterList() {
        return diameterList;
    }

    public void setDiameterList(List<DiameterCharge> diameterList) {
        this.diameterList = diameterList;
    }

    public BigDecimal getStart() {
        return start;
    }

    public void setStart(BigDecimal start) {
        this.start = start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

    public BigDecimal getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigDecimal rowCount) {
        this.rowCount = rowCount;
    }

}
