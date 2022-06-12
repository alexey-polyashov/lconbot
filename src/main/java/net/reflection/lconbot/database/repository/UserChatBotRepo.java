package net.reflection.lconbot.database.repository;

import net.reflection.lconbot.database.entity.ChatUser;
import net.reflection.lconbot.database.entity.UserChatBot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChatBotRepo extends JpaRepository<UserChatBot, Long> {
    Optional<UserChatBot> getBotByBotId(Long bId);
    Optional<UserChatBot> getBotByChatUserId(Long tId);
}
