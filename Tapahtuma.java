package kalenteri;
import java.time.LocalDateTime;
import java.io.Serializable;

//Luokka kalenterin tapahtuma-oliolle
public class Tapahtuma implements Serializable{

  //päiväys ja aika
  private LocalDateTime aika;
  
  //Tapahtuman nimi
  private String nimi;
  
  //Tapahtumaan lisätyt muistiinpanot
  private String muistiinpanot;
    
  
    /**
     * Konstruktori Tapahtuma-oliolle
     * @param aika päiväys ja aika
     * @param nimi Tapahtuman nimi
     */
    public Tapahtuma(LocalDateTime aika, String nimi, String muistiinpanot) {
        this.aika=aika;
        this.nimi=nimi;
        this.muistiinpanot=muistiinpanot;
    }
 
    /**
     * Palauttaa tapahtuman päiväyksen ja ajan
     * @return Tapahtuman päiväys ja aika
     */
    public LocalDateTime annaAika() {
        return aika;
    }
 
    /**
     * Asettaa tapahtuman päiväyksen ja ajan
     * @param aika Tapahtuman päiväys ja aika
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
     * Palauttaa tapahtuman nimen ja päiväyksen luettavaan muotoon muokattuna
     * @return Tapahtuman nimi ja päiväys luettavaan muotoon muokattuna
     */
    @Override
    public String toString() {
        return aika.format(Kalenteri.muotoileAika) + " " + nimi +": '"+muistiinpanot+"'";
    }
}