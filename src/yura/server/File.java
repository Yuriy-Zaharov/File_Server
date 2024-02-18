package yura.server;

public class File {
    private String name;
    private String path;

    File(String name) {
        this.name = name;
    }

    File(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
