/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Mobby
 */
public class ColumnModel implements Serializable {

    private String col_id;
    private String header;
    private String property;

    public ColumnModel() {
    
    }

    public ColumnModel(String header, String property) {
        this.header = header;
        this.property = property;
    }

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "ColumnModel{" + "col_id=" + col_id + ", header=" + header + ", property=" + property + '}';
    }
    
}
