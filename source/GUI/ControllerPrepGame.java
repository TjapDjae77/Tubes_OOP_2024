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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class ControllerPrepGame implements Initializable {

    @FXML private HBox hboxdeck;
    @FXML private Button letsrockbutton, menubutton;
    @FXML private GridPane inventoryGridPane;
    @FXML private AnchorPane layardasar;

    private ArrayList<InventoryPane> listinventory;
    private ArrayList<DeckPane> listdeck;
    private ArrayList<Pane> listdeckpane;
    private ArrayList<Pane> listinventorypane;

    private static ControllerPrepGame instance;

    private int x=0;

    private boolean isNight = false;

    public boolean getDayStatus(){
        return isNight;
    }

    public void setToNight(boolean setNight) {
        this.isNight = setNight;
    }

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
        setupPane(pane);
        return pane;
    }

    private void setupPane(Pane pane){
        pane.setOnDragOver(this::handleDragOver);
        pane.setOnDragDropped(this::switchDeck);
        pane.setOnMouseClicked(this::returnToDeck);
    }

    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        loadScene((Stage) menubutton.getScene().getWindow(), "/source/GUI/mainmenu.fxml");
    }

    @FXML
    private void goToPlay(MouseEvent event) throws IOException {
        loadScene((Stage) letsrockbutton.getScene().getWindow(), "/source/GUI/pvzgui.fxml");
        Timer timer = new Timer();
        TimerTask nightIsHere = new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() -> {
                    ControllerMainGame.getInstanceGame().switchToNight();
                    setToNight(true);
                });
            }
        };
        timer.schedule(nightIsHere, 100000);
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
                    swapInventoryPanes(targetImageViewActive, targetImageViewInactive, sourceImageActive, sourceImageInactive, targetImageActive, targetImageInactive,
                            sourceInventoryPane, targetInventoryPane, tempInventoryPane, source, targetPane, sourcePane);
                    success = true;
                }
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    private void swapInventoryPanes(ImageView targetImageViewActive, ImageView targetImageViewInactive, Image sourceImageActive, Image sourceImageInactive, Image targetImageActive, Image targetImageInactive,
                                    InventoryPane sourceInventoryPane, InventoryPane targetInventoryPane, InventoryPane tempInventoryPane,
                                    Node source, Pane targetPane, Pane sourcePane) {
        targetImageViewActive.setImage(sourceImageActive);
        ((ImageView) source).setImage(targetImageActive);
        ((InventoryPane) sourcePane.getChildren().getFirst()).setInventoryPane(targetInventoryPane);
        ((InventoryPane) targetPane.getChildren().getFirst()).setInventoryPane(sourceInventoryPane);
        targetImageViewInactive.setImage(sourceImageInactive);
        ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
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
        Pane p = new Pane();
        setupPane(p);
        p.getChildren().add(newDeck);
        p.getChildren().add(newDeck.getPlantImageInactive());
        p.getChildren().add(newDeck.getPlantImageActive());
        listdeckpane.set(listdeck.size()-1, p);
        hboxdeck.getChildren().set(listdeck.size()-1, p);
        sourcePane.getChildren().get(2).setVisible(false);
        sourceInventoryPane.setUsed(true);

//        int x = 0;
//        for(Pane pp : listdeckpane){
//            if(!pp.getChildren().isEmpty()){
//                ImageView img = ((ImageView) pp.getChildren().get(2));
//                layardasar.getChildren().add(pp.getChildren().get(2));
//                x += 150;
//            }
//        }

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


//                    int sourceindex=0;
//                    int targetindex=0;
//                    listdeck.get(sourceindex).setDeckPane(targetDeckPane);
//                    listdeck.get(targetindex).setDeckPane(sourceDeckPane);
//
//                    listdeck.get(sourceindex).setPlantImageInactive(((ImageView)targetPane.getChildren().get(1)).getImage());
//                    listdeck.get(targetindex).setPlantImageInactive(((ImageView)sourcePane.getChildren().get(1)).getImage());
//
//                    listdeck.get(sourceindex).setPlantImageActive(((ImageView)targetPane.getChildren().get(2)).getImage());
//                    listdeck.get(targetindex).setPlantImageActive(((ImageView)sourcePane.getChildren().get(2)).getImage());

                    success = true;

//        if (db.hasString()) {
//            Node target = (Node) dragEvent.getGestureTarget();
//            Node source = (Node) dragEvent.getGestureSource();
//
//            if (target instanceof Pane && source instanceof ImageView) {
//                Pane targetPane = (Pane) target;
//                Pane sourcePane = (Pane) source.getParent();
//
//                // Data Source DeckPane
////                DeckPane sourceDeckPane = (DeckPane) sourcePane.getChildren().get(0);
//
//                // Data Temporary DeckPane berisi Source Pane
////                DeckPane tempDeckPane = new DeckPane(sourceDeckPane);
//
//                // Data Target Deck Pane
////                DeckPane targetDeckPane = (DeckPane) targetPane.getChildren().get(0);
//
//
//                // Dapetin data-data pane di source
//                DeckPane sourceDeckPane = (DeckPane) sourcePane.getChildren().getFirst();
//                DeckPane tempDeckPane = new DeckPane(sourceDeckPane);
//
//                Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
//                Image sourceImageActive = ((ImageView) source).getImage();
//
//                // Dapetin data-data pane di target
//                DeckPane targetDeckPane = (DeckPane) targetPane.getChildren().getFirst();
//
//                ImageView targetImageViewInactive = (ImageView) targetPane.getChildren().get(1);
//                Image targetImageInactive = targetImageViewInactive.getImage();
//
//                ImageView targetImageViewActive = (ImageView) targetPane.getChildren().get(2);
//                Image targetImageActive = targetImageViewActive.getImage();
//
//
//
//
//                // AMAN
////                System.out.println("Data Source Deck Pane: " + sourceDeckPane.getPlantsName());
////                System.out.println("Data Target Deck Pane: " + targetDeckPane.getPlantsName());
//
//
//                // Data Image View Inactive dari Source Pane
//                ImageView newsourceImageViewInactive = new ImageView(((ImageView) sourcePane.getChildren().get(1)).getImage());
//                Image newsourceImageInactive = (newsourceImageViewInactive).getImage();
//
//                // AMAN
////                sourceImageViewInactive.setLayoutX(x);
////                layardasar.getChildren().add(sourceImageViewInactive);
////                x += 150;
//
//                // Data Image View Active dari Source Pane
//                ImageView newsourceImageViewActive = new ImageView(((ImageView) sourcePane.getChildren().get(2)).getImage());
//                Image newsourceImageActive = (newsourceImageViewActive).getImage();
//
//                // AMAN
////                sourceImageViewActive.setLayoutX(x);
////                layardasar.getChildren().add(sourceImageViewActive);
////                x+=150;
//
//                // Data Image View Inactive dari Target Pane
//                ImageView newtargetImageViewInactive = new ImageView(((ImageView) targetPane.getChildren().get(1)).getImage());
//                Image newtargetImageInactive = newtargetImageViewInactive.getImage();
//
//                // AMAN
////                targetImageViewInactive.setLayoutX(x);
////                layardasar.getChildren().add(targetImageViewInactive);
////                x+=150;
//
//
//                // Data Image View Active dari Target Pane
//                ImageView newtargetImageViewActive = new ImageView(((ImageView) targetPane.getChildren().get(2)).getImage());
//                Image newtargetImageActive = newtargetImageViewActive.getImage();
//
//                // AMAN
////                targetImageViewActive.setLayoutX(x);
////                layardasar.getChildren().add(targetImageViewActive);
////                x+=150;
//
////                ImageView tempImageViewActive = new ImageView(sourceImageActive);
////                ImageView tempImageViewInactive = new ImageView(sourceImageInactive);
//
//
//
////                setupImageView(tempImageViewActive, 120, 165, 3, 2);
////                setupImageView(tempImageViewInactive, 120, 165, 3, 2);
//
//                int sourceindex=0;
//                int targetindex=0;
//                if(!targetPane.getChildren().isEmpty()){
//                    targetImageViewActive.setImage(sourceImageActive);
//                    ((ImageView) source).setImage(targetImageActive);
//                    targetImageViewInactive.setImage(sourceImageInactive);
//                    ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
//
//
////                    ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
////                    ((ImageView) targetPane.getChildren().get(1)).setImage(sourceImageInactive);
////                    ((ImageView) sourcePane.getChildren().get(2)).setImage(targetImageActive);
////                    ((ImageView) targetPane.getChildren().get(2)).setImage(sourceImageActive);
//
//                    sourceindex = listdeck.indexOf(sourceDeckPane);
//                    targetindex = listdeck.indexOf(targetDeckPane);
//
//                    // Swap Panes
//                    Pane tempPane1 = new Pane();
//                    setupPane(tempPane1);
//
//                    tempPane1.getChildren().addAll(
//                            new DeckPane(sourceDeckPane),
//                            new ImageView(sourceImageInactive),
//                            new ImageView(sourceImageActive)
//                    );
//
//                    Pane tempPane2 = new Pane();
//                    setupPane(tempPane2);
//
//                    tempPane2.getChildren().addAll(
//                            new DeckPane(targetDeckPane),
//                            new ImageView(targetImageInactive),
//                            new ImageView(targetImageActive)
//                    );
//
////                    tempPane1.getChildren().get(2).setCursor(Cursor.HAND);
////                    tempPane1.getChildren().get(2).setOnDragDetected(this::choosePlant);
////                    tempPane2.getChildren().get(2).setCursor(Cursor.HAND);
////                    tempPane2.getChildren().get(2).setOnDragDetected(this::choosePlant);
//
////                    listdeckpane.set(sourceindex, targetPane);
////                    listdeckpane.set(targetindex, tempPane2);
//
//                    ((DeckPane)listdeckpane.get(sourceindex).getChildren().get(0)).setDeckPane((DeckPane) tempPane2.getChildren().get(0));
//                    ((DeckPane)listdeckpane.get(targetindex).getChildren().get(0)).setDeckPane((DeckPane) tempPane1.getChildren().get(0));
//
////                    ImageView image1 = (ImageView) listdeckpane.get(sourceindex).getChildren().get(1);
//////                    image1.setImage(targetImageViewInactive.getImage());
////
////                    ImageView image2 = (ImageView) listdeckpane.get(targetindex).getChildren().get(1);
//////                    image2.setImage(sourceImageViewInactive.getImage());
//
//                    newtargetImageViewInactive.setLayoutX(x);
//                    layardasar.getChildren().add(newtargetImageViewInactive);
//                    x += 150;
//
//                    newsourceImageViewInactive.setLayoutX(x);
//                    layardasar.getChildren().add(newsourceImageViewInactive);
//                    x += 150;
//
////                    ((ImageView)listdeckpane.get(sourceindex).getChildren().get(1)).setImage(((ImageView)tempPane2.getChildren().get(1)).getImage());
////                    ((ImageView)listdeckpane.get(targetindex).getChildren().get(1)).setImage(((ImageView)tempPane1.getChildren().get(1)).getImage());
////
////                    ((ImageView)listdeckpane.get(sourceindex).getChildren().get(2)).setImage(((ImageView)tempPane2.getChildren().get(2)).getImage());
////                    ((ImageView)listdeckpane.get(targetindex).getChildren().get(2)).setImage(((ImageView)tempPane1.getChildren().get(2)).getImage());
//
//
//
//
//                    listdeck.get(sourceindex).setDeckPane((DeckPane) tempPane2.getChildren().get(0));
//                    listdeck.get(targetindex).setDeckPane((DeckPane) tempPane1.getChildren().get(0));
//
//                    listdeck.get(sourceindex).setPlantImageInactive(((ImageView)tempPane2.getChildren().get(1)).getImage());
//                    listdeck.get(targetindex).setPlantImageInactive(((ImageView)tempPane1.getChildren().get(1)).getImage());
//
//                    listdeck.get(sourceindex).setPlantImageActive(((ImageView)tempPane2.getChildren().get(2)).getImage());
//                    listdeck.get(targetindex).setPlantImageActive(((ImageView)tempPane1.getChildren().get(2)).getImage());
//
//
//                }
//                System.out.println("--------DECK AFTER SWITCH--------");
////                int z = 0;
////                for(DeckPane dp: listdeck){
////                    System.out.println(dp.getPlantsName());
////                    ImageView imgA = new ImageView(dp.getPlantImageActive().getImage());
////                    imgA.setLayoutY(300);
////                    imgA.setLayoutX(z);
////                    layardasar.getChildren().add(imgA);
////                    z += 150;
////
////                }
//
//                System.out.println("--------DECK PANE AFTER SWITCH--------");
//                for(Pane p: listdeckpane){
//                    System.out.println(((DeckPane) p.getChildren().getFirst()).getPlantsName());
////                    ImageView img1 = ((ImageView) p.getChildren().get(1));
////                    img1.setLayoutX(x);
////                    layardasar.getChildren().add(img1);
////                    x += 150;
//                }
//                success = true;
                }
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }
//                    // Dapetin data-data pane di source
//                    DeckPane sourceDeckPane = (DeckPane) sourcePane.getChildren().getFirst();
//                    DeckPane tempDeckPane = new DeckPane(sourceDeckPane);
//                    Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
//                    Image sourceImageActive = ((ImageView) source).getImage();
//
//                    // Dapetin data-data pane di target
//                    DeckPane targetDeckPane = (DeckPane) targetPane.getChildren().getFirst();
//                    ImageView targetImageViewInactive = (ImageView) targetPane.getChildren().get(1);
//                    Image targetImageInactive = targetImageViewInactive.getImage();
//                    ImageView targetImageViewActive = (ImageView) targetPane.getChildren().get(2);
//                    Image targetImageActive = targetImageViewActive.getImage();
//
//                    System.out.println("--------DECK BEFORE SWITCH--------");
//                    System.out.println("Source Deck: " + sourceDeckPane.getPlantsName());
//                    System.out.println("Target Deck: " + targetDeckPane.getPlantsName());
//                    for(DeckPane dp: listdeck){
//                        System.out.println(dp.getPlantsName());
//                    }
//
//                    int sourceindex = listdeck.indexOf(sourceDeckPane);
//                    int targetindex = listdeck.indexOf(targetDeckPane);
//
//                    listdeck.set(sourceindex, sourceDeckPane);
//                    listdeck.set(targetindex, targetDeckPane);
//
//                    targetImageViewActive.setImage(sourceImageActive);
//                    ((ImageView) source).setImage(targetImageActive);
//                    targetImageViewInactive.setImage(sourceImageInactive);
//                    ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
//                    ((DeckPane) sourcePane.getChildren().getFirst()).setDeckPane(targetDeckPane);
//                    ((DeckPane) targetPane.getChildren().getFirst()).setDeckPane(sourceDeckPane);
//                    sourceDeckPane.setDeckPane(targetDeckPane);
//                    targetDeckPane.setDeckPane(tempDeckPane);
//                    success = true;



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