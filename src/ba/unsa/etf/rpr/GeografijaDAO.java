package ba.unsa.etf.rpr;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeografijaDAO {

    private ObservableList<Grad> gradovi1 = FXCollections.observableArrayList();
    private ObservableList<Drzava> drzave = FXCollections.observableArrayList();
    private static GeografijaDAO instance = null;

    private static GeografijaDAO instanca = null;

    private Connection conn;
    private PreparedStatement stmt;

    private GeografijaDAO() {
        try {/*
            conn = DriverManager.getConnection("jdbc:sqlite:resources/baza.db");
            stmt = conn.prepareStatement("SELECT autor, naslov, isbn, brojstranica FROM knjige");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // System.out.println()
                Grad g = new Grad(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                gradovi1.add(g);
                Drzava d = new Drzava(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                drzave.add(d);
                // if (trenutnaKnjiga == null) trenutnaKnjiga = new SimpleObjectProperty<Knjiga>(k);
            }*/
        } catch (SQLException e) {
            System.out.println("Neuspješno čitanje iz baze: " + e.getMessage());
        }

        //if (trenutnaKnjiga == null) trenutnaKnjiga = new SimpleObjectProperty<>();
    }

    private static void initialize() {
        instance = new GeografijaDAO();
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }

    public Grad glavniGrad(String drzava) {
        int i = 0;
        for (i = 0; i < drzave.size(); i++) {
            if (drzave.get(i).equals(drzava)) {
                return null;
                //  return drzave.getGlavnigrad;
            }
        }
        return null;
    }

    public void obrisiDrzavu(String drzava) {
        //briše i sve gradove u toj državi
        int i = 0;
        for (i = 0; i < drzave.size(); i++)
            if (drzave.get(i).equals(drzava))
                drzave.remove(i);
    }


    public ObservableList<Grad> gradovi() {
       // ArrayList<Grad> spisakGradova = gradovi1;
        Collections.sort(gradovi1, Collections.reverseOrder());

//vraća spisak gradova sortiranih po broju stanovnika u
//opadajućem redoslijedu
        return gradovi1;
    }

    public void dodajGrad(Grad grad) {
        int i = 0;
        for (i = 0; i < gradovi1.size(); i++)
            if (gradovi1.get(i).equals(grad))
                gradovi1.add(grad);
    }

    public void dodajDrzavu(Drzava drzava) {
        int i = 0;
        for (i = 0; i < drzave.size(); i++)
            if (drzave.get(i).equals(drzava))
                drzave.add(drzava);
    }

    public void izmijeniGrad(Grad grad) {
        // ažurira slog u bazi za dati grad
        int i = 0;
        for (i = 0; i < gradovi1.size(); i++)
            if (gradovi1.get(i).equals(grad))
                gradovi1.set(i, grad);
    }

    public Drzava nadjiDrzavu(String drzava) {
        int i = 0;
        for (i = 0; i < drzave.size(); i++)
            if (drzave.get(i).equals(drzava))
                return drzave.get(i);
       // return null;
    }
}
