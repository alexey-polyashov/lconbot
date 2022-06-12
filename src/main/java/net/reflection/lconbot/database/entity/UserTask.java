package net.reflection.lconbot.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_tasks")
@Getter
@Setter
@NoArgsConstructor
public class UserTask {

    @Column(name = "chat_user_id")
    Long chatUserId;

    @Column(name="task_id")
    Long taskId;

    @Column(name="answer_variant_id")
    Long answerVariantId;

    @Column(name="state")
    String state;

    @Column(name="cost")
    Integer cost;

    @Column(name="completion_date")
    Date completionDate;

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
