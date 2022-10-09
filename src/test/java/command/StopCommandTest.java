package command;

import com.example.telegram_bot.command.Command;
import com.example.telegram_bot.command.StopCommand;
import org.junit.jupiter.api.DisplayName;

import static com.example.telegram_bot.command.CommandName.STOP;
import static com.example.telegram_bot.command.StopCommand.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}