package kalenteri;

import java.nio.*;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

//Luokka olioiden tiedostoon tallentamista varten
public class Tallenna implements Serializable{
  
public static void serializeLista(ArrayList<Tapahtuma> lista) {
  FileOutputStream tiedostoTulostus = null;
  ObjectOutputStream olioTulostus = null;

  try {
   tiedostoTulostus = new FileOutputStream("kalenteri/tapahtumat/data.txt");
   olioTulostus = new ObjectOutputStream(tiedostoTulostus);
   olioTulostus.writeObject(lista);
   
   olioTulostus.flush();
   olioTulostus.close();

  } catch (Exception e) {
    System.out.println("Virhe tiedostonkäsittelyssä");
   e.printStackTrace();
  
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
}