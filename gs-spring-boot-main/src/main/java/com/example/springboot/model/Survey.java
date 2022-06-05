package com.example.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String emoji1;
    private String emoji2;
    private String emoji3;
    private String emoji4;

    private int answerCounter1;
    private int answerCounter2;
    private int answerCounter3;
    private int answerCounter4;

    private String startedAt;
    private String expiredAt;
    private String link;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Survey(String title, String question, String answer1, String answer2, String answer3,
                  String answer4, String emoji1, String emoji2, String emoji3, String emoji4, String startedAt, String expiredAt, User user) {
        this.title = title;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.emoji1 = emoji1;
        this.emoji2 = emoji2;
        this.emoji3 = emoji3;
        this.emoji4 = emoji4;
        this.answerCounter1 = 0;
        this.answerCounter2 = 0;
        this.answerCounter3 = 0;
        this.answerCounter4 = 0;
        this.startedAt = startedAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}