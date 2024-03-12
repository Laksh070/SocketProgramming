import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CServer{
    public static void main(String args[]) throws IOException {
        int port = 8081;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server is running on port "+port);

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.err.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String s;
            while((s = in.readLine())!=null){
                System.out.println(s);
//                if(s.isEmpty())
//                    break;
            }
            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b> Welcome!!! </b>".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<p> Hello this is server </p>".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<img src=/home/laksh.nagar/Homeimg.jpg>".getBytes());
            clientOutput.flush();
            System.err.println("Client Connection Closed");
            in.close();
            clientOutput.close();

        }

    }
}