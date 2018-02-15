package kalenteri;
import java.io.IOException;
import java.util.Scanner;

public class Testaa {
  
  /**
   * kesken olevaa:
   * -kun tapahtumia etsit��n, ohjelman ei pit�isi k�ytt�� "tapahtumat" listaa (ei s�ily ohjelman sulkeuduttua), vaan etsi� "tapahtumat"-kansiosta tiedostoja. 
   * ohjelma voisi tallentaa tiedoston ekalle riville p�iv�m��r�n oikeassa muodossa. ohjelma siis lukisi kaikkien kansiosta l�ytyvien tiedostojen ekan rivin
   * ja vertailisi sit� etsitt�v��n p�iv�m��r��n.
   * -muisiinpanoja ei olla toteutettu ollenkaan. eli ne tulisi tallentaa my�s tiedostoon (jonka nimi on siis tapahtuman nimi)
   * -olisi ihan kiva ominaisuus voida muokata tapahtuman nime� (tiedoston nime�) ja muistiinpanoja (tiedoston sis�lt��)
   * -jotenkin toimintojen valitsemista voisi tehd� jouhevammaksi. esim. se, ett� enteri� on pakko aina painaa voitaisiin ehk� vain poistaa? vai olisiko ehk� ihan hyv� ominaisuus
   * -p�iv�ysten muodot. eli nyt p�iv�ykset ovat muodossa mm/dd/yy, mit� ei suomessa paljoa k�ytet�
   * -virhedenk�sittely� voitaisiin my�s v�h�n hienos��t��
   * -poista tapahtumia-ominaisuus ei n�yt� toimivan, en ole paljoa ehtinyt sit� muuttaa vanhasta ohjelmasta mist� tuon otin. pit�isi siis voida poistaa tapahtumia p�iv�m��r�n sy�tt�m�ll�
   * -jotain muutakin muutettavaa on kai, lis�ill��n t�nne sitten
   * */

    public static void main(String[] args) throws IOException{
        Scanner lukija = new Scanner(System.in);
        // kalenteri instance
        Kalenteri kalenteri = new Kalenteri();
        String vaihtoehto = "0";
        // main loop
        while (!vaihtoehto.equals("4")) {
            kalenteri.tulostaAlkuvalikko(); 
            System.out.println();
            System.out.println("Valitse toiminnallisuus:");
            System.out.println("1 - Lis�� tapahtuma");
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
                    System.out.println("Paina n�pp�int� poistuaksesi sovelluksesta");
                    break;
                default:
                    System.out.println("Tapahtui virhe, valitse jokin annetuista vaihtoehdoista sy�tt�m�lle toiminnolle kuuluva numero");
                    break;
            }
        }
    }
    
}
