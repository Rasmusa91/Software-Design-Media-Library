package DefaultPackage;

import java.util.ArrayList;

import UserPackage.*;
import GUIPackage.GUI;
import MediaPackage.*;
import TicketPackage.*;
import StatisticsPackage.*;

public class MediaLibrary 
{
	private GUI gui;
	private UserHandlerState userHandler;
	private MediaHandlerState mediaHandler;
	private TicketHandlerState ticketHandler;
	private StatisticsHandlerState statisticsHandler;
	
	public MediaLibrary()
	{
		DatabaseHandler.checkRentedMediaAndQueues();
		initialize();
		
		login("asd", "asd2");
		//login("Oliver", "CoolOliver"); // STAFF
		
		//rentMedia(new Media("2", "asd", 100f, 10, MediaStatus.InStock, new ArrayList<String>()));
		//System.out.println(userHandler.getClass().toString());
		//System.out.println(addMedia("b", 0f, 10, MediaStatus.InStock, MediaType.AudioBook));
		
		
		//addFunds(1000, new String[] {});
		
		
		//addTicket("helo");
		//addCustomerToQueue(new Media("1", "asd", 0f, 10, MediaStatus.InStock, new ArrayList<String>()));
	}
	
	private void initialize()
	{
		gui = new GUI();
		
		userHandler = new UserHandlerStateNone(new IUserHandlerStateChangeCallback() {
			@Override
			public void stateChange(UserHandlerState handler) {
				userHandler = handler;				
			}
		});
		
		mediaHandler = new MediaHandlerStateNone(new IMediaHandlerStateChangeCallback() {
			@Override
			public void stateChange(MediaHandlerState handler) {
				userHandler.removeObserver(mediaHandler);
				userHandler.addObserver(handler);

				mediaHandler = handler;
			}
		}, true);
		userHandler.addObserver(mediaHandler);
		
		ticketHandler = new TicketHandlerStateNone(new ITicketHandlerStateChangeCallback() {
			@Override
			public void stateChange(TicketHandlerState handler) {
				userHandler.removeObserver(ticketHandler);
				userHandler.addObserver(handler);

				ticketHandler = handler;
			}
		});
		userHandler.addObserver(ticketHandler);

		statisticsHandler = new StatisticsHandlerStateNone(new IStatisticsHandlerStateChangeCallback() {
			@Override
			public void stateChange(StatisticsHandlerState handler) {
				userHandler.removeObserver(statisticsHandler);
				userHandler.addObserver(handler);

				statisticsHandler = handler;
			}
		});
		userHandler.addObserver(statisticsHandler);	
	}
	
	public boolean login(String name, String password)
	{
		return userHandler.login(name, password);
	}
	
	public boolean logout()
	{
		return userHandler.logout();		
	}
	
	public boolean signup(String name, String password)
	{
		return userHandler.createUser(name, password);
	}
	
	public boolean addFunds(float amount, String[] credentials)
	{
		boolean success = false;
		
		if(BankHandler.Deposit(amount, credentials)) 
		{
			userHandler.editBalance(amount);
			statisticsHandler.addTransaction(TransactionType.Deposition, amount);		
			
			success = true;
		}
		
		return success;
	}

	public boolean submitTicket(String message)
	{
		return ticketHandler.addTicket(message);
	}
	
	public boolean processTicket(Ticket ticket, String message)
	{
		return ticketHandler.resolveTicket(ticket, message);
	}
	
	public boolean addMedia(String name, float price, int amount, MediaStatus status, MediaType type)
	{
		return mediaHandler.addMediaItem(name, price, amount, status, type);
	}
	
	public boolean editMedia(Media media, String name, float price, int amount, MediaStatus status, MediaType type)
	{
		return mediaHandler.editMedia(media, name, price, amount, status, type);
	}

	public boolean rentMedia(Media media)
	{
		boolean success = mediaHandler.rent(media);
		
		if(success) {
			statisticsHandler.addTransaction(TransactionType.Withdrawal, media.getPrice());
		}
		
		return success;
	}
	
	public boolean addCustomerToQueue(Media media)
	{
		return mediaHandler.addCustomerToQueue(media);
	}
}
