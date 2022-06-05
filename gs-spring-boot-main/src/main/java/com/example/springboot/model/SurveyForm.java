package com.example.springboot.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class SurveyForm {

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
    private String startedAt;
    private String expiredAt;
    private String email;

    public SurveyForm(){
        super();
    }

    public String getEmoji1() {
        return emoji1;
    }

    public void setEmoji1(String emoji1) {
        this.emoji1 = emoji1;
    }

    public String getEmoji2() {
        return emoji2;
    }

    public void setEmoji2(String emoji2) {
        this.emoji2 = emoji2;
    }

    public String getEmoji3() {
        return emoji3;
    }

    public void setEmoji3(String emoji3) {
        this.emoji3 = emoji3;
    }

    public String getEmoji4() {
        return emoji4;
    }

    public void setEmoji4(String emoji4) {
        this.emoji4 = emoji4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
