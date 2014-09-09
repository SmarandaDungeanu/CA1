package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;
import utils.Utils;

public class EchoServer
{
    private static boolean keepRunning = true;
    private static ServerSocket serverSocket;
    private static final Properties properties = Utils.initProperties("server.properties");
    private static HashMap<String, ClientHandler> clientHandlers;

    public static void removeHandler(ClientHandler ch)
    {
        clientHandlers.remove(ch.getClientName());
    }

    public static void addHandler(ClientHandler ch)
    {
        clientHandlers.put(ch.getClientName(), ch);
//        ch.start();
    }

    public static void stopServer()
    {
        keepRunning = false;
    }

    public static void send(String msg, String[] recipients)
    {
        switch (msg)
        {
            case ProtocolStrings.ONLINE:
                //write back the list of users send("ONLINE#Marek,Smara","*")
                //In case that the client wants to connect, the server must send back ONLINE and list of connected users
                msg = msg + ProtocolStrings.DIVIDER;
                for (Map.Entry<String, ClientHandler> ch : clientHandlers.entrySet())
                {
                    String name = ch.getKey();
                    msg = msg + name + ",";
                }
                //At the end the last person will have a comma after his name so we just have to remove it
                msg = msg.substring(0, msg.length() - 1);

                break;
            case ProtocolStrings.MESSAGE:

                break;
            case ProtocolStrings.CLOSE:
                msg = msg + ProtocolStrings.DIVIDER;
                break;
            default:
                break;
        }

        if (recipients[0].equals(ProtocolStrings.EVERYBODY))
        {
            for (Map.Entry<String, ClientHandler> ch : clientHandlers.entrySet())
            {
                ch.getValue().send(msg);
            }
        } else
        {
            for (String s : recipients)
            {
                for (Map.Entry<String, ClientHandler> ch : clientHandlers.entrySet())
                {
                    if (ch.getKey().equals(s))
                    {
                        ch.getValue().send(msg);
                    }
                }
            }
        }

    }

    public static void main(String[] args)
    {
        int port = Integer.parseInt(properties.getProperty("port"));
        String ip = properties.getProperty("serverIp");
        String logFile = properties.getProperty("logFile");
        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Sever started");
        clientHandlers =  new HashMap<String, ClientHandler>() {};
        try
        {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            do
            {
                Socket socket = serverSocket.accept(); //Important Blocking call
                Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Connected to a client");
                ClientHandler ch = new ClientHandler(socket);
//                ch.setClientName("TestShit");
//                addHandler(ch);
                ch.start();
            } while (keepRunning);
        } catch (IOException ex)
        {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Utils.closeLogger(EchoServer.class.getName());
        }
    }
}
