import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

    //Создаем новый объект класса парсер и вызываем его методы для определения процентов и ставки


    public static void main(String[] args) throws IOException {
        String getBid;
        String getPercent;
        //Создаем объект класса parser и парсим ставку и процент
        Parser parser = new Parser("YOUR BROWSER COOKIE");
        {
            try {
                getPercent = parser.percent();
                getBid = parser.bid();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Открываем файл, в котором хранится процентная ставка, с которой будем сравнивать сравку
        File file = new File("files/bidAndPercentFileDump.txt");
        Scanner scanner = new Scanner(file);
        String percentInFile = scanner.nextLine();

        //Если процентная ставка не совпадает с процентной ставкой записанной в файл (т.е. процентная ставка изменилась, то отправляем сообщение в телегу)
        if (!percentInFile.equals(getPercent)) {
            //Создаем объект класса FileWriter для дальнейшей записи процентов в файл
            PrintWriter pw = new PrintWriter(file);

            PercentBot bot = new PercentBot(new DefaultBotOptions());
            try {
                bot.execute(SendMessage.builder().chatId("Chat ID").text(getBid + "\nПроцент по картам: " + getPercent).build());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            pw.print(getPercent);
            pw.close();
        }
        scanner.close();
    }
}
