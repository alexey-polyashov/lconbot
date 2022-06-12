package net.reflection.lconbot.bot.action;

import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.database.entity.UserChatBot;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RegisterNewUser implements Action<BotState, BotEvent> {

    @Override
    public void execute(StateContext<BotState, BotEvent> stateContext) {

        System.out.println("RegisterNewUser");

        final Message message = stateContext.getExtendedState().get("MESSAGE", Message.class);
        final Long userId = stateContext.getExtendedState().get("USER_ID", Long.class);
        final ChatUser chatUser = stateContext.getExtendedState().get("CHAT_USER", ChatUser.class);
        final UserChatBot userChatBot = stateContext.getExtendedState().get("USER_CHAT_BOT", UserChatBot.class);
        final SendMessage sendMessage = stateContext.getExtendedState().get("SENDMESSAGE", SendMessage.class);
        Integer stepNumber = userChatBot.getBotStateStepNumber();
        PreparedMesages preparedMessages = new PreparedMesages();
        SendMessage sm = new SendMessage();
        switch (stepNumber){
            case 0:
                sm.setText("Привет!");
                preparedMessages.add(sm);
                sm = new SendMessage();
                sm.setText("Поздравляю с началом прохождения нового курса!");
                preparedMessages.add(sm);

                sendMessage.setText("Для начала необходимо ответить на несколько вопросов. Готов?");

                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("Да");
                button.setCallbackData("Да");
                keyboardButtonsRow.add(button);

                button = new InlineKeyboardButton();
                button.setText("Нет");
                button.setCallbackData("Нет");
                keyboardButtonsRow.add(button);

                List<List<InlineKeyboardButton>> kRows = new ArrayList<>();
                kRows.add(keyboardButtonsRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(kRows);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                userChatBot.setBotStateStepNumber(stepNumber+1);

                break;

            case 1:
                sm.setText("Привет!");
                preparedMessages.add(sm);
                sm = new SendMessage();
                sm.setText("Поздравляю с началом прохождения нового курса!");
                preparedMessages.add(sm);
                sm = new SendMessage();
                sm.setText("Для начала необходимо ответить на несколько вопросов. Готов?");
                preparedMessages.add(sm);
        }
        stateContext.getExtendedState().getVariables().put("PREPARED_MESSAGES", preparedMessages);
        stateContext.getExtendedState().getVariables().put("SEND_MESSAGE", sendMessage);
    }




}
