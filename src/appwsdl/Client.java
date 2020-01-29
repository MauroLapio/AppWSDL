package appwsdl;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client
{
    private Socket socket;
    private PrintWriter out;
    
    public Client ()
    {
        try
        {
            socket = new Socket("websrv.cs.fsu.edu", 80);
            out = new PrintWriter(socket.getOutputStream(), true);  //output dell'header
        }
        catch(Exception e)
        {
            System.out.println("Errore: "+e);
        }
    }
    
    public void send(String operation, int a, int b)
    {
        try
        {
            String request =
                    "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                    "<soapenv:Header/>\n" +
                    "<soapenv:Body>\n" +
                    "<urn:"+operation+" soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "<a xsi:type=\"xsd:double\">"+a+"</a>\n" +
                    "<b xsi:type=\"xsd:double\">"+b+"</b>\n" +
                    "</urn:"+operation+">\n" +
                    "</soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            
            File f = new File("./request.xml");
            BufferedWriter writer = new BufferedWriter(new FileWriter("request.xml"));
            writer.write(request);

            writer.close();
            System.out.println("REQUEST:\n"+request+"\n");
            
            out.println("POST /~engelen/calcserver.cgi HTTP/1.1");
            out.println("Host: websrv.cs.fsu.edu");
            out.println("Connection: Keep-Alive");
            out.println("Content-Length: "+f.length());
            System.out.println("File Length: "+f.length());
            out.println(); // blank line between headers and content, very important !
            String content = new String ( Files.readAllBytes( Paths.get("./request.xml")));
            out.println(content);
            out.flush(); // flush character output stream buffer
            
            System.out.println("RESPONSE: ");
            
            Scanner in = new Scanner(socket.getInputStream());
            String ln;
            while(in.hasNext())
            {
                ln = in.nextLine();
                System.out.println(ln);
                if(ln.length()>0 && ln.substring(0,5).equals("<?xml"))  //output del risultato solo della stringa contenente XML
                {
                    System.out.println("Result: "+ln.substring(ln.indexOf("<result>")+8,ln.indexOf("</result>"))+"\n");
                }
            }
            
        }
        catch(IOException e)
        {
            System.out.println("Errore: "+e);
        }
    }
    
    public void close ()
    {
        try
        {
            socket.close();
        }
        catch(Exception e)
        {
            System.out.println("Errore: "+e);
        }
    }
}
