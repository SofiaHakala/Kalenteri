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

//kesken: muutokset LocalDateTime (p�iv�m ja kellonaika) ja LocalDate (vain p�iv�m) olioiden v�lill�.
//esim tapahtumaa lis�tess� ja luettaessa halutaan tiet�� sen kellonaika ja p�iv�ys, mutta kellonaikaa ei tarvitse sy�tt�� tapahtumia haettaessa tai poistettaessa


//Tietokanta kalenterille
public class Tietokanta{
  //Lista, joka sis�lt�� kaikki kalenteriin lis�tyt tapahtuma-oliot
  private ArrayList<Tapahtuma> tapahtumat;

  //Konstruktori. Uutta tietokantaa luodessa luodaan samalla uusi tapahtuma-olioita sis�lt�v� lista
  public Tietokanta() {
    tapahtumat = new ArrayList<>();
  }

    /**
     * Lis�� uuden tapahtuman listaan ja luo uuden tiedoston tapahtuman nimell�
     * @param aika Tapahtuman p�iv�ys ja kellonaika
     * @param nimi Tapahtuman nimi
     */
  public void lisaaTapahtuma(LocalDateTime aika, String nimi) throws IOException{
    tapahtumat.add(new Tapahtuma(aika, nimi));
    
    Path polku = Paths.get("kalenteri/tapahtumat/"+nimi+".txt");
    BufferedWriter w = new BufferedWriter(new FileWriter("kalenteri/tapahtumat/"+nimi+".txt"));
    
    //kesken: pit�� lis�t� tiedoston sis�lle kirjoitus. pit�� muuttaa my�s esim. parametrit
    
    w.write(nimi);
    w.flush();
    w.close();
    }
    
    /**
     * Etsii tapahtuma-olioita annetun ajan perusteella.
     * @param aika Tapahtuman p�iv�ys
     * @return Annetulle p�iv�ykselle l�ydetyt tapahtumat
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
     * @param aika Tapahtuman p�iv�ys
     */
    public void poistaTapahtumia(LocalDateTime aika) {
 ArrayList<Tapahtuma> loydetty = etsiTapahtumia(aika);
 for (Tapahtuma tapahtuma : loydetty) {
            tapahtumat.remove(tapahtuma);
        }
    }
    
}