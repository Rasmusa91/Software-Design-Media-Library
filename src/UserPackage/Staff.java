package UserPackage;

public class Staff extends User 
{
	public Staff(String id, String name) {
		super(id, name);
	}

	@Override
	public boolean isStaff() {
		return true;
	}
}
