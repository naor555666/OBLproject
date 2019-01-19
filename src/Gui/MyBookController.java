package Gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Client.Main;
import Controllers.MemberController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyBookController {
	
    @FXML
    private AnchorPane AnchorHome;

    @FXML
    private ImageView Image;

    @FXML
    private VBox Vbox;

    @FXML
    private Label TimeL;

    @FXML
    private Label DateL;

    @FXML
    private Label NameLabel;

    @FXML
    private Button Profilebnt;

    @FXML
    private Button MyBooksbn;

    @FXML
    private Button SearchBookbnt;

    @FXML
    private ImageView SearchImage;

    @FXML
    private Button ActivityLogbnt;

    @FXML
    private Button Logoutbnt;

    @FXML
    private Label Title;

    @FXML
    private Label TaggedLabel;

    @FXML
    private Label IssueDateLabel;

    @FXML
    private Label ReturnDateLabel;

    @FXML
    private Label OptionLabel;

    @FXML
    private Label MyBooksLabel;
    
    MainController mainController;
    MemberController membercontroller;
     static Stage MyBooksStage;
    

    @FXML
    void ActivityLogbntHandler(ActionEvent event) {

    }

    @FXML
    void LogoutHandler(ActionEvent event) {

    }

    @FXML
    void MyBooksbnHandler(ActionEvent event) {

    }

    @FXML
    void ProfileHandler(ActionEvent event) {
    	Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	try
    	    	{
    	    		Main.client.mybookController.MyBooksStage.close();

    	/*Stage primaryStage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        System.out.println(getClass().getResource("Profile.fxml"));
        AnchorPane pane =(AnchorPane) loader.load();
        Scene scene = new Scene( pane );
        
        // setting the stage
        primaryStage.setScene( scene );
        primaryStage.setTitle( "Profile" );
        primaryStage.show();*/
    	    		Main.client.profileController.ProfileStage.show();
    	    	}
    	    	catch (Exception e)
    	    	{
    	    		displayAlert(AlertType.ERROR, "Error", "Exception", e.getMessage());
    	    	}
    	    }
    	});

    }

    @FXML
    void SearchBookbntHandler(ActionEvent event) {

    }

    public void initialize() {
        Main.client.mybookController=this;
    	String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    	String timeStampclock = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    	NameLabel.setText("Hello "+membercontroller.curntllyLogIn.getFirstname()+" "+membercontroller.curntllyLogIn.getLastname()+"!");
    	DateL.setText(timeStamp);
    	TimeL.setText(timeStampclock);
    	
    }
    
	/**
	 * Show an Alert dialog with custom info
	 * @param type type alert
	 * @param title title window
	 * @param header header of the message
	 * @param content message
	 */
	public static void displayAlert(AlertType type , String title , String header , String content)
	{

		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    			Alert alert = new Alert(type);
    			alert.setTitle(title);
    			alert.setHeaderText(header);
    			alert.setContentText(content);
    			alert.showAndWait();
    	    }
    	});
	}
	
    
}
