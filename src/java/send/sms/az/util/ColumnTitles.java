/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

/**
 *
 * @author Mobby
 */
public enum ColumnTitles {

    MSISDN(1),
    MSG_BODY(2),
    LAST_SENT_DATE(3),
    NAME(10),
    SURNAME(11),
    ADD_NAME(12),
    DEBT(13),
    BIRTH_DATE(14),
    ADD_DATE1(15),
    ADD_DATE2(16),
    ADD_COL1(17),
    ADD_COL2(18),
    COMPANY(19),
    PAYMENT(20),
    TYPE(21),
    STATUS(22),
    CATEGORY(23),
    DOCUMENT_ID(24),
    TAX_DOC_ID(25),
    EMAIL(26),
    ADD_INFO1(27);

    private ColumnTitles(int val) {
        this.val = val;
    }

    private final int val;

    public int getVal() {
        return val;
    }



}
