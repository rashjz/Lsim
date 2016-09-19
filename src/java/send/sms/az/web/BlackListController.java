/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.ComponentUtils;
import send.sms.az.dao.BlackListDao;
import send.sms.az.filter.BlackListFilter;
import send.sms.az.model.BlackList;
import send.sms.az.model.Entry;
import send.sms.az.model.ext.BlackListModel;
import send.sms.az.util.ExportXLS;
import send.sms.az.util.FacesUtil;
import send.sms.az.util.UploadBlackList;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "blackLCnt")
@ViewScoped
public class BlackListController implements Serializable {

    private static final Logger logger = Logger.getLogger(BlackListController.class.getName());

    private LazyDataModel<BlackList> model;
    private BlackList bl = new BlackList();
    private BlackListModel blackListModel = new BlackListModel();
    private UploadedFile uploadedFile;
    private JasperPrint jasperPrint;
    private BlackListDao blackListDao;
    private List<Map<String, Entry>> mttbl = new ArrayList<>();

    public BlackListController() {
        blackListDao = new BlackListDao();
    }

    public void newBL() {
        bl = new BlackList();
    }

    @PostConstruct
    public void init() {//pagination for DataTable
        logger.info("init() invoked");
        try {
            logger.info("here1");
            this.model = new LazyDataModel<BlackList>() {
                private static final long serialVersionUID = 1L;

                @Override
                public List<BlackList> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    logger.info("first " + first + " pageSize " + pageSize);
                    blackListModel.setStart(new BigDecimal(first));
                    blackListModel.setEnd(new BigDecimal(first + pageSize));
                    BlackListFilter.filterBL(filters, blackListModel);
                    blackListModel = blackListDao.blackList(blackListModel);
                    model.setRowCount((int) blackListModel.getRowCount().intValue());
                    logger.info("count " + model.getRowCount());
                    return blackListModel.getList();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("exception occured " + e);
        }
    }

    public void moveDown() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        logger.info(FacesUtil.findComponent(facesContext.getViewRoot(), "blackListTBL").getClientId());
        DataTable table = (DataTable) FacesUtil.findComponent(facesContext.getViewRoot(), "blackListTBL");
        logger.info(table);
        LazyDataModel<BlackList> cl = (LazyDataModel<BlackList>) table.getValue();
        String p_code = "" + bl.getId();
        int count = 0;
        for (BlackList c : blackListModel.getList()) {
            count++;
            if (c.getId().compareTo(bl.getId()) == 0) {
//                logger.info(c.getId() + " " + bl.getId() + " " + count);
                if (count == model.getPageSize()) {
                    bl = blackListModel.getList().get(1);///sonuncudusa 1cini secsin
                } else {
                    bl = blackListModel.getList().get(count);
                }
                break;
            } else {
//                logger.info(c.getId() + " ***** " + bl.getId() + " " + count + " " + model.getRowCount());
            }
        }
    }

    public void moveUp() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        logger.info(FacesUtil.findComponent(facesContext.getViewRoot(), "blackListTBL").getClientId());
        DataTable table = (DataTable) FacesUtil.findComponent(facesContext.getViewRoot(), "blackListTBL");
        logger.info(table);
        LazyDataModel<BlackList> cl = (LazyDataModel<BlackList>) table.getValue();
        String p_code = "" + bl.getId();
        int count = 0;
        for (BlackList c : blackListModel.getList()) {
            count++;
            if (c.getId().compareTo(bl.getId()) == 0) {
                logger.info(c.getId() + " " + bl.getId() + " " + count);
                if (count == 1) {
                    bl = blackListModel.getList().get(model.getPageSize() - 1);/// 1cidise sonuncu secsin
                } else {
                    logger.info(count);
                    bl = blackListModel.getList().get(count);
                }
                break;
            } else {
                logger.info(c.getId() + " ***** " + bl.getId() + " " + count + " " + model.getPageSize());
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        logger.info("handle file upload");
        uploadedFile = event.getFile();
    }

    public void searchBlackList() {
        logger.info("searchBlackList" + blackListModel.getFdate() + "   ++  " + blackListModel.getMsisdn());
    }

    public void save() {
        logger.info("save invoked" + bl);
        if (bl.getId().compareTo(BigDecimal.ZERO) == 0) {
            bl = blackListDao.insBlackList(bl);
        } else {
            //update
            blackListDao.updateBlackList(bl);
        }
    }

    public void deleteBL() {
        logger.info("deleteBL");
        if (bl != null && bl.getId().compareTo(BigDecimal.ZERO) != 0) {
            blackListDao.deleteBlackList(bl);
        }
    }

    public void saveUploadList() {
        logger.info("saveUploadList");
        try {
            if (uploadedFile != null & (uploadedFile.getFileName().endsWith("xls") | uploadedFile.getFileName().endsWith("xlsx"))) {
                List<String> blstr = UploadBlackList.readExcelData(uploadedFile);
                for (String blt : blstr) {
                    BlackList bl = new BlackList(blt, "1", "1");
                    blackListDao.insBlackList(bl);
                }
                logger.info("//xls file " + mttbl.size());
            }
        } catch (Exception e) {
        }
        blackListModel.setUpload(false);
    }

    public void upload() {
        logger.info("  upload" + bl);
        blackListModel.setUpload(true);
    }

    public void cancelUpload() {
        logger.info("  cancelUpload");
        blackListModel.setUpload(false);
    }

    public void listener() {
        logger.info(bl.getId().toString());
    }

    public BlackList getBl() {
        return bl;
    }

    public void setBl(BlackList bl) {
        this.bl = bl;
    }

    public LazyDataModel<BlackList> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<BlackList> model) {
        this.model = model;
    }

    public BlackListModel getBlackListModel() {
        return blackListModel;
    }

    public void setBlackListModel(BlackListModel blackListModel) {
        this.blackListModel = blackListModel;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void expXLSX() {
        try {
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jreport/testList.jasper");
            ExportXLS.expXLSX(blackListModel.getExpSQL(), reportPath, "testList");//blackList
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getRowKey(BlackList bl) {
        return bl.getId();
    }
}
