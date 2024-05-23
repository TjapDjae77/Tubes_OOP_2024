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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;

import source.Characters.Plants.*;
import source.Sun.Sun;
import source.Map.*;

import javafx.application.Platform;

public class ControllerMainGame implements Initializable {

    @FXML
    private AnchorPane layardasar;

    @FXML
    private AnchorPane zombieArea;

    @FXML
    private ImageView loseImage;

    @FXML
    private ImageView winImage;

    @FXML
    private GridPane gridtilesmap;

    @FXML
    private HBox hboxdeck;

    @FXML
    private Pane paneEndGame;

    @FXML
    private Pane shovelPane;

    @FXML
    private Label sunValue;

    @FXML
    private Button menubutton;

    @FXML
    private Button quitButton;

    MediaPlayer mediaPlayer;

    WalkingZombieSpawner wzs = new WalkingZombieSpawner();

    private ArrayList<DeckPane> deck;

    private ArrayList<Pane> listdeckpane;

    private Sun sun;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    Timer timer = new Timer();

    private static ControllerMainGame instanceGame;

    private static WalkingZombieSpawner walkingZombieSpawner;


    public ControllerMainGame() {
        instanceGame = this;
    }

    public static ControllerMainGame getInstanceGame() {
        return instanceGame;
    }

    public GridPane getGridtilesmap(){
        return gridtilesmap;
    }

