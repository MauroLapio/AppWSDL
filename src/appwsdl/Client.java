package appwsdl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Client
{
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    
    public Client ()
    {
        //costruttore vuoto
    }
    
    public void send(String operation, double a, double b)
    {
        try
        {
            socket = new Socket("websrv.cs.fsu.edu", 80);
            out = new PrintWriter(socket.getOutputStream(), true);  //output dell'header
            in = new Scanner(socket.getInputStream(), "UTF-8");  //input del socket
            
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
            System.out.println("REQUEST:\n"+request+"\n");  //OUTPUT DELLA RICHIESTA

            out.println("POST /~engelen/calcserver.cgi HTTP/1.1");
            out.println("Host: websrv.cs.fsu.edu");
            out.println("Connection: Keep-Alive");
            out.println("Content-Length: "+f.length());
            System.out.println("File Length: "+f.length());
            out.println(); // blank line between headers and content, very important !
            String content = new String ( Files.readAllBytes( Paths.get("./request.xml")));
            out.println(content);
            out.flush(); // flush character output stream buffer
            
            DecimalFormat df = new DecimalFormat("#.#");  //approssimazione dei numeri in output

            System.out.println("RESPONSE: ");  //OUTPUT DELLA RISPOSTA
            String ln;
            while(in.hasNextLine())
            {
                ln = in.nextLine();
                System.out.println(ln);
                //System.out.println(in.nextLine());
                if(ln.length()>0 && ln.substring(0,5).equals("<?xml"))  //output del risultato solo della stringa contenente XML
                {
                    System.out.println("RESULT OF "+ operation.toUpperCase()+" BETWEEN " + df.format(a) + " AND "+ df.format(b) + " : "+ln.substring(ln.indexOf("<result>")+8,ln.indexOf("</result>"))+"\n");
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
