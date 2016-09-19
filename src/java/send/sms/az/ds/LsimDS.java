/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.ds;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mobby
 */
public  abstract class LsimDS {
     public abstract Connection connect() throws SQLException;
}
