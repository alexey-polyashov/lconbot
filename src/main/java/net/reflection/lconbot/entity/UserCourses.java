package net.reflection.lconbot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_courses")
@Getter
@Setter
@NoArgsConstructor
public class UserCourses {

    @Column(name="chat_user_id")
    Long chatUserId;

    @Column(name="course_id")
    Long courseId;

    @Column(name="state")
    String state;

    @Column(name="begin_date")
    Date beginDate;

    @Column(name="completion_date")
    Date completionDate;

    @Column(name="active_stage_id")
    Long activeStageId;

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
