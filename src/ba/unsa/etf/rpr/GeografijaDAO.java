package ba.unsa.etf.rpr;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeografijaDAO {

    private ArrayList<Grad> gradovi1;
    private ArrayList<Drzava> drzave;
    private static GeografijaDAO instance = null;

    private Connection conn;
    private PreparedStatement stmt;

    public GeografijaDAO() {
        conn = null;
        stmt = null;

        try {
            String url = "jdbc:sqlite:resources/baza.db";
            conn = DriverManager.getConnection(url);

            String sql = "CREATE TABLE IF NOT EXISTS gradovi (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	naziv text NOT NULL UNIQUE,\n"
                    + " brojStanovnika integer,\n"
                    + " drzava integer,\n"
                    + "	FOREIGN KEY(drzava) REFERENCES drzave(id)\n"
                    + ");";

            stmt = conn.prepareStatement(sql);
            stmt.execute();

            sql = "CREATE TABLE IF NOT EXISTS drzave (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	naziv text NOT NULL UNIQUE,\n"
                    + " glavniGrad integer,\n"
                    + "	FOREIGN KEY(glavniGrad) REFERENCES gradovi(id)\n"
                    + ");";

            stmt = conn.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        Grad g = new Grad();
        try {
            stmt = conn.prepareStatement("SELECT gradovi.id, gradovi.naziv, brojStanovnika, drzava, " +
                    "drzave.id as d_id, drzave.naziv as d_naziv, drzave.glavniGrad as d_gg FROM gradovi INNER JOIN drzave ON " +
                    "gradovi.drzava = drzave.id WHERE drzave.naziv = ?");


            stmt.setString(1, drzava);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                g.setId(rs.getInt(1));
                g.setNaziv(rs.getString(2));
                g.setBrojStanovnika(rs.getInt(3));
                Drzava d = new Drzava();
                d.setId(rs.getInt(5));
                d.setNaziv(rs.getString(6));
                d.setGlavniGrad(g);
                g.setDrzava(d);
                return g;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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


    public ArrayList<Grad> gradovi() {
        try {
            gradovi1.clear();
            stmt = conn.prepareStatement("SELECT * FROM gradovi;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // System.out.println()


                // if (trenutnaKnjiga == null) trenutnaKnjiga = new SimpleObjectProperty<Knjiga>(k);
            }
            return gradovi1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void dodajGrad(Grad grad) {
        try {
            stmt = conn.prepareStatement("INSERT OR REPLACE INTO gradovi(naziv, brojStanovnika, drzava) VALUES(?,?,?)");
            stmt.setString(1, grad.getNaziv());
            stmt.setInt(2, grad.getBrojStanovnika());
            stmt.setInt(3, grad.getDrzava().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            stmt = conn.prepareStatement("INSERT OR REPLACE INTO drzave(naziv, glavniGrad) VALUES(?,null)");
            stmt.setString(1, drzava.getNaziv());
            stmt.executeUpdate();
            Drzava d = nadjiDrzavu(drzava.getNaziv());
            drzava.getGlavniGrad().setDrzava(d);
            dodajGrad(drzava.getGlavniGrad());
            Grad g = nadjiGrad(drzava.getGlavniGrad().getNaziv());
            drzava.getGlavniGrad().setId(g.getId());
            g.setDrzava(drzava);
            izmijeniGrad(g);
            izmijeniDrzavu(drzava);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void izmijeniDrzavu(Drzava drzava) {
        try {
            stmt = conn.prepareStatement("UPDATE drzave SET naziv=?, glavniGrad=?");
            stmt.setString(1, drzava.getNaziv());
            stmt.setInt(2, drzava.getGlavniGrad().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            stmt = conn.prepareStatement("UPDATE gradovi SET naziv=?, brojStanovnika=?, drzava=?");
            stmt.setString(1, grad.getNaziv());
            stmt.setInt(2, grad.getBrojStanovnika());
            stmt.setInt(3, grad.getDrzava().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        Drzava d = new Drzava();
        try {
            stmt = conn.prepareStatement("SELECT id, naziv, glavniGrad FROM drzave WHERE naziv=?");
            stmt.setString(1, drzava);
            ResultSet rs = stmt.executeQuery();
            d.setId(rs.getInt(1));
            d.setNaziv(rs.getString(2));
            return d;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Grad nadjiGrad(String grad) {
        Grad g = new Grad();
        try {
            stmt = conn.prepareStatement("SELECT id, naziv, brojStanovnika, drzava FROM gradovi WHERE naziv=?");
            stmt.setString(1, grad);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                g.setId(rs.getInt(1));
                g.setNaziv(rs.getString(2));
                g.setBrojStanovnika(rs.getInt(3));
                return g;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
