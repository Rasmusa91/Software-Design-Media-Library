package MediaPackage;

import java.util.ArrayList;

public class EBook extends Media 
{
	public EBook(String id, String name, float price, int amount,
			MediaStatus status, ArrayList<String> queuedUsers) {
		super(id, name, price, amount, status, queuedUsers);
	}

	@Override
	public MediaType getType() {
		return MediaType.EBook;
	}
}
