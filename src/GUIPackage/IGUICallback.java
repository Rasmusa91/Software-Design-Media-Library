package GUIPackage;

import MediaPackage.Media;
import MediaPackage.MediaStatus;
import MediaPackage.MediaType;
import TicketPackage.Ticket;

public interface IGUICallback {
	public boolean onLogin(String name, String password);
	public boolean onLogout();
	public boolean onSignup(String name, String password);
	public boolean onAddFunds(float amount, String[] credentials);
	public boolean onSubmitTicket(String message);
	public boolean onProcessTicket(Ticket ticket, String message);
	public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type);
	public boolean onEditMedia(Media media, String name, float price, int amount, MediaStatus status, MediaType type);
	public boolean onRentMedia(Media media);
	public boolean onAddCustomerToQueue(Media media);
}
