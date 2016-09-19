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
public class IncomingSMS extends BaseModel {

    private BigDecimal msgID;
    private String hostname;
    private String dest_address;
    private String message;
    private String source_addres;

    public BigDecimal getMsgID() {
        return msgID;
    }

    public void setMsgID(BigDecimal msgID) {
        this.msgID = msgID;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDest_address() {
        return dest_address;
    }

    public void setDest_address(String dest_address) {
        this.dest_address = dest_address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource_addres() {
        return source_addres;
    }

    public void setSource_addres(String source_addres) {
        this.source_addres = source_addres;
    }

    @Override
    public String toString() {
        return "IncomingSMS{" + "msgID=" + msgID + ", hostname=" + hostname + ", dest_address=" + dest_address + ", message=" + message + ", source_addres=" + source_addres + '}';
    }

}
