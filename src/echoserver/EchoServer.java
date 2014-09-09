package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
  private static ClientHandler clientHandler;
 
 

  public static void stopServer() {
    keepRunning = false;
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
        clientHandler = new ClientHandler(socket);
        clientHandler.start();
      } while (keepRunning);
    } catch (IOException ex) {
      Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
        Utils.closeLogger(EchoServer.class.getName());
    }
  }
}
