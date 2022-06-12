package net.reflection.lconbot.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_achievements")
@Getter
@Setter
@NoArgsConstructor
public class UserAchievements {

    @Column(name="chat_user_id")
    Long chatUserId;

    @Column(name="course_id")
    Long courseId;

    @Column(name="course_stage_id")
    Long courseStageId;

    @Column(name="task_id")
    Long task;

    @Column(name="type")
    String type;

    @Column(name="cost")
    Integer cost;

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
