/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author Mobby
 */
public class Books {

    private BigDecimal abid;
    private String name = "";

    public Books(BigDecimal id, String name) {
        this.abid = id;
        this.name = name;
    }

    public Books() {
        abid = BigDecimal.ZERO;
    }

    public BigDecimal getAbid() {
        return abid;
    }

    public void setAbid(BigDecimal abid) {
        this.abid = abid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Books{" + "abid=" + abid + ", name=" + name + '}';
    }

}
