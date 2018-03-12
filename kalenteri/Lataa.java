package kalenteri;

import java.io.*;
import java.nio.*;
import java.util.*;
import java.time.LocalDateTime;

//Luokka olioiden tiedostoon kirjoittamista varten
public class Lataa implements Serializable{
  
public static ArrayList<Tapahtuma> deserializeLista(String tiedostopolku) {
  ArrayList<Tapahtuma> tapahtumat2 = null;
  FileInputStream tiedostoSyote = null;
  ObjectInputStream olioSyote = null;
  
  try {
   tiedostoSyote = new FileInputStream(tiedostopolku);
   olioSyote = new ObjectInputStream(tiedostoSyote);
   tapahtumat2 = (ArrayList) olioSyote.readObject();
   
   olioSyote.close();
   
  } catch (Exception e) {
    System.out.println("Virhe tiedostonk�sittelyss�.");
   e.printStackTrace();
   
  } finally {
   if (tiedostoSyote != null) {
    try {
     tiedostoSyote.close();
    } catch (IOException e) {
      System.out.println("Virhe tiedostosy�tteen sulkemisessa.");
     e.printStackTrace();
    }
   }
   if (olioSyote != null) {
    try {
     olioSyote.close();
    } catch (IOException e) {
      System.out.println("Virhe oliosy�tteen sulkemisessa.");
     e.printStackTrace();
    }
   }
  }
  return tapahtumat2;
 }
}