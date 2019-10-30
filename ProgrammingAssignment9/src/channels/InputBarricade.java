import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

final class InputBarricade {
    final LogState state;

    void apply(String input) {
        getCommandsFrom(input).parallelStream().forEach(state::applyCommand);
    }

    void handle(FileIOBarricade barricade) {
        while (!isConnected() && !barricade.isFinished()) {
            apply(barricade.nextLine());
        }
    }

    InputBarricade(int sourceAddr, int destAddr) {
        state = new LogState(destAddr, sourceAddr);
    }

    boolean isConnected() {
        return state.isConnected();
    }

    String connectionStatus() {
        return state.toString();
    }

    //complexity 4
    private static List<Command> getCommandsFrom(String input) {
        List<Command> commands = new LinkedList<>();

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] splitTabs = line.split("\t");
            Collections.reverse(Arrays.asList(splitTabs));
            for (String actualLine : splitTabs) {
                if (!actualLine.isEmpty()) {
                    Command commandToAdd = commandFromLine(actualLine.split(" ", 2));

                    if (!commandToAdd.equals(Command.EMPTY)) {
                        commands.add(commandToAdd);
                    }
                }
            }
        }


        return Collections.unmodifiableList(commands);
    }

    private static Command commandFromLine(String[] lineParts) {
        if (lineParts.length == 2) {
            String type = lineParts[0];
            String argument = lineParts[1];

            String[] argumentParts = argument.split(" ");
            if (argumentParts.length == 1) {
                try {
                    Integer.parseInt(argument);
                }
                catch (NumberFormatException e){
                   return Command.EMPTY;
                }
                return new Command(type, argument);
            }
        }

        return Command.EMPTY;
    }

    static class TestHook{
        List<Command> testCommandsFrom(String s){
            return getCommandsFrom(s);
        }
    }
}