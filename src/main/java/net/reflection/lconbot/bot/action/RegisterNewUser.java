package net.reflection.lconbot.bot.action;

import lombok.extern.slf4j.Slf4j;
import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import net.reflection.lconbot.bot.tlg_utils.TlgHelper;
import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.database.entity.UserChatBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.*;

@Slf4j
public class RegisterNewUser implements Action<BotState, BotEvent> {

    @Autowired
    private TlgHelper tlgHelper;

    @Override
    public void execute(StateContext<BotState, BotEvent> stateContext) {

        log.info("RegisterNewUser");

        final Message message = stateContext.getExtendedState().get("MESSAGE", Message.class);
        final String callBack = stateContext.getExtendedState().get("CALLBACK", String.class);
        final Long userId = stateContext.getExtendedState().get("USER_ID", Long.class);
        final ChatUser chatUser = stateContext.getExtendedState().get("CHAT_USER", ChatUser.class);
        final UserChatBot userChatBot = stateContext.getExtendedState().get("USER_CHAT_BOT", UserChatBot.class);
        final SendMessage sendMessage = stateContext.getExtendedState().get("SENDMESSAGE", SendMessage.class);

        PreparedMesages preparedMessages = new PreparedMesages();
        SendMessage sm = new SendMessage();

        String btnCode= tlgHelper.getCodeFromCallBack(callBack);
        String step= tlgHelper.getStepFromCallBack(callBack);

        if(step.isEmpty()){
            step = userChatBot.getBotStateStepNumber();
        }

        switch (step){

            case "":

                sm = new SendMessage();
                sm.setText("Привет!");
                preparedMessages.add(sm);
                sm = new SendMessage();
                sm.setText("Поздравляю с началом прохождения нового курса!");
                preparedMessages.add(sm);

                sendMessage.setText("Для начала необходимо ответить на несколько вопросов. Готовы?");

                InlineKeyboardMarkup inlineKeyboardMarkup = tlgHelper.getInlineKeyboard(new ArrayList<Map<String, String>>(){{
                    add(new HashMap<String, String>(){{
                        put("Да",   tlgHelper.getCallBackCode(BotEvent.INIT_START, "1","YES"));
                        put("Нет",  tlgHelper.getCallBackCode(BotEvent.INIT_START,"1","NO"));
                    }});
                }});

                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                userChatBot.setBotStateStepNumber("1");
                break;

            case "1":

                if(btnCode.equals("YES")){
                    sm = new SendMessage();
                    sm.setText("Отлично!");
                    preparedMessages.add(sm);
                    sendMessage.setText("Введите ваше имя, по которому я могу к вам обращаться:");
                }else{
                    sm.setText("Тогда до следующей встречи.");
                    preparedMessages.add(sm);
                }
                userChatBot.setBotStateStepNumber("2");
                break;

            case "2":

                if(message.getText().isEmpty()){
                    sendMessage.setText("Введите ваше имя, по которому я могу к вам обращаться::");
                }else{
                    chatUser.setName(message.getText());
                    sendMessage.setText("Введите ваш email, с которым вы зарегистрированы на курс:");
                    userChatBot.setBotStateStepNumber("3");
                }
                break;

            default:


        }
        stateContext.getExtendedState().getVariables().put("PREPARED_MESSAGES", preparedMessages);
        stateContext.getExtendedState().getVariables().put("SEND_MESSAGE", sendMessage);
    }


}
