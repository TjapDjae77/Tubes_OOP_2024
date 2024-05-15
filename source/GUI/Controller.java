package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane cell11;

    @FXML
    private Pane cell12;

    @FXML
    private Pane cell21;

    @FXML
    private Pane cell22;

    @FXML
    private Pane cell31;

    @FXML
    private Pane cell32;

    @FXML
    private Button menubutton;

    @FXML
    private ImageView slot1;

    @FXML
    private ImageView slot2;

    @FXML
    private ImageView slot3;

    @FXML
    private ImageView slot4;

    @FXML
    private ImageView slot5;

    @FXML
    private ImageView slot6;

    @FXML
    private Label sunValue;

    MediaPlayer mediaPlayer;

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        Stage mainMenu = (Stage) menubutton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/mainmenu.fxml"));
        mainMenu.setTitle("Plants vs. Zombies");
        mainMenu.setScene(new Scene(root));
        mediaPlayer.stop();
    }

    @FXML
    void checkPlantable(DragEvent event) {
        Pane targetPane = (Pane) event.getSource();
        event.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML
    void selectedPlant(MouseEvent event) {
        ImageView selectedImageView = (ImageView) event.getSource();
        Dragboard dragboard = selectedImageView.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(selectedImageView.getImage());
        dragboard.setContent(content);
        selectedImageView.setOnDragDone(e -> {
            // Mengecek apakah operasi drag berhasil
            if (e.getTransferMode() == TransferMode.MOVE) {
                // Logika untuk menempatkan tanaman ke petak
                // Misalnya, Anda dapat menambahkan tanaman ke petak di sini
                Pane targetPane = (Pane) e.getSource(); // Mendapatkan petak yang menjadi target
                ImageView draggedPlant = (ImageView) e.getGestureSource(); // Mendapatkan gambar tanaman yang ditarik
                // Misalkan kita ingin menempatkan gambar tanaman ke dalam petak target
                targetPane.getChildren().add(draggedPlant);
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        java.net.URL resource = getClass().getClassLoader().getResource("assets/Music/Pool_Day_Song.mp3");
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
