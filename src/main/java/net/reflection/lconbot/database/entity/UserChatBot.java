package net.reflection.lconbot.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_chat_bots")
@Getter
@Setter
@NoArgsConstructor
public class UserChatBot {

    @Column(name="bot_id")
    private Long botId;

    @Column(name="chat_user_id")
    private Long chatUserId;

    @Column(name="bot_state")
    private String botState;

    @Column(name="bot_state_step_number")
    private Integer botStateStepNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateTime;
}
