import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TCPClient {
    public static void main(String[] args) {
        try {
            runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runClient() throws IOException {
        Socket serverSocket = new Socket("127.0.0.1", 8081);

        ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

        for (int i = 1; i <= 100; i++) {
            int length = in.readInt();
            byte[] receivedBytes = new byte[length];
            in.readFully(receivedBytes);
            String receivedMessage = new String(receivedBytes, StandardCharsets.UTF_8);
            System.out.println("Client: Received: " + receivedMessage);

            String response = "Response to Message " + i;
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
            out.writeInt(responseBytes.length);
            out.write(responseBytes);
            out.flush();
            System.out.println("Client: Sent: " + response);
        }

        serverSocket.close();
    }
}
