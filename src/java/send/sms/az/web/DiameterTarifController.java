/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.DiameterTarifDao;
import send.sms.az.model.DTarifs;
import send.sms.az.model.ext.TarifSearchModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "dtarifCnt")
@ViewScoped
public class DiameterTarifController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(DiameterTarifController.class.getName());

    private List<DTarifs> diameterList = new ArrayList<>();
    private TarifSearchModel searchModel = new TarifSearchModel();
    private DiameterTarifDao diameterTarifDao;

    public DiameterTarifController() {
        diameterTarifDao = new DiameterTarifDao(); 
    }

    public void searchDiameterTarif() {
        diameterList = diameterTarifDao.dTarifList(searchModel);
    }

    public List<DTarifs> getDiameterList() {
        return diameterList;
    }

    public void setDiameterList(List<DTarifs> diameterList) {
        this.diameterList = diameterList;
    }

    public TarifSearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(TarifSearchModel searchModel) {
        this.searchModel = searchModel;
    }

}
