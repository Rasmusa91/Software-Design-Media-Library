package DefaultPackage;

import java.util.ArrayList;
import java.util.HashMap;

import UserPackage.*;
import GUIPackage.GUI;
import GUIPackage.IGUICallback;
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
		HashMap<String, Observable> observableList = new HashMap<String, Observable>();
		observableList.put("userHandler", userHandler);
		observableList.put("mediaHandler", mediaHandler);
		observableList.put("ticketHandler", ticketHandler);
		observableList.put("statisticsHandler", statisticsHandler);
		
		gui = new GUI(new IGUICallback() {
			
			@Override
			public boolean onSubmitTicket(String message) {
				return submitTicket(message);
			}
			
			@Override
			public boolean onSignup(String name, String password) {
				return signup(name, password);
			}
			
			@Override
			public boolean onRentMedia(Media media) {
				return rentMedia(media);
			}
			
			@Override
			public boolean onProcessTicket(Ticket ticket, String message) {
				return processTicket(ticket, message);
			}
			
			@Override
			public boolean onLogout() {
				return logout();
			}
			
			@Override
			public boolean onLogin(String name, String password) {
				return login(name, password);
			}
			
			@Override
			public boolean onEditMedia(Media media, String name, float price, int amount, MediaStatus status, MediaType type) {
				return editMedia(media, name, price, amount, status, type);
			}
			
			@Override
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type) {
				return addMedia(name, price, amount, status, type);
			}
			
			@Override
			public boolean onAddFunds(float amount, String[] credentials) {
				return addFunds(amount, credentials);
			}
			
			@Override
			public boolean onAddCustomerToQueue(Media media) {
				return addCustomerToQueue(media);
			}
		}, observableList);
	}
	
	private void initialize()
	{
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
