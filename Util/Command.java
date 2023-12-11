package Util;

import java.util.function.Consumer;
import java.util.function.Function;

public class Command {
    public String name;
    public Function<String, Boolean> onCommand;

    public Command(String name, Function<String, Boolean> onCommand) {
        this.name = name;
        this.onCommand = onCommand;
    }
}
