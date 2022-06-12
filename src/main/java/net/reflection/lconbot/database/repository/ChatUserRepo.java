package net.reflection.lconbot.database.repository;

import net.reflection.lconbot.database.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserRepo extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> getUserByTelegramId(Long tId);
}
