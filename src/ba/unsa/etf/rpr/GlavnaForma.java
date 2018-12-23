package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;


public class GlavnaForma {


    public TableView tableView;
    public TableColumn idColumn;
    public TableColumn nazivColumn;
    public TableColumn brojStanovnikaColumn;
    public TableColumn drzavaColumn;
    public MenuItem exitMenuItem;

    public void handleKeyInput(KeyEvent keyEvent) {
    }

    public void saveAction(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        napuniTabelu();

    }

    private void napuniTabelu() {
        ObservableList<Grad> listaGradova = FXCollections.observableArrayList();

        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory("naziv"));
        brojStanovnikaColumn.setCellValueFactory(new PropertyValueFactory("brojStanovnika"));
        drzavaColumn.setCellValueFactory(new PropertyValueFactory("drzava.getNaziv()"));

        for (Grad g: gradovi) {
            listaGradova.add(g);
        }
        tableView.setItems(listaGradova);

    }
}
