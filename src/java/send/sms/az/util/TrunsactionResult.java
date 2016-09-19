/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import send.sms.az.model.Result;
import send.sms.az.model.ext.TransModel;

/**
 *
 * @author Mobby
 */
public class TrunsactionResult {

    public static List<Result> getresultList(TransModel model) {
        List<Result> listResult = new ArrayList<>();
        Map typeMap = new HashMap();
        if (model.getSelectedTypes() != null) {
            for (String mas : model.getSelectedTypes()) {
                typeMap.put(mas, mas);
            }
        }

        if (typeMap.get("1") != null) {//income
            listResult.add(new Result("Processed", "Processed"));
            listResult.add(new Result("Not processed", "Not processed"));
            listResult.add(new Result("Error", "Error"));
        }
        if (typeMap.get("2") != null) {//output
            listResult.add(new Result("Queued", "Queued"));
//            listResult.add(new Result("Queued", "Queued"));
            listResult.add(new Result("Sent", "Sent"));
            listResult.add(new Result("Error", "Income Error"));
            listResult.add(new Result("Black list", "Black list"));
            listResult.add(new Result("Unknown", "Unknown"));
            listResult.add(new Result("Rejected", "Rejected"));
            listResult.add(new Result("Expired", "Expired"));
        }
        if (typeMap.get("3") != null) {//charge
            listResult.add(new Result("0", "Success"));
            listResult.add(new Result("1", "Invalid MSISDN"));
            listResult.add(new Result("2", "Insufficient balance"));
            listResult.add(new Result("3", "Wrong tariff"));
            listResult.add(new Result("4", "In black list"));
            listResult.add(new Result("5", "Not active")); 
            listResult.add(new Result("6", "Ported out"));
            listResult.add(new Result("7", "Operator error")); 
            listResult.add(new Result("9", "Wrong login"));
            listResult.add(new Result("10", "Wrong service id"));
            listResult.add(new Result("11", "Duplicate request"));
            listResult.add(new Result("12", "Invalid tariff")); 
            listResult.add(new Result("13", "Access denied"));
            listResult.add(new Result("100", "Parameter not set"));
            listResult.add(new Result("100", "Parameter not set"));
            listResult.add(new Result("500", "Local error"));
            listResult.add(new Result("501", "Local async error"));
        }
        return listResult;
    }

    public static void main(String[] args) {
        TransModel model = new TransModel();
        String[] ses = {"1", "2"};
        model.setSelectedTypes(ses);
        List<Result> rl = getresultList(model);
        for (Result rl1 : rl) {
            System.out.println(rl1.getResult_name());
        }
    }
}
