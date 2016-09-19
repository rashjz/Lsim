/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rashad Javadov
 */
public class StringUtil {

    public static Map<String, String> strToMap(String cols) {
        Map<String, String> colmap = new HashMap<String, String>();
        String[] arr = cols.trim().split(",");
        int count = 1;
        for (String arr1 : arr) {
            String data = arr1.substring(1, arr1.length() - 1);
            colmap.put(count+"", data);
            count++;
        }
        return colmap;
    }

    public static void main(String[] args) {
        for (Map.Entry<String, String> entrySet : strToMap("'MSISDN','NAME'").entrySet()) {
            Object key = entrySet.getKey();
            Object value = entrySet.getValue();
            System.out.println(key+""+value);
        }
    }
}
