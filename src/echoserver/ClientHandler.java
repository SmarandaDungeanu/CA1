/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import static echoserver.EchoServer.send;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

/**
 *
 * @author smarandadungeanu
 */
public class ClientHandler extends Thread
{

    Scanner input;
    PrintWriter writer;
    Socket socket;
    private String name;
    boolean isActive;

    public ClientHandler(Socket socket) throws IOException
    {
        input = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
        isActive = true;
    }

    @Override
    public void run()
    {
        String message;
//        String message = input.nextLine(); //IMPORTANT blocking call
//        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message));
////        while(!message.equals(ProtocolStrings.STOP))
        while (isActive)
        {
            message = input.nextLine(); //IMPORTANT blocking call
            Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, String.format("Received the message: %1$S ", message.toUpperCase()));
            decode(message);

            // message = input.nextLine(); //IMPORTANT blocking call
        }
        //writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
        try
        {
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(EchoServer.class.getName()).log(Level.INFO, "Closed a Connection");
    }

    public String getClientName()
    {
        return name;
    }

    public void setClientName(String name)
    {
        this.name = name;
    }

    public void send(String msg)
    {

        writer.println(msg);
    }

    public void decode(String msg)
    {
        String[] msgParts = msg.split("\\#");
        String command = msgParts[0];
        switch (command)
        {
            case ProtocolStrings.CONNECT:
                //write back the list of users send("ONLINE#Marek,Smara","*")
                //message must have a name after the connect
                if (msgParts.length > 1)
                {
                    name = msgParts[1];
                    //ch.setClientName(msgParts[1]);
                    EchoServer.addHandler(this);
                    EchoServer.send(ProtocolStrings.ONLINE, new String[]
                    {
                        ProtocolStrings.EVERYBODY
                    });
                }
                break;
            case ProtocolStrings.SEND:
                //message must contain the names of recipients and also the message
                 if (msgParts.length > 2)
                {
                String[] recipients = msgParts[1].split(",");
                String message = ProtocolStrings.MESSAGE + ProtocolStrings.DIVIDER + msgParts[1] + ProtocolStrings.DIVIDER + msgParts[2];
                EchoServer.send(message, recipients);
                break;
                }
            case ProtocolStrings.CLOSE:
                //message must be ended with a #
                if (msg.equals(ProtocolStrings.STOP))
                {
                    isActive = false;
                    //send a remove statement to the caller
                    EchoServer.send(ProtocolStrings.CLOSE, new String[]
                    {
                        name
                    });
                    //remove him from the clienthandlers in server
                    EchoServer.removeHandler(this);
                    //after user disconects, the message with the new ONLINE user list will be sent out to others
                    EchoServer.send(ProtocolStrings.ONLINE, new String[]
                    {
                        ProtocolStrings.EVERYBODY
                    });
                }
                break;
            default:
                break;
        }
    }

}
