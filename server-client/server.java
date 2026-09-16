import java.io.*;
import java.net.*;

public class server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5001);
            System.out.println("Server started. Waiting for clients to connect...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: ");
            BufferedReader input = 
                          new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = 
                          new PrintWriter(socket.getOutputStream(), true);
            
            String message = input.readLine();
            System.out.println("Received from client: " + message);
            output.println("Hello client");
            socket.close();
            serverSocket.close();
            System.out.println("Server closed.");





            }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }






}
