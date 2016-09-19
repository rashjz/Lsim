/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mobby
 */
public class ScheduledModel {

    private Date fromDate;
    private Date toDate;
    private List<ScheduledSMS> scheduledList;
    boolean search = false;

    public ScheduledModel() {
        scheduledList = new ArrayList<>();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<ScheduledSMS> getScheduledList() {
        return scheduledList;
    }

    public void setScheduledList(List<ScheduledSMS> scheduledList) {
        this.scheduledList = scheduledList;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

}
