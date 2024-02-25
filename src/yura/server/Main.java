package yura.server;

import java.util.Scanner;

public class Main {
    private final static int COUNT_FILES = 10;
    static boolean appOn = true;

    public static String readCommand(Scanner scanInput) {
        String strInput;
        try {
            strInput = scanInput.nextLine();
        } catch (Exception e) {
            return "";
        }
        return strInput;
    }

    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);

        Server server = new Server(COUNT_FILES);
        Client client = new Client(server);


        while (appOn) {
            String strInput = readCommand(scanInput);
            client.handleCommand(strInput);

        }
    }
}
