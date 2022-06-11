package net.reflection.lconbot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_users")
@Getter
@Setter
@NoArgsConstructor
public class ChatUser {

    @Column(name = "telegram_id")
    String telegramId;

    @Column(name = "name")
    String name;

    @Column(name = "male") //m-мужской, w-женский
    String male;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "birth_day")
    Date birthDay;

    @Column(name = "about")
    String about;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @OneToMany
    @JoinColumn(name = "chat_user_id")
    List<UserCourses> courses;

    @OneToMany
    @JoinColumn(name = "chat_user_id")
    List<UserPurpose> purposes;

    @OneToMany
    @JoinColumn(name = "chat_user_id")
    List<UserAchievements> aghievements;

    @OneToMany
    @JoinColumn(name = "chat_user_id")
    List<UserAchievements> tasks;

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
