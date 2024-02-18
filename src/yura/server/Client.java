package yura.server;

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

    private void addFile(String[] commandWords) {
        if (commandWords.length < 2) {
            System.out.println("File's name wasn't inputted. add usage: add " + server.FILE_NAME + "1");
            return;
        } else if (! server.FILE_NAME.equals(commandWords[1].substring(0, server.FILE_NAME.length())) ) {
            System.out.println("Cannot add the file " + commandWords[1]);
            return;
        }
        server.addFile(commandWords[1]);
    }

    public void handleCommand(String strCommand) {
        String[] commandWords = strCommand.split("\\s+");

        if (commandWords.length < 1) {
            System.out.println("Inputted empty string!");
            return;
        }

        switch (commandWords[0]) {
            case "add": {
                addFile(commandWords);
                break;
            }
            case "exit": {
                Main.appOn = false;
                break;
            }
            default: {
                System.out.println("Command wasn't found.");
            }
        }
    }
}
