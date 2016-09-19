/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;
 

/**
 *
 * @author rasha_000
 */
public class DelimiterTypes {

    public static String comma = ",\\s*";
    public static String tab ="[\\r\\n;]+"; // "\\n";
    public static String pipe = "\\|s*";

    public static String getType(String code) {
        if (code.equals("1")) {
            return comma;
        } else if (code.equals("2")) {
            return pipe;
        } else {
            return tab;
        }
    }
}
