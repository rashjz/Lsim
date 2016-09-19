/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.util.logging.Logger;
import org.primefaces.model.SortOrder;
import send.sms.az.model.ext.TransModel;

/**
 *
 * @author rasha_000
 */
public class SortTransaction {

    private static final Logger logger = Logger.getLogger(SortTransaction.class.getName());

    public static void setdetailsModel(TransModel model, String sortField, SortOrder sortOrder) {
        if (sortField != null && !sortField.equals("")) {
            logger.info(" sortField : " + sortField);
            if (sortField.equals("id")) {
                sortField = "1";
            }
            if (sortField.equals("trans_type")) {
                sortField = "2";
            }
            if (sortField.equals("trans_date")) {
                sortField = "3";
            }
            if (sortField.equals("done_date")) {
                sortField = "4";
            }
            if (sortField.equals("msisdn")) {
                sortField = "5";
            }
            if (sortField.equals("short_num")) {
                sortField = "6";
            }
            if (sortField.equals("trans_desc")) {
                sortField = "7";
            }
            if (sortField.equals("smssize")) {
                sortField = "8";
            }
            if (sortField.equals("trans_result")) {
                sortField = "9";
            }
            if (sortField.equals("operator_name")) {
                sortField = "10";
            }
            if (sortField.equals("host_name")) {
                sortField = "11";
            }
            if (sortField.equals("app_name")) {
                sortField = "12";
            }
            if (sortField.equals("client")) {
                sortField = "13";
            }
            model.setSortField(sortField);
        }
        if (sortOrder.equals(SortOrder.ASCENDING)) {
            logger.info("siralama asc");
            model.setSortOrder("asc");
        } else if (sortOrder.equals(SortOrder.DESCENDING)) {
            model.setSortOrder("desc");
            logger.info("siralama desc");
        }
    }
}
