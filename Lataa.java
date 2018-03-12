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
  return tapahtumat2;
 }
}