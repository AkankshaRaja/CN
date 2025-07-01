import java.io.*;
import java.net.Socket;

public class HttpWebClient {

    public static void main(String[] args) {
        String host = "example.com";  // Change to the target website
        int port = 80; // HTTP default port
        
        try {
            // Establish a connection to the server
            Socket socket = new Socket(host, port);

            // Output stream to send the HTTP request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Input stream to read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send HTTP GET request to the server
            String httpRequest = "GET / HTTP/1.1\r\n" +
                                 "Host: " + host + "\r\n" +
                                 "Connection: close\r\n" +
                                 "\r\n";  // End of headers

            out.print(httpRequest);
            out.flush();  // Send the request immediately

            // Read and print the server's response
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close the socket and streams
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }
}
