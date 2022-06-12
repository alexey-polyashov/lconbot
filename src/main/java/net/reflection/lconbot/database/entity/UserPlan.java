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
@Table(name = "user_plan")
@Getter
@Setter
@NoArgsConstructor
public class UserPlan {

    @Column(name="chat_user_id")
    Long chatUserId;

    @Column(name="course_id")
    Long courseId;

    @Column(name="stage_id")
    Long stageId;

    @Column(name="lesson_id")
    Long lessonId;

    Date date;

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
