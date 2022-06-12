package net.reflection.lconbot.database.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Column(name="course_id")
    Long courseId;

    @Column(name="stage_id")
    Long stageId;

    @Column(name="lesson_id")
    Long lessonId;

    @Column(name="presentation")
    String presentation;

    @Column(name="description")
    String description;

    @Column(name="type")
    String type;

    @Column(name="duration")
    Integer duration;

    @OneToMany
    @JoinColumn(name = "task_id")
    List<AnswerVariant> answers;

    @Column(name="cost")
    Integer cost;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "deletion_mark")
    @ColumnDefault("false")
    private Boolean deletionMark = false;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateTime;
}
