/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import send.sms.az.model.Books;
import send.sms.az.model.ColumnModel;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Entry;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.util.StringUtil;

/**
 *
 * @author Mobby
 */
public class AddressBookDao {

    private static final Logger LOG = Logger.getLogger(AddressBookDao.class.getName());

    public List<Books> abookNames(BigDecimal userId) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Books> abookList = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "  SELECT ab_id, ab_title\n"
                    + "    FROM bulk_sms2_addr_book\n"
                    + "   WHERE user_id = ? AND is_hidden = 0 AND is_deleted = 0\n"
                    + "ORDER BY ab_title";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, userId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                abookList.add(new Books(rs.getBigDecimal(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return abookList;
    }

    public static List<ComboModel> getTempleNames() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> tempList = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "  select * from bulk_sms2_addr_book_cols where is_hidden=0 order by sort_order";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                ComboModel cm = new ComboModel();
                cm.setItem_value(rs.getBigDecimal(1));
                cm.setItem_name(rs.getString(2));
                tempList.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return tempList;
    }

    public BigDecimal getLastRowIX(BigDecimal abid) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        BigDecimal index = BigDecimal.ZERO;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = " SELECT NVL (MAX (row_ix), -1) AS last_row_ix\n"
                    + "  FROM BULK_SMS2_ADDR_BOOK_ENTRIES\n"
                    + " WHERE ab_id = ?";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, abid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                index = rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return index;
    }

    public BigDecimal selectQuery(BigDecimal abid, BigDecimal colID) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        BigDecimal index = BigDecimal.ZERO;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT COUNT(0) as item_count\n"
                    + "                        FROM (\n"
                    + "                        SELECT row_ix,\n"
                    + "                                col_value,\n"
                    + "                                col_title\n"
                    + "                        FROM bulk_sms2_abe_view\n"
                    + "                        WHERE ab_id = ?)\n"
                    + "                        PIVOT (MAX(col_value) FOR (col_title) IN(1))";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, abid);
//              pstm.setBigDecimal(2, colID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                index = rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return index;
    }

    public BigDecimal insertAddBook(String addBookName, BigDecimal userID, BigDecimal isHidden) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        BigDecimal AB_ID = BigDecimal.ZERO;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "{call INSERT INTO BULK_SMS2_ADDR_BOOK (AB_ID, AB_TITLE, USER_ID, IS_HIDDEN)\n"
                    + "                   VALUES (BULK_SMS2_ADDR_BOOK_SEQ.NEXTVAL, ?, ?, ?)\n"
                    + "                   RETURNING AB_ID INTO ? }";
            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, addBookName);
            pstm.setBigDecimal(2, userID);
            pstm.setBigDecimal(3, isHidden);
            pstm.registerOutParameter(4, Types.NUMERIC);
            int count = pstm.executeUpdate();
            if (count > 0) {
                AB_ID = pstm.getBigDecimal(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return AB_ID;
    }

    public void insertAddBookEntry(BigDecimal abID, BigDecimal colID, BigDecimal rowIX, String colValue) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;

        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = " DECLARE "
                    + "   l_msisdn_row_ix   NUMBER;"
                    + "BEGIN"
                    + "   BEGIN"
                    + "      SELECT ROW_IX"
                    + "        INTO l_msisdn_row_ix"
                    + "        FROM BULK_SMS2_ADDR_BOOK_ENTRIES"
                    + "       WHERE AB_ID = ? AND COL_ID = ? AND COL_VALUE = ? ;"
                    + "   EXCEPTION"
                    + "      WHEN NO_DATA_FOUND"
                    + "      THEN"
                    + "         l_msisdn_row_ix := NULL;"
                    + "   END;"
                    + ""
                    + "   IF l_msisdn_row_ix IS NOT NULL"
                    + "   THEN"
                    + "      UPDATE BULK_SMS2_ADDR_BOOK_ENTRIES"
                    + "         SET COL_VALUE = ? "
                    + "       WHERE AB_ID = ? AND COL_ID = ? AND ROW_IX = l_msisdn_row_ix;"
                    + "   END IF;"
                    + ""
                    + "   IF l_msisdn_row_ix IS NULL OR SQL%ROWCOUNT = 0"
                    + "   THEN  "
                    + "      INSERT INTO BULK_SMS2_ADDR_BOOK_ENTRIES (ENTRY_ID,"
                    + "                                               AB_ID,"
                    + "                                               COL_ID,"
                    + "                                               ROW_IX,"
                    + "                                               COL_VALUE)"
                    + "           VALUES (BULK_SMS2_ADDR_BOOK_ENTRY_SEQ.NEXTVAL,"
                    + "                   ?,"
                    + "                   ?,"
                    + "                   NVL (l_msisdn_row_ix, ? ),"
                    + "                   ?);"
                    + "   END IF;"
                    + "END; ";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, abID);
            pstm.setBigDecimal(2, colID);
            pstm.setString(3, colValue);
            pstm.setString(4, colValue);
            pstm.setBigDecimal(5, abID);
            pstm.setBigDecimal(6, colID);
            pstm.setBigDecimal(7, abID);
            pstm.setBigDecimal(8, colID);
            pstm.setBigDecimal(9, rowIX);
            pstm.setString(10, colValue);
