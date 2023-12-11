package Util;

import java.util.Scanner;

public class CommandInterpreter {
    private final Command[] commands;

    public CommandInterpreter(
            Command[] commands
    ) {
        this.commands = commands;
    }

    public void listen(String msg) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(msg);
            String message = in.nextLine();

            var words = message.split(" ");
            String commandName = words[0];

            for (var command : commands) {
                if (command.name.equals(commandName)) {
                    boolean shouldContinue = command.onCommand.apply(message);
                    if (!shouldContinue) return;
                    break;
                }
            }
        }
    }
}
