/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import send.sms.az.model.User;

/**
 *
 * @author rasha_000
 */
public class UserModel {

    private BigDecimal start;
    private BigDecimal end;
    private List<User> list;
    private BigDecimal rowCount;

    public UserModel() {
        start = BigDecimal.ZERO;
        end = BigDecimal.ZERO;
        list = new ArrayList<>();
        rowCount = BigDecimal.ZERO;
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

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public BigDecimal getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigDecimal rowCount) {
        this.rowCount = rowCount;
    }
}
