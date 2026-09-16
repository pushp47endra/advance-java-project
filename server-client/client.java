import java.io.*;
import java.net.*;
public class client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5001);
            PrintWriter output = 
                          new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = 
                          new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            output.println("Hello server");
            String response = input.readLine();
            System.out.println("Received from server: " + response);
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    
}
