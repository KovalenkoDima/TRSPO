/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientandserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Clientandserver {

  
    public static void main(String [] args) {
      String serverName = "127.0.0.1";
      int port = Integer.parseInt("7777");
      try {
          
          
          
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         while(true)
         {
             String xmlString = "";
             try
             {
             
            StringWriter stringWriter = new StringWriter();
  Scanner inn = new Scanner(System.in);
         XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
         XMLStreamWriter xMLStreamWriter =
         xMLOutputFactory.createXMLStreamWriter(stringWriter);
   
         xMLStreamWriter.writeStartDocument();
         Boolean a = false;
         
         xMLStreamWriter.writeStartElement("students");
         
         while(a == false)
         {
         
         xMLStreamWriter.writeStartElement("student");
          String rollno = inn.nextLine();
         xMLStreamWriter.writeAttribute("rollno", rollno);
   
         xMLStreamWriter.writeStartElement("firstname");
         String firstname = inn.nextLine();
         xMLStreamWriter.writeCharacters(firstname);
         xMLStreamWriter.writeEndElement();
      
         xMLStreamWriter.writeStartElement("lastname");	
         String lastname = inn.nextLine();
         xMLStreamWriter.writeCharacters(lastname);
         xMLStreamWriter.writeEndElement();

         xMLStreamWriter.writeStartElement("nickname");	
         String nickname = inn.nextLine();
         xMLStreamWriter.writeCharacters(nickname);
         xMLStreamWriter.writeEndElement();
         
         xMLStreamWriter.writeStartElement("marks");	
         String marks = inn.nextLine();
         xMLStreamWriter.writeCharacters(marks);
         xMLStreamWriter.writeEndElement();

         xMLStreamWriter.writeEndElement();
         
         System.out.println("Добавить еще одного сутдента?");
         String aa = inn.nextLine();
         if(aa.equals("no")) a = true;
         }
          xMLStreamWriter.writeEndElement();
         xMLStreamWriter.writeEndDocument();
      
         xMLStreamWriter.flush();
         xMLStreamWriter.close();
      
         xmlString = stringWriter.getBuffer().toString();

         stringWriter.close();
         
        

         System.out.println(xmlString);
             }
             catch (XMLStreamException e) {
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

    
                    
         OutputStream outToServer = client.getOutputStream();
          DataOutputStream out = new DataOutputStream(outToServer);
          out.writeUTF(xmlString);
          
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         System.out.println(in.readUTF());
             
             /*
         System.out.println("Just connected to" + client.getRemoteSocketAddress());
         
         
         System.out.println("Server says " + in.readUTF());
         */
         }
      }catch(IOException e) {
         e.printStackTrace();
      }
    
}
}
