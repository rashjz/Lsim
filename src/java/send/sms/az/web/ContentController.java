/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import send.sms.az.dao.ContentDao;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Content;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "contentCnt")
@ViewScoped
public class ContentController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(ContentController.class.getName());
    private ContentDao dao;
    private List<Content> contentList;
    private List<ComboModel> subscribeList;
    private Content content = new Content();

    public ContentController() {
        dao = new ContentDao();
        contentList = dao.subsCustomerList();
        subscribeList = dao.getSubsList();
    }

    public void insertContent() {
        LOG.info("insertContent ");
        content = new Content();
    }

    public void updateContent() {
        LOG.info("updateContent ");
        content.setAddedBy(loginCnt.getUser().getLogin());
        if (content != null && content.getContent_id().compareTo(BigDecimal.ZERO) == 0) {
            LOG.info(content.getSubList().toString());
            dao.insContent(content);
            dao.subsCustomerList();
        } else if (content != null && content.getContent_id().compareTo(BigDecimal.ZERO) != 0) {
//            LOG.info("updateContent " + content.toString());
            dao.updContent(content);
        }
    }

    public void listener() {
        LOG.info(content.toString());
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<ComboModel> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<ComboModel> subscribeList) {
        this.subscribeList = subscribeList;
    }

}
