package kalenteri;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

//K�ytt�j�n kanssa kommunikoiva luokka
public class Kalenteri {
  private Tietokanta tietokanta;

  private Scanner lukija = new Scanner(System.in);

  //muotoilee ajan luettavaan muotoon
  public static DateTimeFormatter muotoileAika = DateTimeFormatter.ofPattern("M/d/y HH:mm");
//kesken: laitetaan aika mieluummin ehk� muotoon (p�iv�.kuukausi.vuosi tunti:minuutti) esim 15.2.2018 18:00 eik� 2/15/2018 18:00
    
  //muotoilee ajan (vain p�iv�m��r�n, ei kellonaikaa) luettavaan muotoon.
  public static DateTimeFormatter muotoileAikaP�iv�ys = DateTimeFormatter.ofPattern("M/d/y");
//kesken: sama ongelma t�ss�kin

  //Konstruktori kalenteri-oliolle, luodaan uusi tietokanta uutta kalenteria luotaessa
    public Kalenteri() {
        tietokanta = new Tietokanta();
    }
    
    /**
     * Palauttaa k�ytt�j�lt� kysytyn p�iv�m��r�n ja ajan
     * @return K�ytt�j�n sy�tt�m� validi p�iv�m��r� ja aika
     */
    private LocalDateTime lueAika() {
        System.out.println("Sy�t�  p�iv�m��r� ja aika muodossa kk/p�iv�/vuosi tunti:min");
        
        LocalDateTime aika;
        try {
            aika = LocalDateTime.parse(lukija.nextLine(), muotoileAika);
        }
        catch (DateTimeParseException e) {
            System.out.println("Tapahtui virhe, sy�t� p�iv�m��r� ja aika uudelleen muodossa kk/p�iv�/vuosi tunti:min");
            //Kysyt��n p�iv�m ja aika uudelleen
            return lueAika();
        }
        return aika;
    }

    /**
     * Palauttaa k�ytt�j�lt� pyydetyn p�iv�yksen
     * @return K�ytt�j�n sy�tt�m� p�iv�ys
     */
    private LocalDate lueAikaPaivays() {
        System.out.println("Sy�t� p�iv�m��r� muodossa kk/p�iv�/vuosi");
        LocalDate aikaPaivays;
        try {
            aikaPaivays = LocalDate.parse(lukija.nextLine(), muotoileAikaP�iv�ys);
        }
        catch (DateTimeParseException e) {
            System.out.println("Tapahtui virhe, sy�t� p�iv�m��r� uudelleen muodossa kk/p�iv�/vuosi");
            return lueAikaPaivays();
        }
        return aikaPaivays;
    }
    
    /**
     * Tulostaa kaikki annetun p�iv�m��r�n tapahtumat
     * @param day 
     */
    public void tulostaTapahtumat(LocalDate aikaPaivays) {
        ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aikaPaivays.atStartOfDay());
        for (Tapahtuma tapahtuma : tapahtumat) {
            System.out.println(tapahtuma);
        }
    }
    //kesken: voisi tulostaa j�rjestyksess� tapahtuman kellonajan mukaan, ja samalla n�ytt�� tapahtuman kellonajan sen vieress� tulostettaessa
    
    //Pyyt�� k�ytt�j�� sy�tt�m��n uuden tapahtuman ja lis�� sen tietokantaan
    public void lisaaTapahtuma()throws IOException{
        LocalDateTime aika = lueAika();
        System.out.println("Sy�t� tapahtuman nimi: ");
        String nimi = lukija.nextLine();
        tietokanta.lisaaTapahtuma(aika, nimi);
    }
    //kesken: pit�� lis�t� my�s ne tapahtumaan liityv�t muistiinpanot
   
    //K�ytt�j� voi metodin avulla etsi� tapahtumia halutulta p�iv�ykselt�
    public void etsiTapahtumia() {

        LocalDateTime aika = lueAikaPaivays().atStartOfDay();
        ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aika);

        if (tapahtumat.size() > 0) {
            System.out.println("L�ydetyt tapahtumat: ");
            for (Tapahtuma tapahtuma : tapahtumat) {
                System.out.println(tapahtuma);
            }
        } else {
            System.out.println("Tapahtumia ei l�ytynyt annetulle p�iv�ykselle");
        }
        lukija.nextLine(); // Enter
    }
    
       //Anataa k�ytt�j�n poistaa tapahtumia halutulta p�iv�lt�
    public void poistaTapahtumia() {
        System.out.println("Annetulle p�iv�lle lis�tyt tapahtuman poistetaan");
        LocalDateTime aika = lueAika();
        tietokanta.poistaTapahtumia(aika);
    }
    
     //Tulostetaan ohjelman alkuvalikko
    public void tulostaAlkuvalikko() {
        System.out.println("\nKalenteri");
        System.out.println("T�n��n on: " + LocalDateTime.now().format(muotoileAika)+"\n");
        System.out.println("P�iv�n tapahtumat:\n------");
        tulostaTapahtumat(LocalDate.now());
        System.out.println("\nHuomisen tapahtumat:\n---------");
        tulostaTapahtumat(LocalDate.now().plusDays(1));
        System.out.println();
    }
    
}