import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args)throws Exception {

        ServerSocket ss = new ServerSocket(6690);
        Socket s = ss.accept();

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        System.out.println("If you want to stop the conversation type 'stop'");
        while(!str.equals("stop")) {

            str = din.readUTF();
            System.out.println("Client says: " +str);
            System.out.print("Enter Message: ");
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();

    }
}