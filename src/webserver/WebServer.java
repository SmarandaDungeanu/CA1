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
        port = 80;
//        String logFile = properties.getProperty("logFile");

//        InetSocketAddress i = new InetSocketAddress("127.0.0.1", 8080);
        InetSocketAddress i = new InetSocketAddress(ip, port);
        HttpServer server = HttpServer.create(i, 0);
        server.createContext("/welcome", new WelcomeHandler());
        server.createContext("/headers", new HeadersHandler());
        server.createContext("/pages", new FilesHandler());
        server.createContext("/status", new OnlineUsersHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("zee server was started, haha xD");
        System.out.println("bound to " + ip + ", listening on port " + port);
    }

    static class WelcomeHandler implements HttpHandler
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
            sb.append("<h2>Welcome to my very first home made Web Server :-)</h2>\n");
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

    static class HeadersHandler implements HttpHandler
    {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            Map<String, List<String>> map = he.getRequestHeaders();
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>HTTP-headers</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
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
