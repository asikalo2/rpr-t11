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

    public String ispisiGradove(){
        String s = "";

        return s;
    }

    public void glavniGrad(){

        System.out.println("Glavni grad države Država je Grad");
        System.out.println("Nepostojeća država");
    }

    public static void main(String[] args) {

        //pristup bazi
        String url = "jdbc:sqlite:resources/proba.db";
        //String url = "jdbc:mysql://localhost/test";
        String upit = "SELECT naziv, cijena FROM proizvodi WHERE racun=4";

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(upit);

            float ukupno = 0;
            while(result.next()) {
                String naziv = result.getString(1);
                float cijena = result.getFloat(2);
                System.out.println (naziv + " " + cijena);
                ukupno += cijena;
            }
            System.out.println("UKUPNO: "+ukupno);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //} catch (ClassNotFoundException e) {
            //    e.printStackTrace();
        }

        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }

}
