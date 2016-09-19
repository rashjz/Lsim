/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import send.sms.az.dao.AddressBookDao;
import send.sms.az.dao.BulkSMSDao;
import send.sms.az.dao.DiameterChargeDao;
import send.sms.az.dao.DiameterTarifDao;
import send.sms.az.dao.ShortNumberDao;
import send.sms.az.dao.TransactionDao;
import send.sms.az.dao.UserDao;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Host;

/**
 *
 * @author Rashad Javadov
 */
@ManagedBean(name = "baseCnt")
@ApplicationScoped
public class BaseController implements Serializable {

    private List<ComboModel> sendtimeList = BulkSMSDao.sendTimeList();
    private List<ComboModel> shortNumbers = BulkSMSDao.shortNumList();
    private List<ComboModel> colNames = AddressBookDao.getTempleNames();
    private List<ComboModel> companyList = UserDao.getCompanyList();
    private List<Host> hostList = TransactionDao.hostList();
    private List<String> shortNumList = TransactionDao.shortNumList();
    private List<String> operatorList = TransactionDao.operatorList();
    private List<ComboModel> mobOperatorList = DiameterTarifDao.mobOperatorList();
    private List<String> clientList = TransactionDao.clientList();
    private List<ComboModel> tarifPackss = DiameterChargeDao.tarifPacksList();
    private List<ComboModel> userList = DiameterTarifDao.userList();
    private List<ComboModel> diameterResponseCodes = DiameterChargeDao.diameterResponseCodeList();
    private List<String> opShortNumList = ShortNumberDao.getOPShortNumberList();
    private List<ComboModel> detailedUsers = UserDao.userListDetailed();
    private List<ComboModel> hostValList = ShortNumberDao.getHostList();

    public List<ComboModel> getShortNumbers() {
        return shortNumbers;
    }

    public List<ComboModel> getSendtimeList() {
        return sendtimeList;
    }

    public List<ComboModel> getColNames() {
        return colNames;
    }

    public List<Host> getHostList() {
        return hostList;
    }

    public List<String> getShortNumList() {
        return shortNumList;
    }

    public List<String> getOperatorList() {
        return operatorList;
    }

    public List<String> getClientList() {
        return clientList;
    }

    public List<ComboModel> getCompanyList() {
        return companyList;
    }

    public List<ComboModel> getTarifPackss() {
        return tarifPackss;
    }

    public List<ComboModel> getDiameterResponseCodes() {
        return diameterResponseCodes;
    }

    public List<ComboModel> getMobOperatorList() {
        return mobOperatorList;
    }

    public List<ComboModel> getUserList() {
        return userList;
    }

    public List<String> getOpShortNumList() {
        return opShortNumList;
    }

    public List<ComboModel> getDetailedUsers() {
        return detailedUsers;
    }

    public List<ComboModel> getHostValList() {
        return hostValList;
    }

}
