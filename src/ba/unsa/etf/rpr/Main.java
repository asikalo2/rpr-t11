package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

           /* String ispisiGradove() - poziva metodu gradovi() i vraća string sa podacima u formatu:
        Ime grada (ime države) - broj stanovnika
● void glavniGrad() - omogućuje korisniku da putem tastature unese naziv države, a zatim
        na ekran ispisuje poruku u obliku "Glavni grad države Država je Grad" ili "Nepostojeća
        država"*/

    public static String ispisiGradove(){
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        String rez = "";
        for (Grad grad: gradovi) {
            rez += grad.getNaziv() + "(" + grad.getDrzava().getNaziv() + ")" + " - " +
            grad.getBrojStanovnika() + "\n";
        }
        return rez;
    }

    public static void glavniGrad(){

        System.out.println("Glavni grad države Država je Grad");
        System.out.println("Nepostojeća država");
    }

    public static void main(String[] args) {
        System.out.println(ispisiGradove());
    }

}
