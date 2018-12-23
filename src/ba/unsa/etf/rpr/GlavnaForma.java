package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;


public class GlavnaForma {


    public TableView tableView;
    public TableColumn idColumn;
    public TableColumn nazivColumn;
    public TableColumn glavniGradColumn;

    public void handleKeyInput(KeyEvent keyEvent) {
    }

    public void saveAction(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {
        napuniTabelu();

    }

    private void napuniTabelu() {
    }
}
