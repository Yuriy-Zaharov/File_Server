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
        if (fileIndex + 1 > countFiles) {
            return -1;
        }
        return fileIndex;
    }
}
