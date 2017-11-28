/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientandserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class serverpart   extends Thread  {


   private ServerSocket serverSocket;
   
   public serverpart(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   
   }
   
   public String XmlToString(String xml)
   {
   String answer="";

 try {
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         
         InputSource is = new InputSource();
         is.setCharacterStream(new StringReader(xml));
         Document doc = dBuilder.parse(is);
         doc.getDocumentElement().normalize();
         
         NodeList nList = doc.getElementsByTagName("student");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            answer += "\nCurrent Element :" + nNode.getNodeName();
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               answer += "Student roll no : " 
                  + eElement.getAttribute("rollno")+"\n";
              answer += "First Name : " 
                  + eElement
                  .getElementsByTagName("firstname")
                  .item(0)
                  .getTextContent()+"\n";
              answer += "Last Name : " 
                  + eElement
                  .getElementsByTagName("lastname")
                  .item(0)
                  .getTextContent()+"\n";
               answer += "Nick Name : " 
                  + eElement
                  .getElementsByTagName("nickname")
                  .item(0)
                  .getTextContent()+"\n";
              answer += "Marks : " 
                  + eElement
                  .getElementsByTagName("marks")
                  .item(0)
                  .getTextContent()+"\n";
            }
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   
   return answer;
   }

   public void run() {
        try {
            
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
           
            Socket server = serverSocket.accept();

            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
           while(true) {

            DataInputStream in = new DataInputStream(server.getInputStream());
            String b = in.readUTF();
            System.out.println(b);
            
            
            
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(XmlToString(b));
            
            
        
           
           }
           
         }
        catch(IOException e) {
            e.printStackTrace();
           
         }
      
   }
   
   public static void main(String [] args) throws IOException {
      int port = Integer.parseInt("7777");
         Thread t = new serverpart(port);
         t.start();
      
     
   }

 

    
}
