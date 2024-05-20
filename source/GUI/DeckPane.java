package source.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import source.Characters.Plants.*;

public class DeckPane extends Pane {
    private String plantsName;
    private String plantImageInactivePath;
    private String plantImageActivePath;
    private String plantImageOriginalPath;
    private ImageView plantImageActive;
    private ImageView plantImageInactive;
    private ImageView plantImageOriginal;
    private Plants plants;

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
        plants = createPlant(plantsName);
    }



    public DeckPane(DeckPane referenceDeckPane){
        this.plantsName = referenceDeckPane.plantsName;
        this.plantImageActive = referenceDeckPane.plantImageActive;
        this.plantImageInactive = referenceDeckPane.plantImageInactive;
        this.plantImageOriginal = referenceDeckPane.plantImageOriginal;
        this.plants = createPlant(plantsName);
    }

    public String getPlantsName() { return plantsName; }

    public String getPlantImageInactivePath() {return plantImageInactivePath;}
    public String getPlantImageActivePath() { return plantImageActivePath; }
    public String getPlantImageOriginalPath() { return plantImageOriginalPath; }
    public ImageView getPlantImageActive() { return plantImageActive; }
    public ImageView getPlantImageInactive() { return plantImageInactive; }
    public ImageView getPlantImageOriginal() { return plantImageOriginal; }

    public Plants getPlants() { return plants; }

    public void setPlantsName(String plantsName) { this.plantsName = plantsName; }

    public void setPlantImageInactivePath(String plantImageInactivePath) { this.plantImageInactivePath = plantImageInactivePath; }
    public void setPlantImageActivePath(String plantImageActivePath) { this.plantImageActivePath = plantImageActivePath; }
    public void setPlantImageOriginalPath(String plantImageOriginalPath) { this.plantImageOriginalPath = plantImageOriginalPath; }

    public void setPlantImageActive(Image plantImageActive) { this.plantImageActive.setImage(plantImageActive); }
    public void setPlantImageInactive(Image plantImageInactive) { this.plantImageInactive.setImage(plantImageInactive); }
    public void setPlantImageOriginal(Image plantImageOriginal) { this.plantImageOriginal.setImage(plantImageOriginal); }

    public void setPlants(Plants plants) { this.plants = plants; }

    public void setDeckPane(DeckPane deckPane) {
        plantsName = deckPane.getPlantsName();
        plantImageActive = deckPane.getPlantImageActive();
        plantImageInactive = deckPane.getPlantImageInactive();
        plantImageOriginal = deckPane.getPlantImageOriginal();
    }
    private Plants createPlant(String plantsName) {
        switch (plantsName) {
            case "Sunflower":
                return new Sunflower();

            case "Peashooter":
                return new Peashooter();

            case "Wallnut":
                return new Wallnut();

            case "SnowPea":
                return new Snowpea();

            case "Squash":
                return new Squash();

            case "Lilypad":
                return new Lilypad();

            case "TangleKelp":
                return new TangleKelp();

            case "SeaShroom":
                return new Seashroom();

            case "Tallnut":
                return new Tallnut();

            case "Spikeweed":
                return new Spikeweed();

            default:
                throw new IllegalArgumentException("Unknown plant type: " + plantsName);
        }
    }
}
