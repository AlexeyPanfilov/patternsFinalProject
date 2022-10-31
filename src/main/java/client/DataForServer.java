package client;

public class DataForServer {

    protected String type;
    protected String task;

    public DataForServer () {};
    public DataForServer(String type) {
        this.type = type;
    }
    public DataForServer(String type, String task) {
        this.type = type;
        this.task = task;
    }
}
