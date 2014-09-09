package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;
import utils.Utils;


public class EchoServer {

  private static boolean keepRunning = true;
  private static ServerSocket serverSocket;
  private static final Properties properties = Utils.initProperties("server.properties");
  private static Map<String, ClientHandler> clientHandlers;
 
 

  public static void removeHandler(ClientHandler ch)
  {
      clientHandlers.remove(ch.getClientName());
  }
  
  public static void addHandler(ClientHandler ch)
  {
      clientHandlers.put(ch.getClientName(), ch);
      ch.start();
  }
  
  public static void stopServer() {
    keepRunning = false;
  }
  
  public static void send(String msg)
  {
      for(Map.Entry<String, ClientHandler> ch : clientHandlers.entrySet())
      {
          ch.getValue().send(msg.toUpperCase());
      }
  }

  public static void main(String[] args) {
    int port = Integer.parseInt(properties.getProperty("port"));
    String ip = properties.getProperty("serverIp");
    String logFile = properties.getProperty("logFile");
    Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Sever started");
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress(ip, port));
      do {
        Socket socket = serverSocket.accept(); //Important Blocking call
        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Connected to a client");    
        ClientHandler ch = new ClientHandler(socket);
        addHandler(ch);
      } while (keepRunning);
    } catch (IOException ex) {
      Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
        Utils.closeLogger(EchoServer.class.getName());
    }
  }
}
