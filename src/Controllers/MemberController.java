package Controllers;
import Entity.*;
import Gui.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entity.Msg;

import Client.Command;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;
import Client.Main;

/***
 * 
 * Controller class to perform subscription actions
 *
 */

public class MemberController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  MainController mainController;
	public static Member curntllyLogIn; 
	public static SearchBookController searchbookcontroller;
	public MemberController() {
		super();
	}
	/**
	 * handle login Checks for if the member exist and logs in to the system
	 * @param userName - geting from the MainController
	 * @param pass - geting from the MainController
	 * @throws IOException
	 */
	public void handlelogin(String userName,String pass  ) throws IOException {
			
    	Msg msg=new Msg("SELECT * FROM Member WHERE MemberID ="+ userName+ " and Password =" + pass +";", Command.getMemberByIdAndPass);
    	//msg.dataToServer.add(userName);
        //msg.dataToServer.add(pass);
    	System.out.println("from Member Controller handlelogin");
    	Main.client.sendToServer(msg);
	}
	
	/**
	 * Create a new member 
	 * 
	 * @param dataFromServer
	 * @return new Member 
	 */
	
	public Member creatNewMember(ArrayList<Object> dataFromServer) {
	
		Member member=new Member (Boolean.valueOf(dataFromServer.get(10).toString()),dataFromServer.get(0).toString(),
				dataFromServer.get(1).toString(),dataFromServer.get(2).toString(),
				dataFromServer.get(3).toString(),dataFromServer.get(4).toString(),
				dataFromServer.get(5).toString(),dataFromServer.get(6).toString(),
				dataFromServer.get(7).toString(),Integer.valueOf(dataFromServer.get(8).toString()),
				dataFromServer.get(9).toString());
	
		System.out.println(member.toString());
		return member;
		
		
	}
	/**
	 * handle Forgot password help the member to login by identification by ID and year of graduation to sent the password
	 * @param ID - geting from the ForgotpasswordController
	 * @param graduation - geting from the ForgotpasswordController
	 * @throws IOException
	 */
	public void handlelForgotpassword(String ID , String graduation) throws IOException {
		Msg msg=new Msg("SELECT * FROM Member WHERE MemberID ="+ ID+ " and Graduationyear =" + graduation +";", Command.getMemberByIdAndGraduation);
    	msg.dataToServer.add(ID);
    	msg.dataToServer.add(graduation);
    	Main.client.sendToServer(msg);
		
	}
	/**
	 *  check the Status Member if he could login to the system
	 * @param curntllyLogIn
	 * @throws Exception
	 */
	public void checkStatusLogin(Member curntllyLogIn) throws Exception {
		//Platform.runLater(new Runnable() {
    	 //   @Override
    	//    public void run() {
		System.out.println("checkStatusLogin" );
		if(curntllyLogIn.getStatus().equals("Active")) {
			try {
				Main.client.mainController.handleloginresult();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
			Main.client.mainController.displayAlert(AlertType.INFORMATION, "Blocked", "User Blocked", "Your user has been blocked. Contact our stuff managment");

			}
    	    }
	//	});
			
		
	     
	//}
	
	/**
	 *  Up Data Member- updating his personal information
	 * @param newPhoneNumber
	 * @param newEmail
	 * @throws IOException 
	 */
	
	public void UpDataMember(String newPhoneNumber,String newEmail) throws IOException {
		System.out.println("Up Data Member");
		
		if(!(curntllyLogIn.getPhonenumber().equals(newPhoneNumber)) && !(curntllyLogIn.getEmail().equals(newEmail))) {
			                 
			Msg msg=new Msg("UPDATE member SET Phonenumber="+ newPhoneNumber + " , Email="+ newEmail + " WHERE MemberID =" +curntllyLogIn.getMemberID()+ ";",Command.updataMemberPhoneANDEmail);
			msg.dataToServer.add(newPhoneNumber);
			msg.dataToServer.add(newEmail);
			Main.client.sendToServer(msg);
			return;
		}
		
		else{if(!(curntllyLogIn.getPhonenumber().equals(newPhoneNumber))) {
		Msg msg=new Msg("UPDATE member SET Phonenumber="+ newPhoneNumber + " WHERE MemberID ="+curntllyLogIn.getMemberID()+";",Command.updataMemberPhoneANDEmail);
		msg.dataToServer.add(newPhoneNumber);
		msg.dataToServer.add(curntllyLogIn.getEmail());
		Main.client.sendToServer(msg);
		return;
		}
		
		if(!(curntllyLogIn.getEmail().equals(newEmail))) {
			Msg msg=new Msg("UPDATE member SET Email="+ newEmail + " WHERE MemberID ="+curntllyLogIn.getMemberID()+";",Command.updataMemberPhoneANDEmail);
			msg.dataToServer.add(curntllyLogIn.getPhonenumber());
			msg.dataToServer.add(newEmail);
			Main.client.sendToServer(msg);
			return;
			}
		
		}
		
		Main.client.profileController.displayAlert(AlertType.INFORMATION, "No change was made", "Unchanged Message ", "You have not entered any new information, so no change was made");
		
		
	}
	
	/**
	 *  check If The UpData data was a success and print a message in accordance
	 * @param newmsg
	 */
	public void  checkIfTheUpDataSucceeded(Msg newmsg){
		
		
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	if (newmsg.result) { 
    	    		System.out.println("check If The Up Data Succeeded");
    	    		String SetEmail=(String)newmsg.dataFromServer.get(1);
    	    		curntllyLogIn.setPhonenumber((String)newmsg.dataFromServer.get(0));
    	    		curntllyLogIn.setEmail(SetEmail.substring(1, (SetEmail.length()-1)));
    	    		System.out.println(curntllyLogIn.getPhonenumber().toString()+" "+curntllyLogIn.getEmail().toString());
    	    		Main.client.profileController.initialize();
    				Main.client.profileController.displayAlert(AlertType.INFORMATION, "Success", "Success Message ", "Your information has been successfully updated, thank you for updating us");
    				
    			}
    			else {
    				
    				Main.client.profileController.displayAlert(AlertType.ERROR, "Error", "Error Message ", "Sorry, the update did not pass successfully, please try again later");
    				
    			}
    	    }
    	});
  	

	
	}
	/**
	 * create a Book Search Request for server 
	 * @param where
	 * @param search
	 * @throws IOException
	 */
	public void createaBookSearchRequest(String where , String search) throws IOException {
		

	        	Platform.runLater(new Runnable() {
	        	    @Override
	        	    public void run() {
	        			System.out.println("create a Book Search Request");
	        			   Msg msg=new Msg("SELECT * FROM books WHERE " +where+ " LIKE " + search+ ";", Command.searchBookFromController);
	        		            msg.dataToServer.add(where);
	        		            msg.dataToServer.add(search);
	        		            System.out.println("SERACH"+Thread.currentThread().getId());
	        		            try {
									Main.client.sendToServer(msg);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        	    }
	        	});
	             	
	        
			
	}
	/**
	 * make Order - Create a request from the server to order a book
	 * @param orderBook - the book the member choose
	 */
	
	public void makeOrder(Book orderBook) {
		
	//	Msg msg=new Msg("",Command.MakeOrderBook);
		
	}

}
