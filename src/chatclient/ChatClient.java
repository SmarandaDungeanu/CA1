package chatclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

public class ChatClient extends Thread
{

    private Socket socket;
    private int port;
    private String username;
    private InetAddress serverAddress;
    private Scanner input;
    private PrintWriter output;
    private List<MessageListener> listeners = new ArrayList();

    public void connect(String address, int port, String username) throws UnknownHostException, IOException
    {
        this.username = username;
        this.port = port;
        serverAddress = InetAddress.getByName(address);
        socket = new Socket(serverAddress, port);
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
        send("CONNECT#" + username);
        start();
    }

    public void send(String msg)
    {
        output.println(msg);
    }

    public void closeTheConnection() throws IOException
    {
        output.println(ProtocolStrings.CLOSE);
    }

    @Override
    public void run()
    {
        String msg = input.nextLine();
        while (!msg.equals(ProtocolStrings.STOP))
        {
            notifyListeners(msg);
            msg = input.nextLine();
        }
        try
        {
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerEchoListener(MessageListener l)
    {
        listeners.add(l);
    }

    public void unRegisterEchoListener(MessageListener l)
    {
        listeners.remove(l);
        send("CLOSE#");
    }

    private void notifyListeners(String msg)
    {
        for (MessageListener l : listeners)
        {
            l.messageArrived(msg);
        }
    }

    public static void main(String[] args)
    {
//        int port = 9090;
//        String ip = "localhost";
//        if (args.length == 2) {
//            port = Integer.parseInt(args[0]);
//            ip = args[1];
//        }
//        try {
//            ChatClient client = new ChatClient();
//            View tester = new View();
//            tester.setVisible(true);
//            client.registerEchoListener(tester);
//            client.connect(ip, port);
//            System.out.println("Sending 'Hello world'");
//            client.send("Hello World");
//            System.out.println("Waiting for a reply");
//            client.stopIt();
//            //System.in.read();      
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