//            pstm.registerOutParameter(3, Types.NUMERIC);
            int count = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
    }

    public void updateAddBookEntry(BigDecimal abID, BigDecimal colID, BigDecimal rowix, String colValue) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "BEGIN\n"
                    + "   UPDATE BULK_SMS2_ADDR_BOOK_ENTRIES\n"
                    + "      SET COL_VALUE = ? \n"
                    + "    WHERE AB_ID = ? AND COL_ID = ? AND ROW_IX = ?;\n"
                    + "\n"
                    + "   IF SQL%ROWCOUNT = 0\n"
                    + "   THEN\n"
                    + "      INSERT INTO BULK_SMS2_ADDR_BOOK_ENTRIES (ENTRY_ID,\n"
                    + "                                               AB_ID,\n"
                    + "                                               COL_ID,\n"
                    + "                                               ROW_IX,\n"
                    + "                                               COL_VALUE)\n"
                    + "           VALUES (BULK_SMS2_ADDR_BOOK_ENTRY_SEQ.NEXTVAL,\n"
                    + "                   ?,\n"
                    + "                   ?,\n"
                    + "                   ?,\n"
                    + "                   ?);\n"
                    + "   END IF;\n"
                    + "END;";
            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, colValue);
            pstm.setBigDecimal(2, abID);
            pstm.setBigDecimal(3, colID);
            pstm.setBigDecimal(4, rowix);
            pstm.setBigDecimal(5, abID);
            pstm.setBigDecimal(6, colID);
            pstm.setBigDecimal(7, rowix);
            pstm.setString(8, colValue);

            int count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
    }

    public int deleteAddrBook(BigDecimal abookID, BigDecimal userID) {
        Connection c = null;
        CallableStatement pstm = null;
        int status = 0;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "UPDATE bulk_sms2_addr_book\n"
                    + "   SET is_deleted = 1\n"
                    + " WHERE ab_id IN (" + abookID + ")";
            pstm = c.prepareCall(sql.toString());
            status = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return status;
    }

    public String getColumnNames(BigDecimal abookID, BigDecimal userID) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        String columns = "";
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT wm_concat (col_title) AS col_names\n"
                    + "  FROM (  SELECT DISTINCT '''' || col_title || '''' AS col_title, col_id\n"
                    + "            FROM bulk_sms2_abe_view\n"
                    + "           WHERE ab_id = ? \n"
                    + "        ORDER BY col_id) t";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, abookID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                columns = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return columns;
    }

    public List<ColumnModel> getColNamesListAsPlaceholders(BigDecimal abookID) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<ColumnModel> colList = new ArrayList();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "  SELECT DISTINCT\n"
                    + "         col_id,\n"
                    + "         '{' || col_display_name || '}' AS col_display_name,\n"
                    + "         COUNT (0) AS exists_for,COL_TITLE as columnkey\n"
                    + "    FROM bulk_sms2_abe_view\n"
                    + "   WHERE ab_id = ? AND is_hidden = 0\n"
                    + "GROUP BY col_id, '{' || col_display_name || '}',COL_TITLE\n"
                    + "ORDER BY col_id";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, abookID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ColumnModel cm = new ColumnModel();
                cm.setCol_id(rs.getString(1));
                cm.setHeader(rs.getString(2));
                cm.setProperty(rs.getString(4));
                colList.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return colList;
    }

    public List<Map<String, Entry>> GETAddBook(BigDecimal abookID, BigDecimal userID) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        String colNames = getColumnNames(abookID, userID);
