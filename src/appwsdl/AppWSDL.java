package appwsdl;

import java.io.PrintWriter;
import java.net.Socket;

public class AppWSDL
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.send();
        client.close();
    }
    
}
