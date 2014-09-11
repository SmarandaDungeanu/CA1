package webserver;

import chatserver.ChatServer;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import utils.Utils;

/**
 *
 * @author Cristian
 */
public class WebServer
{

    private static ChatServer chatSV;
    static int port;
    static String ip;

    public static void main(String[] args) throws IOException
    {
        chatSV = ChatServer.getInstance();
        Properties properties = Utils.initProperties("server.properties");
        ip = properties.getProperty("serverIp");
//        port = Integer.parseInt(properties.getProperty("port"));
<<<<<<< HEAD
        port = 80;
=======
        port = 8080;
>>>>>>> origin/Smara
//        String logFile = properties.getProperty("logFile");

//        InetSocketAddress i = new InetSocketAddress("127.0.0.1", 8080);
        InetSocketAddress i = new InetSocketAddress(ip, port);
        HttpServer server = HttpServer.create(i, 0);
<<<<<<< HEAD
        server.createContext("/welcome", new WelcomeHandler());
        server.createContext("/headers", new HeadersHandler());
        server.createContext("/pages", new FilesHandler());
=======
        server.createContext("/index.html", new WelcomeHandler());
        server.createContext("/CA1.jar", new WelcomeHandler());
        server.createContext("/chatLog.txt", new WelcomeHandler());
        server.createContext("/chatLog", new ChatLogHandler());
>>>>>>> origin/Smara
        server.createContext("/status", new OnlineUsersHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("zee server was started, haha xD");
        System.out.println("bound to " + ip + ", listening on port " + port);
<<<<<<< HEAD
=======
        ChatServer.getInstance().start();
>>>>>>> origin/Smara
    }

    static class WelcomeHandler implements HttpHandler
    {

        @Override
<<<<<<< HEAD
        public void handle(HttpExchange he) throws IOException
        {
//            String response = "Welcome to my first http server!";
//            he.   // here we can add more details to the header
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>My fancy Web Site</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            sb.append("<h2>Welcome to my very first home made Web Server :-)</h2>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");
=======
        public void handle(HttpExchange he) throws IOException {
            //String response = "Welcome to my first http server!";
            // here we can add more details to the header
            String requestedFile = he.getRequestURI().toString();
            String f = requestedFile.substring(requestedFile.lastIndexOf("/") + 1);
            String extension = f.substring(f.lastIndexOf("."));
            String mime = "";
            switch (extension) {
                case ".pdf":
                    mime = "application/pdf";
                    break;
                case ".html":
                    mime = "text/html";
                    break;
                case ".jar":
                    mime = "application/java-archive";
                    break;
                case ".txt":
                    mime = "text/plain";
                    break;
            }
            String contentFolder = "public/";
            File file = new File(contentFolder + f);
            byte[] bytesToSend = new byte[(int) file.length()];
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(bytesToSend, 0, bytesToSend.length);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", mime);
>>>>>>> origin/Smara

            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response);
            }
        }
    }
<<<<<<< HEAD

    static class HeadersHandler implements HttpHandler
    {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            Map<String, List<String>> map = he.getRequestHeaders();
=======
    
    static class ChatLogHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
>>>>>>> origin/Smara
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>Chat log information</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
<<<<<<< HEAD
            sb.append("<table border=\"1\" style=\"width:300px\">\n");
            sb.append("<tr>\n");
            sb.append("<td><b>Header</b></td>\n");
            sb.append("<td><b>Value</b></td>\n");
            sb.append("</tr>\n");
            for (Map.Entry<String, List<String>> entry : map.entrySet())
            {
                sb.append("<tr>\n");
                sb.append("<td>").append(entry.getKey()).append("</td>\n");
                sb.append("<td>").append(entry.getValue()).append("</td>\n");
                sb.append("</tr>\n");
            }

            sb.append("</table>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");

            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response);
            }
        }
    }

    static class FilesHandler implements HttpHandler
    {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>My fancy Web Site</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            sb.append("<h2>Welcome to my very first home made Web Server :-)</h2>\n");
=======
            sb.append("<h2>This should work, but java...</h2>\n");
>>>>>>> origin/Smara
            sb.append("</body>\n");
            sb.append("</html>\n");

            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response);
            }
        }
    }

    static class OnlineUsersHandler implements HttpHandler
    {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
//            String response = "Welcome to my first http server!";
//            he.   // here we can add more details to the header
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>My fancy Web Site</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
//            String connected = "" + chatSV.getNbOfConnectedUsers();
//            int connected = chatSV.getNbOfConnectedUsers();
//            sb.append("<h2>").append(connected).append("</h2>\n");
            sb.append("<h2>This should work, but java...</h2>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");

            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody()))
            {
                pw.print(response);
            }
        }
    }
}
