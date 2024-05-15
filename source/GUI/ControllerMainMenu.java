package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainMenu implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button loadButton;

    @FXML
    private Pane pane;

    @FXML
    private Button plantListButton;

    @FXML
    private Button startButton;

    @FXML
    private Button zombieListButton;

    MediaPlayer mediaPlayer;

    @FXML
    void startGame(MouseEvent event) throws IOException {
        Stage mainGame = (Stage) startButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/pvzgui.fxml"));
        mainGame.setTitle("Plants vs. Zombies");
        mainGame.setScene(new Scene(root));
        mediaPlayer.stop();
    }

    @FXML
    void showPlantsList(MouseEvent event) throws IOException {
        Stage plantsList = (Stage) plantListButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/plantslist.fxml"));
        plantsList.setTitle("Plants vs. Zombies");
        plantsList.setScene(new Scene(root));
        mediaPlayer.stop();
    }

    @FXML
    void showZombiesList(MouseEvent event) throws IOException {
        Stage zombiesList = (Stage) zombieListButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/zombieslist.fxml"));
        zombiesList.setTitle("Plants vs. Zombies");
        zombiesList.setScene(new Scene(root));
        mediaPlayer.stop();
    }



    @FXML
    void showHelp(MouseEvent event) throws IOException {
        Stage zombiesList = (Stage) helpButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/help.fxml"));
        zombiesList.setTitle("Plants vs. Zombies");
        zombiesList.setScene(new Scene(root));
        mediaPlayer.stop();
    }

    @FXML
    void exitGame(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        java.net.URL resource = getClass().getClassLoader().getResource("assets/Music/Main_Menu_Song.mp3");
        if (resource == null) {
            System.err.println("Could not find file: " + "assets/Music/Main_Menu_Song.mp3");
            return;
        }
        String path = resource.getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
