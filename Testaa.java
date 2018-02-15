package kalenteri;
import java.io.IOException;
import java.util.Scanner;

public class Testaa {
  
  /**
   * kesken olevaa:
   * - kun tapahtumia etsitään (etsiTapahtumia()), ohjelman ei pitäisi käyttää "tapahtumat" listaa (ei säily ohjelman sulkeuduttua), 
   * vaan etsiä "tapahtumat"-kansiosta tiedostoja. 
   * ohjelma voisi tallentaa tiedoston ekalle riville päivämäärän oikeassa muodossa. ohjelma siis lukisi kaikkien kansiosta löytyvien 
   * tiedostojen ekan rivn ja vertailisi sitä etsittävään päivämäärään.
   * - muisiinpanoja ei olla toteutettu ollenkaan. eli ne tulisi tallentaa myös tiedostoon (jonka nimi on siis tapahtuman nimi)
   * - olisi ihan kiva ominaisuus voida muokata tapahtuman nimeä (tiedoston nimeä) ja muistiinpanoja (tiedoston sisältöä)
   * - joidenkin toimintojen valitsemista voisi tehdä jouhevammaksi. esim. riippuvuus enter-näppäimeen.
   * - päiväysten muodot. eli nyt päiväykset ovat muodossa mm/dd/yy, mitä ei suomessa paljoa käytetä.
   * - virhedenkäsittelyä voitaisiin myös vähän hienosäätää
   * - joissain luokissa taisi olla turhia import-lauseita jotka voisi poistaa (tai en ole oikeastaan tarkistanut)
   * - poista tapahtumia-ominaisuus ei näytä toimivan, en ole paljoa ehtinyt sitä muuttaa vanhasta ohjelmasta mistä tuon otin. 
   * pitäisi siis voida poistaa tapahtumia päivämäärän syöttämällä. myös tapahtumasta tehty tiedosto pitäisi poistaa.
   * pari muuta ominaisuutta pitäisi saada toimimaan ilman riippuvuutta "tapahtumat"-listaan. eli ne, joiden pitäisi pystyä hakemaan 
   * tietoa tapahtumista ohjelman sulkemisenkin jälkeen.
   * - "tapahtumat" alikansion automaattinen luonti pitäisi tehdä. ja tarkistaa onko kansio jo olemassa yms.
   * - jotain muutakin muutettavaa on kai, lisäillään tänne sitten
   * */

    public static void main(String[] args) throws IOException{
        Scanner lukija = new Scanner(System.in);
      
        Kalenteri kalenteri = new Kalenteri();
        String vaihtoehto = "0";
        // main loop
        while (!vaihtoehto.equals("4")) {
            kalenteri.tulostaAlkuvalikko(); 
            System.out.println();
            System.out.println("Valitse toiminnallisuus:");
            System.out.println("1 - Lisää tapahtuma");
            System.out.println("2 - Etsi tapahtumia");
            System.out.println("3 - Poista tapahtumia");
            System.out.println("4 - Poistu");
            vaihtoehto = lukija.nextLine();
            System.out.println();

            switch (vaihtoehto) {
                case "1":
                    kalenteri.lisaaTapahtuma();
                    break;
                case "2":
                    kalenteri.etsiTapahtumia();
                    break;
                case "3":
                    kalenteri.poistaTapahtumia();
                    break;
                case "4":
                    System.out.println("Paina näppäintä poistuaksesi sovelluksesta");
                    break;
                default:
                    System.out.println("Tapahtui virhe, valitse jokin annetuista vaihtoehdoista syöttämälle toiminnolle kuuluva numero");
                    break;
            }
        }
    }
    
}
