package source.GUI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InventoryPane extends Pane {

    private ImageView plantImageActive;
    private ImageView plantImageInactive;
    private String plantsName;

    public InventoryPane(String plantsName, String plantImageActivePath, String plantImageInactivePath) {
        this.plantsName = plantsName;

        this.plantImageActive = plantImageActive;
        this.plantImageInactive = plantImageInactive;
    }

    public String getPlantsName() {
        return plantsName;
    }

    public ImageView getPlantImageActive() {
        return plantImageActive;
    }
    public ImageView getPlantImageInactive() {
        return plantImageInactive;
    }

    public void setPlantsName(String plantsName) {
        this.plantsName = plantsName;
    }

    public void setPlantImageActive(ImageView plantImageActive) {
        this.plantImageActive = plantImageActive;
    }

    public void setPlantImageInactive(ImageView plantImageInactive) {
        this.plantImageInactive = plantImageInactive;
    }
}
