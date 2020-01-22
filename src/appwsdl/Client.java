package appwsdl;

import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    Socket socket;
    PrintWriter out;
    
    public Client ()
    {
        try
        {
            socket = new Socket("127.0.0.1", 80);
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(Exception e)
        {
            System.out.println("Errore: "+e);
        }
    }
    
    public void send (String url)
    {
        out.print("ADD:\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<urn:pow soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "<a xsi:type=\"xsd:double\">?</a>\n" +
                "<b xsi:type=\"xsd:double\">?</b>\n" +
                "</urn:add>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n" +
                "DIV:\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<urn:div soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "<a xsi:type=\"xsd:double\">?</a>\n" +
                "<b xsi:type=\"xsd:double\">?</b>\n" +
                "</urn:div>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n" +
                "MUL:\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<urn:mul soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "<a xsi:type=\"xsd:double\">?</a>\n" +
                "<b xsi:type=\"xsd:double\">?</b>\n" +
                "</urn:mul>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n" +
                "POW:\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<urn:pow soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "<a xsi:type=\"xsd:double\">?</a>\n" +
                "<b xsi:type=\"xsd:double\">?</b>\n" +
                "</urn:pow>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n" +
                "SUB:\n" +
                "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:calc\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<urn:sub soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "<a xsi:type=\"xsd:double\">?</a>\n" +
                "<b xsi:type=\"xsd:double\">?</b>\n" +
                "</urn:sub>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>");
        
    }
}
