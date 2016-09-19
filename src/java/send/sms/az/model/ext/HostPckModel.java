/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model.ext;

/**
 *
 * @author rasha_000
 */
public  class HostPckModel {

    private boolean general;
    private boolean table;
    private boolean packList;
    private boolean packEdit;
    private boolean hostEdit;
//   private boolean packList ;

    public HostPckModel() {
    }

    public HostPckModel(String action) {
        if (action.equals("packEdit")) {
            table = false;
            general = true;
            packEdit = true;
            packList = false;
            hostEdit = false;
        } else if (action.equals("hostEdit")) {
            table = false;
            general = false;
            packEdit = false;
            packList = false;
            hostEdit = true;
        } else if (action.equals("hostList")) {
            table = true;
            general = false;
            packEdit = false;
            packList = false;
            hostEdit = false;
        } else if (action.equals("hostView")) {
            table = false;
            general = true;
            packEdit = false;
            packList = true;
            hostEdit = false;
        }
    }

    public boolean isGeneral() {
        return general;
    }

    public void setGeneral(boolean general) {
        this.general = general;
    }

    public boolean isTable() {
        return table;
    }

    public void setTable(boolean table) {
        this.table = table;
    }

    public boolean isPackList() {
        return packList;
    }

    public void setPackList(boolean packList) {
        this.packList = packList;
    }

    public boolean isPackEdit() {
        return packEdit;
    }

    public void setPackEdit(boolean packEdit) {
        this.packEdit = packEdit;
    }

    public boolean isHostEdit() {
        return hostEdit;
    }

    public void setHostEdit(boolean hostEdit) {
        this.hostEdit = hostEdit;
    }

}
