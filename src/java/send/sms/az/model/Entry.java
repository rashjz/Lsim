/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

/**
 *
 * @author Mobby
 */
public class Entry {

    private String rowix;
    private String value;

    public Entry() {
    }

    public Entry(String rowix, String value) {
        this.rowix = rowix;
        this.value = value;
    }

    public String getRowix() {
        return rowix;
    }

    public void setRowix(String rowix) {
        this.rowix = rowix;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) {
            value = "";
        }
        return value;
    }

}
