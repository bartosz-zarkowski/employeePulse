package com.example.springboot.controllers;

import com.example.springboot.model.*;
import com.example.springboot.services.LoginService;
import com.example.springboot.services.SurveyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String getGenerateSurveyPage(@RequestParam String email, Model model){
        return surveyService.getPage(email, model);
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String generateSurvey(@ModelAttribute (name="surveyForm") SurveyForm surveyForm, @RequestParam String email,
                                 RedirectAttributes redirectAttrs){
        return surveyService.generate(surveyForm, redirectAttrs);
    }

    @RequestMapping(value = "/link", method = RequestMethod.GET)
    public String getLink(Model model){
        return surveyService.getSurveyLink(model);
    }

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String getSurvey(@RequestParam String link, Model model){
        return surveyService.getSurvey(link, model);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String vote(@RequestParam(name="id") String id, @RequestParam(name="option", defaultValue = "1") String option, Model model){
        return surveyService.vote(id, option, model);
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String display(@ModelAttribute (name="filterForm") FilterForm filterForm, @RequestParam String email, Model model){
        return surveyService.display(filterForm, email, model);
    }

    @RequestMapping(value = "/display", method = RequestMethod.POST)
    public String display1(@ModelAttribute (name="filterForm") FilterForm filterForm, @RequestParam String email, Model model){
        return surveyService.display(filterForm, email, model);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@ModelAttribute (name="filterForm") FilterForm filterForm, @RequestParam(required = false) String email, RedirectAttributes redirectAttributes){
        return surveyService.filter(filterForm, email, redirectAttributes);
    }

}
