import java.io.*;
import java.net.*;

public class ImgServer {

    public static void main(String args[]) throws IOException {
        int port = 8081;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.err.println("Client connected");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                    if (s.isEmpty())
                        break;
                }

                // Pre-rendered HTML with image source
                String htmlContent = "<html><body><h1>Hello!</h1><img src=\"img.jpg\" alt=\"Sample Image\"></body></html>";

                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/html");
                writer.println("Content-Length: " + htmlContent.length());
                writer.println();
                writer.println(htmlContent);
                writer.flush();
            }

            System.err.println("Client Connection Closed");
        }
    }
}