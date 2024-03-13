import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ImgServer {
    private static String filePath = "/home/laksh.nagar/IdeaProjects/SocketProgram/HttpSocketServer/src/index.jsp";
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8008);
        String person="";
        while (true) {
            Socket client = socket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            while (true) {
                String line = br.readLine();
                System.out.println(line);
                if (line.equals("")) {
                    break;
                }
                if (line.contains("GET")) {
                    int startIndex = line.indexOf("/");
                    int endIndex = line.indexOf(" HTTP");
                    person = line.substring(startIndex + 1, endIndex);
                    System.out.println("Person name: " +person);
                }
            }

            String clrf ="\r\n";
            Employee employee = getEmployeeDetails(person);
            String a = readFile(filePath);

            if(person.equals("image")){
                writer.print("HTTP/1.1 200 OK"+clrf);
                writer.println("Content-Type: image/jpg"+clrf);
                writer.print("<html><body><h1>Hello!</h1>" +
                        "<img src=\"/home/laksh.nagar/IdeaProjects/SocketProgram/download.jpeg\" alt=\"Sample Image\"></body></html>");
                writer.flush();
            }
            else if(person.equals("text")){
                writer.print("HTTP/1.1 200 OK"+clrf);
                writer.print("Content-Type: text/html"+clrf);
                writer.println("Content-length"+a.toString().length()+clrf);
                writer.print(a+clrf);
            }
            else {
                writer.print("HTTP/1.1 200 OK" + clrf);
                writer.print("Content-Type: text/html" + clrf);
                writer.println("Content-length" + employee.toString().length() +clrf);
                writer.print(employee.toString() + clrf);
                writer.flush();
            }

            writer.flush();
            writer.close();
            br.close();
            client.close();
        }
    }

    private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    public static Employee getEmployeeDetails(String name) {
        Employee pushkar = new Employee(name, "abc@pushkar.com", "NewCompany");
        if (Objects.equals(name, "pushkar")) {
            return pushkar;
        } else {
            return new Employee();
        }
    }
}
