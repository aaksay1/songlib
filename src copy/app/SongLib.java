package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import view.songLibController;

// Author: Akin Aksay
public class SongLib extends Application{

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();   
        loader.setLocation(getClass().getResource("/view/songListController.fxml"));
        BorderPane root = (BorderPane)loader.load();

        songLibController listController = loader.getController(); 
        listController.start(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
