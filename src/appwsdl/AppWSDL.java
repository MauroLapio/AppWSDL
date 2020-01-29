package appwsdl;

import java.io.PrintWriter;
import java.net.Socket;

public class AppWSDL
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.send("pow", 3, 2);
        client.send("add",2,2);
        client.close();
    }
    
}
