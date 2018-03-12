package kalenteri;
import java.time.LocalDateTime;
import java.io.Serializable;

//Luokka kalenterin tapahtuma-oliolle
public class Tapahtuma implements Serializable{

  //p�iv�ys ja aika
  private LocalDateTime aika;
  
  //Tapahtuman nimi
  private String nimi;
  
  //Tapahtumaan lis�tyt muistiinpanot
  private String muistiinpanot;
    
  
    /**
     * Konstruktori Tapahtuma-oliolle
     * @param aika p�iv�ys ja aika
     * @param nimi Tapahtuman nimi
     */
    public Tapahtuma(LocalDateTime aika, String nimi, String muistiinpanot) {
        this.aika=aika;
        this.nimi=nimi;
        this.muistiinpanot=muistiinpanot;
    }
 
    /**
     * Palauttaa tapahtuman p�iv�yksen ja ajan
     * @return Tapahtuman p�iv�ys ja aika
     */
    public LocalDateTime annaAika() {
        return aika;
    }
 
    /**
     * Asettaa tapahtuman p�iv�yksen ja ajan
     * @param aika Tapahtuman p�iv�ys ja aika
     */
    public void asetaAika(LocalDateTime aika) {
        this.aika=aika;
    }

    /**
     * Palauttaa tapahtuman nimen
     * @return Tapahtuman nimi
     */
    public String annaNimi() {
        return nimi;
    }
 
    /**
     * Asettaa tapahtuman nimen
     * @param nimi Tapahtuman nimi
     */
    public void asetaNimi(String nimi) {
        this.nimi=nimi;
    }
 

    /**
     * Palauttaa tapahtuman nimen
     * @return Tapahtuman nimi
     */
    public String annaMuistiinpanot() {
        return muistiinpanot;
    }
 
    /**
     * Asettaa tapahtuman nimen
     * @param nimi Tapahtuman nimi
     */
    public void asetaMuistiinpanot(String muistiinpanot) {
        this.muistiinpanot=muistiinpanot;
    }
    
    /**
     * Palauttaa tapahtuman nimen ja p�iv�yksen luettavaan muotoon muokattuna
     * @return Tapahtuman nimi ja p�iv�ys luettavaan muotoon muokattuna
     */
    @Override
    public String toString() {
        return aika.format(Kalenteri.muotoileAika) + " " + nimi +": '"+muistiinpanot+"'";
    }
}