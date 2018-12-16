package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {

    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty naziv = new SimpleStringProperty("");
   // private Grad glavni_grad = new Grad();

    public Drzava() {}

    public Drzava (Integer a, String n, Grad g) {
        id = new SimpleIntegerProperty(a);
        naziv = new SimpleStringProperty(n);
     //   g = new Grad(g);
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
}
