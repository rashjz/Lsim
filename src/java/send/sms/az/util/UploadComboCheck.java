/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.math.BigDecimal;
import java.util.List;
import send.sms.az.model.ColumnModel;
import send.sms.az.model.Processor;

/**
 *
 * @author Mobby
 */
public class UploadComboCheck {

    public static String msisdn = "1";

    public static boolean checkColumns(List<ColumnModel> columns) {
        boolean nonSelected = false;
        if (columns != null && columns.size() > 0) {

            for (ColumnModel column : columns) {
                if (column.getHeader().equals(msisdn)) {
                    nonSelected = true;
                }
            }
            if (!nonSelected) {
                FacesUtil.addErrorMessage("yükləmək üçün ən azı bir sütün seçilməlidir");
            }
        }
        return nonSelected;
    }

    public static boolean checkActionType(String actionType) {

        if (actionType != null && !actionType.equals("")) {
            return true;
        } else {
            FacesUtil.addErrorMessage("Əməliyyat növünü seçin");
            return false;
        }
    }
}
