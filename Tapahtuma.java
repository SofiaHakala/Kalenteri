package kalenteri;

import java.time.LocalDateTime;
import java.io.Serializable;

//Luokka kalenterin tapahtuma-oliolle
//Toteuttaa rajapintaluokan Serializable olioiden tiedostoon tallennusta varten (Luokat Tallenna ja Lataa)
public class Tapahtuma implements Serializable{

  //p‰iv‰m‰‰r‰ ja kellonaika
  private LocalDateTime aika;
  
  //Tapahtuman nimi
  private String nimi;
  
  //Tapahtumaan lis‰tyt muistiinpanot
  private String muistiinpanot;
    
  
  /**
   * Konstruktori Tapahtuma-oliolle
   * @param aika p‰iv‰m‰‰r‰ ja kellonaika
   * @param nimi tapahtuman nimi
   * @param muistiinpanot tapahtumaan liittyv‰t muistiinpanot
   */
  public Tapahtuma(LocalDateTime aika, String nimi, String muistiinpanot) {
    this.aika=aika;
    this.nimi=nimi;
    this.muistiinpanot=muistiinpanot;
  }
 
  //Getterit ja setterit
  public LocalDateTime annaAika() {
    return aika;
  }
  public void asetaAika(LocalDateTime aika) {
    this.aika=aika;
  }
  
  
  public String annaNimi() {
    return nimi;
  }
  public void asetaNimi(String nimi) {
    this.nimi=nimi;
  }

  
  public String annaMuistiinpanot() {
    return muistiinpanot;
  }
  public void asetaMuistiinpanot(String muistiinpanot) {
    this.muistiinpanot=muistiinpanot;
  }
    
  /**
   * Palauttaa tapahtuman nimen ja p‰iv‰yksen luettavaan muotoon muokattuna
   * @return Tapahtuman nimi ja p‰iv‰ys luettavaan muotoon muokattuna
   */
  @Override
  public String toString() {
    return aika.format(Kalenteri.muotoileAika) + " " + nimi +": '"+muistiinpanot+"'";
  }
}