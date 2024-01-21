import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket  server = new ServerSocket(30333);
        while(true) {
//            Socket sock = server.accept();
//            ClientThread client = new ClientThread(sock);
//            client.startProcessing();
            new ClientThread(server.accept()).startProcessing();
        }
    }
}
