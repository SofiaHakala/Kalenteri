package kalenteri;

import java.io.*;
import java.nio.*;
import java.util.*;
import java.time.LocalDateTime;

//Tietokanta kalenterille
//Toteuttaa rajapintaluokan Serializable olioiden tiedostoon tallennusta varten (Luokat Tallenna ja Lataa)
public class Tietokanta implements Serializable{
  
  //Lista, joka sis‰lt‰‰ kaikki kalenteriin lis‰tyt tapahtuma-oliot
  private ArrayList<Tapahtuma> tapahtumat;

  //Konstruktori. Uutta tietokantaa luodessa luodaan samalla uusi tapahtuma-olioita sis‰lt‰v‰ lista
  public Tietokanta() {
    tapahtumat = new ArrayList<Tapahtuma>();
  }

  /**
   * Lis‰‰ uuden tapahtuma-olion listaan
   * @param aika tapahtuman p‰iv‰m‰‰r‰ ja kellonaika
   * @param nimi tapahtuman nimi
   * @param muistiinpanot tapahtumaan liittyv‰t muistiinpanot
   * @throws IOException tiedostojenk‰sittelyss‰ tapahtuvan virheen sattuessa
   */
  public void lisaaTapahtuma(LocalDateTime aika, String nimi, String muistiinpanot) throws IOException{
    Tapahtuma t = new Tapahtuma(aika, nimi, muistiinpanot);
    //Luodaan uusi tyhj‰ lista jos lista on null
    if(tapahtumat==null){
      tapahtumat= new ArrayList<Tapahtuma>();
    }
      tapahtumat.add(t);
    Tallenna.serializeLista(tapahtumat);
    }
    
  /**
   * Etsii tapahtuma-olioita annetun p‰iv‰m‰‰r‰n ja kellonajan perusteella.
   * @param aika Tapahtuman p‰iv‰m‰‰r‰ ja aika muodossa LocalDateTime
   * @return annetulle p‰iv‰ykselle lˆydetyt tapahtumat
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
   * @param aika tapahtuman p‰iv‰m‰‰r‰ ja aika muodossa LocalDateTime
   */
 public void poistaTapahtumia(LocalDateTime aika) {
   ArrayList<Tapahtuma> loydetty = etsiTapahtumia(aika);
   
   for (Tapahtuma tapahtuma : loydetty) {
     tapahtumat.remove(tapahtuma);
   }
 }
    
  /**
   * Alustaa ohjelman tilan, eli listan 'tapahtumat'
   */
 public void alusta(){
   //Ladataan mahdollinen tallennettu lista
   tapahtumat = Lataa.deserializeLista("kalenteri/tapahtumat/data.txt");
   //Tallennetaan nykyinen lista
   Tallenna.serializeLista(tapahtumat);
 }
}