//        LOG.info(colNames);
        List<Map<String, Entry>> list = new ArrayList<>();
        Map<String, String> colmap = StringUtil.strToMap(colNames);
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT *\n"
                    + "  FROM (SELECT row_ix, col_value, col_title\n"
                    + "          FROM bulk_sms2_abe_view\n"
                    + "         WHERE ab_id = " + abookID + ") PIVOT (MAX (col_value)\n"
                    + "                           FOR (col_title)\n"
                    + "                           IN (" + colNames + "))";
            pstm = c.prepareCall(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Map<String, Entry> map = new HashMap<>();
                for (int i = 1; i < colmap.size() + 1; i++) {//setir setir goturur
                    String mapkey = colmap.get("" + i).toString();
                    if (colmap.get("" + i) != null) {
                        map.put(mapkey, new Entry(rs.getString(1), rs.getString(i + 1)));
                        System.out.println(mapkey + " * " + rs.getString(i + 1) + " " + (i + 1) + "  entr   " + rs.getString(1));//string colunm key and column value
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return list;
    }

    public void deleteAddBookEntry(BigDecimal abookID, BigDecimal rowIX) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "delete from bulk_sms2_addr_book_entries where ab_id = ? and row_ix in (?) ";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, abookID);
            pstm.setBigDecimal(2, rowIX);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }

    }

    public static void main(String[] args) {
//        updateAddBookEntry(new BigDecimal(11), BigDecimal.TEN, "qwe");
//        System.out.println(abookNames(new BigDecimal(107)));
//        for (Map.Entry<BigDecimal, String> entrySet : getTempleNames().entrySet()) {
//            Object key = entrySet.getKey();
//            Object value = entrySet.getValue();
//            System.out.println(key + " " + value);
//        }
//        System.out.println("0000 " + getLastRowIX(new BigDecimal(3652)));
//        System.out.println("---"+abookNames(new BigDecimal(82)));
//        insertAddBookEntry(new BigDecimal(3655), BigDecimal.ONE, BigDecimal.TEN, "xxxvvv");
//        for (AddressBook arg : GETAddBookENTRIES(BigDecimal.ZERO, BigDecimal.ZERO)) {
//            System.out.println(arg.toString());
//        }
//        System.out.println(GETAddBook(new BigDecimal(3652), BigDecimal.ZERO));
//        String cols = getColumnNames(new BigDecimal(103), new BigDecimal(82));
//        System.out.println(cols);
//        for (int i = 1; i < strToMap(cols).size() + 1; i++) {
//            System.out.println(i);
//            System.out.println(strToMap(cols).get("" + i));
//        }
//        for (int i = 1; i < 20; i++) {
//            System.out.println(i);
//        }
//        for (Map<String, Entry> arg : GETAddBook(new BigDecimal(103), new BigDecimal(82))) {
//            for (Map.Entry<String, Entry> entrySet : arg.entrySet()) {
//                String key = entrySet.getKey();
//                Entry value = entrySet.getValue();
//                System.out.println(key + " " + value.getRowix()+" "+value.getValue());
//            }
//        }
//
//        for (ColumnModel arg1 : getColNamesListAsPlaceholders(new BigDecimal(103))) {
//            System.out.println(arg1.toString());
//        }
//        insertAddBook("tsttss", new )
//        for (Books arg1 : abookNames(new BigDecimal(82))) {
//            System.out.println(arg1.toString());
//        }
//        insertAddBookEntry(new BigDecimal(3634), BigDecimal.ONE, getLastRowIX(new BigDecimal(3634).add(BigDecimal.ZERO)), "235235343");
//        System.out.println(getLastRowIX(new BigDecimal(103)));

//        System.out.println(selectQuery(new BigDecimal(1), new BigDecimal(1)));
//        for (Books arg : abookNames(new BigDecimal(389))) {
//            System.out.println(arg.toString());
//        }
    }
}
