package ru.aaa.shelper.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Project {
    private String projectName;
}
