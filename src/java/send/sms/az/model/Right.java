/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author rasha_000
 */
public class Right extends BaseModel {
 
    private String right_name;
    private String right_desc;
    private BigDecimal for_everyone;

    public Right() {
        for_everyone = BigDecimal.ZERO;
    }

    public String getRight_name() {
        return right_name;
    }

    public void setRight_name(String right_name) {
        this.right_name = right_name;
    }

    public String getRight_desc() {
        return right_desc;
    }

    public void setRight_desc(String right_desc) {
        this.right_desc = right_desc;
    }

    public BigDecimal getFor_everyone() {
        return for_everyone;
    }

    public void setFor_everyone(BigDecimal for_everyone) {
        this.for_everyone = for_everyone;
    }

    @Override
    public String toString() {
        return "Right{ id=" + getId() + ", right_name=" + right_name + ", right_desc=" + right_desc + ", for_everyone=" + for_everyone + '}';
    }

}
