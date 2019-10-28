final class Command {
    final String type;
    final Integer argument;

    public static final Command EMPTY = new Command("empty", "0");

    Command(String type, String argument) {
        this.type = type;
        this.argument = Integer.parseInt(argument);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Command && ((Command)other).type.equals(type) && ((Command)other).argument.equals(argument);
    }
}
