/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

/**
 *
 * @author rasha_000
 */
/**
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * The purpose of this class is to generate an excel sheet based on the results
 * from a sql query.
 */
public class SqlExcel {

    public SqlExcel() {
    }

    public static void main(String[] args) {
        SqlExcel sqlExcel = new SqlExcel();
        String sqlQuery = "SELECT * FROM USERS";
        ResultSet rs = sqlExcel.getResultsetFromSql(sqlQuery);
        sqlExcel.generateExcel(sqlExcel.processResultSet(rs), "USERS");
    }

    /**
     * This method returns a database connection given the necessary parameters
     * to create the connection.
     */
    public Connection getDBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("<jdbc_url>", "<user_name>", "<pass_word>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * This method returns a ResultSet for the given sql query.
     */
    public ResultSet getResultsetFromSql(String sql) {
        Connection conn = getDBConnection();
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This method returns a Map with keys as row numbers and values as another
     * LinkedHashMap containing key as column name and value as column value ,
     * present in the ResulSet. We have used LinkedHashMap because it maintains
     * the order in which the values are put in the Map.
     */
    public Map<String, LinkedHashMap<String, String>> processResultSet(ResultSet rs) {
        ArrayList<String> columnNames = new ArrayList<String>();
        LinkedHashMap<String, String> rowDetails = new LinkedHashMap<String, String>();
        Map<String, LinkedHashMap<String, String>> resultMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        ResultSetMetaData rsm = null;

        if (rs != null) {
            try {
                rsm = (ResultSetMetaData) rs.getMetaData();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    System.out.println(i + " -> " + rsm.getColumnName(i));
                    columnNames.add(rsm.getColumnName(i));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            int rowCount = 1;
            while (rs.next()) {
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    rowDetails.put(rsm.getColumnName(i), rs.getString(i));
                }
                resultMap.put(new Integer(rowCount).toString(), rowDetails);
                rowCount++;
                rowDetails = new LinkedHashMap<String, String>();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * This method generates an excel sheet containing data from the given Map.
     * The name of the excel sheet will be the String passed as a parameter.
     */
    public void generateExcel(Map<String, LinkedHashMap<String, String>> resultMap, String name) {
        FileOutputStream fileOut = null;

        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle headerStyle = wb.createCellStyle();
            HSSFSheet sheet3 = wb.createSheet(name);

            HSSFFont headerFont = wb.createFont();
//                        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            headerStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
            headerStyle.setFont(headerFont);

            try {
                fileOut = new FileOutputStream("C:\\" + name + ".xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            HSSFRow sessionname = sheet3.createRow(2);
            HSSFCell title = sessionname.createCell(3);
            title.setCellStyle(headerStyle);
            title.setCellValue(name);

            HSSFRow row = sheet3.createRow(5);

            Map<String, LinkedHashMap<String, String>> rMap = resultMap;
            Map<String, String> columnDetails = rMap.get("1");

            Set<String> s = columnDetails.keySet();
            int cellNo = 0;
            for (String s1 : s) {
                HSSFCell cell0 = row.createCell(cellNo);
                cell0.setCellStyle(headerStyle);
                cell0.setCellValue(s1);
                cellNo++;
            }

            for (int i = 1; i <= rMap.size(); i++) {
                columnDetails = rMap.get(new Integer(i).toString());
                System.out.println(i);
                HSSFRow nextrow = sheet3.createRow(5 + i);
                Set<String> set = columnDetails.keySet();
                int cellNum = 0;
                for (String s2 : set) {
                    nextrow.createCell(cellNum).setCellValue(columnDetails.get(s2));
                    cellNum++;
                }
            }

            sheet3.autoSizeColumn(0);
            sheet3.autoSizeColumn(1);
            sheet3.autoSizeColumn(2);
            sheet3.autoSizeColumn(3);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.flush();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
