package yura.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 34522;
    public static boolean appOn = true;
    public final String FILE_NAME = "file";
    private File[] files;

    Server() {
        this.files = new File[5];
    }

    public void addFile(String fileName) {
        int fileIndex = getFileIndex(fileName);
        if (fileIndex < 0) {
            System.out.println("Cannot add the file " + fileName);
            return;
        }
        if (files[fileIndex] != null) {
            System.out.println("Cannot add the file " + fileName);
            System.out.println("The file already exists");
            return;
        }

        files[fileIndex] = new File(fileName);
        System.out.println("The file " + fileName + " added successfully");
    }

    public void getFile(String fileName) {
        int fileIndex = getFileIndex(fileName);

        if (fileIndex < 0) {
            System.out.println("The file " + fileName + " not found");
            return;
        }
        if (files[fileIndex] == null) {
            System.out.println("The file " + fileName + " not found");
            return;
        }

        System.out.println("The file " + fileName + " was sent");
    }

    public void deleteFile(String fileName) {
        int fileIndex = getFileIndex(fileName);
        if (fileIndex < 0) {
            System.out.println("The file " + fileName + " not found");
            return;
        }
        if (files[fileIndex] == null) {
            System.out.println("The file " + fileName + " not found");
            return;
        }

        files[fileIndex] = null;
        System.out.println("The file " + fileName + " was deleted");
    }

    private int getFileIndex(String fileName) {
        int fileIndex;

        try {
            if (! FILE_NAME.equals(fileName.substring(0, FILE_NAME.length())) ) {
                return -1;
            }
            fileIndex = Integer.parseInt(fileName.substring(FILE_NAME.length())) - 1;
        } catch (Exception e) {
            return -1;
        }
        return fileIndex;
    }


    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);

        String address = "127.0.0.1";

        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(address));) {
            while (true) {
                try (
                    Socket socket = server.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output  = new DataOutputStream(socket.getOutputStream());
                ) {
                    String msg = input.readUTF(); // read a message from the client
                    output.writeUTF(msg); // resend it to the client
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
