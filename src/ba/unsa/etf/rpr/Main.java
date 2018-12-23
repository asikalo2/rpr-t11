package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    public static String ispisiGradove() {
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        String rez = "";
        for (Grad grad : gradovi) {
            rez += grad.getNaziv() + "(" + grad.getDrzava().getNaziv() + ")" + " - " +
                    grad.getBrojStanovnika() + "\n";
        }
        return rez;
    }

    public static void glavniGrad() {
        System.out.println("Unesite ime drzave: ");
        Scanner scanner = new Scanner(System.in);
        String drzava = scanner.nextLine();
        Grad grad = GeografijaDAO.getInstance().glavniGrad(drzava);
        if (grad != null) {
            System.out.println("Glavni grad drzave " + drzava + " je " + grad.getNaziv());
        } else {
            System.out.println("Nepostojeca drzava");
        }

    }

    public static void main(String[] args) {
        /*System.out.println(ispisiGradove());
        glavniGrad();*/
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("glavnaForma.fxml"), bundle);
        stage.setTitle("Drzave");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }


}
