import java.io.InputStream;

public final class ChannelDriver {
    public static void main(String[] args) {
        FileIOBarricade ioHandler = new FileIOBarricade(System.in);

        callStatusOf(System.in, Integer.parseInt(ioHandler.nextLine()), Integer.parseInt(ioHandler.nextLine()));
    }

    static String callStatusOf(InputStream stream, int destAddr, int sourceAddr) {
        FileIOBarricade ioHandler = new FileIOBarricade(stream);

        InputBarricade inputHandler = new InputBarricade(sourceAddr, destAddr);
        inputHandler.handle(ioHandler);
        System.out.println(inputHandler.connectionStatus());

        return inputHandler.connectionStatus();
    }
}
