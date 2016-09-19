/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web.ext;

import javax.faces.bean.ManagedProperty;
import send.sms.az.model.User;
import send.sms.az.web.BulkSMSController;

/**
 *
 * @author rasha_000
 */
public class BulkSMSDetail extends UserDetails {

    @ManagedProperty(value = "#{bulksmsCnt}")
    public BulkSMSController bulkCnt;

    public BulkSMSController getBulkCnt() {
        return bulkCnt;
    }

    public void setBulkCnt(BulkSMSController bulkCnt) {
        this.bulkCnt = bulkCnt;
    }
}
