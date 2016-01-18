import myclasses.*;

import org.wikipedia.miner.util.text.*;
import org.wikipedia.miner.model.WikipediaDatabase;
import org.wikipedia.miner.model.Anchor;
import java.io.*;
import java.util.*;
import java.util.Set;

public class pras {

public static ArrayList<String> getListFromTextArea(String textAreaValue) {

  ArrayList list = new ArrayList();
  StringTokenizer tokens = new StringTokenizer(textAreaValue," ");
  while(tokens.hasMoreTokens()) {
    list.add(tokens.nextElement());
  }
  return list;
}

public static void main(String args[]) {
DBSCANAlgorithm db = new DBSCANAlgorithm();
//db.foo();
//Cluster cs=new Cluster();
try {
  String str = null;
  StringBuffer sb = new StringBuffer();
  WikipediaDatabase wd = new WikipediaDatabase("localhost","wikipedia","root","nb");
  File fp = new File("kumar2");
  FileInputStream fis = null;
  DataInputStream dis = null;
  BufferedInputStream bis = null;

try {
  fis = new FileInputStream(fp);
  bis = new BufferedInputStream(fis);
  dis = new DataInputStream(bis);

while(dis.available()!=0)
{
  sb.append(dis.readLine());
  sb.append('\n');
  str = sb.toString();
}
fis.close();
bis.close();
dis.close();
}
catch(Exception e) { e.printStackTrace(); }

String text=str,textc,texts;
Cleaner clnr = new Cleaner();
//wd.prepareForTextProcessor(clnr) ;
textc = clnr.processText(text);
try {
  StopwordRemover swr = new StopwordRemover(new File("stopwords"));
  texts = swr.processText(textc);
  TextProcessor tp = new Cleaner();
  ArrayList<String> list = getListFromTextArea(texts);
/*
for(int i=0;i<list.size();i++) {
  for (int j=i+1;j<list.size();j++) {
    try { 
      Anchor anchor1 = new Anchor(list.get(i),tp,wd);
      Anchor anchor2 = new Anchor(list.get(j),tp,wd);
     // System.out.println(anchor1+" "+anchor2+" "+anchor1.getRelatednessTo(anchor2));
    }
    catch(Exception e) { // Continue if the word doesn't occur in dB
       continue;
    } 
  }
}
*/

int a = list.size();
//System.out.println(list.get(1));
String temp = null;
String word[]= new String[a];
//double word[]=new double[a];
double store= 0.0;

double storearray[][]=new double[a][a];
for(int i=0;i<a;i++) {
      word[i]=list.get(i);
     System.out.println(); 
  for (int j=0;j<a;j++) {
    try {
      Anchor anchor1 = new Anchor(list.get(i),tp,wd);
      Anchor anchor2 = new Anchor(list.get(j),tp,wd);
      store=anchor1.getRelatednessTo(anchor2);
 //     System.out.print(store+" ");      
storearray[i][j]=store;
      storearray[j][i]=store;
       //anchor1.getRelatednessTo(anchor2));
       //System.out.println(store);
     }
    
     catch(Exception e) { // Continue if the word doesn't occur in dB
    
    storearray[i][j]=0;
     storearray[j][i]=0;
       continue;
     }
  }
}
System.out.println("Matrix built\n");
DataPoint[] elements = new DataPoint[a];
for(int i=0;i<a;i++){
   elements[i]=new DataPoint(word[i], new double[] {});
   }
   double threshold=0.4;
   int minPoints=4;
   DBSCANAlgorithm dbscan = new DBSCANAlgorithm(elements, storearray, threshold, minPoints);
   System.out.println(storearray);
   dbscan.cluster();



/*      for(int i=0;i<a;i++)
      {
          System.out.print(word[i]+" ");
          for(int j=0;j<a;j++)
           {
             System.out.print(storearray[i][j]+" ");
          }
          System.out.println();
      }
*/
}
catch(Exception e) { e.printStackTrace(); }
}
catch(Exception e) { e.printStackTrace(); }
}
}

