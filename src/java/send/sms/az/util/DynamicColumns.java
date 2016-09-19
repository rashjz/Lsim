/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import send.sms.az.model.ColumnModel;
import send.sms.az.model.Entry;

/**
 *
 * @author rasha_000
 */
public class DynamicColumns {

    public static List<ColumnModel> createmodel(List<Map<String, Entry>> mttbl) {
        List<ColumnModel> columns = new ArrayList<>();
        int a = 0;
        for (Map<String, Entry> map : mttbl) {
            if (map.size() > a) {
                a = map.size();
            }
        }
        for (int i = 0; i < a; i++) {
            columns.add(new ColumnModel("sutun " + i, i + ""));
        }
        return columns;
    }
}
