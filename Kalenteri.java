package kalenteri;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

//K‰ytt‰j‰n kanssa kommunikoiva luokka. Toimii Tietokanta- ja Testaa-luokkien v‰liss‰.
public class Kalenteri { 
  
  private Tietokanta tietokanta = new Tietokanta();
  private Scanner lukija = new Scanner(System.in);
  
  //Luokkamuuttuja p‰iv‰m‰‰r‰n ja kellonajan luettavaan muotoon vaihtamiseen
  public static DateTimeFormatter muotoileAika = DateTimeFormatter.ofPattern("M/d/y HH:mm");
    
  //Luokkamuuttuja p‰iv‰m‰‰r‰n luettavaan muotoon vaihtamiseen
  public static DateTimeFormatter muotoileAikaP‰iv‰ys = DateTimeFormatter.ofPattern("M/d/y");
 
  
  
  //Konstruktori Kalenteri-oliolle, luodaan uusi tietokanta uutta kalenteria luotaessa
  public Kalenteri() {
    tietokanta = new Tietokanta();
  }
    
  
  /**
   * Kysyy k‰ytt‰j‰lt‰ p‰iv‰m‰‰r‰n ja kellonajan joka palautetaan muodossa LocalDateTime
   * @return K‰ytt‰j‰n syˆtt‰m‰ oikeaksi tarkistettu p‰iv‰m‰‰r‰ ja aika
   */
  private LocalDateTime lueAika() {
    System.out.println("Syˆt‰  p‰iv‰m‰‰r‰ ja aika muodossa kk/p‰iv‰/vuosi tunti:min");
    LocalDateTime aika;
    try {
      aika = LocalDateTime.parse(lukija.nextLine(), muotoileAika);
    }
    catch (DateTimeParseException e) {
      System.out.println("Tapahtui virhe, syˆt‰ p‰iv‰m‰‰r‰ ja aika uudelleen muodossa kk/p‰iv‰/vuosi tunti:min");
      return lueAika();
    }
    return aika;
  }

  /**
   * Palauttaa k‰ytt‰j‰lt‰ pyydetyn p‰iv‰m‰‰r‰n muodossa LocalDate
   * @return K‰ytt‰j‰n syˆtt‰m‰ p‰iv‰m‰‰r‰
   */
  private LocalDate lueAikaPaivays() {
    System.out.println("Syˆt‰ p‰iv‰m‰‰r‰ muodossa kk/p‰iv‰/vuosi");
    LocalDate aikaPaivays;
    try {
      aikaPaivays = LocalDate.parse(lukija.nextLine(), muotoileAikaP‰iv‰ys);
    }
    catch (DateTimeParseException e) {
      System.out.println("Tapahtui virhe, syˆt‰ p‰iv‰m‰‰r‰ uudelleen muodossa kk/p‰iv‰/vuosi");
      return lueAikaPaivays();
    }
    return aikaPaivays;
  }
    
  /**
   * Tulostaa kaikki annetun p‰iv‰m‰‰r‰n tapahtumat
   * @param aikaP‰iv‰ys p‰iv‰m‰‰r‰ muodossa LocalDate
   */
  public void tulostaTapahtumat(LocalDate aikaPaivays) {
    
    //Koska metodi etsiTapahtumia k‰ytt‰‰ p‰iv‰m‰‰r‰n lis‰ksi kellonaikaa, k‰ytet‰‰n kellonaikana 
    //atStartOfDay()-metodia.
    ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aikaPaivays.atStartOfDay());
    for (Tapahtuma tapahtuma : tapahtumat) {
      System.out.println(tapahtuma);
    }
  }
  
  /**
   * Pyyt‰‰ k‰ytt‰j‰‰ syˆtt‰m‰‰n uuden tapahtuman ja lis‰‰ sen tietokantaan
   * @throws IOException kun tiedoston lukemisessa (metodissa .lisaaTapahtuma()) tapahtuu virhe.
   */
  public void lisaaTapahtuma()throws IOException{
    Scanner lukija2 = new Scanner(System.in);
    LocalDateTime aika = lueAika();
        
    System.out.println("Anna tapahtuman nimi: ");
    String nimi = lukija2.nextLine();
        
    System.out.println("Lis‰‰ tapahtumaan muistiinpanot: ");
    String muistiinpanot = lukija2.nextLine();
        
    tietokanta.lisaaTapahtuma(aika, nimi, muistiinpanot);
  }
   
  /**
   * Metodin avulla voi etsi‰ tapahtumia halutulta p‰iv‰m‰‰r‰lt‰
   */
  public void etsiTapahtumia() {
    //Muutetaan lueAikaP‰iv‰ys()-metodin avulla k‰ytt‰j‰lt‰ syˆtteen‰ saatu p‰iv‰m‰‰r‰ oikeaan muotoon
    //metodia etsiTapahtumia() varten.
    LocalDateTime aika = lueAikaPaivays().atStartOfDay();
    ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aika);

    if (tapahtumat.size() > 0) {
      System.out.println("Lˆydetyt tapahtumat: ");
      for (Tapahtuma tapahtuma : tapahtumat) {
        System.out.println(tapahtuma);
      }
    } else {
      System.out.println("Tapahtumia ei lˆytynyt annetulle p‰iv‰ykselle");
    }
    lukija.nextLine(); // Enter
  }
  
  /**
   * Metodi poistaa tapahtumat k‰ytt‰j‰lt‰ syˆtteen‰ saadulta p‰iv‰m‰‰r‰lt‰ ja kellonajalta
   */
  public void poistaTapahtumia() {
    System.out.println("Annetulle p‰iv‰lle lis‰tyt tapahtuman poistetaan");
    LocalDateTime aika = lueAika();
    tietokanta.poistaTapahtumia(aika);
    }
    
  /**
   * Tulostaa ohjelman alkuvalikon
   */
  public void tulostaAlkuvalikko() {
    System.out.println("\nKalenteri");
    
    System.out.println("T‰n‰‰n on: " + LocalDateTime.now().format(muotoileAika)+"\n");
    System.out.println("P‰iv‰n tapahtumat:\n------");
    tulostaTapahtumat(LocalDate.now());
        
    System.out.println("\nHuomisen tapahtumat:\n---------");
    tulostaTapahtumat(LocalDate.now().plusDays(1));
    System.out.println();
    }
  
  /**
   * Alustaa ohjelman tilan, eli hakee mahdollisesti aikaisemman tallennetun tapahtumat-listan
   */
  public void alustaOhjelmanTila(){
    tietokanta.alusta();
  }
}