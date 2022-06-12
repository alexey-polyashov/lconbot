package net.reflection.lconbot.bot;

import lombok.RequiredArgsConstructor;
import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.exceptions.ResourceNotFound;
import net.reflection.lconbot.database.service.ChatUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramFacade {

    private final ChatUserService chatUserService;
    private final TlgStateMachineService tlgStateMachineService;

    public BotApiMethod<?> handleUpdate(Update update){

        if(update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return null;
        } else {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            if(message.hasText()){

                if (update.getMessage().getText().equals("/start")) {

                    //проверим нет ли в базе этого пользователя
                    Long userId = message.getFrom().getId();
//                    ChatUser chatUser;
//                    try {
//                        chatUser = chatUserService.getUserByTelegramId(userId);
//                        //этот пользователь уже есть в базе
//                    } catch (ResourceNotFound e) {
//                        //это новый пользователь, продолжим
//                        chatUser = null;
//                    }
                    tlgStateMachineService.started(userId, message, sendMessage);


                }

//
//                    SendMessage sendMessage = new SendMessage()
//                            .setChatId(chat_id)
//                            .setText("You send /start");
//
//                    // Создаем клавиуатуру
//                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
//                    replyKeyboardMarkup.setSelective(true);
//                    replyKeyboardMarkup.setResizeKeyboard(true);
//                    replyKeyboardMarkup.setOneTimeKeyboard(true);
//
//                    // Создаем список строк клавиатуры
//                    List<KeyboardRow> keyboard = new ArrayList<>();
//
//                    // Первая строчка клавиатуры
//                    KeyboardRow keyboardFirstRow = new KeyboardRow();
//                    // Добавляем кнопки в первую строчку клавиатуры
//                    KeyboardButton keyboardButton = new KeyboardButton();
//
//                    keyboardButton.setText("Share your number >").setRequestContact(true);
//                    keyboardFirstRow.add(keyboardButton);
//
//                    // Добавляем все строчки клавиатуры в список
//                    keyboard.add(keyboardFirstRow);
//                    // и устанваливаем этот список нашей клавиатуре
//                    replyKeyboardMarkup.setKeyboard(keyboard);
//
//                    try {
//                        sendMessage(sendMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("#############");
//                    System.out.println(update.getMessage().getContact());
//                    System.out.println("#############");
//
//
//                message.
////                sendMessage.setText("Это lcon бот. Скоро меня научат помогать вам в освоении новых знаний");

                return sendMessage;



            }
        }
        return null;
    }
}
