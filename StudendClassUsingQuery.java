package studentCRUD;
import java.sql.*;
import java.util.*;


public class StudendClassUsingQuery 
{

	    public static List<Map<String, Object>> getDataFromDB(String sqlQuery) {
	        List<Map<String, Object>> dataList = new ArrayList<>();
	        Connection con = null;

	        try {
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=dinga");

	            ResultSet resultSet = con.createStatement().executeQuery(sqlQuery);

	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            while (resultSet.next()) {
	                Map<String, Object> rowData = new LinkedHashMap<>();
	                for (int i = 1; i <= columnCount; i++) {
	                    String columnName = metaData.getColumnName(i);
	                    Object value = resultSet.getObject(i);
	                    rowData.put(columnName, value);
	                }
	                dataList.add(rowData);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return dataList;
	    }

	    public static void main(String[] args) {
	        // Example usage
	        String sqlQuery = "SELECT * FROM studentMark";
	        List<Map<String, Object>> studentDataList = getDataFromDB(sqlQuery);
	        System.out.println(studentDataList);
	    }
	}

