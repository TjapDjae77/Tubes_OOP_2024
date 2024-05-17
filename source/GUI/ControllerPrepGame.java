package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrepGame implements Initializable {
    @FXML
    private HBox hboxdeck;

    @FXML
    private Pane inv1;

    @FXML
    private Pane inv10;

    @FXML
    private Pane inv2;

    @FXML
    private Pane inv3;

    @FXML
    private Pane inv4;

    @FXML
    private Pane inv5;

    @FXML
    private Pane inv6;

    @FXML
    private Pane inv7;

    @FXML
    private Pane inv8;

    @FXML
    private Pane inv9;

    @FXML
    private Button menubutton;

    @FXML
    private Pane paneSlot1;

    @FXML
    private Pane paneSlot2;

    @FXML
    private Pane paneSlot3;

    @FXML
    private Pane paneSlot4;

    @FXML
    private Pane paneSlot5;

    @FXML
    private Pane paneSlot6;

    @FXML
    private ImageView plant1;

    @FXML
    private ImageView plant10;

    @FXML
    private ImageView plant2;

    @FXML
    private ImageView plant3;

    @FXML
    private ImageView plant4;

    @FXML
    private ImageView plant5;

    @FXML
    private ImageView plant6;

    @FXML
    private ImageView plant7;

    @FXML
    private ImageView plant8;

    @FXML
    private ImageView plant9;

    @FXML
    private Button startGameButton;

    private boolean success;

    private List<InventoryPane> listinventory;

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        Stage mainMenu = (Stage) menubutton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/mainmenu.fxml"));
        mainMenu.setTitle("Plants vs. Zombies");
        mainMenu.setScene(new Scene(root));
    }

    @FXML
    void choosePlant(MouseEvent event) { //onDragDetected Method
        ImageView source = (ImageView) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

        ClipboardContent content = new ClipboardContent();
        content.putImage(source.getImage());
        db.setContent(content);

        event.consume();
    }

    @FXML
    void goToPlay(MouseEvent event) throws IOException {
        Stage mainGame = (Stage) startGameButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/pvzgui.fxml"));
        mainGame.setTitle("Plants vs. Zombies");
        mainGame.setScene(new Scene(root));
    }

    @FXML
    void insertPlant(DragEvent event) { //onDragDrop Method
        Dragboard db = event.getDragboard();
        success = false;

        if (db.hasImage()) {
            ImageView plant = new ImageView(db.getImage());
            Pane targetPane = (Pane) event.getGestureTarget();
            targetPane.getChildren().add(plant);
            success = true;
            checkCondition();
        }

        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    void insertToDeck(MouseDragEvent event) { //onMouseDragReleased Method

    }

    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    // Metode untuk memeriksa kondisi
    private void checkCondition() {
        List<Node> listPane = hboxdeck.getChildren();
        boolean isSuccess = true;
        for(Node node : listPane){
            Pane pane = (Pane) node;
            if(pane.getChildren().isEmpty()){
                isSuccess = false;
            }
        }
        if (isSuccess) {
            // Jika kondisi terpenuhi, munculkan tombol dan aktifkan
            startGameButton.setVisible(true);
            startGameButton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGameButton.setVisible(false);
        startGameButton.setDisable(true);
        // for loop inisialisasi inventory pane pake semua plants sebagi active imagenya sekaligus dark image
        // terus dimasukkin ke grid pane (add children), terus diset size
    }

    private void initializeInventory(){
        listinventory = new ArrayList<>();

    }

}
