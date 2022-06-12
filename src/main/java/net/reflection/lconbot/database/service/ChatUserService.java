package net.reflection.lconbot.database.service;

import lombok.RequiredArgsConstructor;
import net.reflection.lconbot.bot.BotState;
import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.database.entity.UserChatBot;
import net.reflection.lconbot.database.repository.UserChatBotRepo;
import net.reflection.lconbot.exceptions.ResourceNotFound;
import net.reflection.lconbot.database.repository.ChatUserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatUserService {

    private final ChatUserRepo chatUserRepo;
    private final UserChatBotRepo userChatBotRepo;

    public ChatUser getUserByTelegramId(Long tId){
        return chatUserRepo.getUserByTelegramId(tId).orElseThrow(
                ()->new ResourceNotFound("User '" + tId + "' not found"));
    }

    public Long save(ChatUser chatUser) {
        chatUserRepo.save(chatUser);
        return chatUser.getId();
    }

    public ChatUser initNewUser(Long tlgUserId) {
        ChatUser chatUser = new ChatUser();
        chatUser.setTelegramId(tlgUserId);
        chatUserRepo.save(chatUser);
        return chatUser;
    }

    public UserChatBot initNewBot(Long userId) {
        UserChatBot userChatBot = new UserChatBot();
        userChatBot.setChatUserId(userId);
        userChatBot.setBotStateStepNumber(0);
        userChatBot.setBotState(BotState.INIT.toString());
        userChatBotRepo.save(userChatBot);
        return userChatBot;
    }

}
