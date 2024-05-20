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
        loadScene((Stage) startButton.getScene().getWindow(), "/source/GUI/prepgame.fxml");
    }

    @FXML
    void showPlantsList(MouseEvent event) throws IOException {
        loadScene((Stage) plantListButton.getScene().getWindow(), "/source/GUI/plantslist.fxml");
    }

    @FXML
    void showZombiesList(MouseEvent event) throws IOException {
        loadScene((Stage) zombieListButton.getScene().getWindow(), "/source/GUI/zombieslist.fxml");
    }

    @FXML
    void showHelp(MouseEvent event) throws IOException {
        loadScene((Stage) helpButton.getScene().getWindow(), "/source/GUI/help.fxml");
    }

    @FXML
    void exitGame(MouseEvent event) {
        System.exit(0);
    }

    private void loadScene(Stage stage, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage.setTitle("Michael vs. Lalapan");
        stage.setScene(new Scene(root));
        mediaPlayer.stop();
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
        mediaPlayer.setVolume(1);
        mediaPlayer.play();
    }
}
