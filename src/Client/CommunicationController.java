package Client;

import Entity.*;
import java.io.IOException;
import java.util.ArrayList;



import Controllers.*;
import Gui.*;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import ocsf.client.AbstractClient;
import Gui.ForgotpasswordController;
public class CommunicationController extends AbstractClient {
	
	public Main mainControl;
	public  MainController mainController;
	public  MemberController memberController;
	public SearchBookController searchbookcontroller;
	public ForgotpasswordController forgotpasswordController;
	public SearchBookMainController searchBookMainController;
	public ProfileController profileController;
	public MyBookController mybookController;
	public HomepageController HomePageController;
	
	public CommunicationController(String host, int port) {
		super(host, port);
		try {
			openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected  synchronized void handleMessageFromServer(Object msg) {
		Msg newmsg = (Msg)msg;	
		System.out.println(newmsg.dataFromServer.toString()+"from comm");

		switch(newmsg.getFuncToRun()) {

		case getMemberByIdAndPass:
			try {
				if(!(newmsg.getDataFromServer().isEmpty())){
					System.out.println(newmsg.dataFromServer.toString()+"from comm");
							
					mainControl.clientMember.curntllyLogIn=mainControl.clientMember.creatNewMember(newmsg.dataFromServer);
					Main.clientMember.checkStatusLogin(Main.clientMember.curntllyLogIn);

				}
			} 
				catch (Exception e) {
				// TODO Auto-generated catch block we need to add Alert

				e.printStackTrace();
			}
			break;


		case getMemberByIdAndGraduation:
			try {
				if((newmsg.getDataFromServer().isEmpty())){
					System.out.println(newmsg.dataFromServer.toString()+"from comm");
					forgotpasswordController.displayAlert(AlertType.INFORMATION, "Error", "Member does not exist", "Member does not exist in the system");
				}
				else {
					forgotpasswordController.handleforgotpasswordresult(newmsg.getDataFromServer().get(5).toString());
				}
			}

			catch (Exception e) {
				// TODO Auto-generated catch block we need to add Alert

				e.printStackTrace();
			}
			break;	 
		case searchBook:{
			Book book=(Book) newmsg.dataFromServer.get(0);
			System.out.println(book.getBookname());
		
			if(!(newmsg.getDataFromServer().isEmpty())){
				for(int i = 0 ; i<newmsg.dataFromServer.size();i++) {
					Book.books.add((Book) newmsg.dataFromServer.get(i));
					System.out.println(Book.books.get(i).getBookname());
				}
				
				searchBookMainController.handleSearchresult(Book.books);

	 }
	
			break;
		       }
		
		case updataMemberPhoneANDEmail:
		   {System.out.println("up data Member Phone AND Email");
			   mainControl.clientMember.checkIfTheUpDataSucceeded(newmsg);
			
		    }
		   
		   
		case searchBookFromController:
		   {
				Book book=(Book) newmsg.dataFromServer.get(0);
				System.out.println(book.getBookname());
			
				if(!(newmsg.getDataFromServer().isEmpty())){
					for(int i = 0 ; i<newmsg.dataFromServer.size();i++) {
						Book.books.add((Book) newmsg.dataFromServer.get(i));
						System.out.println(Book.books.get(i).getBookname());
					}
					Main.client.HomePageController.handleSearchresult(Book.books);			
				}
		   }
		}
	
		
		
		}

	 	

















	public void handleMessageFromClientUI(Object arr) {
		System.out.println(arr);
		try
		{
			sendToServer(arr);		 
		}
		catch(IOException e)
		{
			System.out.println("Could not send message to server.  Terminating client." + e);
		}
	}

}

