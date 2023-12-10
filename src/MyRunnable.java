public class MyRunnable implements Runnable {
    private final String[] args;

    public MyRunnable(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        TCPServer.main(args);
    }
}

