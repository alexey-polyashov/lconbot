package net.reflection.lconbot.bot;

import lombok.RequiredArgsConstructor;

import net.reflection.lconbot.database.service.ChatUserService;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramFacade {

    private final ChatUserService chatUserService;
    private final TlgStateMachineService tlgStateMachineService;
    private final StateMachineFactory<BotState, BotEvent> stateMachineFactory;

    public BotApiMethod<?> handleUpdate(Update update){

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        Long chatId = 0L;
        Long userId = 0L;
        boolean isCallback = false;
        String callBackText = "";

        if(update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            isCallback = true;
            message = callbackQuery.getMessage();
            callBackText = callbackQuery.getData();
            userId = callbackQuery.getFrom().getId();
        } else {
            userId = message.getFrom().getId();
        }

        chatId = message.getChatId();
        sendMessage.setChatId(chatId.toString());

        if(isCallback){
            tlgStateMachineService.doWork(userId, message, sendMessage, callBackText);
        }else {
            tlgStateMachineService.doWork(userId, message, sendMessage, "");
        }

        return sendMessage;

    }
}
