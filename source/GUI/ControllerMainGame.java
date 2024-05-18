package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMainGame implements Initializable {

    @FXML
    private AnchorPane layardasar;

    @FXML
    private GridPane gridtilesmap;

    @FXML
    private HBox hboxdeck;


    @FXML
    private Pane shovelPane;

    @FXML
    private Label sunValue;

    @FXML
    private Button menubutton;

    MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerPrepGame prepGameController = ControllerPrepGame.getInstance();
        ArrayList<DeckPane> deck = prepGameController.getListDeck();

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

        initializeDeck(deck);
        initializeGridPane();
        initializeShovelPane();
    }

    private void initializeDeck(ArrayList<DeckPane> deck) {
        ArrayList<Pane> listdeckpane = new ArrayList<>();
        for(int i = 0; i < deck.size(); i++) {
            Pane deckpane = createDeckPane();
            listdeckpane.add(deckpane);
            hboxdeck.getChildren().add(deckpane);
        }
        int i = 0;
        for (Pane p : listdeckpane) {
            DeckPane dp = (DeckPane) deck.get(i);
            p.setId("Slot"+i);
            p.getChildren().add(dp);
            ImageView imageViewInactive = dp.getPlantImageInactive();
            setupImageView(imageViewInactive, 90, 126, 0,0);
            ImageView imageViewActive = dp.getPlantImageActive();
            setupImageView(imageViewActive, 90, 126, 0,0);
            ImageView imageViewOriginal = dp.getPlantImageOriginal();
            setupImageView(imageViewOriginal, 126, 126, 0,0);
            imageViewOriginal.setOpacity(0);
            imageViewActive.setOnDragDetected(null);
            imageViewOriginal.setOnDragDetected(this::chooseplant);
            imageViewOriginal.setCursor(Cursor.HAND);
            p.getChildren().add(imageViewInactive);
            p.getChildren().add(imageViewActive);
            p.getChildren().add(imageViewOriginal);
            i++;
        }
    }




    private Pane createDeckPane() {
        Pane deckpane = new Pane();
        deckpane.setOnDragOver(this::handleDragOver);
        return deckpane;
    }

    private void initializeGridPane(){
        ArrayList<Pane> tilescontentpane = new ArrayList<>();
        int row = 0;
        int col = 0;
        for(int i = 0; i < 66; i++){
            Pane pane = new Pane();
            if(i % 11 != 0 && i % 11 != 10){
                pane.setOnDragDropped(this::operationOnPlant);
                pane.setOnDragEntered(this::foreshadowingplant);
                pane.setOnDragExited(this::diseappearingplant);
                pane.setOnDragOver(this::handleDragOver);
            }
            tilescontentpane.add(pane);
        }
        for(Pane p : tilescontentpane){
            GridPane.setConstraints(p, col, row);
            gridtilesmap.getChildren().add(p);
            col++;
            if(col > 10){
                col = 0;
                row++;
            }
        }
    }

    private void initializeShovelPane(){
        ImageView imageViewShovelPane = new ImageView(new Image("assets/Menu/ShovelPane.png"));
        setupImageView(imageViewShovelPane, 153, 146, 0,0);
        ImageView shovel = new ImageView(new Image("assets/Menu/Shovel2.png"));
        shovel.setLayoutX(12);
        shovel.setLayoutY(10);
        shovel.setOnDragDetected(this::shovelplant);
        shovel.setOnDragDone(this::showshovel);
        shovelPane.getChildren().add(imageViewShovelPane);
        shovelPane.getChildren().add(shovel);
    }

    private void operationOnPlant(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            Node target = (Node) dragEvent.getGestureTarget();
            Node source = (Node) dragEvent.getGestureSource();
            if (target instanceof Pane && source instanceof ImageView) {
                Pane targetPane = (Pane) target;
                Pane sourcePane = (Pane) source.getParent();
                if(sourcePane.getId().equals("shovelPane")){
                    targetPane.getChildren().clear();
                }
                else {
                    ImageView sourceImage = (ImageView) sourcePane.getChildren().get(3);
                    ImageView duplicateImage = new ImageView(sourceImage.getImage());
                    setupImageView(duplicateImage, 126, 126, 0, 0);
                    duplicateImage.setOpacity(0);
                    duplicateImage.setOnDragDetected(this::chooseplant);
                    sourcePane.getChildren().add(duplicateImage);
                    sourceImage.setFitWidth(0);
                    sourceImage.setFitHeight(0);
                    sourceImage.setOpacity(1);
                    targetPane.getChildren().add(sourceImage);
                }
            }

            success = true;
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    private void foreshadowingplant(DragEvent dragEvent) {
//        Dragboard db = dragEvent.getDragboard();
//
//        if (db.hasString()) {
//            Node target = (Node) dragEvent.getGestureTarget();
//            Node source = (Node) dragEvent.getGestureSource();
//            if (target instanceof Pane && source instanceof ImageView) {
//                Pane targetPane = (Pane) target;
//                Pane sourcePane = (Pane) source.getParent();
//                ImageView sourceImage = (ImageView) sourcePane.getChildren().get(3);
//                sourceImage.setFitWidth(0);
//                sourceImage.setFitHeight(0);
//                sourceImage.setOpacity(0.5);
//                targetPane.getChildren().add(sourceImage);
//            }
//        }
//        dragEvent.consume();


//        Dragboard db = dragEvent.getDragboard();
//
//        if (db.hasString()) {
//            Node target = (Node) dragEvent.getGestureTarget();
//            Node source = (Node) dragEvent.getGestureSource();
//            if (target instanceof Pane && source instanceof ImageView) {
//                Pane targetPane = (Pane) target;
//                ImageView sourceImage = (ImageView) source;
//                ImageView shadowImage = new ImageView(sourceImage.getImage());
//                shadowImage.setOpacity(0.5);
//                shadowImage.setFitWidth(0);
//                shadowImage.setFitHeight(0);
//                shadowImage.setId("shadowImage");
//                targetPane.getChildren().add(shadowImage);
//                System.out.println(targetPane.getChildren().getFirst().getClass().getSimpleName());
//            }
//        }
//        dragEvent.consume();

        Dragboard db = dragEvent.getDragboard();

        if (db.hasString()) {
            Node target = (Node) dragEvent.getTarget();
            Node source = (Node) dragEvent.getGestureSource();
            if (target instanceof Pane && source instanceof ImageView) {
                Pane targetPane = (Pane) target;
                ImageView sourceImage = (ImageView) source;
                ImageView shadowImage = new ImageView(sourceImage.getImage());
                shadowImage.setOpacity(0.5);
                shadowImage.setFitWidth(0);
                shadowImage.setFitHeight(0);
                shadowImage.setId("shadowImage");  // Set an ID to identify this shadow image later
                targetPane.getChildren().add(shadowImage);
            }
        }
        dragEvent.consume();
    }

    private void diseappearingplant(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();

        if (db.hasString()) {
            Node target = (Node) dragEvent.getTarget();
            if (target instanceof Pane) {
                Pane targetPane = (Pane) target;
                Node shadowImage = targetPane.lookup("#shadowImage");  // Look for the shadow image by its ID
                if (shadowImage != null) {
                    targetPane.getChildren().remove(shadowImage);
                }
            }
        }
        dragEvent.consume();
    }
    private void chooseplant(MouseEvent event) {
        Node source = (Node) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        ImageView imageView = (ImageView) source;
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(content);
        ImageView duplicateImage = new ImageView(imageView.getImage());
        duplicateImage.setOpacity(1);
        db.setDragView(duplicateImage.getImage(), 70, 70);
        event.consume();
    }

    private void shovelplant(MouseEvent event) {
        Node source = (Node) event.getSource();
        Pane shovelpane = (Pane) source.getParent();
        shovelpane.getChildren().get(1).setVisible(false);
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        ImageView imageView = (ImageView) source;
        db.setDragView(imageView.getImage(), 70, 70);
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(content);
        db.setDragView(((ImageView) source).getImage());
        event.consume();
    }

    private void showshovel(DragEvent dragEvent) {
        Node source = (Node) dragEvent.getSource();
        Pane shovelpane = (Pane) source.getParent();
        shovelpane.getChildren().get(1).setVisible(true);
    }

    private void setupImageView(ImageView image, int fitwidth, int fitheight, int layoutX, int layoutY) {
        image.setFitWidth(fitwidth);
        image.setFitHeight(fitheight);
        image.setLayoutX(layoutX);
        image.setLayoutY(layoutY);
    }

    private void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getGestureSource() != dragEvent.getTarget() && dragEvent.getDragboard().hasString()) {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
        dragEvent.consume();
    }

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        loadScene((Stage) menubutton.getScene().getWindow(), "/source/GUI/mainmenu.fxml");
    }

    private void loadScene(Stage stage, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage.setTitle("Michael vs. Lalapan");
        stage.setScene(new Scene(root));
        mediaPlayer.stop();
    }

    @FXML
    void startGame() {


    }
}
