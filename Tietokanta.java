package kalenteri;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

//huom: LocalDateTime: päivämäärä ja kellonaika
//      LocalDateTime: vain päivämäärä


//Tietokanta kalenterille
public class Tietokanta{
  //Lista, joka sisältää kaikki kalenteriin lisätyt tapahtuma-oliot
  private ArrayList<Tapahtuma> tapahtumat;

  //Konstruktori. Uutta tietokantaa luodessa luodaan samalla uusi tapahtuma-olioita sisältävä lista
  public Tietokanta() {
    tapahtumat = new ArrayList<>();
  }

    /**
     * Lisää uuden tapahtuman listaan ja luo uuden tiedoston tapahtuman nimellä
     * @param aika Tapahtuman päiväys ja kellonaika
     * @param nimi Tapahtuman nimi
     */
  public void lisaaTapahtuma(LocalDateTime aika, String nimi) throws IOException{
    tapahtumat.add(new Tapahtuma(aika, nimi));
    
    //huom: jos sovellukseen kuuluvat .java-tiedostot ovat kansiossa "kalenteri", tapahtuma-tiedostot tallennetaan 
    //alakansioon "tapahtumat".
    Path polku = Paths.get("kalenteri/tapahtumat/"+nimi+".txt");
    BufferedWriter w = new BufferedWriter(new FileWriter("kalenteri/tapahtumat/"+nimi+".txt"));
    //kesken: pitää lisätä tiedoston sisälle kirjoitus. pitää muuttaa myös esim. parametrit
    
    w.write(nimi);
    w.flush();
    w.close();
    }
    
    /**
     * Etsii tapahtuma-olioita annetun ajan perusteella.
     * @param aika Tapahtuman päiväys
     * @return Annetulle päiväykselle löydetyt tapahtumat
     */
  public ArrayList<Tapahtuma> etsiTapahtumia(LocalDateTime aika) {
    ArrayList<Tapahtuma> loydetyt = new ArrayList<>();
    for (Tapahtuma tapahtuma : tapahtumat) {
      if(tapahtuma.annaAika().toLocalDate().equals(aika.toLocalDate())){
        loydetyt.add(tapahtuma);
      }
    }
    return(loydetyt);
  }
    
    /**
     * Poistaa tapahtuman annetun ajan perusteella
     * @param aika Tapahtuman päiväys
     */
    public void poistaTapahtumia(LocalDateTime aika) {
 ArrayList<Tapahtuma> loydetty = etsiTapahtumia(aika);
 for (Tapahtuma tapahtuma : loydetty) {
            tapahtumat.remove(tapahtuma);
        }
    }
    
}
