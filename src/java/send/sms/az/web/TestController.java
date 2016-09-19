/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import send.sms.az.dao.AddressBookDao; 
import send.sms.az.model.ColumnModel;
import send.sms.az.model.Entry;

/**
 *
 * @author TEST
 */
@ManagedBean(name = "dtColumnsView")
@SessionScoped
public class TestController implements Serializable {

    private static final Logger LOG = Logger.getLogger(TestController.class.getName());

    private List<ColumnModel> columns = new ArrayList<>();
    private List<Map<String, Entry>> mttbl = new ArrayList<>();
    private Map selected;

    public TestController() {
        columns =new AddressBookDao().getColNamesListAsPlaceholders(new BigDecimal(103));
        mttbl = new AddressBookDao().GETAddBook(new BigDecimal(103), new BigDecimal(82));
//        Map<String, String> my = new HashMap<>();
//        my.put("a", "1");
//        my.put("b", "1");
//        my.put("c", "1");
//        mttbl.add(my);
//        Map<String, String> my2 = new HashMap<>();
//        my2.put("a", "2");
//        my2.put("b", "2");
//        my2.put("c", "2");
//        mttbl.add(my2);
//        Map<String, String> my3 = new HashMap<>();
//        my3.put("a", "3");
//        my3.put("b", "3");
//        my3.put("c", "3");
//        mttbl.add(my3);
//        LOG.info("size is " + mttbl.get(0).size());

//        columns.add(new ColumnModel("Sutun 1", "a"));
//        columns.add(new ColumnModel("Sutun 2", "b"));
//        columns.add(new ColumnModel("Sutun 3", "c"));
    }

    public void onRowSelect(SelectEvent event) {
        LOG.info("data " + event.getObject().toString());
        Map<String, Entry> m = (HashMap<String, Entry>) event.getObject();
        for (Map.Entry<String, Entry> entrySet : m.entrySet()) {
            String key = entrySet.getKey();
            Entry value = entrySet.getValue();
            LOG.info("key " + key + " " + value.getRowix());
        }
    }

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

}
