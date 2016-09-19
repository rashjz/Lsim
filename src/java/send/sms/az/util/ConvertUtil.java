/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rasha_000
 */
public class ConvertUtil {

    public static String dateToString(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    public static Date dayBeforeDate(Date dt, int days) {//not in use  
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, days); //days may me minus and plus 
        dt = c.getTime();
        return c.getTime();
    }

    public static String dateToString(Date date, String format) {
        Format formatter = new SimpleDateFormat(format);
        String s = formatter.format(date.getTime());
        return s;
    }

    public static void main(String[] args) {
        System.out.println(dateToString(new Date(), "dd/MM/YYYY"));
    }
}
