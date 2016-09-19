/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import send.sms.az.dao.AddressBookDao;
import send.sms.az.dao.UserLogDao;
import send.sms.az.model.Books;
import send.sms.az.model.ColumnModel;
import send.sms.az.model.Entry;
import send.sms.az.model.Processor;
import send.sms.az.model.SaveData;
import send.sms.az.util.ColumnTitles;
import send.sms.az.util.DynamicColumns;
import send.sms.az.util.FacesUtil;
import send.sms.az.util.LogTypes;
import send.sms.az.util.ReadExcelFileToList;
import send.sms.az.util.ReadTemps;
import send.sms.az.util.UploadComboCheck;
import send.sms.az.web.ext.BulkSMSDetail;

/**
 *
 * @author Rashad Javadov
 */
@ManagedBean(name = "abookCnt")
@SessionScoped
public class ABookController extends BulkSMSDetail implements Serializable {

    private static final Logger LOG = Logger.getLogger(ABookController.class.getName());

    private Books book = new Books();
    private List<ColumnModel> columns = new ArrayList<>();
    private List<Map<String, Entry>> mttbl = new ArrayList<>();
    private Processor processor = new Processor();
    private Map selected;
    private UploadedFile uploadedFile;
    private AddressBookDao addressBookDao;

    public ABookController() {
        addressBookDao = new AddressBookDao();
    }

