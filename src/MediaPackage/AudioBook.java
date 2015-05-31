package MediaPackage;

import java.util.ArrayList;

public class AudioBook extends Media 
{
	public AudioBook(String id, String name, float price, int amount,
			MediaStatus status, ArrayList<String> queuedUsers) {
		super(id, name, price, amount, status, queuedUsers);
	}
}
