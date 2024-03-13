import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost"; // Replace with server's IP if necessary
        int port = 8081;

        // Connect to server
        Socket clientSocket = new Socket(host, port);
        System.out.println("Connected to server!");

        // Get user input and send messages
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true);

        String message;
        do {
            System.out.print("Enter message (or 'stop' to quit): ");
            message = reader.readLine();
            writer.println(message);
        } while (!message.equalsIgnoreCase("stop"));

        // Read server response (optional)

        BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String serverResponse;
        while ((serverResponse = serverReader.readLine()) != null) {
            System.out.println("Server: " + serverResponse);
        }


        // Close connection
        System.out.println("Closing connection...");
        clientSocket.close();
        reader.close();
        writer.close();
        serverReader.close(); // Uncomment if reading server response
    }
}
