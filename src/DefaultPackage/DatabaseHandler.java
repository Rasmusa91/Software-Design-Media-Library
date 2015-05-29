import java.sql.*;
import java.util.*;

public class DatabaseHandler
{
    public static String executeQuery(String query){
	try{
	    Class.forName("org.sqlite.JDBC");
	    Connection connection = DriverManager.GetConnection("jdbc:sqlite:database.db");
	    Statement stmt = connection.createStatement();

	    stmt.executeUpdate(query);
	    stmt.close();
	    connection.close();
	    return null;
	}catch(Exception e){
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    return e.getMessage();
	}
    }

    public static ArrayList<Object> executeQueryAndFetch(String query){
	try{
	    Class.forName("org.sqlite.JDBC");
	    Connection connection = DriverManager.GetConnection("jdbc:sqlite:database.db");
	    Statement stmt = connection.createStatement();

	    ResultSet rs = stmt.executeQuery(query);

	    ArrayList<Object> objectList = new ArrayList<Object>();
	    int columns = rs.getMetaData().getColumnCount();
	    while(rs.next()){
		for(int i = 0; i < columns; i++){
		    objectList.add(rs.getObject(i));
		}
	    }
	    stmt.close();
	    connection.close();
	    return objectList;
	}catch(Exception e){
	    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    return null;
	}
    }
}