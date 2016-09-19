/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;

/**
 *
 * @author rasha_000
 */
public class Processor extends BaseModel{

    private String type;
    private String txtType;
    private String manualStr;
    private boolean uploadForm;
    private boolean abookRender;
    private boolean entryRender;
    private boolean addNewBook;
    private boolean typeComboRender;
    private String saveRadioType;
    private String newABookName;
    private boolean editData;
    private boolean bulksms;
//
    private BigDecimal titleID;
    private SaveData saveData = new SaveData();

    public Processor() {
        type = "";
        titleID = BigDecimal.ZERO;
        uploadForm = true;
        abookRender = true;
    }

    public String getManualStr() {
        return manualStr;
    }

    public void setManualStr(String manualStr) {
        this.manualStr = manualStr;
    }

    public BigDecimal getTitleID() {
        return titleID;
    }

    public void setTitleID(BigDecimal titleID) {
        this.titleID = titleID;
    }
//uploads

    public boolean isUploadForm() {
        return uploadForm;
    }

    public void setUploadForm(boolean uploadForm) {
        this.uploadForm = uploadForm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public boolean isAbookRender() {
        return abookRender;
    }

    public void setAbookRender(boolean abookRender) {
        this.abookRender = abookRender;
    }

    public String getSaveRadioType() {
        return saveRadioType;
    }

    public void setSaveRadioType(String saveRadioType) {
        this.saveRadioType = saveRadioType;
    }

    public String getNewABookName() {
        return newABookName;
    }

    public void setNewABookName(String newABookName) {
        this.newABookName = newABookName;
    }

    public boolean isEntryRender() {
        return entryRender;
    }

    public void setEntryRender(boolean entryRender) {
        this.entryRender = entryRender;
    }

    public boolean isAddNewBook() {
        return addNewBook;
    }

    public void setAddNewBook(boolean addNewBook) {
        this.addNewBook = addNewBook;
    }

    public boolean isTypeComboRender() {
        return typeComboRender;
    }

    public void setTypeComboRender(boolean typeComboRender) {
        this.typeComboRender = typeComboRender;
    }

    public boolean isEditData() {
        return editData;
    }

    public void setEditData(boolean editData) {
        this.editData = editData;
    }

    public SaveData getSaveData() {
        return saveData;
    }

    public void setSaveData(SaveData saveData) {
        this.saveData = saveData;
    }

    public boolean isBulksms() {
        return bulksms;
    }

    public void setBulksms(boolean bulksms) {
        this.bulksms = bulksms;
    }

    @Override
    public String toString() {
        return "Processor{" + "type=" + type + ", txtType=" + txtType + ", manualStr=" + manualStr + ", uploadForm=" + uploadForm + ", abookRender=" + abookRender + ", saveRadioType=" + saveRadioType + ", newABookName=" + newABookName + ", titleID=" + titleID + ", saveData=" + saveData + '}';
    }

}
