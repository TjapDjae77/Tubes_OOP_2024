package source.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DeckPane extends Pane {
    private String plantsName;
    private String plantImageInactivePath;
    private String plantImageActivePath;
    private String plantImageOriginalPath;
    private ImageView plantImageActive;
    private ImageView plantImageInactive;
    private ImageView plantImageOriginal;

    public DeckPane(String plantsName, ImageView plantImageActive, ImageView plantImageInactive) {
        this.plantsName = plantsName;
        this.plantImageActive = plantImageActive;
        this.plantImageInactive = plantImageInactive;
        this.plantImageInactivePath = "@../../assets/Seed Packet/Seeds_" + plantsName + "_Dark.png";
        this.plantImageActivePath = "@../../assets/Seed Packet/Seeds_" + plantsName + ".png";
        this.plantImageOriginalPath = "@../../assets/Plants/PVZ_" + plantsName + ".png";
        if(plantsName.equals("Peashooter")){
            plantImageOriginalPath = "@../../assets/Plants/PVZ_" +  plantsName +"2.png";
        }
        this.plantImageOriginal = new ImageView(new Image(plantImageOriginalPath));
    }

    public DeckPane(DeckPane referenceDeckPane){
        this.plantsName = referenceDeckPane.plantsName;
        this.plantImageActive = referenceDeckPane.plantImageActive;
        this.plantImageInactive = referenceDeckPane.plantImageInactive;
        this.plantImageOriginal = referenceDeckPane.plantImageOriginal;
    }

    public String getPlantsName() { return plantsName; }

    public String getPlantImageInactivePath() {return plantImageInactivePath;}
    public String getPlantImageActivePath() { return plantImageActivePath; }
    public String getPlantImageOriginalPath() { return plantImageOriginalPath; }
    public ImageView getPlantImageActive() { return plantImageActive; }
    public ImageView getPlantImageInactive() { return plantImageInactive; }
    public ImageView getPlantImageOriginal() { return plantImageOriginal; }

    public void setPlantsName(String plantsName) { this.plantsName = plantsName; }

    public void setPlantImageInactivePath(String plantImageInactivePath) { this.plantImageInactivePath = plantImageInactivePath; }
    public void setPlantImageActivePath(String plantImageActivePath) { this.plantImageActivePath = plantImageActivePath; }
    public void setPlantImageOriginalPath(String plantImageOriginalPath) { this.plantImageOriginalPath = plantImageOriginalPath; }

    public void setPlantImageActive(Image plantImageActive) { this.plantImageActive.setImage(plantImageActive); }
    public void setPlantImageInactive(Image plantImageInactive) { this.plantImageInactive.setImage(plantImageInactive); }
    public void setPlantImageOriginal(Image plantImageOriginal) { this.plantImageOriginal.setImage(plantImageOriginal); }

    public void setDeckPane(DeckPane deckPane) {
        plantsName = deckPane.getPlantsName();
        plantImageActive = deckPane.getPlantImageActive();
        plantImageInactive = deckPane.getPlantImageInactive();
        plantImageOriginal = deckPane.getPlantImageOriginal();
    }
}
