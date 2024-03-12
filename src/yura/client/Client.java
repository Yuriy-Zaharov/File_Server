package yura.client;

import yura.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Server server;
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public Client() {

    }

    public Client(Server server) {
        this.server = server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public static String readCommand(Scanner scanInput) {
        String strInput;
        try {
            strInput = scanInput.nextLine();
        } catch (Exception e) {
            return "";
        }
        return strInput;
    }

    public void handleCommand(String strCommand) {
        String[] commandWords = strCommand.split("\\s+");

        if (commandWords.length < 1) {
            System.out.println("Inputted empty string!");
            return;
        }
        if (commandWords.length < 2) {
            if ("exit".equals(commandWords[0])) {
                Server.appOn = false;
            } else {
                System.out.println("File's name wasn't inputted. add usage: add " + server.FILE_NAME + "1");
            }
            return;
        }

        switch (commandWords[0]) {
            case "add" -> {
                server.addFile(commandWords[1]);
            }
            case "get" -> {
                server.getFile(commandWords[1]);
            }
            case "delete" -> {
                server.deleteFile(commandWords[1]);
            }
            default -> {
                System.out.println("Command wasn't found.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);

        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output  = new DataOutputStream(socket.getOutputStream())
        ) {
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();

            output.writeUTF(msg); // send a message to the server
            String receivedMsg = input.readUTF(); // read the reply from the server

            System.out.println("Received from the server: " + receivedMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
