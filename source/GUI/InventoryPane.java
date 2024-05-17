package source.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InventoryPane extends Pane {

    private ImageView plantImageActive;
    private ImageView plantImageInactive;
    private String plantsName;
    private boolean used;

    public InventoryPane(String plantsName, String plantImageActivePath, String plantImageInactivePath, boolean used) {
        this.plantsName = plantsName;
        plantImageActive = new ImageView(new Image(plantImageActivePath));
        plantImageInactive = new ImageView(new Image(plantImageInactivePath));
        this.used = used;
    }

    public InventoryPane(InventoryPane referenceInventoryPane){
        this.plantsName = referenceInventoryPane.plantsName;
        this.plantImageActive = referenceInventoryPane.plantImageActive;
        this.plantImageInactive = referenceInventoryPane.plantImageInactive;
        this.used = referenceInventoryPane.used;
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
    public boolean isUsed() { return used; }

    public void setPlantsName(String plantsName) {
        this.plantsName = plantsName;
    }

    public void setPlantImageActive(String plantImageActivePath) {
        plantImageActive = new ImageView(new Image(plantImageActivePath));
    }

    public void setPlantImageInactive(String plantImageInactivePath) {
        plantImageInactive = new ImageView(new Image(plantImageInactivePath));
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setInventoryPane(InventoryPane inventoryPane) {
        this.plantsName = inventoryPane.getPlantsName();
        this.plantImageActive = inventoryPane.getPlantImageActive();
        this.plantImageInactive = inventoryPane.getPlantImageInactive();
        this.used = inventoryPane.isUsed();
    }
}
