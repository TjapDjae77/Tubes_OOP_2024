package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrepGame implements Initializable {

    @FXML private HBox hboxdeck;
    @FXML private Button letsrockbutton, menubutton;
    @FXML private GridPane inventoryGridPane;

    private ArrayList<InventoryPane> listinventory;
    private ArrayList<DeckPane> listdeck;
    private ArrayList<Pane> listdeckpane;
    private ArrayList<Pane> listinventorypane;

    private static ControllerPrepGame instance;

    public ControllerPrepGame() {
        instance = this;
    }

    public static ControllerPrepGame getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        letsrockbutton.setVisible(false);
        letsrockbutton.setDisable(true);
        initializeInventory();
        initializeGridPane();
        initializeDeck();
    }

    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        loadScene((Stage) menubutton.getScene().getWindow(), "/source/GUI/mainmenu.fxml");
    }

    @FXML
    private void goToPlay(MouseEvent event) throws IOException {
        loadScene((Stage) letsrockbutton.getScene().getWindow(), "/source/GUI/pvzgui.fxml");
    }

    private void loadScene(Stage stage, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage.setTitle("Michael vs. Lalapan");
        stage.setScene(new Scene(root));
    }

    @FXML
    private void choosePlant(MouseEvent event) { // onDragDetected method
        Node source = (Node) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        ImageView imageView = (ImageView) source;
        db.setDragView(((ImageView) source).getImage(), 50,70);
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(content);
        db.setDragView(((ImageView) source).getImage());
        event.consume();
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @FXML
    private void switchInventory(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            Node target = (Node) dragEvent.getGestureTarget();
            Node source = (Node) dragEvent.getGestureSource();

            if (target instanceof Pane && source instanceof ImageView) {
                Pane targetPane = (Pane) target;
                Pane sourcePane = (Pane) source.getParent();

                InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().get(0);
                InventoryPane tempInventoryPane = new InventoryPane(sourceInventoryPane);

                Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
                Image sourceImageActive = ((ImageView) source).getImage();

                InventoryPane targetInventoryPane = (InventoryPane) targetPane.getChildren().get(0);
                ImageView targetImageViewInactive = (ImageView) targetPane.getChildren().get(1);
                Image targetImageInactive = targetImageViewInactive.getImage();
                ImageView targetImageViewActive = (ImageView) targetPane.getChildren().get(2);
                Image targetImageActive = targetImageViewActive.getImage();

                if (!targetInventoryPane.isUsed()) {
                    swapInventoryPanes(sourceImageActive, sourceImageInactive, targetImageActive, targetImageInactive,
                            sourceInventoryPane, targetInventoryPane, tempInventoryPane, source, targetPane, sourcePane);
                    success = true;
                }
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    private void swapInventoryPanes(Image sourceImageActive, Image sourceImageInactive, Image targetImageActive, Image targetImageInactive,
                                    InventoryPane sourceInventoryPane, InventoryPane targetInventoryPane, InventoryPane tempInventoryPane,
                                    Node source, Pane targetPane, Pane sourcePane) {
        ((ImageView) source).setImage(targetImageActive);
        ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
        ((ImageView) targetPane.getChildren().get(2)).setImage(sourceImageActive);
        ((ImageView) targetPane.getChildren().get(1)).setImage(sourceImageInactive);

        sourceInventoryPane.setInventoryPane(targetInventoryPane);
        targetInventoryPane.setInventoryPane(tempInventoryPane);
    }

    @FXML
    private void insertToDeck(MouseEvent event) {
        Node source = (Node) event.getSource();
        Pane sourcePane = (Pane) source.getParent();
        InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().get(0);
        ImageView sourceImageViewInactive = new ImageView(((ImageView) sourcePane.getChildren().get(1)).getImage());
        ImageView sourceImageViewActive = new ImageView(((ImageView) source).getImage());

        if (listdeck.size() < 6 && !sourceInventoryPane.isUsed()) {
            DeckPane newDeck = new DeckPane(sourceInventoryPane.getPlantsName(), sourceImageViewActive, sourceImageViewInactive);
            setupDeckPane(newDeck);
            listdeck.add(newDeck);
            addDeckToPane(newDeck, sourcePane, sourceInventoryPane);
            checkCondition();
        }
    }

    private void setupDeckPane(DeckPane newDeck) {
        setupImageView(newDeck.getPlantImageInactive(), 120, 165, 3, 2);
        setupImageView(newDeck.getPlantImageActive(), 120, 165, 3, 2);
        newDeck.getPlantImageActive().setCursor(Cursor.HAND);
        newDeck.getPlantImageActive().setOnDragDetected(this::choosePlant);
    }

    private void addDeckToPane(DeckPane newDeck, Pane sourcePane, InventoryPane sourceInventoryPane) {
        for (Pane p : listdeckpane) {
            if (p.getChildren().isEmpty()) {
                p.getChildren().add(newDeck);
                p.getChildren().add(newDeck.getPlantImageInactive());
                p.getChildren().add(newDeck.getPlantImageActive());
                sourcePane.getChildren().get(2).setVisible(false);
                sourceInventoryPane.setUsed(true);
                break;
            }
        }
    }

    private void returnToDeck(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Pane sourcePane = (Pane) source;
        DeckPane deckPane = (DeckPane) sourcePane.getChildren().getFirst();
        String plantName = deckPane.getPlantsName();

        int index = findDeckIndex(plantName);
        if (index != -1) {
            removeDeckFromList(index);
            removeDeckFromPane(index);
            updateInventoryPane(plantName);
            checkCondition();
        }
    }

    private int findDeckIndex(String plantName) {
        for (int i = 0; i < listdeck.size(); i++) {
            if (listdeck.get(i).getPlantsName().equals(plantName)) {
                return i;
            }
        }
        return -1;
    }

    private void removeDeckFromList(int index) {
        listdeck.remove(index);
    }

    private void removeDeckFromPane(int index) {
        for (int i = index; i < listdeckpane.size() - 1; i++) {
            Pane currentPane = listdeckpane.get(i);
            Pane nextPane = listdeckpane.get(i + 1);
            if (!nextPane.getChildren().isEmpty()) {
                DeckPane nextDeckPane = (DeckPane) nextPane.getChildren().get(0);
                currentPane.getChildren().clear();
                currentPane.getChildren().add(nextDeckPane);
                currentPane.getChildren().add(nextDeckPane.getPlantImageInactive());
                currentPane.getChildren().add(nextDeckPane.getPlantImageActive());
            } else {
                currentPane.getChildren().clear();
            }
        }
        Pane lastPane = listdeckpane.get(listdeckpane.size() - 1);
        lastPane.getChildren().clear();
    }

    private void updateInventoryPane(String plantName) {
        for (InventoryPane inventoryPane : listinventory) {
            if (inventoryPane.getPlantsName().equals(plantName)) {
                inventoryPane.setUsed(false);
                int index = listinventory.indexOf(inventoryPane);
                Pane pane = listinventorypane.get(index);
                pane.getChildren().get(2).setVisible(true);
                break;
            }
        }
    }

    private void checkCondition() {
        boolean isDeckFull = listdeck.size() == 6;
        letsrockbutton.setVisible(isDeckFull);
        letsrockbutton.setDisable(!isDeckFull);
    }

    private void switchDeck(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            Node target = (Node) dragEvent.getGestureTarget();
            Node source = (Node) dragEvent.getGestureSource();

            if (target instanceof Pane && source instanceof ImageView) {
                Pane targetPane = (Pane) target;
                Pane sourcePane = (Pane) source.getParent();

                if(!targetPane.getChildren().isEmpty()) {

                    // Dapetin data-data pane di source
                    DeckPane sourceDeckPane = (DeckPane) sourcePane.getChildren().getFirst();
                    DeckPane tempDeckPane = new DeckPane(sourceDeckPane);
                    Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
                    Image sourceImageActive = ((ImageView) source).getImage();

                    // Dapetin data-data pane di target
                    DeckPane targetDeckPane = (DeckPane) targetPane.getChildren().getFirst();
                    ImageView targetImageViewInactive = (ImageView) targetPane.getChildren().get(1);
                    Image targetImageInactive = targetImageViewInactive.getImage();
                    ImageView targetImageViewActive = (ImageView) targetPane.getChildren().get(2);
                    Image targetImageActive = targetImageViewActive.getImage();

                    targetImageViewActive.setImage(sourceImageActive);
                    ((ImageView) source).setImage(targetImageActive);
                    ((DeckPane) sourcePane.getChildren().getFirst()).setDeckPane(targetDeckPane);
                    ((DeckPane) targetPane.getChildren().getFirst()).setDeckPane(sourceDeckPane);
                    targetImageViewInactive.setImage(sourceImageInactive);
                    ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
                    sourceDeckPane.setDeckPane(targetDeckPane);
                    targetDeckPane.setDeckPane(tempDeckPane);
                    success = true;
                }
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    private void initializeInventory(){
        listinventory = new ArrayList<>();
        listinventory.add(new InventoryPane("Sunflower"));
        listinventory.add(new InventoryPane("Peashooter"));
        listinventory.add(new InventoryPane("Wallnut"));
        listinventory.add(new InventoryPane("SnowPea"));
        listinventory.add(new InventoryPane("Squash"));
        listinventory.add(new InventoryPane("Lilypad"));
        listinventory.add(new InventoryPane("TangleKelp"));
        listinventory.add(new InventoryPane("SeaShroom"));
        listinventory.add(new InventoryPane("Tallnut"));
        listinventory.add(new InventoryPane("Spikeweed"));
        initializeInventoryPanes();
    }

    private void initializeInventoryPanes() {
        listinventorypane = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pane pane = new Pane();
            listinventorypane.add(pane);
        }
    }

    private void initializeGridPane() {
        int row = 0;
        int col = 0;
        for (InventoryPane inventory : listinventory) {
            Pane plantPane = listinventorypane.get((row*4) + col);
            setupPlantPane(plantPane, inventory);
            GridPane.setConstraints(plantPane, col, row);
            inventoryGridPane.getChildren().add(plantPane);
            col++;
            if(col > 3){
                col = 0;
                row++;
            }
        }
    }

    private void setupPlantPane(Pane plantPane, InventoryPane inventory) {
        plantPane.setPrefSize(200,200);
        plantPane.getChildren().add(inventory);

        ImageView inactiveImage = inventory.getPlantImageInactive();
        setupImageView(inactiveImage, 160, 220, 15, 9);

        ImageView activeImage = inventory.getPlantImageActive();
        setupImageView(activeImage, 160, 220, 15, 9);

        plantPane.getChildren().addAll(inactiveImage, activeImage);
        activeImage.setOnMouseClicked(this::insertToDeck);
        activeImage.setOnDragDetected(this::choosePlant);
        plantPane.setOnDragDropped(this::switchInventory);
        plantPane.setOnDragOver(this::handleDragOver);
        activeImage.setCursor(Cursor.HAND);
    }

    private void initializeDeck() {
        listdeck = new ArrayList<>();
        listdeckpane = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Pane deckPane = createDeckPane();
            listdeckpane.add(deckPane);
            hboxdeck.getChildren().add(deckPane);
        }
    }

    private Pane createDeckPane() {
        Pane pane = new Pane();
        pane.setOnDragOver(this::handleDragOver);
        pane.setOnDragDropped(this::switchDeck);
        pane.setOnMouseClicked(this::returnToDeck);
        return pane;
    }

    private void setupImageView(ImageView imageView, int fitWidth, int fitHeight, int layoutX, int layoutY) {
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        imageView.setLayoutX(layoutX);
        imageView.setLayoutY(layoutY);
    }

    public ArrayList<DeckPane> getListDeck() {
        return listdeck;
    }
}