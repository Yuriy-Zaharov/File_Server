package yura.server;

public class Server {
    public final String FILE_NAME = "file";
    private int countFiles;
    private File[] files;

    Server(int countFiles) {
        this.countFiles = countFiles;
        this.files = new File[countFiles];
    }

    public void addFile(String fileName) {
        int fileIndex;
        try {
            fileIndex = Integer.parseInt(fileName.substring(FILE_NAME.length())) - 1;
        } catch (Exception e) {
            System.out.println("Cannot add the file " + fileName + " with num " + fileName.substring(FILE_NAME.length()));
            System.out.println(e.getMessage());
            return;
        }
        if (fileIndex + 1 > countFiles) {
            System.out.println("Cannot add the file file11" + fileName);
            System.out.println("Because " + fileIndex + " is out of range file storage");
            return;
        }
        if (files[fileIndex] != null) {
            System.out.println("Cannot add the file " + fileName);
            System.out.println("Because this file already exists");
            return;
        }

        files[fileIndex] = new File(fileName);
        System.out.println("The file " + fileName + "added successfully");
    }
}
