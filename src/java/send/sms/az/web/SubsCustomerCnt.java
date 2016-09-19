/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.CustomerSubsDao;
import send.sms.az.model.ext.SubCustomerModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "subCustCnt")
@ViewScoped
public class SubsCustomerCnt extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubsCustomerCnt.class.getName());
    private SubCustomerModel model = new SubCustomerModel();
    private CustomerSubsDao dao;
    List<String> subNames;

    @PostConstruct
    public void init() {// 
        dao = new CustomerSubsDao(); 
        subNames=dao.subsNameList();
        search();
    }

    public void search() {
        LOG.info("search " + model.toString());
//        model = dao.subsCustomerList(model);
    }

    public SubCustomerModel getModel() {
        return model;
    }

    public void setModel(SubCustomerModel model) {
        this.model = model;
    }

    public List<String> getSubNames() {
        return subNames;
    }

    public void setSubNames(List<String> subNames) {
        this.subNames = subNames;
    }

}
