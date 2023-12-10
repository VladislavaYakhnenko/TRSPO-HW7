import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TCPServer {
    public static void main(String[] args) {
        try {
            runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Server: Waiting for client connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Server: Client connected");

        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

        for (int i = 1; i <= 100; i++) {
            String message = "Message " + i;
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            out.writeInt(messageBytes.length);
            out.write(messageBytes);
            out.flush();
            System.out.println("Server: Sent: " + message);

            int length = in.readInt();
            byte[] receivedBytes = new byte[length];
            in.readFully(receivedBytes);
            String receivedMessage = new String(receivedBytes, StandardCharsets.UTF_8);
            System.out.println("Server: Received: " + receivedMessage);
        }

        clientSocket.close();
        serverSocket.close();
    }
}
