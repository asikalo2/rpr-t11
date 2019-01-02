package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
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

    public void saveAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        //nadjen primjer na netu sa saveOpcijom, kreira se filter, dodaje u chooser, zatim se otvara i kad pritisnemo OK
        //dobijemo putanju filea koje spasavamo, ako je != null
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("DOCX files (*.docx)", "*.docx");
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");

        fileChooser.getExtensionFilters().add(extFilter1);
        fileChooser.getExtensionFilters().add(extFilter2);
        fileChooser.getExtensionFilters().add(extFilter3);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(Main.getStage());

        if (file != null) {
            GradoviReport gradoviReport = new GradoviReport();
            try {
                //gradoviReport.showReport(GeografijaDAO.getConn());
                //dobijamo ekstenziju iz fajla, jer SaveAs u GradoviReport trazi ekstenziju, konekciju na bazu i putanju
                gradoviReport.saveAs(FilenameUtils.getExtension(file.getCanonicalPath()).toUpperCase(),
                        GeografijaDAO.getConn(),
                        file.getCanonicalPath());
            }
            catch (JRException ex) {
                ex.printStackTrace();
            }

        }
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
        drzavaColumn.setCellValueFactory(new PropertyValueFactory("drzava"));

        for (Grad g: gradovi) {
            listaGradova.add(g);
        }
        tableView.setItems(listaGradova);
    }

    public void changeToBosnian(ActionEvent actionEvent) {
        //postavljamo novi Locale, sa defaultom bosanskog jezika i zatim pomoccu loadView osvjezavamo formu s novim jezikom
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

    public void changeToGerman(ActionEvent actionEvent) {
        System.out.println("to de");
        Locale.setDefault(new Locale("de", "DE"));
        try {
            Main.loadView(Locale.getDefault());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void changeToFrench(ActionEvent actionEvent) {
        System.out.println("to fr");
        Locale.setDefault(new Locale("fr", "FR"));
        try {
            Main.loadView(Locale.getDefault());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void viewReportAction(ActionEvent actionEvent) {
        GradoviReport gradoviReport = new GradoviReport();
        try {
            gradoviReport.showReport(GeografijaDAO.getConn());
        }
        catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void viewReportCountryAction(ActionEvent actionEvent) {
        //pozivamo novu formu za odabir drzave, i kada je odaberemo iz comboboxa, pritiskom na dugme se prikaze izvjestaj
        //poziva se iz GeografijaDAO metoda drzave da napuni vrijednosti u comboboxu
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            fxmlLoader.setResources(bundle);
            fxmlLoader.setLocation(getClass().getResource("odabirDrzave.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
