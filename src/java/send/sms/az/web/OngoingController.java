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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import send.sms.az.dao.OngoingSMSDao;
import send.sms.az.model.ext.OngoingModel;
import send.sms.az.model.Ongoing_SMS;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "ongoingCnt")
@ViewScoped
public class OngoingController implements Serializable {

    private static final Logger LOG = Logger.getLogger(OngoingController.class.getName());
    private LazyDataModel<Ongoing_SMS> model;
    private OngoingSMSDao ongoingSMSDao;
    private List<Ongoing_SMS> balanceList;
    private Ongoing_SMS osms = new Ongoing_SMS();
    private OngoingModel bsmsm = new OngoingModel();

    public OngoingController() {
        ongoingSMSDao = new OngoingSMSDao();
    }

    @PostConstruct
    public void init() {//pagination for DataTable
        LOG.info("init() invoked");
        try {
            LOG.info("here1");
            this.model = new LazyDataModel<Ongoing_SMS>() {
                private static final long serialVersionUID = 1L;

                @Override
                public List<Ongoing_SMS> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    LOG.info("first " + first + " pageSize " + pageSize);
                    bsmsm.setStart(new BigDecimal(first));
                    bsmsm.setEnd(new BigDecimal(first + pageSize));
//                    ProtocolFilter.filterProtocolTable(filters, detailSearchModel);
                    bsmsm = ongoingSMSDao.OngoingList(bsmsm);
                    model.setRowCount((int) bsmsm.getRowCount().intValue());
                    LOG.info("count " + model.getRowCount());
                    return bsmsm.getList();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("exception occured " + e);
        }
    }

    public void listener() {
        LOG.info(osms.getId().toString());
    }

    public void save() {
        LOG.info("save invoked" + osms);
        if (osms.getId().compareTo(BigDecimal.ZERO) == 0) {
            //insert       
        } else {
            //update 
        }
    }

    public Ongoing_SMS getOsms() {
        return osms;
    }

    public void setOsms(Ongoing_SMS osms) {
        this.osms = osms;
    }

    public LazyDataModel<Ongoing_SMS> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Ongoing_SMS> model) {
        this.model = model;
    }

}
