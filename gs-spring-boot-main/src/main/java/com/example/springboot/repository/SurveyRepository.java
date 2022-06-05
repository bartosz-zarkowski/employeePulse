package com.example.springboot.repository;

import com.example.springboot.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Survey findByLink(String link);
    List<Survey> findByUserId(Long id);
}
