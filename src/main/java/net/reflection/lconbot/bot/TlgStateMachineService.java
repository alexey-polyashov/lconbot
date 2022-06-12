package net.reflection.lconbot.bot;

import lombok.RequiredArgsConstructor;
import net.reflection.lconbot.bot.tlg_utils.TlgHelper;
import net.reflection.lconbot.bot.action.PreparedMesages;
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

@Service
@RequiredArgsConstructor
public class TlgStateMachineService {

    private final StateMachinePersister<BotState, BotEvent, Long> persister;
    private final ChatUserService chatUserService;
    private final StateMachineFactory<BotState, BotEvent> stateMachineFactory;
    private final TlgHelper tlgHelper;

    public void doWork(Long userId, Message message, SendMessage sendMessage, String callBack){

        final StateMachine<BotState, BotEvent> stateMachine = stateMachineFactory.getStateMachine();
        PreparedMesages preparedMessages = new PreparedMesages();

        stateMachine.getExtendedState().getVariables().put("MESSAGE", message);
        stateMachine.getExtendedState().getVariables().put("USER_ID", userId);
        stateMachine.getExtendedState().getVariables().put("SENDMESSAGE", sendMessage);
        stateMachine.getExtendedState().getVariables().put("PREPARED_MESSAGES", preparedMessages);
        stateMachine.getExtendedState().getVariables().put("CALLBACK", callBack);

        ChatUser chatUser;
        UserChatBot userChatBot;
        final Long chatId = message.getChatId();

        if(!callBack.isEmpty()){

            chatUser = chatUserService.getUserByTelegramId(userId);
            userChatBot = chatUser.getBots().stream()
                    .findFirst().orElseThrow(()->new ResourceNotFound("Not found chat bot for user"));
            stateMachine.getExtendedState().getVariables().put("CHAT_USER", chatUser);
            stateMachine.getExtendedState().getVariables().put("USER_CHAT_BOT", userChatBot);

            BotEvent event = tlgHelper.getEventFromCallBack(callBack);

            stateMachine.getExtendedState().getVariables().put("CALLBACK", callBack);
            stateMachine.sendEvent(event);
            chatUserService.save(chatUser);
            chatUserService.save(userChatBot);

        }else{
            if(message.hasText()){
                if(message.getText().equals("/start")){
                    try {
                        chatUser = chatUserService.getUserByTelegramId(userId);
                        userChatBot = chatUser.getBots().stream()
                                .findFirst().orElseThrow(()->new ResourceNotFound("Not found chat bot for user"));
                        //этот пользователь уже есть в базе
                    } catch (ResourceNotFound e) {
                        //это новый пользователь, инициализируем его
                        chatUser = chatUserService.initNewUser(userId);
                        userChatBot = chatUserService.initNewBot(chatUser.getId(), chatId);
                    }
                    stateMachine.getExtendedState().getVariables().put("CHAT_USER", chatUser);
                    stateMachine.getExtendedState().getVariables().put("USER_CHAT_BOT", userChatBot);

                    started(chatUser, stateMachine);
                    chatUserService.save(userChatBot);
                    chatUserService.save(chatUser);

                }else{

                    chatUser = chatUserService.getUserByTelegramId(userId);
                    userChatBot = chatUser.getBots().stream()
                            .findFirst().orElseThrow(()->new ResourceNotFound("Not found chat bot for user"));
                    stateMachine.getExtendedState().getVariables().put("CHAT_USER", chatUser);
                    stateMachine.getExtendedState().getVariables().put("USER_CHAT_BOT", userChatBot);

                    if(stateMachine.getState().getId()==BotState.INIT) {
                        stateMachine.sendEvent(BotEvent.INIT_START);
                    }else{
                        stateMachine.sendEvent(BotEvent.SEND_TEXT);
                    }

                    chatUserService.save(chatUser);
                    chatUserService.save(userChatBot);

                }
            }
        }

        preparedMessages = stateMachine.getExtendedState().get("PREPARED_MESSAGES", PreparedMesages.class);
        preparedMessages.getMessages().stream().forEach(m->tlgHelper.sendMessageHere(m,chatId));

        try {
            persister.persist(stateMachine, userId);
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void started(ChatUser chatUser, StateMachine<BotState, BotEvent> stateMachine) {

        stateMachine.sendEvent(BotEvent.INIT_START);

    }


}
