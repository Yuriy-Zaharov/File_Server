package yura.client;

import yura.server.Main;
import yura.server.Server;

public class Client {
    private Server server;

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

    public void handleCommand(String strCommand) {
        String[] commandWords = strCommand.split("\\s+");

        if (commandWords.length < 1) {
            System.out.println("Inputted empty string!");
            return;
        }
        if (commandWords.length < 2) {
            if ("exit".equals(commandWords[0])) {
                Main.appOn = false;
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
}
