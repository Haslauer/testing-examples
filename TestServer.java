import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestServer {
    public void connectToServer() {
        try (ServerSocket serverSocket = new ServerSocket(9991)) {
            Socket connectionSocket = serverSocket.accept();

            InputStream inputToServer = connectionSocket.getInputStream();
            OutputStream outputFromServer = connectionSocket.getOutputStream();

            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            System.out.println("Start input");
            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                done = true;
            }

            System.out.println("Start output");
            serverPrintOut.println("HTTP/1.1 200 OK");
            serverPrintOut.println("Content-Type: application/json; charset=utf-8");
            serverPrintOut.println();
            serverPrintOut.println("{ test: \"data\" }");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
