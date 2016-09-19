package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import send.sms.az.dao.BulkSMSDao;
import send.sms.az.dao.UserLogDao;
import send.sms.az.model.Books;
import send.sms.az.model.BulkSMS;
import send.sms.az.model.ext.BulkSMSModel;
import send.sms.az.model.BulkSMSSend;
import send.sms.az.model.ScheduledModel;
import send.sms.az.util.FacesUtil;
import send.sms.az.util.LogTypes;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Rashad Javadov
 */
@ManagedBean(name = "bulksmsCnt")
@SessionScoped
public class BulkSMSController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(BulkSMSController.class.getName());

    private LazyDataModel<BulkSMSSend> model;
    private BulkSMSDao bulkSMSDao;
    private BulkSMSModel bsmsm = new BulkSMSModel();
    private BulkSMSSend send = new BulkSMSSend();
    private BulkSMS bsms = new BulkSMS(); 
    private ScheduledModel scheduledModel = new ScheduledModel();

    public BulkSMSController() {
        bulkSMSDao=new BulkSMSDao();
    }

    @PostConstruct
    public void init() {//pagination for DataTable
        LOG.info("init() invoked");
        try {
            LOG.info("here1");
            this.model = new LazyDataModel<BulkSMSSend>() {
                private static final long serialVersionUID = 1L;

                @Override
                public List<BulkSMSSend> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    LOG.info("first " + first + " pageSize " + pageSize);
                    bsmsm.setStart(new BigDecimal(first));
                    bsmsm.setEnd(new BigDecimal(first + pageSize));
//                    ProtocolFilter.filterProtocolTable(filters, detailSearchModel);
                    bsmsm = bulkSMSDao.BulkSMSList(bsmsm);
                    model.setRowCount((int) bsmsm.getRowCount().intValue());
                    LOG.info("count " + model.getRowCount());
                    return bsmsm.getList();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
             userLogger.setType(LogTypes.BULKLIST.toString());
            userLogger.setDescription(e.getMessage());
            userLogger.setUserID(loginCnt.getUser().getUser_id());
            UserLogDao.insertUserLog(userLogger);
            LOG.info("exception occured " + e);
        }
    }

    public void countCharSize() {
        bsms.setSmsCharSize(bsms.getMessage().toString().getBytes().length);
        if (bsms.getSmsCharSize() == 160) {
            FacesUtil.addErrorMessage("Diqqət SMS sayi 2 ");
        }
    }

    public void tableListener(BigDecimal id) {
        bsms.setAddressBooks(new Books(id, ""));
    }

    public void openTempModal() {
        FacesUtil.createModal(400, 800, "modal/templTbl");
    }

    public void comfirmSMSsend() {
        LOG.info("comfirmSMSsend invoked " + bsms.getSendAt() + " " + bsms.getSendDate());
        try {
            if (bsms.getAddressBooks().getAbid().compareTo(BigDecimal.ZERO) != 0) {//addressbook
                if (bsms.getMessage() != null && !bsms.getMessage().equals("")) {//mesaj
                    boolean nullDate = false;
                    if (bsms.getSendAt() != null && bsms.getSendAt().compareTo(new BigDecimal(2)) == 0) {
                        if (bsms.getSendDate() == null) {
                            nullDate = true;
                            FacesUtil.addErrorMessage("Tarixi daxil edin");
                        }
                    }
                    if (bsms.getSendFrom() != null && bsms.getSendFrom().compareTo(BigDecimal.ZERO) != 0) {
                        if (!nullDate) {
                            FacesUtil.createModal(150, 180, "modal/confirmDetail");
                            //show details of bulk sms
                        } else {
                            FacesUtil.addErrorMessage("Tarixi daxil edin");
                        }
                    } else {
                        FacesUtil.addErrorMessage("Göndərəni daxil edin");
                    }
                } else {
                    FacesUtil.addErrorMessage("Mesajı daxil edin");
                }

            } else {
                FacesUtil.addErrorMessage("Address Book daxil edin");
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtil.addErrorMessage("Xəta baş verdi!");
            bsms = new BulkSMS();
        }

    }

    public void selectTemplate(String msg) {
        LOG.info("selectTemplate  ");
        bsms.setMessage(msg);
        bsms.setSmsCharSize(msg.getBytes().length);
        FacesUtil.closeDialog();
    }

    public void insertNewBulkSMS() {
        bsms.setUserID(loginCnt.getUser().getUser_id());
        try {
            LOG.info("SENDER_ID " + bsms.getSendFrom());
            bulkSMSDao.insertBulkSMS(bsms);
            FacesUtil.addInfoMessage("Uğurla qeydə alındı!");
            bsms = new BulkSMS();
        } catch (Exception e) {
            e.printStackTrace();
            userLogger.setType(LogTypes.INSERTBULK.toString());
            userLogger.setDescription(e.getMessage());
            userLogger.setUserID(loginCnt.getUser().getUser_id());
            UserLogDao.insertUserLog(userLogger);
            FacesUtil.addErrorMessage("Xəta baş verdi!");
            bsms = new BulkSMS();
        }
    }

    public BulkSMSModel getBsmsm() {
        return bsmsm;
    }

    public void setBsmsm(BulkSMSModel bsmsm) {
        this.bsmsm = bsmsm;
    }

    public BulkSMS getBsms() {
        return bsms;
    }

    public void setBsms(BulkSMS bsms) {
        this.bsms = bsms;
    }

    public LazyDataModel<BulkSMSSend> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<BulkSMSSend> model) {
        this.model = model;
    }

    public BulkSMSSend getSend() {
        return send;
    }

    public void setSend(BulkSMSSend send) {
        this.send = send;
    } 
}
