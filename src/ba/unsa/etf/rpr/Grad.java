package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad {

    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty naziv = new SimpleStringProperty("");
    private SimpleIntegerProperty broj_stanovnika = new SimpleIntegerProperty(0);
    //private SimpleProperty drzava = new SimpleIntegerProperty(0);

    public Grad() {}

    public Grad (Integer a, String n, Integer i, int b) {
        id = new SimpleIntegerProperty(a);
        naziv = new SimpleStringProperty(n);
        broj_stanovnika = new SimpleIntegerProperty(i);
        //  drzava = new SimpleIntegerProperty(b);
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika.get();
    }

    public SimpleIntegerProperty broj_stanovnikaProperty() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika.set(broj_stanovnika);
    }
}
