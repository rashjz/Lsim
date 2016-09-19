/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import send.sms.az.model.BlackList;

/**
 *
 * @author rasha_000
 */
public class BlackListModel {

    private BigDecimal start;
    private BigDecimal end;
    private List<BlackList> list;
    private BigDecimal rowCount;
    private String msisdn;
    private Date fdate;
    private boolean upload;
    private String expSQL;

    public BlackListModel() {
        msisdn = "";
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

    public List<BlackList> getList() {
        return list;
    }

    public void setList(List<BlackList> list) {
        this.list = list;
    }

    public BigDecimal getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigDecimal rowCount) {
        this.rowCount = rowCount;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public String getExpSQL() {
        return expSQL;
    }

    public void setExpSQL(String expSQL) {
        this.expSQL = expSQL;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

}
