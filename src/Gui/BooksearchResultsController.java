package Gui;

import Entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BooksearchResultsController {

    @FXML
    private AnchorPane AnchSerchBook;

    @FXML
    private ImageView Image;

    @FXML
    private TableView<Book> Tableview;

    @FXML
    private TableColumn<Book, String> Title;

    @FXML
    private TableColumn<Book, String> Genre;

    @FXML
    private TableColumn<Book, String> Author;

    @FXML
    private TableColumn<Book, String> Description;

    @FXML
    private TableColumn<Book, String> tableofcontents;

    @FXML
    private Label SearchBookresultsLabel;
    
    
    ObservableList<Book> booklist=FXCollections.observableArrayList();
    
    
    public void initialize() {
    	Title.setCellValueFactory(new PropertyValueFactory<>("bookname"));
    	Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
    	Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
    	Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
    	tableofcontents.setCellValueFactory(new PropertyValueFactory<>("tableofcontents"));
    }

}

    
 


