import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Thread self;
    private final Socket sock;
    private BufferedReader reader;
    private BufferedWriter writer ;
    private static int clientsNum;
    private final int clientID;

    public ClientThread(Socket sock) {
        this.sock = sock;
        self = new Thread(this);
        clientID=clientsNum++;
    }
    public void startProcessing() throws IOException {
        reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        self.start();
    }

    @Override
    public void run() {
        String str;
       try{
           while(true) {

            str =reader.readLine();
            if("exit".equals(str)) break;

            System.out.println("client-" + clientID + " " +  sock.getInetAddress().getCanonicalHostName() + " sent: " + str);
            writer.write("accepted\n");
            writer.flush();
        }
       }catch (IOException ex){}
        System.out.println("client-" + clientID + " closed connection");
    }
}


