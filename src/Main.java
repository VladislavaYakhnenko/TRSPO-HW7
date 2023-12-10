public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable(args);

        Thread serverThread = new Thread(myRunnable);
        serverThread.start();

        TCPClient.main(args);
    }
}
