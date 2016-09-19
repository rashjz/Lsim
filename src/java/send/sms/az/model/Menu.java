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
public class Menu extends BaseModel{
 
    private String menu_name;
    private String parent_name;
    private String right_name;
    private String icon_name;
 

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getRight_name() {
        return right_name;
    }

    public void setRight_name(String right_name) {
        this.right_name = right_name;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + getId() + ", menu_name=" + menu_name + ", parent_name=" + parent_name + ", right_name=" + right_name + ", icon_name=" + icon_name + '}';
    }
    
    
}
