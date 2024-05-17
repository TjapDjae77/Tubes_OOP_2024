package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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

public class ControllerPrepGame implements Initializable {
    @FXML
    private HBox hboxdeck;

    @FXML
    private Button letsrockbutton, menubutton;

    @FXML
    private GridPane inventoryGridPane;

    @FXML
    private AnchorPane layardasar;

    private boolean success;

    private ArrayList<InventoryPane> listinventory;

    private ArrayList<DeckPane> listdeck;

    private ArrayList<Pane> listdeckpane;

    private ArrayList<Pane> listinventorypane;

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        loadScene((Stage) menubutton.getScene().getWindow(), "/source/GUI/mainmenu.fxml");
    }

    @FXML
    void goToPlay(MouseEvent event) throws IOException {
        loadScene((Stage) letsrockbutton.getScene().getWindow(), "/source/GUI/pvzgui.fxml");
    }

    private void loadScene(Stage stage, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage.setTitle("Michael vs. Lalapan");
        stage.setScene(new Scene(root));
    }

    @FXML
    void choosePlant(MouseEvent event) { //onDragDetected Method
        Node source = (Node) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(content);

        db.setDragView(((ImageView) source).getImage());

        event.consume();
    }


    @FXML
    void handleDragOver(DragEvent event) {
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

            if(target instanceof Pane && source instanceof ImageView){
                Pane targetPane = (Pane) target;
                Pane sourcePane = (Pane) source.getParent();

                // Dapetin data-data pane di source
                InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().getFirst();
                InventoryPane tempInventoryPane = new InventoryPane(sourceInventoryPane);
                Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
                Image sourceImageActive = ((ImageView) source).getImage();

                // Dapetin data-data pane di target
                InventoryPane targetInventoryPane = (InventoryPane) targetPane.getChildren().getFirst();
                ImageView targetImageViewInactive = (ImageView) targetPane.getChildren().get(1);
                Image targetImageInactive = targetImageViewInactive.getImage();
                ImageView targetImageViewActive = (ImageView) targetPane.getChildren().get(2);
                Image targetImageActive  = targetImageViewActive.getImage();

                if(!targetInventoryPane.isUsed()) {
                    // swap data
                    targetImageViewActive.setImage(sourceImageActive);
                    ((ImageView) source).setImage(targetImageActive);
                    ((InventoryPane) sourcePane.getChildren().getFirst()).setInventoryPane(targetInventoryPane);
                    ((InventoryPane) targetPane.getChildren().getFirst()).setInventoryPane(sourceInventoryPane);
                    targetImageViewInactive.setImage(sourceImageInactive);
                    ((ImageView) sourcePane.getChildren().get(1)).setImage(targetImageInactive);
                    sourceInventoryPane.setInventoryPane(targetInventoryPane);
                    targetInventoryPane.setInventoryPane(tempInventoryPane);
                    success = true;
                }
            }
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    // Metode untuk memeriksa kondisi sudah bisa memulai game atau belum
    private void checkCondition() {
        if(listdeck.size() == 6){
            letsrockbutton.setVisible(true);
            letsrockbutton.setDisable(false);
        }
        else{
            letsrockbutton.setVisible(false);
            letsrockbutton.setDisable(true);
        }
    }

    @FXML
    private void insertToDeck(MouseEvent event){
        Node source = (Node) event.getSource();
        Pane sourcePane = (Pane) source.getParent();

        InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().get(0);
        Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
        Image sourceImageActive = ((ImageView) source).getImage();
        ImageView sourceImageViewInactive = new ImageView(sourceImageInactive);
        ImageView sourceImageViewActive = new ImageView(sourceImageActive);

        if(listdeck.size() < 6 && !sourceInventoryPane.isUsed()){
            DeckPane newDeck = new DeckPane(sourceInventoryPane.getPlantsName(), sourceImageViewActive, sourceImageViewInactive);
            setupImageView(newDeck.getPlantImageInactive(), 120,165,3,2);
            setupImageView(newDeck.getPlantImageActive(), 120,165,3,2);
            newDeck.getPlantImageActive().setOnDragDetected(this::choosePlant);
            listdeck.add(newDeck);
            for(Pane p : listdeckpane){
                if(p.getChildren().isEmpty()){
                    p.getChildren().add(newDeck);
                    p.getChildren().add(sourceImageViewInactive);
                    p.getChildren().add(sourceImageViewActive);
                    sourcePane.getChildren().get(2).setVisible(false);
                    sourceInventoryPane.setUsed(true);
                    break;
                }
            }
            checkCondition();

        }


//        Dragboard db = dragEvent.getDragboard();
//        boolean success = false;
//        if (db.hasString()) {
//            Node target = (Node) dragEvent.getGestureTarget();
//            Node source = (Node) dragEvent.getGestureSource();
//
//            if(target instanceof Pane && source instanceof ImageView){
//                Pane sourcePane = (Pane) source.getParent();
//                Pane targetPane = (Pane) target;
//                InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().getFirst();
//                Image sourceImageInactive = ((ImageView) sourcePane.getChildren().get(1)).getImage();
//                Image sourceImageActive = ((ImageView) source).getImage();
//                ImageView sourceImageViewActive = new ImageView(sourceImageActive);
//                ImageView sourceImageViewInactive = new ImageView(sourceImageInactive);
//                if(!sourceInventoryPane.isUsed() && targetPane.getChildren().isEmpty()){
//                    int i = 0;
//                    boolean found = false;
//                    while(i < listdeckpane.size() && !found){
//                        if(listdeckpane.get(i).equals(targetPane)){
//                            found = true;
//                        }
//                        else{
//                            i++;
//                        }
//                    }
//                    if(i == 0 || !listdeckpane.get(i-1).getChildren().isEmpty()) {
//                        DeckPane newDeck = new DeckPane(sourceInventoryPane.getPlantsName(), sourceImageViewActive, sourceImageViewInactive);
//                        setupImageView(newDeck.getPlantImageInactive(), 120, 165, 3, 2);
//                        setupImageView(newDeck.getPlantImageActive(), 120, 165, 3, 2);
//                        newDeck.getPlantImageActive().setOnDragDetected(this::choosePlant);
//                        listdeck.add(newDeck);
//                        targetPane.getChildren().add(newDeck);
//                        targetPane.getChildren().add(newDeck.getPlantImageActive());
//                        success = true;
//                    }
//
//                }
//            }
//        }
//        dragEvent.setDropCompleted(success);
//        if(success){
//            Node source = (Node) dragEvent.getGestureSource();
//            Pane sourcePane = (Pane) source.getParent();
//            InventoryPane sourceInventoryPane = (InventoryPane) sourcePane.getChildren().getFirst();
//            sourcePane.getChildren().get(2).setVisible(false);
//            sourceInventoryPane.setUsed(true);
//        }
//        for(DeckPane dp : listdeck){
//            System.out.println(dp.getPlantsName());
//        }
//        dragEvent.consume();
//        checkCondition();
    }

    private void returnToDeck(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Pane sourcepane = (Pane) source;
        DeckPane deckPane = (DeckPane) sourcepane.getChildren().getFirst();
        String plantName = deckPane.getPlantsName();

        int index = -1;
        int j = 0;
        for(int i = 0; i < listdeck.size(); i++){
            if(listdeck.get(i).getPlantsName().equals(plantName)){
                index = i;
                break;
            }
        }

        if(index != -1){
            listdeck.remove(index);

            for(int i = index; i < listdeckpane.size()-1; i++){
                Pane currentPane = listdeckpane.get(i);
                Pane nextPane = listdeckpane.get(i+1);
                if(!nextPane.getChildren().isEmpty()){
                    DeckPane nextDeckPane = (DeckPane) nextPane.getChildren().get(0);
                    currentPane.getChildren().clear();
                    currentPane.getChildren().add(nextDeckPane);
                    currentPane.getChildren().add(nextDeckPane.getPlantImageInactive());
                    currentPane.getChildren().add(nextDeckPane.getPlantImageActive());
                }
                else{
                    currentPane.getChildren().clear();
                }
            }
            Pane p = (Pane) hboxdeck.getChildren().get(5);
            p.getChildren().clear();

            for (InventoryPane inventoryPane : listinventory) {
                if (inventoryPane.getPlantsName().equals(plantName)) {
                    break;
                }
                else{
                    j++;
                }
            }
            List<Node> listPane = inventoryGridPane.getChildren();
            Pane pane = (Pane) listPane.get(j);
            InventoryPane inventoryPane = (InventoryPane) pane.getChildren().getFirst();
            inventoryPane.setUsed(false);
            pane.getChildren().get(2).setVisible(true);
            checkCondition();
        }
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        letsrockbutton.setVisible(false);
        letsrockbutton.setDisable(true);
        initializeInventory();
        initializeGridPane();
        initializeDeck();
    }



    private void initializeDeck(){
        listdeck = new ArrayList<>();
        listdeckpane = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            Pane pane = new Pane();
            pane.setPrefSize(123,165);
            pane.setOnMouseClicked(this::returnToDeck);
            pane.setOnDragDropped(this::switchDeck);
            pane.setOnDragOver(this::handleDragOver);
            listdeckpane.add(pane);
        }
        for(Pane pane : listdeckpane){
            hboxdeck.getChildren().add(pane);
        }
    }





    private void initializeInventory(){
        listinventory = new ArrayList<>();
        listinventory.add(new InventoryPane("Sunflower", "@../../assets/Seed Packet/Seeds_Sunflower.png", "@../../assets/Seed Packet/Seeds_Sunflower_Dark.png", false));
        listinventory.add(new InventoryPane("Peashooter", "@../../assets/Seed Packet/Seeds_Peashooter.png", "@../../assets/Seed Packet/Seeds_Peashooter_Dark.png", false));
        listinventory.add(new InventoryPane("Wallnut", "@../../assets/Seed Packet/Seeds_Wallnut.png", "@../../assets/Seed Packet/Seeds_Wallnut_Dark.png", false));
        listinventory.add(new InventoryPane("SnowPea", "@../../assets/Seed Packet/Seeds_SnowPea.png", "@../../assets/Seed Packet/Seeds_SnowPea_Dark.png", false));
        listinventory.add(new InventoryPane("Squash", "@../../assets/Seed Packet/Seeds_Squash.png", "@../../assets/Seed Packet/Seeds_Squash_Dark.png", false));
        listinventory.add(new InventoryPane("Lilypad", "@../../assets/Seed Packet/Seeds_Lilypad.png", "@../../assets/Seed Packet/Seeds_Lilypad_Dark.png", false));
        listinventory.add(new InventoryPane("TangleKelp", "@../../assets/Seed Packet/Seeds_TangleKelp.png", "@../../assets/Seed Packet/Seeds_TangleKelp_Dark.png", false));
        listinventory.add(new InventoryPane("SeaShroom", "@../../assets/Seed Packet/Seeds_SeaShroom.png", "@../../assets/Seed Packet/Seeds_SeaShroom_Dark.png", false));
        listinventory.add(new InventoryPane("Tallnut", "@../../assets/Seed Packet/Seeds_Tallnut.png", "@../../assets/Seed Packet/Seeds_Tallnut_Dark.png", false));
        listinventory.add(new InventoryPane("Spikeweed", "@../../assets/Seed Packet/Seeds_Spikeweed.png", "@../../assets/Seed Packet/Seeds_Spikeweed_Dark.png", false));
        initializeInventoryPanes();
    }

    private void initializeInventoryPanes() {
        listinventorypane = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Pane pane = new Pane();
            pane.setId("inv" + (i + 1));
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
    }

    private void setupImageView(ImageView imageView, int fitwidth, int fitheight, int layoutx, int layouty) {
        imageView.setFitWidth(fitwidth);
        imageView.setFitHeight(fitheight);
        imageView.setLayoutX(layoutx);
        imageView.setLayoutY(layouty);
    }

}