package source.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DeckPane extends Pane {
    private String plantsName;
    private ImageView plantImageActive;
    private ImageView plantImageInactive;
    private ImageView plantImageOriginal;

    public DeckPane(String plantsName, ImageView plantImageActive, ImageView plantImageInactive) {
        this.plantsName = plantsName;
        this.plantImageActive = plantImageActive;
        this.plantImageInactive = plantImageInactive;
        String plantImageOriginalPath = "@../../assets/Plants/PVZ_" +  plantsName +".png";
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
    public ImageView getPlantImageActive() { return plantImageActive; }
    public ImageView getPlantImageInactive() { return plantImageInactive; }
    public ImageView getPlantImageOriginal() { return plantImageOriginal; }

    public void setPlantsName(String plantsName) { this.plantsName = plantsName; }
    public void setPlantImageActive(ImageView plantImageActive) { this.plantImageActive = plantImageActive; }
    public void setPlantImageInactive(ImageView plantImageInactive) { this.plantImageInactive = plantImageInactive; }
    public void setPlantImageOriginal(ImageView plantImageOriginal) { this.plantImageOriginal = plantImageOriginal; }

    public void setDeckPane(DeckPane deckPane) {
        plantsName = deckPane.getPlantsName();
        plantImageActive = deckPane.getPlantImageActive();
        plantImageInactive = deckPane.getPlantImageInactive();
        plantImageOriginal = deckPane.getPlantImageOriginal();
    }
}
