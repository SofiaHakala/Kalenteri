package kalenteri;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//kesken: muutokset LocalDateTime (päiväm ja kellonaika) ja LocalDate (vain päiväm) olioiden välillä.
//esim tapahtumaa lisätessä ja luettaessa halutaan tietää sen kellonaika ja päiväys, mutta kellonaikaa ei tarvitse syöttää tapahtumia haettaessa tai poistettaessa


//Tietokanta kalenterille
public class Tietokanta implements Serializable{
  //Lista, joka sisältää kaikki kalenteriin lisätyt tapahtuma-oliot
  private ArrayList<Tapahtuma> tapahtumat;

  //Konstruktori. Uutta tietokantaa luodessa luodaan samalla uusi tapahtuma-olioita sisältävä lista
  public Tietokanta() {
    tapahtumat = new ArrayList<Tapahtuma>();
  }

    /**
     * Lisää uuden tapahtuman listaan ja luo uuden tiedoston tapahtuman nimellä
     * @param aika Tapahtuman päiväys ja kellonaika
     * @param nimi Tapahtuman nimi
     */
  public void lisaaTapahtuma(LocalDateTime aika, String nimi, String muistiinpanot) throws IOException{
    Tapahtuma t = new Tapahtuma(aika, nimi, muistiinpanot);
    if(tapahtumat==null){
      tapahtumat= new ArrayList<Tapahtuma>();
    }
      tapahtumat.add(t);
    Tallenna.serializeLista(tapahtumat);
    }
    
    /**
     * Etsii tapahtuma-olioita annetun ajan perusteella.
     * @param aika Tapahtuman päiväys
     * @return Annetulle päiväykselle löydetyt tapahtumat
     */
  public ArrayList<Tapahtuma> etsiTapahtumia(LocalDateTime aika) {
    ArrayList<Tapahtuma> loydetyt = new ArrayList<>();
    if(tapahtumat != null && tapahtumat.size() != 0){
    for (Tapahtuma tapahtuma : tapahtumat) {
      if(tapahtuma.annaAika().toLocalDate().equals(aika.toLocalDate())){
        loydetyt.add(tapahtuma);
      }
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
    
  public void alusta(){
    tapahtumat = Lataa.deserializeLista("kalenteri/tapahtumat/data.txt");
    Tallenna.serializeLista(tapahtumat);
  }
}