/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author Rashad Javadov
 */
public class ComboModel extends BaseModel {

    private BigDecimal item_value;
    private String item_name;

    public ComboModel() {
    }

    public BigDecimal getItem_value() {
        return item_value;
    }

    public void setItem_value(BigDecimal item_value) {
        this.item_value = item_value;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return "ComboModel{" + "item_value=" + item_value + ", item_name=" + item_name + '}';
    }

}
