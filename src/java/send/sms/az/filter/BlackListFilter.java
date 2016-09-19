/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import send.sms.az.model.ext.BlackListModel;

/**
 *
 * @author rasha_000
 */
public class BlackListFilter {

    private static final Logger logger = Logger.getLogger(BlackListFilter.class.getName());

    public static void filterBL(Map<String, Object> filters, BlackListModel model) {
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            if (!filter.getValue().equals("")) {
                logger.info(filter.getKey() + "  " + filter.getValue());
                switch (filter.getKey()) {
                    case "msisdn":
                        model.setMsisdn((String) filter.getValue());
//                        logger.info("msisdn " + filter.getValue());
                        break;
                    case "fdate":
                        String sdate = (String) filter.getValue(); 
                        if (sdate.length() == 10) {//aa.bb.cccc tarix
                            try {
                                DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
                                Date date = format.parse(sdate);
                                logger.info("date is " + date);
//                                detailSearchModel.setCreateDate(date); 
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
//                                    detailSearchModel.setCreateDate(filter.getValue());
                        logger.info("protocol.number " + filter.getValue());
                        break;
                }
            }
        }
    }
}
