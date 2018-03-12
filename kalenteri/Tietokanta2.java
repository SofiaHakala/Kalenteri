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

//kesken: muutokset LocalDateTime (päiväm ja kellonaika) ja LocalDate (vain päiväm) olioiden välillä.
//esim tapahtumaa lisätessä ja luettaessa halutaan tietää sen kellonaika ja päiväys, mutta kellonaikaa ei tarvitse syöttää tapahtumia haettaessa tai poistettaessa


//Tietokanta kalenterille
public class Tietokanta implements Serializable{
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
    WriteObject olio = new WriteObject();
    olio.serializeTapahtuma(new Tapahtuma(aika, nimi));
    
    /*
    Path polku = Paths.get("kalenteri/tapahtumat/"+nimi+".txt");
    BufferedWriter w = new BufferedWriter(new FileWriter("kalenteri/tapahtumat/"+nimi+".txt"));
    
    //kesken: pitää lisätä tiedoston sisälle kirjoitus. pitää muuttaa myös esim. parametrit
    
    w.write(nimi);
    w.flush();
    w.close();
    */
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
    
 public ArrayList<Tapahtuma> deserialzeLista(String tiedostonimi) {
  ArrayList<Tapahtuma> tapahtumat = null;
  FileInputStream tiedostoSyote = null;
  ObjectInputStream olioSyote = null;
  
  try {
   tiedostoSyote = new FileInputStream(tiedostonimi);
   olioSyote = new ObjectInputStream(tiedostoSyote);
   tapahtumat = (Arraylist) olioSyote.readObject();

  } catch (Exception e) {
    System.out.println("Virhe tiedostonkäsittelyssä.");
   e.printStackTrace();
  } finally {
    
   if (tiedostoSyote != null) {
    try {
     tiedostoSyote.close();
    } catch (IOException e) {
      System.out.println("Virhe tiedostosyötteen sulkemisessa.");
     e.printStackTrace();
    }
   }
   if (olioSyote != null) {
    try {
     olioSyote.close();
    } catch (IOException e) {
      System.out.println("Virhe oliosyötteen sulkemisessa.");
     e.printStackTrace();
    }
   }
  }
  return tapahtuma;
 }
 
 
  public void serializeLista(ArrayList<Tapahtuma> lista) {
  FileOutputStream tiedostoTulostus = null;
  ObjectOutputStream olioTulostus = null;

  try {
   tiedostoTulostus = new FileOutputStream("kalenteri/tapahtumat/data.txt");
   olioTulostus = new ObjectOutputStream(tiedostoTulostus);
   olioTulostus.writeObject(lista);

  } catch (Exception e) {
    System.out.println("Virhe tiedostonkäsittelyssä");
   ex.printStackTrace();

  } finally {
   if (tiedostoTulostus != null) {
    try {
     tiedostoTulostus.close();
    } catch (IOException e) {
      System.out.println("Virhe tiedoston tulostuksen sulkemisessa");
     e.printStackTrace();
    }
   }
   if (olioTulostus != null) {
    try {
     olioTulostus.close();
    } catch (IOException e) {
      System.out.println("Virhe olion tulostuksen sulkemisessa");
     e.printStackTrace();
    }
   }
  }
 }
  
  public void lataaOhjelmanTila(){
    
  }
  public void tallennaOhjelmanTila(){
  }
  
}