    public void onRowEdit(RowEditEvent event) {
        LOG.info("" + ((Books) event.getObject()).getAbid() + "  " + ((Books) event.getObject()).getName());
        FacesMessage msg = new FacesMessage("Car Edited", "" + ((Books) event.getObject()).getAbid() + "  " + ((Books) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
      public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Books) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String toAddrBookPage() {
        processor = new Processor();
        return "/addressBook/addBookIndex.xhtml?faces-redirect=true";
    }

    public void handleFileUpload(FileUploadEvent event) {
        LOG.info("handle file upload");
        uploadedFile = event.getFile();
    }

    public void activeUpload() {
        LOG.info("activeUpload");
        processor = new Processor();
        processor.setUploadForm(false);

    }

    public void bulkSMSOK() {
        LOG.info("bulkSMSOK");
        FacesUtil.closeDialog();
    }

    public void opentAddressBooks() {
        LOG.info("selectAddressBook invoked");
        processor.setNote("");
        activeUpload();
        processor.setBulksms(true);
        FacesUtil.createModal(300, 900, "/addressBook/ABDetails");
    }

    public void tableListener(BigDecimal id) {
        LOG.info("tableListener " + id);

        if (processor.isBulksms()) {
            LOG.info("is bulk");
            processor.setNote("Kitabça Uğurla seçildi");
//            FacesUtil.addInfoMessage("Address Book ugurla secildi");
        } else {
            LOG.info("is address book ");
            columns = addressBookDao.getColNamesListAsPlaceholders(id);
            mttbl = addressBookDao.GETAddBook(id, loginCnt.getUser().getUser_id());
            book.setAbid(id);

            processor.setEditData(true);
            processor.getSaveData().setUpdate(true);
            processor.setAbookRender(false);
            processor.setEntryRender(true);
            processor.setTypeComboRender(true);
        }
    }

    public void sendBULKfromAddr(BigDecimal id) {
        LOG.info("sendBULKfromAddr " + id);
        processor.setNote("Kitabça Uğurla əlavə edildi ");
        bulkCnt.getBsms().setAddressBooks(new Books(id, ""));//setting abid to bulk sms controller 
        bulkCnt.getBsms().setNumberCount("" + addressBookDao.selectQuery(id, BigDecimal.ZERO));
        LOG.info("selected getAddressBooks " + bulkCnt.getBsms().getAddressBooks());
    }

    public void saveAddressBook() {
        LOG.info("saveAddressBook");
        try {
            BigDecimal newabid;
            LOG.info("abookName " + processor.getNewABookName() + " comboID " + processor.getTitleID() + " saveRadioType " + processor.getSaveRadioType() + " is hidden " + processor.isBulksms());
            if (UploadComboCheck.checkActionType(processor.getSaveRadioType()) && UploadComboCheck.checkColumns(columns)) {//en azi bir suttun secilmelidir 

                if (processor.getSaveRadioType().equals("0")) {
                    //create new book  
                    if (processor.isBulksms()) {
                        newabid = addressBookDao.insertAddBook(processor.getNewABookName(), loginCnt.getUser().getUser_id(), BigDecimal.ONE);//ishidden
                        processor.setNote("Kitabça Uğurla əlavə edildi ");
                        bulkCnt.getBsms().setAddressBooks(new Books(newabid, ""));//setting abid to bulk sms controller 
                        bulkCnt.getBsms().setNumberCount("" + addressBookDao.selectQuery(newabid, BigDecimal.ONE));
                        LOG.info("ab id : " + newabid + " number count : " + bulkCnt.getBsms().getNumberCount());
                    } else {
                        newabid = addressBookDao.insertAddBook(processor.getNewABookName(), loginCnt.getUser().getUser_id(), BigDecimal.ZERO);
                    }
                    for (Map<String, Entry> column : mttbl) {//herbir sütun ucun  
                        //eger sehde column adi secilibse o zaman insert etsin
                        //map daxilinde herbir table setirini gotururem ve butun columnlara gore baxiram
                        for (Map.Entry<String, Entry> entrySet : column.entrySet()) {
                            Integer key = Integer.parseInt(entrySet.getKey());
                            Entry value = entrySet.getValue();
//                     
                            if (columns.get(key).getHeader().length() < 3) {//sutun n deyilse
//                            LOG.info(" Rowix[" + value.getRowix() + "] Value[" + value.getValue() + "]" + " key[" + key + "] header[" + columns.get(key).getHeader() + "]");
                                addressBookDao.insertAddBookEntry(newabid, new BigDecimal(columns.get(key).getHeader()), new BigDecimal(value.getRowix()), value.getValue());
                            }
                        }

                    }
                } else {
// add existing
                    LOG.info("addressBook ID " + processor.getTitleID() + " row id " + addressBookDao.getLastRowIX(processor.getTitleID()));
                    BigDecimal rowIX = addressBookDao.getLastRowIX(processor.getTitleID());
                    for (Map<String, Entry> column : mttbl) {//herbir sütun ucun  
                        //eger sehde column adi secilibse o zaman insert etsin
                        //map daxilinde herbir table setirini gotururem ve butun columnlara gore baxiram
                        for (Map.Entry<String, Entry> entrySet : column.entrySet()) {
                            Integer key = Integer.parseInt(entrySet.getKey());
                            Entry value = entrySet.getValue();
//                     
                            if (columns.get(key).getHeader().length() < 3) {//sutun n deyilse
//                            LOG.info(" Rowix[" + value.getRowix() + "] Value[" + value.getValue() + "]" + " key[" + key + "] header[" + columns.get(key).getHeader() + "]");
                                addressBookDao.insertAddBookEntry(processor.getTitleID(), new BigDecimal(columns.get(key).getHeader()), new BigDecimal(value.getRowix()).add(rowIX), value.getValue());
                            }
                        }

                    }
                }
                if (!processor.isBulksms()) {//back to books
                    processor.setAbookRender(true);
                } else {//for modal back to checklist
                    activeUpload();
                }

                processor.setAddNewBook(false);
                processor.setEntryRender(false);
            }
//        processor.setUploadForm(false);

        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e);
            userLogger.setType(LogTypes.ADDBOOK.toString());
            userLogger.setDescription(e.toString());
            userLogger.setUserID(userLogger.getUserID());
            UserLogDao.insertUserLog(userLogger);
        }
    }

    public void columnToSelect() {
        for (ColumnModel column : columns) {
            LOG.info(column.toString());
        }
    }

    public void newEntryData() {
        LOG.info("new data");
        processor.setSaveData(new SaveData());
        processor.getSaveData().setUpdate(false);
    }

    public void deleteEntryData() {
        try {
            LOG.info("delete row data");
            if (processor.getSaveData().getRowix() != null && processor.getSaveData().getRowix().compareTo(BigDecimal.ZERO) != 0) {
                addressBookDao.deleteAddBookEntry(book.getAbid(), processor.getSaveData().getRowix());
                LOG.info("deleted!");
            }
            updateTable();
        } catch (Exception e) {
            userLogger.setType(LogTypes.ABENTRYDELETE.toString());
            userLogger.setDescription(e.toString());
            userLogger.setUserID(userLogger.getUserID());
            UserLogDao.insertUserLog(userLogger);
        }
    }

    public void saveEntryData() {
        try {
            LOG.info("saveEntryData " + mttbl.get(0).entrySet().size());
            if (processor.getSaveData().isUpdate()) {
                //update   
                BigDecimal rowix = processor.getSaveData().getRowix();
//            LOG.in
                if (processor.getSaveData().getMsisdn() != null && !processor.getSaveData().getMsisdn().equals("")) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.MSISDN.getVal()), rowix, processor.getSaveData().getMsisdn());
                }
                if (processor.getSaveData().getName() != null && !processor.getSaveData().getName().equals("")) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.NAME.getVal()), rowix, processor.getSaveData().getName());
                }
                if (processor.getSaveData().getSurname() != null && !processor.getSaveData().getSurname().equals("")) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.SURNAME.getVal()), rowix, processor.getSaveData().getSurname());
                }
                if (processor.getSaveData().getNote() != null && !processor.getSaveData().getNote().equals("")) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_INFO1.getVal()), rowix, processor.getSaveData().getNote());
                }
                if (processor.getSaveData().getBdate() != null && processor.getSaveData().getBdate() != null) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.BIRTH_DATE.getVal()), rowix, processor.getSaveData().getBdate().toString());
                }
                if (processor.getSaveData().getAddDate1() != null) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_DATE1.getVal()), rowix, processor.getSaveData().getAddDate1().toString());
                }
                if (processor.getSaveData().getAddDate2() != null) {
                    addressBookDao.updateAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_DATE2.getVal()), rowix, processor.getSaveData().getAddDate2().toString());
                }
            } else {
                //insert
                LOG.info(book.getAbid() + " misisdn " + processor.getSaveData().getMsisdn());
                BigDecimal rowix = addressBookDao.getLastRowIX(book.getAbid()).add(BigDecimal.ONE);

                if (processor.getSaveData().getMsisdn() != null && !processor.getSaveData().getMsisdn().equals("")) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.MSISDN.getVal()), rowix, processor.getSaveData().getMsisdn());
                }
                if (processor.getSaveData().getName() != null && !processor.getSaveData().getName().equals("")) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.NAME.getVal()), rowix, processor.getSaveData().getName());
                }
                if (processor.getSaveData().getSurname() != null && !processor.getSaveData().getSurname().equals("")) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.SURNAME.getVal()), rowix, processor.getSaveData().getSurname());
                }
                if (processor.getSaveData().getNote() != null && !processor.getSaveData().getNote().equals("")) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_INFO1.getVal()), rowix, processor.getSaveData().getNote());
                }
                if (processor.getSaveData().getBdate() != null) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.BIRTH_DATE.getVal()), rowix, processor.getSaveData().getBdate().toString());
                }
                if (processor.getSaveData().getAddDate1() != null) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_DATE1.getVal()), rowix, processor.getSaveData().getAddDate1().toString());
                }
                if (processor.getSaveData().getAddDate2() != null) {
                    addressBookDao.insertAddBookEntry(book.getAbid(), new BigDecimal(ColumnTitles.ADD_DATE2.getVal()), rowix, processor.getSaveData().getAddDate2().toString());
                }
            }
            processor.setSaveData(new SaveData());
            updateTable();

        } catch (Exception e) {
            userLogger.setType(LogTypes.ABENTRYSAVE.toString());
            userLogger.setDescription(e.toString());
            userLogger.setUserID(userLogger.getUserID());
            UserLogDao.insertUserLog(userLogger);
        }
    }

    public void updateTable() {
        mttbl = addressBookDao.GETAddBook(book.getAbid(), loginCnt.getUser().getUser_id());
        columns = addressBookDao.getColNamesListAsPlaceholders(book.getAbid());
    }

    public void onRowSelect(SelectEvent event) {
        LOG.info("data " + event.getObject().toString());
        Map<String, Entry> m = (HashMap<String, Entry>) event.getObject();
        for (Map.Entry<String, Entry> entrySet : m.entrySet()) {
            String key = entrySet.getKey();
            Entry value = entrySet.getValue();
            LOG.info("key " + key + " " + value.getRowix());
            if (key.equals("MSISDN")) {
                processor.getSaveData().setMsisdn(value.getValue());
            }
            if (key.equals("NAME")) {
                processor.getSaveData().setName(value.getValue());
            }
            if (key.equals("SURNAME")) {
                processor.getSaveData().setSurname(value.getValue());
            }
            if (key.equals("BIRTH_DATE")) {
//                Date date=new StringFormatterş value.getValue()
//                processor.getSaveData().setBdate();
            }
            processor.getSaveData().setRowix(new BigDecimal(value.getRowix()));
            processor.getSaveData().setUpdate(true);
        }
        LOG.info(processor.getSaveData().toString());
    }

    public void listfromTxtArea() throws IOException {
        LOG.info("data " + processor.getManualStr() + " type " + processor.getType());
        mttbl = ReadTemps.textToList(processor.getManualStr(), processor.getType());
        columns = DynamicColumns.createmodel(mttbl);
        processor.setUploadForm(true);
        processor.setAbookRender(false);
        processor.setAddNewBook(true);
        processor.setEntryRender(true);
//            FacesMessage message = new FacesMessage("Uğurla", uploadedFile.getFileName() + abooks.size() + " adlı fayl yükləndi.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void uf2Table() {
        LOG.info("utest invoked");
        try {
            if (uploadedFile != null & (uploadedFile.getFileName().endsWith("xls") | uploadedFile.getFileName().endsWith("xlsx"))) {
                mttbl = ReadExcelFileToList.readExcelData(uploadedFile);
                columns = DynamicColumns.createmodel(mttbl);
                LOG.info("//xls file " + mttbl.size());
            } else {
                LOG.info("file name " + uploadedFile.getFileName() + " processType " + processor.getTxtType());
                mttbl = ReadTemps.textToList(uploadedFile, processor.getTxtType());
                columns = DynamicColumns.createmodel(mttbl);
            }
            processor.setUploadForm(true);
            processor.setAbookRender(false);
            processor.setAddNewBook(true);
            processor.setEntryRender(true);
        } catch (Exception e) {

            FacesUtil.addErrorMessage("Yükləmək üçün excel fayl seçilməyib");
        }
    }

    public void deleteABook(BigDecimal id) {
        LOG.info("deleteABook");
        try {
            addressBookDao.deleteAddrBook(id, BigDecimal.ZERO);
        } catch (Exception e) {
            userLogger.setType(LogTypes.ABDELETE.toString());
            userLogger.setDescription(e.toString());
            userLogger.setUserID(userLogger.getUserID());
            UserLogDao.insertUserLog(userLogger);
        }
    }

    public void goBackBooks() {
        LOG.info("goBack");
        processor.setUploadForm(true);
        processor.setEditData(false);
        processor.setAbookRender(true);
        processor.setAddNewBook(false);
        processor.setEntryRender(false);
    }

    public List<Books> getUserABList() {
        List<Books> userABList = addressBookDao.abookNames(loginCnt.getUser().getUser_id());
        return userABList;
    }

    //getsets
    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<Map<String, Entry>> getMttbl() {
        return mttbl;
    }

    public void setMttbl(List<Map<String, Entry>> mttbl) {
        this.mttbl = mttbl;
    }

    public Map getSelected() {
        return selected;
    }

    public void setSelected(Map selected) {
        this.selected = selected;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

}
