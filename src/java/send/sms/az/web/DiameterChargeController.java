/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import send.sms.az.dao.DiameterChargeDao;
import send.sms.az.model.DiameterCharge;
import send.sms.az.model.ext.DiameterChargeModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "diameterCnt")
@ViewScoped
public class DiameterChargeController extends UserDetails implements Serializable {

    private static final Logger logger = Logger.getLogger(DiameterChargeController.class.getName());
    private LazyDataModel<DiameterCharge> model;
    private DiameterChargeModel chargeModel = new DiameterChargeModel();
    private DiameterChargeDao chargeDao;

    public DiameterChargeController() {
        chargeDao = new DiameterChargeDao();
    }

    public void searchDiameterCharge() {
        logger.info("msisdn " + chargeModel.getMsisdn() + "   getCodeID " + chargeModel.getCodeID() + " getPackID " + chargeModel.getPackID() + "  getStartDate " + chargeModel.getStartDate());

        this.model = new LazyDataModel<DiameterCharge>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<DiameterCharge> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                logger.info("first " + first + " pageSize " + pageSize);
                chargeModel.setStart(new BigDecimal(first));
                chargeModel.setEnd(new BigDecimal(first + pageSize));
//                        SortTransaction.setdetailsModel(chargeModel, sortField, sortOrder);
                chargeModel = chargeDao.diameterChargeList(chargeModel);
                model.setRowCount((int) chargeModel.getRowCount().intValue());
                logger.info("count " + model.getRowCount() + " sortField : " + sortField + " sortOrder : " + sortOrder);
                return chargeModel.getDiameterList();

            }
        };

    }

    public LazyDataModel<DiameterCharge> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<DiameterCharge> model) {
        this.model = model;
    }

    public DiameterChargeModel getChargeModel() {
        return chargeModel;
    }

    public void setChargeModel(DiameterChargeModel chargeModel) {
        this.chargeModel = chargeModel;
    }

    public void expXLSX() {
//        try {
//            logger.info("*** " + tranMod.getExpSQL());
//            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jreport/transList.jasper");
//            ExportXLS.expXLSX(tranMod.getExpSQL(), reportPath, "transactionList");
//        } catch (Exception e) {
//            e.printStackTrace();
//            userLogger.setType(LogTypes.TREXCELEXP.toString());
//            userLogger.setDescription(e.getMessage());
//            userLogger.setUserID(loginCnt.getUser().getUser_id());
//            UserLogDao.insertUserLog(userLogger);
//            logger.info("exception occured " + e);
//        }
    }

}
