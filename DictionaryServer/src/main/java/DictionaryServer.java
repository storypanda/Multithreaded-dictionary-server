import java.net.ServerSocket;
import java.net.Socket;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class DictionaryServer {

    ServerSocket serverSocket;

    public void startService(int port) throws Exception{
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for connection...");

        while(true)
        {
            //Keep listening for client requests
            Socket socket = serverSocket.accept();

            ServerConnection connection = new ServerConnection(socket);
            MultithreadedProcessing multithreadedProcessing = new MultithreadedProcessing(connection);
            multithreadedProcessing.start();
        }
    }

    public static void main(String[] args) throws Exception
    {
        int port = Integer.parseInt(System.getProperty("port"));
        DictionaryServer server = new DictionaryServer();
        server.startService(port);
    }
}
