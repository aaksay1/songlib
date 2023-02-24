package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import java.util.Optional;

import app.Song;


// Author: Akin Aksay
// Author: Manupreeth Vallala
public class songLibController {

    @FXML
    TextField addAlbum;

    @FXML
    TextField addArtist;

    @FXML
    TextField addTitle;

    @FXML
    TextField addYear;

    @FXML
    Text album;

    @FXML
    Text artist;

    @FXML
    ListView<Song> listView;

    @FXML
    Text title;

    @FXML
    Text year;

    private ObservableList<Song> obsList;

    fileControl control = new fileControl();
    
    public void start(Stage mainStage) {
        
        obsList = FXCollections.observableArrayList(control.readFromFile("src/songList.txt"));
        listView.setItems(obsList);
        setListCell();

        if(!obsList.isEmpty()) {
            listView.getSelectionModel().select(0);
        }
        showItem();


        listView
        .getSelectionModel()
        .selectedIndexProperty()
        .addListener(
            (obs, oldVal, newVal) -> 
            showItem());

        mainStage.setOnCloseRequest(event -> {
            try {
                control.writeToTheFile("src/songList.txt", obsList);
            } catch (Exception e) {
              
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void addButton(){

        String t = addTitle.getText();
        String ar = addArtist.getText();
        String al = addAlbum.getText();
        String y = addYear.getText();

        Song s = new Song(t.replace("|", ""), ar.replace("|", ""), 
                         al.replace("|", ""), y.replace("|", ""));

        
        
        if(s.getTitle().isEmpty() || s.getArtist().isEmpty()) {
            giveError("The Title of the song or the name of the Artist is missing!");
            return;
        }

        if(control.duplicate(s, obsList)) {
            giveError("This song is already in the song library.");
            return;
        }

        if(y.length() != 0) {
            if(y.length() != 4) {
                giveError("Year has to be a 4 digit number!");
                return;
            }
            
            if(!isNumeric(y)) {
                giveError("Year has to be a number!");
                return;
            }

        }


        if(getConfirmation("Confirm add", "Press OK if you want to add this song into the library.")) {
            obsList.add(s);
        }


        setListCell();
        showItem();

        if(obsList.size() == 1) {
            listView.getSelectionModel().select(0);
        } else {
            int i = 0;

            for(Song song : obsList) {
                if(song == s) {
                    listView.getSelectionModel().select(i);
                    break;
                }
                i++;
            }

        }

        obsList.sort(control);  

        addTitle.setText("");
        addAlbum.setText("");
        addYear.setText("");
        addArtist.setText("");

    }
   
    @FXML
    private void delButton() {

        if(obsList.isEmpty()) {
            giveError("The library is empty, there is nothing to delete.");
            return;
        }

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();       

        if (selectedIndex >= 0 && getConfirmation("Confirm delete", "Press OK if you want to delete this song from the library.")) {
            listView.getItems().remove(selectedIndex);
            int newSelectedIndex = selectedIndex;
       
            if (newSelectedIndex == listView.getItems().size()) {
                newSelectedIndex--;
            }
            listView.getSelectionModel().select(newSelectedIndex);

            obsList.sort(control);  
            setListCell();
            showItem();  
            
            if(obsList.isEmpty()) {

                title.setText("");
                artist.setText("");
                album.setText("");
                year.setText("");
            }
        }
        
    }

    @FXML
    private void editButton() {

        if(obsList.isEmpty()) {
            giveError("The library is empty, there is nothing to edit");
            return;
        }

        int index = listView.getSelectionModel().getSelectedIndex();

        Song s = obsList.get(index);

        Song temp = new Song(addTitle.getText().replace("|", ""), 
                            addArtist.getText().replace("|", ""),
                            addAlbum.getText().replace("|", ""),
                            addYear.getText().replace("|", ""));

        if(temp.getTitle().isEmpty() || temp.getArtist().isEmpty()) {
            giveError("The Title of the song or the name of the Artist is missing!");
            return;
        }
        
        if(addYear.getText().length() != 0) {
            if(addYear.getText().length() != 4) {                        
                giveError("Year has to be a 4 digit number!");
                return;
            }
                                
            if(!isNumeric(addYear.getText())) {
                giveError("Year has to be a number!");
                return;
            }
        }

        if(!control.duplicate(temp, obsList)) {

            if(getConfirmation("Confirm edit", "Press OK if you want to edit this song.")) {
                s.setTitle(addTitle.getText().replace("|", ""));
                s.setArtist(addArtist.getText().replace("|", ""));
                s.setAlbum(addAlbum.getText().replace("|", ""));
                s.setYear(addYear.getText().replace("|", ""));
            }
            
        } else {
            giveError("This song is already in the library");
           
        }
        
        listView.getSelectionModel().select(index);
        showItem();
        obsList.sort(control);
        setListCell();

        addTitle.setText("");
        addAlbum.setText("");
        addYear.setText("");
        addArtist.setText("");
        
    }


    // Gives an error statement
    private void giveError(String error) {

        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    // Asks for confirmation
    private boolean getConfirmation(String content1, String content2) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(content1);
        alert.setContentText(content2);

        Optional<ButtonType> result = alert.showAndWait();

        return (result.get() == ButtonType.OK);
    }


    // Method that checks if the year is made up of numbers
    private boolean isNumeric(String str) {  
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }

    // Shows the current item from the listview
    private void showItem() {

        if(listView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }

        Song item = listView.getSelectionModel().getSelectedItem();

        title.setText(item.getTitle());
        artist.setText(item.getArtist());
        album.setText(item.getAlbum());
        year.setText(item.getYear());
    }

     // List configuration
    public void setListCell() {

        listView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {

            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new ListCell<Song>() {

                    @Override
                    protected void updateItem(Song item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item.getTitle()+ "\n" + item.getArtist());
                        }
                    }
                };
            }
        });

    }

    
}

