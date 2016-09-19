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
public class HPackHosts extends BaseModel {

    private BigDecimal rec_id;
    private BigDecimal host_id;
    private BigDecimal operator_id;
    private BigDecimal pack_id;
    private String host_name;
    private String op_name;

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getOp_name() {
        return op_name;
    }

    public void setOp_name(String op_name) {
        this.op_name = op_name;
    }

    public BigDecimal getRec_id() {
        return rec_id;
    }

    public void setRec_id(BigDecimal rec_id) {
        this.rec_id = rec_id;
    }

    public BigDecimal getHost_id() {
        return host_id;
    }

    public void setHost_id(BigDecimal host_id) {
        this.host_id = host_id;
    }

    public BigDecimal getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(BigDecimal operator_id) {
        this.operator_id = operator_id;
    }

    public BigDecimal getPack_id() {
        return pack_id;
    }

    public void setPack_id(BigDecimal pack_id) {
        this.pack_id = pack_id;
    }

    @Override
    public String toString() {
        return "HPackHosts{" + "rec_id=" + rec_id + ", host_id=" + host_id + ", operator_id=" + operator_id + ", host_name=" + host_name + ", op_name=" + op_name + '}';
    }

}
