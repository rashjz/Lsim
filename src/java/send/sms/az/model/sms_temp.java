/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

/**
 *
 * @author rasha_000
 */
public class sms_temp extends BaseModel {

    String tpl_text;

    public String getTpl_text() {
        return tpl_text;
    }

    public void setTpl_text(String tpl_text) {
        this.tpl_text = tpl_text;
    }

    @Override
    public String toString() {
        return "sms_temp{ id " + getId() + "   tpl_text=" + tpl_text + '}';
    }

}
