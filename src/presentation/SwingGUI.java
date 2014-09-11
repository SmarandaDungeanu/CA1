package presentation;

import chatclient.ChatClient;
import chatclient.MessageListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import shared.ProtocolStrings;

public class SwingGUI extends javax.swing.JFrame implements MessageListener
{

    ChatClient client;

    public SwingGUI()
    {
        client = new ChatClient();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ipLabel = new javax.swing.JLabel();
        ipTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList();
        messageTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatClient");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        ipLabel.setText("IP");

        ipTextField.setText("127.0.0.1");

        portLabel.setText("Port");

        portTextField.setText("9080");
        portTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextFieldActionPerformed(evt);
            }
        });

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        chatTextArea.setEditable(false);
        chatTextArea.setColumns(20);
        chatTextArea.setLineWrap(true);
        chatTextArea.setRows(5);
        chatTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(chatTextArea);

        jScrollPane2.setViewportView(usersList);

        messageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageTextFieldKeyPressed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        usernameLabel.setText("Username:");

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText("Userlist");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageTextField)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ipLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(portLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameTextField)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(connectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void messageTextFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_messageTextFieldKeyPressed
    {//GEN-HEADEREND:event_messageTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !messageTextField.getText().isEmpty()
                && usersList.getSelectedIndex() != -1 && !usersList.getSelectedValue().toString().equals(usernameTextField.getText()))
        {
            sendButton.doClick();
        }
    }//GEN-LAST:event_messageTextFieldKeyPressed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_connectButtonActionPerformed
        {//GEN-HEADEREND:event_connectButtonActionPerformed
            if (connectButton.getText().equals("Connect"))
            {
                try
                {
                    client.connect(ipTextField.getText(), Integer.parseInt(portTextField.getText()), usernameTextField.getText());
                    client.registerEchoListener(this);
                    connectButton.setText("Disconnect");
                    usernameTextField.setEditable(false);
                    chatTextArea.append("You are now connected to the chat. \n");
                } catch (IOException ex)
                {
                    Logger.getLogger(SwingGUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "Could not connect to server!", "Dialog", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                client.unRegisterEchoListener(this);
                usernameTextField.setEditable(true);
                connectButton.setText("Connect");
            }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sendButtonActionPerformed
    {//GEN-HEADEREND:event_sendButtonActionPerformed
        if (!messageTextField.getText().isEmpty() && usersList.getSelectedIndex() != -1
                && !usersList.getSelectedValue().toString().equals(usernameTextField.getText()))
        {
            String receiverz = "";
            for (Object o : usersList.getSelectedValuesList())
            {
                receiverz += o.toString() + ",";
            }
            receiverz = receiverz.substring(0, receiverz.length() - 1);
            client.send("SEND#" + receiverz + "#" + messageTextField.getText());
            chatTextArea.append(usernameTextField.getText() + ": " + messageTextField.getText() + "\n");
            messageTextField.setText(null);
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        client.unRegisterEchoListener(this);
    }//GEN-LAST:event_formWindowClosing

    private void portTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SwingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SwingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SwingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SwingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new SwingGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JList usersList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageArrived(String data)
    {
        String[] msgParts = data.split("\\#");
        //1. Find out what kind of message the server is sending back and build the text 
        String command = msgParts[0];
        switch (command)
        {
            case ProtocolStrings.ONLINE:
                //write back the list of users send("ONLINE#Marek,Smara","*")
                //In case that the client wants to connect, the server must send back ONLINE and list of connected users
                String[] users = msgParts[1].split(",");
                DefaultListModel model = new DefaultListModel();
                //We add the star so we can select everybody
                model.addElement("*");
                for (String s : users)
                {
                    model.addElement(s);
                }
                usersList.setModel(model);
                break;
            case ProtocolStrings.MESSAGE:
                //in case the client wants to send the message we must find out who the messae is going to and then we need to add them and the text to the message
//                msg = msg + ProtocolStrings.DIVIDER;
//                for(String s:recipients)
//                {
//                    msg = msg + s + ",";
//                }
//                //At the end the last person will have a comma after his name so we just have to remove it
//                msg = msg.substring(0, msg.length() - 1);
                String sender = msgParts[1];
                String message = msgParts[2];
                chatTextArea.append(sender + " : " + message + "\n");
                break;
            case ProtocolStrings.CLOSE:
                //         msg = msg + ProtocolStrings.DIVIDER;
                chatTextArea.append("You disconected from the server");
                break;
            default:
                break;
        }
    }

}
