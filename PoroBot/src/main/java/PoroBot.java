import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class PoroBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    Ollama ollama = new Ollama();

    public PoroBot(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String response;

            if (messageText.toLowerCase().startsWith("runas ")) {
                String input = messageText.substring(6).trim();
                if (!input.isEmpty()) {
                    // Dividir o input entre campeão e rota
                    String[] parts = input.split(" ");
                    String championName = parts[0];
                    String role = parts.length > 1 ? parts[1] : ""; // Se não tiver rota, deixa em branco

                    response = RunesScraper.getRunes(championName, role);
                } else {
                    response = "Por favor, informe o nome do campeão. Exemplo: 'runas akali' ou 'runas akali mid'";
                }
            } else {
                try {
                    response = ollama.getOllamaResponse(messageText);
                } catch (Exception e) {
                    response = "Erro ao processar sua solicitação.";
                }
            }

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(response)
                    .build();
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


}
