/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

/**
 *
 * @author rasha_000
 */
public class SQLquery {

    public static String roleList = "select * from ui_roles";

    public static String rightsList = "select * from ui_rights";
    
     public static String getRight = "select * from ui_rights where right_id = ? ";
}
