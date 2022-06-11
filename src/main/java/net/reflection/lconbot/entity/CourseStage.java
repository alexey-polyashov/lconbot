package net.reflection.lconbot.entity;

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
@Table(name = "course_stages")
@Getter
@Setter
@NoArgsConstructor
public class CourseStage {

    @Column(name="course_id")
    Long courseId;

    @Column(name="presentation")
    String presentation;

    @Column(name="description")
    String description;

    @Column(name="duration")
    Integer duration; //ih hours

    @OneToMany
    @JoinColumn (name="stage_id")
    List<CourseLesson> lessons;

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
