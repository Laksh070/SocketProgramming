import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args)throws Exception {
        Socket s = new Socket("localhost", 6690);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str="", str2="";
        System.out.println("If you want to stop the conversation type 'stop'");
        while(!str.equals("stop")) {
            System.out.print("Enter a message: ");
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2 = din.readUTF();
            System.out.println("Server says: " +str2);
        }

        dout.close();
        s.close();
    }
}
