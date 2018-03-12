package kalenteri;

import java.time.LocalDate;


//Käyttäjän kanssa kommunikoiva luokka
public class Kalenteri {
  private Tietokanta tietokanta = new Tietokanta();

  private Scanner lukija = new Scanner(System.in);

  //muotoilee ajan luettavaan muotoon
  public static DateTimeFormatter muotoileAika = DateTimeFormatter.ofPattern("d.M.y HH:mm");
//kesken: laitetaan aika mieluummin ehkä muotoon (päivä.kuukausi.vuosi tunti:minuutti) esim 15.2.2018 18:00 eikä 2/15/2018 18:00
    
  //muotoilee ajan (vain päivämäärän, ei kellonaikaa) luettavaan muotoon.
  public static DateTimeFormatter muotoileAikaPäiväys = DateTimeFormatter.ofPattern("d.M.y");
//kesken: sama ongelma tässäkin

  //Konstruktori kalenteri-oliolle, luodaan uusi tietokanta uutta kalenteria luotaessa
    public Kalenteri() {
        tietokanta = new Tietokanta();
    }
    
    /**
     * Palauttaa käyttäjältä kysytyn päivämäärän ja ajan
     * @return Käyttäjän syöttämä validi päivämäärä ja aika
     */
    private LocalDateTime lueAika() {
        System.out.println("Syötä  päivämäärä ja aika muodossa kk/päivä/vuosi tunti:min");
        
        LocalDateTime aika;
        try {
            aika = LocalDateTime.parse(lukija.nextLine(), muotoileAika);
        }
        catch (DateTimeParseException e) {
            System.out.println("Tapahtui virhe, syötä päivämäärä ja aika uudelleen muodossa kk/päivä/vuosi tunti:min");
            //Kysytään päiväm ja aika uudelleen
            return lueAika();
        }
        return aika;
    }

    /**
     * Palauttaa käyttäjältä pyydetyn päiväyksen
     * @return Käyttäjän syöttämä päiväys
     */
    private LocalDate lueAikaPaivays() {
        System.out.println("Syötä päivämäärä muodossa kk/päivä/vuosi");
        LocalDate aikaPaivays;
        try {
            aikaPaivays = LocalDate.parse(lukija.nextLine(), muotoileAikaPäiväys);
        }
        catch (DateTimeParseException e) {
            System.out.println("Tapahtui virhe, syötä päivämäärä uudelleen muodossa kk/päivä/vuosi");
            return lueAikaPaivays();
        }
        return aikaPaivays;
    }
    
    /**
     * Tulostaa kaikki annetun päivämäärän tapahtumat
     * @param day 
     */
    public void tulostaTapahtumat(LocalDate aikaPaivays) {
        ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aikaPaivays.atStartOfDay());
        for (Tapahtuma tapahtuma : tapahtumat) {
            System.out.println(tapahtuma);
        }
    }
    //kesken: voisi tulostaa järjestyksessä tapahtuman kellonajan mukaan, ja samalla näyttää tapahtuman kellonajan sen vieressä tulostettaessa
    
    //Pyytää käyttäjää syöttämään uuden tapahtuman ja lisää sen tietokantaan
    public void lisaaTapahtuma()throws IOException{
        Scanner lukija2 = new Scanner(System.in);
        LocalDateTime aika = lueAika();
        lukija2.nextLine();
        System.out.println("Anna tapahtuman nimi: ");
        String nimi = lukija2.nextLine();
        lukija2.nextLine();
        System.out.println("Lisää tapahtumaan muistiinpanot: ");
        String muistiinpanot = lukija2.nextLine();
        tietokanta.lisaaTapahtuma(aika, nimi, muistiinpanot);
    }
   
    //Käyttäjä voi metodin avulla etsiä tapahtumia halutulta päiväykseltä
    public void etsiTapahtumia() {

        LocalDateTime aika = lueAikaPaivays().atStartOfDay();
        ArrayList<Tapahtuma> tapahtumat = tietokanta.etsiTapahtumia(aika);

        if (tapahtumat.size() > 0) {
            System.out.println("Löydetyt tapahtumat: ");
            for (Tapahtuma tapahtuma : tapahtumat) {
                System.out.println(tapahtuma);
            }
        } else {
            System.out.println("Tapahtumia ei löytynyt annetulle päiväykselle");
        }
        lukija.nextLine(); // Enter
    }
    
       //Anataa käyttäjän poistaa tapahtumia halutulta päivältä
    public void poistaTapahtumia() {
        System.out.println("Annetulle päivälle lisätyt tapahtuman poistetaan");
        LocalDateTime aika = lueAika();
        tietokanta.poistaTapahtumia(aika);
    }
    
     //Tulostetaan ohjelman alkuvalikko
    public void tulostaAlkuvalikko() {
        System.out.println("\nKalenteri");
        System.out.println("Tänään on: " + LocalDateTime.now().format(muotoileAika)+"\n");
        System.out.println("Päivän tapahtumat:\n------");
        tulostaTapahtumat(LocalDate.now());
        System.out.println("\nHuomisen tapahtumat:\n---------");
        tulostaTapahtumat(LocalDate.now().plusDays(1));
        System.out.println();
    }
    public void alustaOhjelmanTila(){
      tietokanta.alusta();
    }
    
}
