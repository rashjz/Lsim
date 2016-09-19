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
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import send.sms.az.dao.TransactionDao;
import send.sms.az.dao.UserLogDao;
import send.sms.az.model.Result;
import send.sms.az.model.Transaction;
import send.sms.az.model.ext.TransModel;
import send.sms.az.util.ExportXLS;
import send.sms.az.util.FacesUtil;
import send.sms.az.util.LogTypes;
import send.sms.az.util.SortTransaction;
import send.sms.az.util.TrunsactionResult;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "transCnt")
@ViewScoped
public class TransController extends UserDetails implements Serializable {

    private static final Logger logger = Logger.getLogger(TransController.class.getName());
    private LazyDataModel<Transaction> model;
    private TransactionDao transactionDao;
    private TransModel tranMod = new TransModel();
    private Transaction trans = new Transaction();

    private List<Result> resultList = TransactionDao.resultList();

    public TransController() {
        transactionDao = new TransactionDao();
    }

    public void searchTrans() {
        logger.info("getOperator_name " + tranMod.getSearch().getOperator_name() + " host name " + tranMod.getSearch().getHost_name() + " Client " + tranMod.getSearch().getClient() + "  Result " + tranMod.getSearch().getTrans_result());
//        for (String m : tranMod.getSelectedShortNum()) {
//            LOG.info(m);
//        }
//        long sstart = System.nanoTime();
        if (tranMod.getFromdate() != null && tranMod.getTodate() != null) {
            final int days = (int) ((tranMod.getTodate().getTime() - tranMod.getFromdate().getTime()) / (86400000));//1000 * 60 * 60 * 24
            logger.info("date count " + days);

//                long start = System.nanoTime();
            this.model = new LazyDataModel<Transaction>() {
                private static final long serialVersionUID = 1L;

                @Override
                public List<Transaction> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    if (days < 90) {
                        logger.info("first " + first + " pageSize " + pageSize);
                        tranMod.setStart(new BigDecimal(first));
                        tranMod.setEnd(new BigDecimal(first + pageSize));
                        SortTransaction.setdetailsModel(tranMod, sortField, sortOrder);
                        tranMod = transactionDao.transList(tranMod);
                        model.setRowCount((int) tranMod.getRowCount().intValue());
                        logger.info("count " + model.getRowCount() + " sortField : " + sortField + " sortOrder : " + sortOrder);
                        return tranMod.getList();
                    } else {
                        return tranMod.getList();
                    }
                }
            };
            if (days > 90) {
                FacesUtil.addErrorMessage("Aralıq 90 gündən çox ola bilməz");
            }
//                long time = start - System.nanoTime();
//                long time2 = sstart - System.nanoTime();
//                logger.info("elapse time is " + time + " " + time2);

        }
    }

    public LazyDataModel<Transaction> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Transaction> model) {
        this.model = model;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    public TransModel getTranMod() {
        return tranMod;
    }

    public void setTranMod(TransModel tranMod) {
        this.tranMod = tranMod;
    }

    public List<Result> getResultList() {
        resultList = TrunsactionResult.getresultList(tranMod);
        return resultList;
    }

    public void expXLSX() {
        try {
            logger.info("*** " + tranMod.getExpSQL());
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jreport/transList.jasper");
            ExportXLS.expXLSX(tranMod.getExpSQL(), reportPath, "transactionList");
        } catch (Exception e) {
            e.printStackTrace();
            userLogger.setType(LogTypes.TREXCELEXP.toString());
            userLogger.setDescription(e.getMessage());
            userLogger.setUserID(loginCnt.getUser().getUser_id());
            UserLogDao.insertUserLog(userLogger);
            logger.info("exception occured " + e);
        }
    }

}
