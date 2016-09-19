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
public class Host extends BaseModel{
    private String host_item;
    private String host_name;

    public String getHost_item() {
        return host_item;
    }

    public void setHost_item(String host_item) {
        this.host_item = host_item;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }
    
    
}
