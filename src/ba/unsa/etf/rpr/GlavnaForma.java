package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class GlavnaForma implements Initializable {


    public TableView tableView;
    public TableColumn idColumn;
    public TableColumn nazivColumn;
    public TableColumn brojStanovnikaColumn;
    public TableColumn drzavaColumn;
    public MenuItem exitMenuItem;

    private ResourceBundle bundle;

    public void handleKeyInput(KeyEvent keyEvent) {
    }

    public void saveAction(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = resourceBundle;
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

    public void changeToBosnian(ActionEvent actionEvent) {
        System.out.println("to bs");
        Locale.setDefault(new Locale("bs", "BA"));
        try {
            Main.loadView(Locale.getDefault());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToEnglish(ActionEvent actionEvent) {
        System.out.println("to en_US");
        Locale.setDefault(new Locale("en", "US"));
        try {
            Main.loadView(Locale.getDefault());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
