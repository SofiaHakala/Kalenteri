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