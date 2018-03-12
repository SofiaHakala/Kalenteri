package kalenteri;

import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

//Ohjelman suoritukseen liittyv� luokka
public class Testaa {
  public static void main(String[] args) throws IOException{
    
    Scanner lukija = new Scanner(System.in);
    Kalenteri kalenteri = new Kalenteri();
    kalenteri.alustaOhjelmanTila();
        
        
    String vaihtoehto = "0";
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