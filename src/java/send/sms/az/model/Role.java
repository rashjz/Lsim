/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.util.logging.Logger;

/**
 *
 * @author rasha_000
 */
public class Role extends BaseModel {

    private String name;

    public Role() {
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{ id= " + getId() + " name=" + name + '}';
    }

}
