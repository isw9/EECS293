import java.io.InputStream;


final class FileIOBarricade {
    private final InputStream stream;
    private boolean finished = false;

    FileIOBarricade(InputStream stream) {
        this.stream = stream;
    }

    String nextLine() {
        StringBuilder contents = new StringBuilder();

        try {
            int nextByte;
            while ((nextByte = stream.read()) >= 0 && nextByte != '\n') {
                contents.append((char)nextByte);
            }

            finished = nextByte < 0;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return contents.toString().trim();
    }

    boolean isFinished() {
        return finished;
    }
}