    public AnchorPane getZombieArea() {
        return zombieArea;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = new ArrayList<>();
        listdeckpane = new ArrayList<>();
        ControllerPrepGame prepGameController = ControllerPrepGame.getInstance();
        deck = prepGameController.getListDeck();

        quitButton.setDisable(true);

        Timer tm = new Timer();

        java.net.URL resource = getClass().getClassLoader().getResource("assets/Music/Pool_Day_Song.mp3");
        if (resource == null) {
            System.err.println("Could not find file: " + "assets/Music/Main_Menu_Song.mp3");
            return;
        }
        String path = resource.getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();

        loseImage.setVisible(false);
        winImage.setVisible(false);

        initializeDeck(deck);
        initializeShovelPane();
        initializeGridPane();
        initializeSun();

        startUpdatingDeckPaneAvailability();

        walkingZombieSpawner = new WalkingZombieSpawner();

        Sun.sunProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> sunValue.setText(newValue.toString()));

        });

        walkingZombieSpawner.startSpawning();
    }

    private void startUpdatingDeckPaneAvailability() {
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(this::updateDeckPaneAvailability);
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void initializeSun(){
        sun = new Sun();
        sunValue.setText(String.valueOf(Sun.getSun()));
    }

    private void initializeDeck(ArrayList<DeckPane> initialdeck) {
        int x = 0;
        for(int i = 0; i < initialdeck.size(); i++) {
            ImageView img1 = new ImageView(new Image("@../../assets/Seed Packet/Seeds_" + initialdeck.get(i).getPlantsName() + "_Dark.png"));
            setupImageView(img1, 90, 126, 0,0);

            ImageView img2 = new ImageView(new Image("@../../assets/Seed Packet/Seeds_" + initialdeck.get(i).getPlantsName() + ".png"));
            setupImageView(img2, 90, 126, 0,0);

            ImageView img3 = null;

            if(!initialdeck.get(i).getPlantsName().equals("Peashooter")){
                img3 = new ImageView(new Image("@../../assets/Plants/PVZ_" + initialdeck.get(i).getPlantsName() + ".png"));
            }
            else {
                img3 = new ImageView(new Image("@../../assets/Plants/PVZ_" + initialdeck.get(i).getPlantsName() + "2.png"));
            }

            setupImageView(img3, 126, 126, 0,0);

            img3.setOpacity(0);
            img2.setOnDragDetected(null);
            img2.setVisible(false);
            if (Sun.getSun() >= initialdeck.get(i).getPlants().getCost() && !initialdeck.get(i).getPlants().getCDStatus()) {
                img3.setOnDragDetected(this::chooseplant);
                img3.setCursor(Cursor.HAND);
                img2.setVisible(true);
            }
            else {
                img3.setCursor(Cursor.DEFAULT);
            }
            Pane pane = new Pane();
            pane.setId("Plant"+i);
            pane.getChildren().addAll(initialdeck.get(i), img1, img2, img3);

            listdeckpane.add(pane);
            hboxdeck.getChildren().add(pane);
        }

    }

    private void updateDeckPaneAvailability(){
        int i = 0;

        for(Pane pane : listdeckpane){
            DeckPane dp = (DeckPane) pane.getChildren().getFirst();
            ImageView img3 = (ImageView) pane.getChildren().get(3);
            ImageView img2 = (ImageView) pane.getChildren().get(2);

            if(Sun.getSun() >= dp.getPlants().getCost() && !dp.getPlants().getCDStatus()){
                img3.setOnDragDetected(this::chooseplant);
                img3.setCursor(Cursor.HAND);
                img2.setVisible(true);

            }
            else {
                img3.setOnDragDetected(null);
                img3.setCursor(Cursor.DEFAULT);
                img2.setVisible(false);

            }
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

        int row = 0;
        int col = 0;

        for(Pane p : tilescontentpane){
            if(col == 0){
                Tiles tiles = createTiles("Protected");
                TilesPane tilesPane = new TilesPane(tiles);
                p.getChildren().add(tilesPane);
            }
            else if(col == 10){
                Tiles tiles = createTiles("Spawn");
                TilesPane tilesPane = new TilesPane(tiles);
                p.getChildren().add(tilesPane);
            }
            else if(row == 2 || row == 3){
                Tiles tiles = createTiles("Pool");
                TilesPane tilesPane = new TilesPane(tiles);
                p.getChildren().add(tilesPane);
            }
            else{
                Tiles tiles = createTiles("Grass");
                TilesPane tilesPane = new TilesPane(tiles);
                p.getChildren().add(tilesPane);
            }
            GridPane.setConstraints(p, col, row);
            gridtilesmap.getChildren().add(p);
            col++;
            if(col > 10){
                col = 0;
                row++;
            }
        }
    }

    private Tiles createTiles(String tilestype) {
        switch (tilestype) {
            case "Protected":
                return new ProtectedTiles();

            case "Grass":
                return new GrassTiles();

            case "Pool":
                return new PoolTiles();

            case "Spawn":
                return new SpawnTiles();

            default:
                throw new IllegalArgumentException("Unknown tiles type: " + tilestype);
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
        shovelPane.setId("shovelPane");
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
                    int sizepane = targetPane.getChildren().size();
                    InformationPlant infoplant = ((InformationPlant)targetPane.getChildren().get(sizepane-4));
                    if(infoplant.getPlant() instanceof Sunflower) {

                        ((Sunflower)infoplant.getPlant()).stopGeneratingSun();
                        System.out.println("Sunflower berhenti produksi sun:" + ((Sunflower)infoplant.getPlant()).isStopRequested());
                    }
                    targetPane.getChildren().remove(1, sizepane-1);
                }
                else {
                    DeckPane dp = new DeckPane((DeckPane) sourcePane.getChildren().getFirst());
                    DeckPane dpold = (DeckPane) sourcePane.getChildren().getFirst();
                    if (targetPane.getChildren().size() < 3){
                        if(((TilesPane) targetPane.getChildren().getFirst()).getTiles() instanceof PoolTiles && dp.getPlants().getIsAquatic()){
                            addingPlant(sourcePane, targetPane, dp, dpold);
                        }
                        else if (((TilesPane) targetPane.getChildren().getFirst()).getTiles() instanceof GrassTiles && !dp.getPlants().getIsAquatic()){
                            addingPlant(sourcePane, targetPane, dp, dpold);
                        }
                        else{
//                            throw new IllegalArgumentException("Tanaman harus ditanam sesuai dengan tipe tiles");
                        }
                    }
                    else if(((InformationPlant) targetPane.getChildren().get(1)).getPlant() instanceof Lilypad && !dp.getPlants().getIsAquatic() && !(dp.getPlants() instanceof Spikeweed) && targetPane.getChildren().size() == 5){
                        addingPlant(sourcePane, targetPane, dp, dpold);

                    }
                    // Terapin Exceptions

//                    System.out.println(targetPane.getChildren().get(0).getClass().getSimpleName());
                }
            }

            success = true;
        }
        dragEvent.setDropCompleted(success);
        dragEvent.consume();
    }

    public void stopGame(boolean wins) {
        for (Node node : gridtilesmap.getChildren()) {
            if (node instanceof Pane) {
                Pane tilePane = (Pane) node;
                for (Node childNode : tilePane.getChildren()) {
                    if (childNode instanceof InformationPlant) {
                        InformationPlant infoPlant = (InformationPlant) childNode;
                        if (infoPlant.getPlant() instanceof Sunflower) {
                            ((Sunflower) infoPlant.getPlant()).stopGeneratingSun();
                        }
                    }
                }
            }
        }
        paneEndGame.toFront();
        if(wins){
            winImage.setVisible(true);
        }
        else{
            loseImage.setVisible(true);
        }
        quitButton.setDisable(false);
        quitButton.setCursor(Cursor.HAND);
        menubutton.setDisable(true);
    }

    private void addingPlant(Pane sourcePane, Pane targetPane, DeckPane dp, DeckPane dpold){
        ImageView sourceImage = (ImageView) sourcePane.getChildren().get(3);
        ImageView duplicateImage = new ImageView(sourceImage.getImage());
        setupImageView(duplicateImage, 126, 126, 0, 0);
        duplicateImage.setOpacity(0);
        duplicateImage.setCursor(Cursor.HAND);
        duplicateImage.setOnDragDetected(this::chooseplant);
        sourcePane.getChildren().add(duplicateImage);
        sourceImage.setFitWidth(0);
        sourceImage.setFitHeight(0);
        sourceImage.setOpacity(1);
        if (dp.getPlantsName().equals("Lilypad")){
            sourceImage.setLayoutX(11);
            sourceImage.setLayoutY(35);
        }
        else if (dp.getPlantsName().equals("Tallnut")) {
            sourceImage.setLayoutY(-63);
            sourceImage.setLayoutX(15);
        }
        else if (dp.getPlantsName().equals("Spikeweed")) {
            sourceImage.setLayoutX(-15);
        }
        else {
            sourceImage.setLayoutX(15);
        }

        int row = GridPane.getRowIndex(targetPane);
        int column = GridPane.getColumnIndex(targetPane);

        Plants plant = createPlant(dp.getPlantsName(), row, column);
        wzs.setPlantPositions(true,row,column);

        InformationPlant plantInfo = new InformationPlant(plant);

        sourceImage.setOnDragDetected(null);
        sourceImage.setCursor(Cursor.DEFAULT);

        if(dp.getPlantsName().equals("Sunflower")){
            ((Sunflower) plantInfo.getPlant()).setStartProduce(true);
        }

        targetPane.getChildren().add(plantInfo);
        targetPane.getChildren().add(dp);
        targetPane.getChildren().add(sourceImage);



        Node shadowImage = targetPane.lookup("#shadowImage");
        if(shadowImage != null){
            targetPane.getChildren().remove(shadowImage);
        }

        Sun.reduceSun(plant.getCost());
        sourcePane.getChildren().get(2).setVisible(false);
        sourcePane.getChildren().get(3).setOnDragDetected(null);
        sourcePane.getChildren().get(3).setCursor(Cursor.DEFAULT);
        updateDeckPaneAvailability();
        startCooldown(dpold);
    }

    private void startCooldown(DeckPane deckPane) {

        Timer tm = new Timer();
        deckPane.getPlants().setOnCooldown(true);

        tm.schedule(new TimerTask() {
            public void run() {
                deckPane.getPlants().setOnCooldown(false);
            }
        }, deckPane.getPlants().getCooldown()*1000L);
    }

    private Plants createPlant(String plantName, int row, int column){
        switch (plantName) {
            case "Sunflower":
                return new Sunflower(row, column);

            case "Peashooter":
                return new Peashooter(row, column);

            case "Wallnut":
                return new Wallnut(row, column);

            case "SnowPea":
                return new Snowpea(row, column);

            case "Squash":
                return new Squash(row, column);

            case "Lilypad":
                return new Lilypad(row, column);

            case "TangleKelp":
                return new TangleKelp(row, column);

            case "SeaShroom":
                return new Seashroom(row, column);

            case "Tallnut":
                return new Tallnut(row, column);

            case "Spikeweed":
                return new Spikeweed(row, column);

            default:
                throw new IllegalArgumentException("Unknown plant type: " + plantName);
        }

    }

    private void foreshadowingplant(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();

        if (db.hasString()) {
            Node target = (Node) dragEvent.getTarget();
            Node source = (Node) dragEvent.getGestureSource();
            if (target instanceof Pane && source instanceof ImageView) {
                Pane targetPane = (Pane) target;
                Pane sourcePane = (Pane) source.getParent();

                if (sourcePane.getId().equals("shovelPane")) {
                    addingShovelImage(source, targetPane);
                }
                else {
                    boolean aquatic = ((DeckPane) sourcePane.getChildren().getFirst()).getPlants().getIsAquatic();

                    DeckPane dp = new DeckPane((DeckPane) sourcePane.getChildren().getFirst());

                    if (targetPane.getChildren().size() < 2) {
                        if (((TilesPane) targetPane.getChildren().getFirst()).getTiles() instanceof PoolTiles && aquatic) {
                            showtransparent(source, targetPane, dp);
                        } else if (((TilesPane) targetPane.getChildren().getFirst()).getTiles() instanceof GrassTiles && !aquatic) {
                            showtransparent(source, targetPane, dp);
                        }
//                    else{
//                            throw new IllegalArgumentException("Tanaman harus ditanam sesuai dengan tipe tiles");
//                    }
                    } else if (((InformationPlant) targetPane.getChildren().get(1)).getPlant() instanceof Lilypad && !aquatic && !(dp.getPlants() instanceof Spikeweed) && targetPane.getChildren().size() < 6) {
                        showtransparent(source, targetPane, dp);
                    }
                }
            }
        }
        dragEvent.consume();
    }

    private void addingShovelImage(Node source, Pane targetPane) {
        ImageView sourceImage = (ImageView) source;
        ImageView shadowImage = new ImageView(sourceImage.getImage());
        shadowImage.setOpacity(0.5);
        shadowImage.setFitWidth(0);
        shadowImage.setFitHeight(0);
        shadowImage.setId("shadowImage");  // Set an ID to identify this shadow image
        targetPane.getChildren().add(shadowImage);
    }

    private void showtransparent(Node source, Pane targetPane, DeckPane dp){
        ImageView sourceImage = (ImageView) source;
        ImageView shadowImage = new ImageView(sourceImage.getImage());
        shadowImage.setOpacity(0.5);
        shadowImage.setFitWidth(0);
        shadowImage.setFitHeight(0);
        if (dp.getPlantsName().equals("Lilypad")){
            shadowImage.setLayoutX(11);
            shadowImage.setLayoutY(35);
        }
        else if (dp.getPlantsName().equals("Tallnut")) {
            shadowImage.setLayoutY(-63);
            shadowImage.setLayoutX(15);
        }
        else if (dp.getPlantsName().equals("Spikeweed")){
            shadowImage.setLayoutX(-15);
        }
        else {
            shadowImage.setLayoutX(15);
        }
        shadowImage.setId("shadowImage");  // Set an ID to identify this shadow image
        targetPane.getChildren().add(shadowImage);
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
        Pane pane = (Pane) source.getParent();
        DeckPane dp = (DeckPane) pane.getChildren().getFirst();
        if(Sun.getSun() >= dp.getPlants().getCost() && !dp.getPlants().getCDStatus()) {
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
            ImageView imageView = (ImageView) source;
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            db.setContent(content);
            ImageView duplicateImage = new ImageView(imageView.getImage());
            duplicateImage.setOpacity(1);
            db.setDragView(duplicateImage.getImage(), 70, 70);
        }
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

    @FXML
    void quitGame(MouseEvent event) {
        System.exit(0);
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
