package ru.aaa.shelper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aaa.shelper.controller.request.*;

@RestController
public class Controller {

    @GetMapping("/notification")
    public NotificationResponse notification(@RequestBody NotificationRequest request) {
        return new NotificationResponse();
    }

    @GetMapping("/competition")
    public NotificationResponse showCompetition(@RequestBody NotificationRequest request) {
        return new NotificationResponse();
    }

    @PostMapping("/ask")
    public AskQuestionResponse askQuestion(@RequestBody AskQuestionRequest request) {
        return new AskQuestionResponse();
    }

    @GetMapping("/settings")
    public SettingsResponse settings() {
        return new SettingsResponse();
    }


}
