package net.reflection.lconbot.bot;

import net.reflection.lconbot.bot.action.PreparedMesages;
import net.reflection.lconbot.config.TelegramConfig;
import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.database.entity.UserChatBot;
import net.reflection.lconbot.database.service.ChatUserService;
import net.reflection.lconbot.exceptions.ResourceNotFound;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
@SuppressWarnings("all")
public class TlgStateMachineService {

    private final StateMachinePersister<BotState, BotEvent, Long> persister;
    private final ChatUserService chatUserService;
    private final StateMachineFactory<BotState, BotEvent> stateMachineFactory;
    private TelegramConfig telegramConfig;
    private TlgFeign tlgFeign;

    public TlgStateMachineService(
            StateMachinePersister<BotState, BotEvent, Long> persister,
            StateMachineFactory<BotState, BotEvent> stateMachineFactory,
            ChatUserService chatUserService,
            TelegramConfig telegramConfig
    ) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
        this.chatUserService = chatUserService;
        this.telegramConfig = telegramConfig;
    }

    public boolean started(Long userId, Message message, SendMessage sendMessage) {

        final StateMachine<BotState, BotEvent> stateMachine = stateMachineFactory.getStateMachine();
        ChatUser chatUser;
        UserChatBot userChatBot;
        try {
            chatUser = chatUserService.getUserByTelegramId(userId);
            userChatBot = chatUser.getBots().stream()
                    .findFirst().orElse(chatUserService.initNewBot(chatUser.getId()));
            //этот пользователь уже есть в базе
        } catch (ResourceNotFound e) {
            //это новый пользователь, инициализируем его
            chatUser = chatUserService.initNewUser(userId);
            userChatBot = chatUserService.initNewBot(chatUser.getId());
        }

        PreparedMesages preparedMessages = new PreparedMesages();
        final Long chatId = message.getChatId();

        stateMachine.getExtendedState().getVariables().put("MESSAGE", userId);
        stateMachine.getExtendedState().getVariables().put("USER_ID", userId);
        stateMachine.getExtendedState().getVariables().put("CHAT_USER", chatUser);
        stateMachine.getExtendedState().getVariables().put("USER_CHAT_BOT", userChatBot);
        stateMachine.getExtendedState().getVariables().put("SENDMESSAGE", sendMessage);
        stateMachine.getExtendedState().getVariables().put("PREPARED_MESSAGES", preparedMessages);

        stateMachine.sendEvent(BotEvent.INIT_START);

        preparedMessages = stateMachine.getExtendedState().get("PREPARED_MESSAGES", PreparedMesages.class);

        preparedMessages.getMessages().stream().forEach(m->sendMessageHere(m,chatId));

        chatUserService.save(chatUser);

        try {
            persister.persist(stateMachine, userId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }


    public void sendMessageHere(SendMessage preparedMessage, Long chatId){

        tlgFeign.sendTextMessage(telegramConfig.getBotToken(), chatId ,preparedMessage.getText());
    }

}
