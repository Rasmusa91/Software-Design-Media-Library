package UserPackage;

import java.util.Date;

public class RentedMedia 
{
	private String mediaID;
	private Date expirationDate;
	
	public RentedMedia(String mediaID, Date expirationDate)
	{
		this.mediaID = mediaID;
		this.expirationDate = expirationDate;
	}
	
	public String getMediaID()
	{
		return mediaID;		
	}
	
	public Date getExpirationDate()
	{
		return expirationDate;		
	}	
}
