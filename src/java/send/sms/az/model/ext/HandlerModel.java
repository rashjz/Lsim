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
public class HandlerModel {

    private boolean tblList;
    private boolean editView;
    private boolean tooBar;

    public HandlerModel() {
        tblList = true;
        editView = false;
        tooBar = true;
    }

    public boolean isTblList() {
        return tblList;
    }

    public void setTblList(boolean tblList) {
        this.tblList = tblList;
    }

    public boolean isEditView() {
        return editView;
    }

    public void setEditView(boolean editView) {
        this.editView = editView;
    }

    public boolean isTooBar() {
        return tooBar;
    }

    public void setTooBar(boolean tooBar) {
        this.tooBar = tooBar;
    }

}
