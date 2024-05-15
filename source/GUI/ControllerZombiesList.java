package source.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerZombiesList {

    @FXML
    private Button backButton;

    @FXML
    private Pane pane;

    @FXML
    void backToMenu(MouseEvent event) throws IOException {
        Stage mainMenu = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/source/GUI/mainmenu.fxml"));
        mainMenu.setTitle("Plants vs. Zombies");
        mainMenu.setScene(new Scene(root));
    }

}
