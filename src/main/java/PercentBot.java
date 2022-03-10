import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.cloud.*;

import java.io.*;
import java.util.Scanner;

public class PercentBot extends DefaultAbsSender {

    protected PercentBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
            return "Telegram Bot API Key";
    }
}
