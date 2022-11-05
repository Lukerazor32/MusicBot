package command;

import com.example.telegram_bot.command.StopCommand;
import com.example.telegram_bot.state.State;
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
    State getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}