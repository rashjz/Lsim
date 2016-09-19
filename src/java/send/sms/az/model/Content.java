/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mobby
 */
public class Content extends BaseModel {

    private BigDecimal content_id;
    private Date dateAdded;
    private String status_add;
    private String addedBy;
    private String az_content;
    private String ru_content;
    private String en_content;
    private String subscription;
    private List<String> subList;

    public Content() {
        content_id =BigDecimal.ZERO;
        subList = new ArrayList<>();
    }

    public BigDecimal getContent_id() {
        return content_id;
    }

    public void setContent_id(BigDecimal content_id) {
        this.content_id = content_id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus_add() {
        return status_add;
    }

    public void setStatus_add(String status_add) {
        this.status_add = status_add;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getAz_content() {
        return az_content;
    }

    public void setAz_content(String az_content) {
        this.az_content = az_content;
    }

    public String getRu_content() {
        return ru_content;
    }

    public void setRu_content(String ru_content) {
        this.ru_content = ru_content;
    }

    public String getEn_content() {
        return en_content;
    }

    public void setEn_content(String en_content) {
        this.en_content = en_content;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public List<String> getSubList() {
        return subList;
    }

    public void setSubList(List<String> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        return "Content{" + "content_id=" + content_id + ", dateAdded=" + dateAdded + ", status_add=" + status_add + ", addedBy=" + addedBy + ", az_content=" + az_content + ", ru_content=" + ru_content + ", en_content=" + en_content + ", subscription=" + subscription + '}';
    }

}
