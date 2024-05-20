package source.GUI;


import javafx.scene.layout.Pane;
import source.Characters.Plants.*;

public class InformationPlant extends Pane {
    private Plants plant;

    public InformationPlant(Plants plant) {
        this.plant = plant;
    }

    public Plants getPlant() {
        return plant;
    }
}
