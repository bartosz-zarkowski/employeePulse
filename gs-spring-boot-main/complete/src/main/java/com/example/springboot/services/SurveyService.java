package com.example.springboot.services;

import com.example.springboot.model.Survey;
import com.example.springboot.model.SurveyForm;
import com.example.springboot.model.User;
import com.example.springboot.repository.LoginRepository;
import com.example.springboot.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
public class SurveyService {

    private final LoginRepository loginRepository;
    private final SurveyRepository surveyRepository;

    public SurveyService(LoginRepository loginRepository, SurveyRepository surveyRepository){
        this.loginRepository = loginRepository;
        this.surveyRepository = surveyRepository;
    }

    public String getPage(String email, Model model) {

        if (model.getAttribute("key") == "key"){
            User user = loginRepository.findByEmail(email);

            String firstName = user.getFirstName();
            String lastName = user.getLastName();

            model.addAttribute("name", firstName + " " + lastName);
            model.addAttribute("email", email);
        } else {
            return "redirect:/";
        }
        return "app/generate_survey";
    }

    public String generate(SurveyForm surveyForm, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("email", surveyForm.getEmail());
        redirectAttrs.addFlashAttribute("title", surveyForm.getTitle());
        redirectAttrs.addFlashAttribute("question", surveyForm.getQuestion());
        redirectAttrs.addFlashAttribute("answer1", surveyForm.getAnswer1());
        redirectAttrs.addFlashAttribute("answer2", surveyForm.getAnswer2());
        redirectAttrs.addFlashAttribute("answer3", surveyForm.getAnswer3());
        redirectAttrs.addFlashAttribute("answer4", surveyForm.getAnswer4());
        redirectAttrs.addFlashAttribute("startedAt", surveyForm.getStartedAt());
        redirectAttrs.addFlashAttribute("expiresAt", surveyForm.getExpiredAt());
        return "redirect:/link";
    }

    public String getSurveyLink(Model model){
        String email = (String) model.getAttribute("email");
        String title = (String) model.getAttribute("title");
        String question = (String) model.getAttribute("question");
        String answer1 = (String) model.getAttribute("answer1");
        String answer2 = (String) model.getAttribute("answer2");
        String answer3 = (String) model.getAttribute("answer3");
        String answer4 = (String) model.getAttribute("answer4");
        String startedAt = (String) model.getAttribute("startedAt");
        String expiresAt = (String) model.getAttribute("expiresAt");

        User user = loginRepository.findByEmail(email);

        model.addAttribute("name", user.getFirstName() + " " + user.getLastName());

        Survey survey = new Survey(title, question, answer1, answer2, answer3, answer4, startedAt, expiresAt, user);
        survey.setLink("123");
        surveyRepository.save(survey);

        Survey changeLink = surveyRepository.findById(survey.getId()).orElseThrow(
                () -> new IllegalStateException("x"));

        changeLink.setLink("http://localhost:8080/survey?link=" + changeLink.getId());
        surveyRepository.save(changeLink);

        model.addAttribute("link", survey.getLink());
        return "app/survey_link";
    }

    public String getSurvey(String id, Model model) {
        String link = "http://localhost:8080/survey?link=" + id;
        Survey survey = surveyRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalStateException("x"));
        model.addAttribute("question", survey.getQuestion());
        model.addAttribute("answer1", survey.getAnswer1());
        model.addAttribute("answer2", survey.getAnswer2());
        model.addAttribute("answer3", survey.getAnswer3());
        model.addAttribute("answer4", survey.getAnswer4());
        model.addAttribute("id", "id");
        return "app/survey";
    }

    public String vote(String id, String option, Model model) {
        Survey survey = surveyRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalStateException("x"));

        switch (option){
            case "1":
                survey.setAnswerCounter1(survey.getAnswerCounter1() + 1);
                break;
            case "2":
                survey.setAnswerCounter2(survey.getAnswerCounter2() + 1);
                break;
            case "3":
                survey.setAnswerCounter3(survey.getAnswerCounter3() + 1);
                break;
            case "4":
                survey.setAnswerCounter4(survey.getAnswerCounter4() + 1);
                break;
        }

        surveyRepository.save(survey);

        return "auth/login";
    }

    public String display() {
        return "app/display_surveys";
    }
}
