/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import send.sms.az.model.Transaction;

/**
 *
 * @author rasha_000
 */
public class TransModel {

    private BigDecimal start;
    private BigDecimal end;
    private List<Transaction> list;
    private BigDecimal rowCount;
    private Date fromdate;
    private Date todate;
    private String[] selectedTypes;
    private String[] selectedShortNum = new String[0];
    private Transaction search;
    private String sortField;
    private String sortOrder;
    private String expSQL;

    public TransModel() {

        Calendar c = Calendar.getInstance();
        start = BigDecimal.ZERO;
        end = BigDecimal.ZERO;
        list = new ArrayList<>();
        rowCount = BigDecimal.ZERO;
        search = new Transaction();
        fromdate = new Date();
        todate = new Date();
        c.setTime(todate);
        c.add(Calendar.DATE, 1);//a day after
        todate = c.getTime();
        fromdate.setMinutes(0);
        fromdate.setSeconds(0);
        fromdate.setHours(0);
        todate.setMinutes(0);
        todate.setSeconds(0);
        todate.setHours(0);
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

    public List<Transaction> getList() {
        return list;
    }

    public void setList(List<Transaction> list) {
        this.list = list;
    }

    public BigDecimal getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigDecimal rowCount) {
        this.rowCount = rowCount;
    }

    public Transaction getSearch() {
        return search;
    }

    public void setSearch(Transaction search) {
        this.search = search;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public String[] getSelectedTypes() {
        return selectedTypes;
    }

    public void setSelectedTypes(String[] selectedTypes) {
        this.selectedTypes = selectedTypes;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public String[] getSelectedShortNum() {
        return selectedShortNum;
    }

    public void setSelectedShortNum(String[] selectedShortNum) {
        this.selectedShortNum = selectedShortNum;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getExpSQL() {
        return expSQL;
    }

    public void setExpSQL(String expSQL) {
        this.expSQL = expSQL;
    }

    @Override
    public String toString() {
        return "TransModel{" + " fromdate=" + fromdate + ", todate=" + todate + ", search=" + search.toString() + '}';
    }

}
