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
	private HashMap<String, Observable> observableList;
	
	public MediaLibrary()
	{
		DatabaseHandler.checkRentedMediaAndQueues();
		observableList = new HashMap<String, Observable>();
		initialize();
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
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type) {
				return addMedia(name, price, amount, status, type);
			}
			
			@Override
			public boolean onAddFunds(float amount, String[] credentials) {
				return addFunds(amount, credentials);
			}
			
		}, observableList);
	}
	
	private void initialize()
	{
		userHandler = new UserHandlerStateNone(new IUserHandlerStateChangeCallback() {
			@Override
			public void stateChange(UserHandlerState handler) {
				userHandler = handler;	
				observableList.put("userHandler", handler);
			}
		});
		
		mediaHandler = new MediaHandlerStateNone(new IMediaHandlerStateChangeCallback() {
			@Override
			public void stateChange(MediaHandlerState handler) {
				userHandler.updateReference(handler, mediaHandler);
				mediaHandler = handler;
				observableList.put("mediaHandler", handler);
			}
		}, true);
		userHandler.addObserver(mediaHandler);
		
		ticketHandler = new TicketHandlerStateNone(new ITicketHandlerStateChangeCallback() {
			@Override
			public void stateChange(TicketHandlerState handler) {
				userHandler.updateReference(handler, ticketHandler);
				ticketHandler = handler;
				observableList.put("ticketHandler", handler);
			}
		});
		userHandler.addObserver(ticketHandler);

		statisticsHandler = new StatisticsHandlerStateNone(new IStatisticsHandlerStateChangeCallback() {
			@Override
			public void stateChange(StatisticsHandlerState handler) {
				userHandler.updateReference(handler, statisticsHandler);
				statisticsHandler = handler;
				observableList.put("statisticsHandler", handler);
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
			userHandler.editBalance(media.getPrice() * -1);
			statisticsHandler.addTransaction(TransactionType.Withdrawal, media.getPrice());
		}
		
		return success;
	}
	
	public boolean addCustomerToQueue(Media media)
	{
		return mediaHandler.addCustomerToQueue(media);
	}
}
