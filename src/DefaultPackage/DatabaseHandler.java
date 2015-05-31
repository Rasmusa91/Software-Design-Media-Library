<<<<<<< HEAD
package DefaultPackage;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import MediaPackage.Media;
import MediaPackage.MediaStatus;
=======
import java.sql.*;
import java.util.*;
>>>>>>> 4b8ed213254b87896e6ae74681fb7ba8b31ddc84

public class DatabaseHandler
{
    public static String executeQuery(String query){
	try{
	    Class.forName("org.sqlite.JDBC");
<<<<<<< HEAD
	    Connection connection = DriverManager.getConnection("jdbc:sqlite:../db/database.db");
=======
	    Connection connection = DriverManager.GetConnection("jdbc:sqlite:database.db");
>>>>>>> 4b8ed213254b87896e6ae74681fb7ba8b31ddc84
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

<<<<<<< HEAD
    public static ArrayList<HashMap<String, Object>> executeQueryAndFetch(String query){
    	try{
		    Class.forName("org.sqlite.JDBC");
		    Connection connection = DriverManager.getConnection("jdbc:sqlite:../db/database.db");
		    Statement stmt = connection.createStatement();
	
		    ResultSet rs = stmt.executeQuery(query);
	
		    ArrayList<HashMap<String, Object>> objectList = new ArrayList<HashMap<String, Object>>();
		    
		    int columns = rs.getMetaData().getColumnCount();
		    while(rs.next())
		    {
	    		HashMap<String, Object> object = new HashMap<String, Object>();
	    		
		    	for(int i = 1; i <= columns; i++){
		    		object.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
		    	}
		    	
	    		objectList.add(object);
		    }
		    stmt.close();
		    connection.close();
		    
		    return objectList;
		}catch(Exception e){
		    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    return null;
		}
    }
    
	public static void checkRentedMediaAndQueues()
	{
		// Get todays date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dateNow = new Date();
		String dateNowString = dateFormat.format(dateNow);

		// Get date in 2 weeks
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateNow);
		calendar.add(Calendar.DATE, 14);
		Date dateThen = calendar.getTime();						
		String dateThenString = dateFormat.format(dateThen);
		
		// Get media items from database where there is an expired rented media
		// Group concat users from queue 
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch(""
				+ "SELECT *, "
				+ "(SELECT GROUP_CONCAT(RentalQueue.id || '-' || RentalQueue.customerId) FROM RentalQueue WHERE RentalQueue.mediaId = Media.id ORDER BY RentalQueue.addedDate ASC) AS queue "
				+ "FROM Media "
				+ "WHERE id IN ("
					+ "SELECT mediaId FROM RentedMedia WHERE expirationDate < '" + dateNowString + "')");
		
		DatabaseHandler.executeQuery("DELETE FROM RentedMedia WHERE expirationDate < '" + dateNowString + "'");		
		
		// Iterate all found media
		for (HashMap<String, Object> mediaData : result) 
		{			
			String mediaID = mediaData.get("id").toString();
			float mediaPrice = (float) ((double) mediaData.get("price"));
			MediaStatus mediaStatus = MediaStatus.valueOf((String) mediaData.get("status"));;
			
			// If someone is in the queue of this item
			if(mediaData.get("queue") != null)
			{
				String[] queue = mediaData.get("queue").toString().split(",");

				for (String queuePos : queue) 
				{
					String[] queueData = queuePos.split("-");
					String queuePosID = queueData[0];
					String queueUserID = queueData[1];
					
					HashMap<String, Object> userResult = DatabaseHandler.executeQueryAndFetch(""
							+ "SELECT * FROM Users "
							+ "WHERE id = '" + queueUserID + "'").get(0);
					
					float userBalance = (float) ((double) userResult.get("accountBalance"));
					
					DatabaseHandler.executeQuery("DElETE FROM RentalQueue "
							+ "WHERE id = '" + queuePosID  + "'");

					if(userBalance >= mediaPrice && mediaStatus == MediaStatus.InStock)
					{
						DatabaseHandler.executeQuery(""
								+ "UPDATE Users "
								+ "SET accountBalance = '" + (userBalance - mediaPrice) + "' "
								+ "WHERE id = '" + queueUserID + "'");
						
						DatabaseHandler.executeQuery("INSERT INTO RentedMedia "
								+ "(userId, mediaId, expirationDate) "
								+ "VALUES ('" + queueUserID + "', '" + mediaID  + "', '" + dateThenString + "')");

						DatabaseHandler.executeQuery("INSERT INTO Transactions "
								+ "(userId, amount, transactionDate) "
								+ "VALUES('" + queueUserID + "', '" + (mediaPrice * -1) + "', '" + dateNowString  + "')");						
						
						break;
					}
				}				
			}
			else 
			{
				DatabaseHandler.executeQuery("UPDATE Media "
						+ "SET amount = amount + 1 "
						+ "WHERE id = '" + mediaID + "'");
			}
		}		
	} 
=======
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
>>>>>>> 4b8ed213254b87896e6ae74681fb7ba8b31ddc84
}