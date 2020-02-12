package appwsdl;

public class AppWSDL
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.send("add", 2, 2);
        client.send("div", 26, 5);
        client.send("mul", 4, 3.5);
        client.send("pow", Math.exp(1), 4);
        client.close();
    }
    
}
