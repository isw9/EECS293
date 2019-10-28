final class LogState {
    private static final int NUMERIC_BASE = 10;

    private final int sourceAddr;
    private final int destAddr;

    private int sourceAddrCandidate = 0;
    private int destAddrCandidate = 0;

    //0 for nothing, 1 for TO, 2 for THISIS
    private int lastSeenType = 0;

    LogState(int destAddr, int sourceAddr) {
        this.sourceAddr = sourceAddr;
        this.destAddr = destAddr;
    }

    void applyCommand(Command command) {


        if (command.type.equals("REP")) {
            REPCase(command);
        }
        else {
            nonREPCase(command);
        }

    }

    private void REPCase(Command command) {
        switch (lastSeenType) {
            case 1:
                destAddrCandidate = intAppend(destAddrCandidate, command.argument); break;
            case 2:
                sourceAddrCandidate = intAppend(sourceAddrCandidate, command.argument); break;
        }
    }

    private void nonREPCase(Command command) {
        switch (command.type) {
        case "TO":
            System.out.println("ahhhhhhhhhhhhhhh");
            destAddrCandidate = command.argument;
            lastSeenType = 1;
            break;

        case "THISIS":
            sourceAddrCandidate = command.argument;
            lastSeenType = 2;
            break;
        }
    }

    boolean isConnected() {
        return sourceAddrCandidate == sourceAddr && destAddrCandidate == destAddr;
    }

    private static int intAppend(int input, int trailing) {
        return input * NUMERIC_BASE + trailing;
    }

    @Override
    public String toString() {
        if (sourceAddrCandidate < sourceAddr) {
            return "false - source address does not match";
        } else if (destAddrCandidate < destAddr) {
            return "false - destination address does not match";
        } else {
            return "true";
        }
    }
}