package ru.aaa.shelper.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AskQuestionResponse {
    private String answer;
}
